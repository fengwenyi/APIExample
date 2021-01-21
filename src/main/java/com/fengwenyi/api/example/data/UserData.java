package com.fengwenyi.api.example.data;

import com.fengwenyi.api.example.entity.UserEntity;
import com.fengwenyi.api.example.exceptions.DataSaveException;

import java.util.*;

/**
 * 用户数据
 * @author Erwin Feng
 * @since 2020/4/11 22:42
 */
public class UserData {

    private static final Map<Integer, UserEntity> map = new HashMap<>();

    public static void save(UserEntity userEntity) {
        int uid = userEntity.getUid();
        UserEntity result = query(uid);
        if (Objects.nonNull(result)) {
            throw new DataSaveException("用户已存在");
        }
        map.put(userEntity.getUid(), userEntity);
    }

    public static UserEntity query(int uid) {
        return map.get(uid);
    }

    public static List<UserEntity> queryAll() {
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
