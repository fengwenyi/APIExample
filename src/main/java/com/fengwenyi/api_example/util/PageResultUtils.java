package com.fengwenyi.api_example.util;

import com.fengwenyi.api_result.helper.PageResultHelper;
import com.fengwenyi.api_result.model.PageResultModel;

/**
 * 分页返回结果工具类
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/1 20:32
 */
public class PageResultUtils {

    /**
     * 成功，携带分页相关数据以及信息
     * @param data     数据
     * @param total    数据总条数
     * @param size     每页条数
     * @param pages    总页数
     * @param current  当前页
     * @param <T>      数据类型
     * @return {@link PageResultModel}
     */
    public static <T> PageResultModel<T> success(T data, long total, int size, long pages, long current) {
        return PageResultHelper.success("Success", data, total, size, pages, current);
    }

}
