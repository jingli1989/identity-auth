package com.identity.auth.dal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询
 * Created by lijing on 2017/10/23 0023.
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageDTO<T> implements Serializable{
    /** 页码 */
    @JsonProperty("page")
    private int page = 1;
    /** 每页记录数 */
    @JsonProperty("page_num")
    private int pageNum = 20;
    /** 总记录数 */
    @JsonProperty("count")
    private int count;
    /** 分页查询结果集 */
    private List<T> result;
    /**
     * 处理页码和每页记录数
     */
    public void checkPage(){
        if(page<1){
            page=1;
        }
        if(pageNum<1||pageNum>1000){
            pageNum=20;
        }
    }
}
