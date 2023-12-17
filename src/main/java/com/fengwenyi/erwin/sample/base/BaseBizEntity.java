package com.fengwenyi.erwin.sample.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serial;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-07-25
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BaseBizEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 3390118383797833658L;

    /**
     * 启用状态
     */
    private Boolean status;

}
