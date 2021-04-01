package com.fengwenyi.api.example.controller;

import com.fengwenyi.api.example.util.Utils;
import com.fengwenyi.api.result.ResultTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 字节数组接口 示例
 * @author Erwin Feng
 * @since 2021-04-01
 */
@RestController
@RequestMapping("/byteArray")
public class ByteArrayController {

    @RequestMapping("/demo1")
    public ResultTemplate<?> demo1(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        byte[] byteArray = Utils.getByteArray(inputStream);
        String data = new String( byteArray);
        return ResultTemplate.success(data);
    }

    @RequestMapping("/demo2")
    public ResultTemplate<?> demo2(byte [] param) {
        String data = new String(param);
        return ResultTemplate.success(data);
    }

}
