package com.fengwenyi.api_example.data;

import com.fengwenyi.api_example.entity.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户数据
 * @author Erwin Feng
 * @since 2020/4/11 22:42
 */
public class UserData {

    private static Map<Integer, UserEntity> map = new HashMap<>();

    public static void set(UserEntity userEntity) {
        map.put(userEntity.getUid(), userEntity);
    }

    public static UserEntity get(int uid) {
        return map.get(uid);
    }

    public static List<UserEntity> getUsers() {
        List<UserEntity> list = new ArrayList<>();
        for (Map.Entry<Integer, UserEntity> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    public static void delete(int uid) {
        map.remove(uid);
    }

}
