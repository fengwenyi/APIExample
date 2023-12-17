package com.fengwenyi.erwin.sample.business.sys.index.service;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-11-22
 */
public interface ILoginUserService {

    boolean judgeSuperAdmin();

    long getUserId();

}
