create table sys_user
(
    id               bigint               not null primary key auto_increment comment '主键ID',
    create_date_time datetime             not null comment '创建时间',
    update_date_time datetime             null comment '更新时间',

    status           tinyint(1) default 1 null comment '状态: 0-禁用; 1-启用',

    username         varchar(32)          not null comment '用户名',
    password         varchar(64)          not null comment '密码',
    nickname         varchar(64)          null comment '昵称',
    locked           tinyint(1) default 0 null comment '锁定',
    email            varchar(32)          null comment '邮箱',
    phone            varchar(20)          null comment '手机',
    constraint uni_emai
        unique (email),
    constraint uni_phone
        unique (phone),
    constraint uni_username
        unique (username)
)
    comment '用户表';

INSERT INTO sys_user (id, create_date_time, username, password, nickname)
VALUES (1, now(), 'super_admin', '$2a$10$NeTf7MMbnBgrA0OA2iCgFeMXr6DkuTXDDNpQOUjha78HrPDzHc3fO', 'Super Admin');

create table sys_tripartite_user
(
    id               bigint               not null primary key auto_increment comment '主键ID',
    create_date_time datetime             not null comment '创建时间',
    update_date_time datetime             null comment '更新时间',

    status           tinyint(1) default 1 null comment '状态: 0-禁用; 1-启用',

    account          varchar(32)             not null comment '账户',
    union_id         varchar(64)             null comment '三方统一ID',
    open_id          varchar(64)             null comment '三方openId',
    register_type    varchar(64) default '0' null comment '注册类型',
    user_id          varchar(32)             not null comment '用户ID'
)
    comment '三方用户表';

create table sys_role
(
    id               bigint               not null primary key auto_increment comment '主键ID',
    create_date_time datetime             not null comment '创建时间',
    update_date_time datetime             null comment '更新时间',

    status           tinyint(1) default 1 null comment '状态: 0-禁用; 1-启用',

    role_name        varchar(64)           not null comment '角色名称',
    role_code        varchar(64)           not null comment '角色编码',
    sort_num         double     default 10 null comment '排序'
)
    comment '角色表';

INSERT INTO sys_role (id, create_date_time, role_name, role_code, sort_num) VALUES (1, now(), '超级管理员', 'SUPER_ADMIN', 99);

create table sys_user_role
(
    id               bigint               not null primary key auto_increment comment '主键ID',
    create_date_time datetime             not null comment '创建时间',
    update_date_time datetime             null comment '更新时间',

    user_id          bigint               not null comment '用户ID',
    role_id          bigint               not null comment '角色ID'
)
    comment '用户_角色表';

INSERT INTO sys_user_role (id, create_date_time, update_date_time, user_id, role_id) VALUES (1, now(), null, 1, 1);

create table sys_menu
(
    id               bigint               not null primary key auto_increment comment '主键ID',
    create_date_time datetime             not null comment '创建时间',
    update_date_time datetime             null comment '更新时间',

    status           tinyint(1) default 1 null comment '状态: 0-禁用; 1-启用',

    chinese_name           varchar(64)           not null comment '菜单名称',
    menu_name           varchar(64)           not null comment '菜单名称',
    menu_path           varchar(255)          not null comment '菜单路径',
    component           varchar(64)           not null comment '菜单组件',
    title               varchar(64)           not null comment '标题',
    affix               tinyint(1)            null comment '是否固定标签',
    icon                varchar(64)           null comment '图标',
    redirect            varchar(64)           null comment '跳转地址',
    parent_id           bigint                null comment '父ID',
    sort_num            double     default 10 null comment '排序',
    hide_menu           tinyint               null comment '隐藏菜单',
    ignore_keep_alive   tinyint               null comment '忽略保持激活',
    current_active_menu varchar(255)          null comment '当前激活菜单'
)
    comment '菜单表';

