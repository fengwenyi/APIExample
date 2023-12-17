package com.fengwenyi.erwin.sample.business.sys.index.pojo.bo;

import com.fengwenyi.erwin.sample.business.sys.enums.RegisterTypeEnum;
import lombok.Data;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-27
 */
@Data
public class TripartiteRegisterBo {

    private String account;
    private String unionId;
    private String openId;
    private RegisterTypeEnum registerTypeEnum;

}
