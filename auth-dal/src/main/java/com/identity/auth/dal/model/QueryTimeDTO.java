package com.identity.auth.dal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询时间对象
 * Created by lijing on 2017/11/14 0014.
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryTimeDTO implements Serializable {
    /** 起始时间 */
    @JsonProperty("start_time")
    private Date startTime;
    /** 结束时间时间 */
    @JsonProperty("end_time")
    private Date endTime;
}
