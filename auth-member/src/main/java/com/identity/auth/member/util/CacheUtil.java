package com.identity.auth.member.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.identity.auth.member.model.ChannelInfoResDTO;
import com.identity.auth.member.model.MemberInfoResDTO;
import com.identity.auth.member.model.MemberProductCheckResDTO;
import com.identity.auth.member.model.ProductInfoResDTO;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 本地缓存管理
 * Created by lijing on 2017/10/26 0026.
 */
public class CacheUtil {


    private final static Cache<String, MemberInfoResDTO> memberCache = CacheBuilder.newBuilder()
            //设置cache的初始大小为10，要合理设置该值
            .initialCapacity(0)
            //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
            .concurrencyLevel(5)
            //设置cache中的数据在写入之后的存活时间为10秒
            .expireAfterWrite(60*60, TimeUnit.SECONDS)
            //构建cache实例
            .build();

    private final static Cache<String, ProductInfoResDTO> productCache = CacheBuilder.newBuilder()
            //设置cache的初始大小为10，要合理设置该值
            .initialCapacity(0)
            //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
            .concurrencyLevel(5)
            //设置cache中的数据在写入之后的存活时间为10秒
            .expireAfterWrite(60*60, TimeUnit.SECONDS)
            //构建cache实例
            .build();

    private final static Cache<String, MemberProductCheckResDTO> memberProductCheckCache = CacheBuilder.newBuilder()
            //设置cache的初始大小为10，要合理设置该值
            .initialCapacity(0)
            //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
            .concurrencyLevel(5)
            //设置cache中的数据在写入之后的存活时间为10秒
            .expireAfterWrite(60*60, TimeUnit.SECONDS)
            //构建cache实例
            .build();

    private final static Cache<String, List<ChannelInfoResDTO>> productChannelCache = CacheBuilder.newBuilder()
            //设置cache的初始大小为10，要合理设置该值
            .initialCapacity(0)
            //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
            .concurrencyLevel(5)
            //设置cache中的数据在写入之后的存活时间为10秒
            .expireAfterWrite(60*60, TimeUnit.SECONDS)
            //构建cache实例
            .build();


    /**
     * 获取缓存商户信息
     * @param memberId 商户id
     * @return 商户信息
     */
    public static MemberInfoResDTO getMemberInfo(String memberId){
        return memberCache.getIfPresent(memberId);
    }

    /**
     * 本地缓存商户信息
     * @param resDTO 商户信息
     */
    public static void cacheMemberInfo(MemberInfoResDTO resDTO){
        memberCache.put(resDTO.getMemberId(),resDTO);
    }

    /**
     * 获取缓存中商户产品信息
     * @param memberId 商户id
     * @param productId 产品id
     * @return 商户产品信息
     */
    public static MemberProductCheckResDTO getMemberProduct(String memberId, String productId){
        String key = memberId+"_"+productId;
        return memberProductCheckCache.getIfPresent(key);
    }

    /**
     * 缓存商户产品信息
     * @param resDTO 商户产品信息
     */
    public static void cacheMemberProduct(MemberProductCheckResDTO resDTO){
        String key = resDTO.getMemberId()+"_"+resDTO.getProductId();
        memberProductCheckCache.put(key,resDTO);
    }

    /**
     * 获取缓存中商户产品信息
     * @param memberId 商户id
     * @param productId 产品id
     * @return 商户产品信息
     */
    public static ProductInfoResDTO getProduct(String memberId, String productId){
        String key = memberId+"_"+productId;
        return productCache.getIfPresent(key);
    }

    /**
     * 缓存产品信息
     * @param resDTO 产品信息
     */
    public static void cacheProduct(ProductInfoResDTO resDTO){
        String key = resDTO.getMemberId()+"_"+resDTO.getProductId();
        productCache.put(key,resDTO);
    }

    /**
     * 获取缓存中产品渠道信息
     * @param productId 产品id
     * @return 产品渠道信息
     */
    public static List<ChannelInfoResDTO> getProductChannel(String productId){
        return productChannelCache.getIfPresent(productId);
    }

    /**
     * 缓存产品渠道信息
     * @param productId 产品id
     * @param resDTO 产品渠道信息
     */
    public static void cacheProductChannel(String productId,List<ChannelInfoResDTO> resDTO){
        productChannelCache.put(productId,resDTO);
    }
}
