package com.fengwenyi.erwin.sample.business.sys.index.controller;

import com.fengwenyi.erwin.sample.business.sys.index.dto.LoginDto;
import com.fengwenyi.erwin.sample.business.sys.index.vo.LoginVo;
import com.fengwenyi.erwin.sample.business.sys.strategy.LoginStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-03
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/login")
    public LoginVo login(@RequestBody @Validated LoginDto loginDto) {
        return LoginStrategyFactory.getStrategy(loginDto).login();
    }

}
