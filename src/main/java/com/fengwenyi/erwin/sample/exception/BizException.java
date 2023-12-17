package com.fengwenyi.erwin.sample.exception;

import com.fengwenyi.api.result.IResult;
import lombok.Getter;

import java.io.Serial;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-07-25
 */
@Getter
public class BizException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 9169510042067816399L;

    private final IResult result;

    public BizException(IResult result, String message) {
        super(message);
        this.result = result;
    }

    public BizException(IResult result) {
        super(result.getMsg());
        this.result = result;
    }
}
