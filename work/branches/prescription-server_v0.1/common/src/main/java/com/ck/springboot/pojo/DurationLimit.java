package com.ck.springboot.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DurationLimit {
    private Integer id;

    private Integer medcineId;

    private String ruleName;

    private BigDecimal lowerbound;

    private BigDecimal upperbound;

    private Integer level;

  
}