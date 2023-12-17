package com.fengwenyi.erwin.sample.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fengwenyi.api.result.PageTemplate;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-04
 */
public class PageUtils {

    public static <R, T> PageTemplate<R> convert(IPage<T> page) {
        PageTemplate<R> pageTemplate = new PageTemplate<>();
        pageTemplate.setCurrent(page.getCurrent());
        pageTemplate.setPageSize((int) page.getPages());
        pageTemplate.setTotalPage(page.getPages());
        pageTemplate.setTotalRow(page.getTotal());
        return pageTemplate;
    }

}
