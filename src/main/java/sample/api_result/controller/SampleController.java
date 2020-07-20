package sample.api_result.controller;

import com.fengwenyi.api_result.entity.ResponseEntity;
import com.fengwenyi.api_result.util.ResponseUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用 {@code api-result} 写一个RESTful风格的API样例
 * @author Erwin Feng
 * @since 2020/7/20
 */
@RestController
@RequestMapping(value = "/sample",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class SampleController {

    /**
     * 接口示例
     * @return 只返回处理结果，无数据
     */
    @GetMapping("/api")
    public ResponseEntity<Void, Void> api() {
        return ResponseUtils.success();
    }

}
