package sample.api_result.controller;

import com.fengwenyi.api_result.entity.ResponseEntity;
import com.fengwenyi.api_result.model.ResultApiModel;
import com.fengwenyi.api_result.util.ResponseUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.api_result.result.ResultUtils;
import sample.api_result.vo.response.UserResponseVo;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * API
 * @author Erwin Feng
 * @since 2020/5/26
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    /**
     * 查询所有用户，返回所有用户列表
     * @return {@link ResultApiModel}
     */
    @GetMapping("/users")
    public ResultApiModel<Integer, List<UserResponseVo>> users() {
        List<UserResponseVo> userResponseVos = Arrays.asList(
                new UserResponseVo().setUid(UUID.randomUUID().toString()).setRealName("关羽").setNickname("云长"),
                new UserResponseVo().setUid(UUID.randomUUID().toString()).setRealName("张飞").setNickname("翼德"),
                new UserResponseVo().setUid(UUID.randomUUID().toString()).setRealName("赵云").setNickname("子龙")
        );
        return ResultUtils.success(userResponseVos);
    }

    @GetMapping("/users-2")
    public ResponseEntity<?, ?> users2() {
        List<UserResponseVo> userResponseVos = Arrays.asList(
               new UserResponseVo("u_1", "关羽", "云长"),
               new UserResponseVo("u_2", "张飞", "翼德"),
               new UserResponseVo("u_3", "赵云", "子龙")
        );
        return ResponseUtils.success(userResponseVos);
    }

}
