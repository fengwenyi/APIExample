package com.fengwenyi.apiswaggerstarter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <类说明>
 * <p>
 * <功能详细描述>
 *
 * @author Wenyi Feng
 * @since 2018-08-28
 */
@Api(tags = "API接口Demo")
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class APIController {

    @Autowired
    private UserModel userModel;

    @ApiOperation(value = "获取用户", notes = "获取用户")
    @RequestMapping("/getAllUser")
    public String getAllUser() {

        UserModel userModel = new UserModel();
        userModel.getAge();

        return "all users";
    }

}
