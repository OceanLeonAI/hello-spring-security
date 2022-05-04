CREATE
DATABASE /*!32312 IF NOT EXISTS*/`spring_security` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE
`spring_security`;

DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `menu_name`   varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '菜单名',
    `path`        varchar(200)         DEFAULT NULL COMMENT '路由地址',
    `component`   varchar(255)         DEFAULT NULL COMMENT '组件路径',
    `visible`     char(1)              DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
    `status`      char(1)              DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
    `perms`       varchar(100)         DEFAULT NULL COMMENT '权限标识',
    `icon`        varchar(100)         DEFAULT '#' COMMENT '菜单图标',
    `create_by`   bigint(20) DEFAULT NULL,
    `create_time` datetime             DEFAULT NULL,
    `update_by`   bigint(20) DEFAULT NULL,
    `update_time` datetime             DEFAULT NULL,
    `del_flag`    int(11) DEFAULT '0' COMMENT '是否删除（0未删除 1已删除）',
    `remark`      varchar(500)         DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';


DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `name`        varchar(128) DEFAULT NULL,
    `role_key`    varchar(100) DEFAULT NULL COMMENT '角色权限字符串',
    `status`      char(1)      DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
    `del_flag`    int(1) DEFAULT '0' COMMENT 'del_flag',
    `create_by`   bigint(200) DEFAULT NULL,
    `create_time` datetime     DEFAULT NULL,
    `update_by`   bigint(200) DEFAULT NULL,
    `update_time` datetime     DEFAULT NULL,
    `remark`      varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `role_id` bigint(200) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `menu_id` bigint(200) NOT NULL DEFAULT '0' COMMENT '菜单id',
    PRIMARY KEY (`role_id`, `menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单关联表';

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_name`   varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
    `nick_name`   varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '昵称',
    `password`    varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
    `status`      char(1)              DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
    `email`       varchar(64)          DEFAULT NULL COMMENT '邮箱',
    `phonenumber` varchar(32)          DEFAULT NULL COMMENT '手机号',
    `sex`         char(1)              DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
    `avatar`      varchar(128)         DEFAULT NULL COMMENT '头像',
    `user_type`   char(1)     NOT NULL DEFAULT '1' COMMENT '用户类型（0管理员，1普通用户）',
    `create_by`   bigint(20) DEFAULT NULL COMMENT '创建人的用户id',
    `create_time` datetime             DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint(20) DEFAULT NULL COMMENT '更新人',
    `update_time` datetime             DEFAULT NULL COMMENT '更新时间',
    `del_flag`    int(11) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `user_id` bigint(200) NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `role_id` bigint(200) NOT NULL DEFAULT '0' COMMENT '角色id',
    PRIMARY KEY (`user_id`, `role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

-- data
-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (2, '查询', 'system:dept:list', NULL, '0', '0', 'system:dept:list', '#', NULL, NULL, NULL, NULL, 0, NULL);

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (3, '管理员', 'admin', '0', 0, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (3, 2);

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'leon', '洋哥', '$2a$10$aAr9QYA0Vi84BHv.EUSydOAEN1O3QL594E2zzKVo98q5u1v9Ev0by', '0', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (2, 'zhangsan', '张三', '$2a$10$aAr9QYA0Vi84BHv.EUSydOAEN1O3QL594E2zzKVo98q5u1v9Ev0by', '0', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (3, 'lisi', '李四', '$2a$10$aAr9QYA0Vi84BHv.EUSydOAEN1O3QL594E2zzKVo98q5u1v9Ev0by', '0', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (4, 'wangwu', '王五', '$2a$10$aAr9QYA0Vi84BHv.EUSydOAEN1O3QL594E2zzKVo98q5u1v9Ev0by', '0', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 3);
