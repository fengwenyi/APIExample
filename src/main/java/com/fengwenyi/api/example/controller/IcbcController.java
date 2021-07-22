package com.fengwenyi.api.example.controller;

import com.fengwenyi.api.example.util.Utils;
import com.fengwenyi.api.example.vo.request.IcbcPayNoticeRequestVo;
import com.fengwenyi.javalib.convert.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-22
 */
@RestController
@RequestMapping("/icbc")
@Slf4j
public class IcbcController {

    @PostMapping("/payNotice")
    public void payNotice(HttpServletRequest request) {
        IcbcPayNoticeRequestVo payNotice = Utils.getPayNotice(request);
        log.info("请求参数：{}", JsonUtils.convertString(payNotice));
    }

    @PostMapping("/payNoticeMap")
    public void payNoticeMap(@RequestParam Map<String, String> requestMap) {
        IcbcPayNoticeRequestVo payNotice = Utils.getPayNoticeByMap(requestMap);
        log.info("请求参数：{}", JsonUtils.convertString(payNotice));
    }

}