INSERT INTO sys_menu (id, create_date_time, menu_name, menu_path, component, title, affix, icon, redirect, parent_id, sort_num, hide_menu, ignore_keep_alive, current_active_menu, chinese_name) VALUES (1, now(), 'Dashboard', '/dashboard', 'LAYOUT', 'routes.dashboard.dashboard', 1, 'ion:grid-outline', '/dashboard/analysis', null, 9999, null, null, null, '首页');
INSERT INTO sys_menu (id, create_date_time, menu_name, menu_path, component, title, affix, icon, redirect, parent_id, sort_num, hide_menu, ignore_keep_alive, current_active_menu, chinese_name) VALUES (2, now(), 'Analysis', 'analysis', '/dashboard/analysis/index.vue', 'routes.dashboard.analysis', null, null, null, 1, 9998, null, null, null, '控制台');
INSERT INTO sys_menu (id, create_date_time, menu_name, menu_path, component, title, affix, icon, redirect, parent_id, sort_num, hide_menu, ignore_keep_alive, current_active_menu, chinese_name) VALUES (3, now(), 'Authorization', '/authorization', 'LAYOUT', 'routes.authorization.moduleName', null, 'ion:settings-outline', '/authorization/user', null, 9899, null, null, null, '权限管理');
INSERT INTO sys_menu (id, create_date_time, menu_name, menu_path, component, title, affix, icon, redirect, parent_id, sort_num, hide_menu, ignore_keep_alive, current_active_menu, chinese_name) VALUES (4, now(), 'UserManagement', 'user', '/authorization/user/index.vue', 'routes.authorization.userManagement', null, null, null, 3, 9898, null, null, null, '用户管理');
INSERT INTO sys_menu (id, create_date_time, menu_name, menu_path, component, title, affix, icon, redirect, parent_id, sort_num, hide_menu, ignore_keep_alive, current_active_menu, chinese_name) VALUES (5, now(), 'RoleManagement', 'role', '/authorization/role/index.vue', 'routes.authorization.roleManagement', null, null, '', 3, 9896, null, null, null, '角色管理');
INSERT INTO sys_menu (id, create_date_time, menu_name, menu_path, component, title, affix, icon, redirect, parent_id, sort_num, hide_menu, ignore_keep_alive, current_active_menu, chinese_name) VALUES (6, now(), 'UserDetail', 'user_detail/:id', '/authorization/user/UserDetail.vue', 'routes.authorization.userDetail', null, '', '', 3, 9897, 1, 1, '/authorization/user', '用户详情');
INSERT INTO sys_menu (id, create_date_time, menu_name, menu_path, component, title, affix, icon, redirect, parent_id, sort_num, hide_menu, ignore_keep_alive, current_active_menu, chinese_name) VALUES (7, now(), 'MenuManagement', 'menu', '/authorization/menu/index.vue', 'routes.authorization.menuManagement', null, '', '', 3, 9895, null, null, null, '菜单管理');


create table sys_role_menu
(
    id               bigint               not null primary key auto_increment comment '主键ID',
    create_date_time datetime             not null comment '创建时间',
    update_date_time datetime             null comment '更新时间',

    role_id          bigint               not null comment '角色ID',
    menu_id          bigint               not null comment '菜单ID'
)
    comment '角色_菜单表';

INSERT INTO sys_role_menu (id, create_date_time, role_id, menu_id) VALUES (1, now(), 1, 1);
INSERT INTO sys_role_menu (id, create_date_time, role_id, menu_id) VALUES (2, now(), 1, 2);
INSERT INTO sys_role_menu (id, create_date_time, role_id, menu_id) VALUES (3, now(), 1, 3);
INSERT INTO sys_role_menu (id, create_date_time, role_id, menu_id) VALUES (4, now(), 1, 4);
INSERT INTO sys_role_menu (id, create_date_time, role_id, menu_id) VALUES (5, now(), 1, 5);
INSERT INTO sys_role_menu (id, create_date_time, role_id, menu_id) VALUES (6, now(), 1, 6);
INSERT INTO sys_role_menu (id, create_date_time, role_id, menu_id) VALUES (7, now(), 1, 7);

