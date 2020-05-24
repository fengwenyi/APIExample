package siample.api_result.result;

import lombok.Getter;

/**
 * @author Erwin Feng
 * @since 2020/5/24
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(0, "Success"),


    ;

    /** 返回码 */
    private Integer code;

    /** 描述信息 */
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
