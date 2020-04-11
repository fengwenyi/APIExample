package com.fengwenyi.api_example.vo.param;

import lombok.Data;

/**
 * 用户查询过滤条件
 * @author Erwin Feng
 * @since 2020/4/11 22:28
 */
@Data
public class UserFilterParamVO {

    /**  */
    private String name;

    /** 年龄 */
    private int age;

    /** 查询限制条数 */
    private int limit;

    /** 查询页 */
    private int page;

    /** 排序条件 */
    private String orderBy;

    /** 排序规则：升序（asc） / 降序(desc) */
    private String order;

}
