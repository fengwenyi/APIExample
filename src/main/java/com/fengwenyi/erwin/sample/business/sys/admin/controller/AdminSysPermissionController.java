package com.fengwenyi.erwin.sample.business.sys.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-14
 */
@RestController
@RequestMapping("/admin/sys/permission")
@RequiredArgsConstructor
public class AdminSysPermissionController {

    @GetMapping("/list")
    public List<String> getList() {
        return List.of(
             "add",
             "delete",
             "update",
             "query"
        );
    }

}
