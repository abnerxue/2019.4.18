package com.ck.springboot.vo;

import lombok.Data;

/**
 * @author lx
 * @date 2019/3/18
 * @desc
 */
@Data
public class PageVo {

    /**
     * 页码
     */
    private Integer pageNo = 1;
    /**
     * 每页数量
     */
    private Integer pageSize = 10;
    /**
     * 是否分页。true-是，false-否
     */
    private boolean isPage = true;

    private Integer startIndex;

    private Integer endIndex;

    public Integer getStartIndex() {
        return (pageNo - 1) * pageSize + 1;
    }

    public Integer getEndIndex() {
        return pageNo * pageSize;
    }

}
