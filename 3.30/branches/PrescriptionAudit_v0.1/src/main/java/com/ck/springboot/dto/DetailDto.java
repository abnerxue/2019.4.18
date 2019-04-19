package com.ck.springboot.dto;

import lombok.Data;

/**
 * @author lx
 * @date 2019/3/18
 * @desc
 */
@Data
public class DetailDto {

    /**
     * 医院总数
     */
    private Integer hospitalTatal;
    /**
     * 处方总数
     */
    private Integer total;
    /**
     * 审核总数
     */
    private Integer checkTotal;
    /**
     * 未通过数
     */
    private Integer noPass;
    /**
     * 通过数
     */
    private Integer pass;
    /**
     * 人工审核数
     */
    private Integer personCheck;
    /**
     * 打回数量
     */
    private Integer back;
    /**
     * 警报数量
     */
    private Integer warning;

}
