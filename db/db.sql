-- ----------------------------
-- 创建数据库
-- ----------------------------
DROP DATABASE IF EXISTS `fwy-api-example`; -- 删除数据库
CREATE DATABASE `fwy-api-example` CHARACTER SET utf8mb4 COLLATE utf8mb4_bin; -- 创建数据库并指定编码

USE `fwy-api-example`;

-- ----------------------------
-- 创建博客目录表
-- ----------------------------
DROP TABLE IF EXISTS `t_shop`; -- 删除表
CREATE TABLE `t_shop` (
	`id` BIGINT (20) NOT NULL PRIMARY KEY COMMENT '主键ID',
	`title` VARCHAR (255) NOT NULL COMMENT '博客标题',
	`cover` VARCHAR (255) NOT NULL COMMENT '博客封面',
	`summary` VARCHAR (255) NOT NULL COMMENT '博客摘要',
	`top_score` BIGINT (20) DEFAULT 0 COMMENT '置顶分。默认不置顶，置顶将分取最大值+1，取消置顶为0。排序，置顶分大于0的排序，然后按发布时间排序。',
    `comment_status` INT (1) DEFAULT 0 COMMENT '评论状态。0:允许评论；-1：不允许评论',
	`read_count` BIGINT (20) DEFAULT 0 COMMENT '阅读统计',
	`release_status` INT (1) DEFAULT 0 COMMENT '生效状态，0：不生效；1：生效',
	`release_time` DATETIME COMMENT '发布时间',
	`delete_status` INT (1) DEFAULT 0 COMMENT '删除状态，0：正常；-1：删除',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`update_user_id` BIGINT (20) COMMENT '修改者ID'
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin COMMENT '博客目录表';