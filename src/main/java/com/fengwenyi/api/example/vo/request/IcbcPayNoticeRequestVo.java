package com.fengwenyi.api.example.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * 工行支付通知 请求参数
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-04-19
 */
@Getter
@Setter
@Accessors(prefix = "_", chain = true)
public class IcbcPayNoticeRequestVo implements Serializable {


    /** 追溯码 */
    @ApiModelProperty(hidden = true)
    private String _traceId;

    /** 商户号 */
    @ApiModelProperty(hidden = true)
    private String _merchantSeq;


    /** 交易编号 */
    @ApiModelProperty(hidden = true)
    private String _dealSeq;

    /**
     * 工行调用固定为icbc-api
     */
    @JsonProperty("from")
    private String _from;


    /**
     * 接口路径，调用哪一个接口的回调
     */
    @JsonProperty("api")
    private String _api;


    /**
     * 合作方在工行开具的应用编号
     */
    @JsonProperty("app_id")
    private String _appId;


    /**
     * 调用过程使用的编码格式
     */
    @JsonProperty("charset")
    private String _charset;


    /**
     * 报文类型
     */
    @JsonProperty("format")
    private String _format;


    /**
     * 加密方式，此接口默认不加密
     */
    @JsonProperty("encrypt_type")
    private String _encryptType;

    /**
     * 回调发生时间，格式为“yyyy-MM-dd HH:mm:ss”
     */
    @JsonProperty("timestamp")
    private String _timestamp;

    /**
     * 签名类型。
     */
    @JsonProperty("sign_type")
    private String _signType;

    /**
     * 工行签名
     */
    @JsonProperty("sign")
    private String _sign;

    /**
     * 业务参数集合，详见业务参数说明
     */
    @JsonProperty("biz_content")
    private String _bizContent;

}
