package com.fengwenyi.erwin.sample.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.fengwenyi.api.result.IResult;
import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.erwin.sample.enums.ResultEnum;
import com.fengwenyi.erwin.sample.exception.BizException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-08-11
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 参数缺失异常
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultTemplate<Void> missingServletRequestParameterExceptionHandler(HttpServletRequest request, MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException, uri: [{}], msg: [{}]", request.getRequestURI(), e.getParameterName());
        return ResultTemplate.fail(ResultEnum.PARAM_MISS, "参数缺失异常: [" + e.getParameterName() + "]");
    }

    // 参数校验失败异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultTemplate<Void> handleParamCheckException(HttpServletRequest request, MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringJoiner errMsgJoiner = new StringJoiner(",", "[", "]");
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors) {
                String msg = objectError.getDefaultMessage();
                if (StringUtils.hasText(msg)) {
                    errMsgJoiner.add(msg);
                }
            }
        }
        log.error("MethodArgumentNotValidException, uri: [{}], msg: [{}]", request.getRequestURI(), errMsgJoiner);
        return ResultTemplate.fail(ResultEnum.PARAM_VALIDATED, "参数检验失败: " + errMsgJoiner);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResultTemplate<Void> constraintViolationExceptionHandler(HttpServletRequest request, ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        StringJoiner errMsgJoiner = new StringJoiner(",", "[", "]");
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            String errMsg = constraintViolation.getMessageTemplate();
            if (StringUtils.hasText(errMsg)) {
                errMsgJoiner.add(errMsg);
            }
        }
        log.error("ConstraintViolationException, code: [{}], msg: [{}]", request.getRequestURI(), errMsgJoiner);
        return ResultTemplate.fail(ResultEnum.PARAM_VALIDATED, "参数校验失败: " + errMsgJoiner);
    }

    /**
     * BizException
     * @param request 请求
     * @param e {@link BizException}
     * @return 异常信息
     */
    @ExceptionHandler(BizException.class)
    public ResultTemplate<Void> bizExceptionHandler(HttpServletRequest request, BizException e) {
        String uriInfo = String.format("BizException, uri: [%s]", request.getRequestURI());
        IResult result = e.getResult();
        String message = e.getMessage();
        if (Objects.isNull(result)) {
            log.error("{}, msg: [{}]", uriInfo, message);
            return ResultTemplate.fail(message);
        }
        if (StringUtils.hasText(message)) {
            log.error("{}, code: [{}], msg: [{}]", uriInfo, result.getCode(), message);
            return ResultTemplate.fail(result, message);
        }
        log.error("{}, code: [{}], msg: [{}]", uriInfo, result.getCode(), result.getMsg());
        return ResultTemplate.fail(result);
    }

    // sa-token NotLoginException 异常
    @ExceptionHandler(NotLoginException.class)
    public ResultTemplate<Void> notLoginExceptionHandler(HttpServletRequest request, NotLoginException e) {
        log.error("NotLoginException, uri: [{}], err: [{}]", request.getRequestURI(), e.getMessage());
        return ResultTemplate.fail(ResultEnum.AUTH_FAILED, "认证失败：请登录");
    }

    // sa-token NotRoleException 异常
    @ExceptionHandler(NotRoleException.class)
    public ResultTemplate<Void> notRoleExceptionHandler(HttpServletRequest request, NotRoleException e) {
        log.error("NotRoleException, uri: [{}], err: [{}]", request.getRequestURI(), e.getMessage());
        return ResultTemplate.fail(ResultEnum.AUTH_FAILED, "认证失败：没有角色");
    }

    // sa-token NotPermissionException 异常
    @ExceptionHandler(NotPermissionException.class)
    public ResultTemplate<Void> notPermissionExceptionHandler(HttpServletRequest request, NotPermissionException e) {
        log.error("NotPermissionException, uri: [{}], err: [{}]", request.getRequestURI(), e.getMessage());
        return ResultTemplate.fail(ResultEnum.AUTH_FAILED, "认证失败：没有权限");
    }

    // 系统异常
    @ExceptionHandler(Exception.class)
    public ResultTemplate<Void> exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("Exception, uri: [{}], err: [{}]", request.getRequestURI(), e.getLocalizedMessage(), e);
        return ResultTemplate.fail(ResultEnum.SYSTEM_EXCEPTION);
    }

}
