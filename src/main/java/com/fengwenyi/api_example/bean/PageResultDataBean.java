package com.fengwenyi.api_example.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 分页结果数据bean
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/4 00:11
 */
@Data
@Accessors(chain = true)
public class PageResultDataBean {

    /** 总条数 */
    private Long total;

    /** 每页大小 */
    private Integer size;

    /** 页数 */
    private Long pages;

    /** 当前页 */
    private Long current;

    /** 数据 */
    private List<?> data;

}
