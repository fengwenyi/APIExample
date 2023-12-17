package com.fengwenyi.erwin.sample.business.sys.service;

import com.fengwenyi.erwin.sample.ErwinSampleApplicationTests;
import com.fengwenyi.erwin.sample.business.sys.entity.UserEntity;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpUserService;
import com.fengwenyi.erwin.sample.util.PasswordUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-07-26
 */
public class UserServiceTests extends ErwinSampleApplicationTests {

    @Autowired
    private IMpUserService mpUserService;

    @Test
    public void testRegister() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("test");
        userEntity.setPassword(PasswordUtils.hash("123456"));
        userEntity.setNickname("Test");
        boolean save = mpUserService.save(userEntity);
        Assert.isTrue(save, "保存用户失败");
    }

}
