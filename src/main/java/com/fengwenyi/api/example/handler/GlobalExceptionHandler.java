package com.fengwenyi.api.example.handler;

import com.fengwenyi.api.example.exceptions.DataSaveException;
import com.fengwenyi.api.result.IReturnCode;
import com.fengwenyi.api.result.ResultTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.StringJoiner;

/**
 * 全局异常处理
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/6 17:48
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*============= 请求错误 start ==============================================*/

    /**
     * HTTP 请求方式不支持异常
     * HttpRequestMethodNotSupportedException
     * @return 异常
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResultTemplate<Void> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        return ResultTemplate.fail(IReturnCode.Default.ERROR_REQUEST_METHOD_NOT_SUPPORT);
    }

    /*============= 请求错误 end ==============================================*/

    /*============= 参数错误 start ==============================================*/

    /**
     * 参数校验异常
     * BindException
     * @return 异常
     */
    @ExceptionHandler(value = BindException.class)
    public ResultTemplate<Void> BindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        StringJoiner errorMessage = new StringJoiner(",", "参数校验异常[", "]");
        allErrors.forEach(objectError -> errorMessage.add(objectError.getDefaultMessage()));
        return ResultTemplate.fail(IReturnCode.Default.ERROR_PARAM_EXCEPTION.getCode(), errorMessage.toString());
    }

    /*============= 参数错误 end ==============================================*/

    /**
     * 数据存在异常
     * DataSaveException
     * @return 异常
     */
    @ExceptionHandler(value = DataSaveException.class)
    public ResultTemplate<Void> DataSaveException(DataSaveException e, HttpServletRequest request) {
        return ResultTemplate.fail(IReturnCode.Default.ERROR_DATA_SAVE_FAILURE.getCode(), e.getMessage());
    }
}
