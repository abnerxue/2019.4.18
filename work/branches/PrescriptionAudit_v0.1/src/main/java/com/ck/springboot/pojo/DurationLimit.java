package com.ck.springboot.pojo;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class DurationLimit {
    private Integer id;

    private Integer medcineId;

    private String ruleName;

    private BigDecimal lowerbound;

    private BigDecimal upperbound;

    private Integer level;

  
}