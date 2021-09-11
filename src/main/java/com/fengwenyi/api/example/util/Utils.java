package com.fengwenyi.api.example.util;

import com.fengwenyi.api.example.vo.request.IcbcPayNoticeRequestVo;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author Erwin Feng
 * @since 2021-04-01
 */
public class Utils {

    public static byte[] getByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int rc;
        while((rc=is.read(buff, 0, 1024))>0) {
            baos.write(buff, 0, rc);
        }
        return baos.toByteArray();
    }

    public static IcbcPayNoticeRequestVo getPayNotice(HttpServletRequest request) {

        String from = request.getParameter("from");
        String api = request.getParameter("api");
        String app_id = request.getParameter("app_id");
        String charset = request.getParameter("charset");
        String format = request.getParameter("format");
        String encrypt_type = request.getParameter("encrypt_type");
        String timestamp = request.getParameter("timestamp");
        String biz_content = request.getParameter("biz_content");
        String sign_type = request.getParameter("sign_type");
        String sign = request.getParameter("sign");

        return new IcbcPayNoticeRequestVo()
                .setFrom(from)
                .setApi(api)
                .setAppId(app_id)
                .setCharset(charset)
                .setFormat(format)
                .setEncryptType(encrypt_type)
                .setTimestamp(timestamp)
                .setBizContent(biz_content)
                .setSignType(sign_type)
                .setSign(sign)
                ;
    }

    public static IcbcPayNoticeRequestVo getPayNoticeByMap(Map<String, String> requestMap) {

        String from = requestMap.get("from");
        String api = requestMap.get("api");
        String app_id = requestMap.get("app_id");
        String charset = requestMap.get("charset");
        String format = requestMap.get("format");
        String encrypt_type = requestMap.get("encrypt_type");
        String timestamp = requestMap.get("timestamp");
        String biz_content = requestMap.get("biz_content");
        String sign_type = requestMap.get("sign_type");
        String sign = requestMap.get("sign");

        return new IcbcPayNoticeRequestVo()
                .setFrom(from)
                .setApi(api)
                .setAppId(app_id)
                .setCharset(charset)
                .setFormat(format)
                .setEncryptType(encrypt_type)
                .setTimestamp(timestamp)
                .setBizContent(biz_content)
                .setSignType(sign_type)
                .setSign(sign)
                ;
    }

}
