-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2023-07-19 18:25:01
-- 服务器版本： 5.7.40-log
-- PHP 版本： 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `web_open_api`
--

-- --------------------------------------------------------

--
-- 表的结构 `interface_info`
--

CREATE TABLE `interface_info` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '接口名',
  `description` varchar(512) DEFAULT NULL COMMENT '接口描述',
  `method` varchar(255) NOT NULL COMMENT '接口类型',
  `host` varchar(255) NOT NULL COMMENT '接口地址',
  `uri` varchar(512) NOT NULL COMMENT '接口路径',
  `apply_num` int(11) NOT NULL DEFAULT '100' COMMENT '接口申请时的可调用次数',
  `request_params` text COMMENT '请求参数',
  `request_params_remark` varchar(512) DEFAULT NULL COMMENT '请求参数说明',
  `response_params_remark` varchar(512) DEFAULT NULL COMMENT '响应参数说明',
  `request_header` text COMMENT '请求头',
  `response_header` text COMMENT '响应头',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '接口状态（0-关闭，1-开启）',
  `user_id` bigint(20) NOT NULL COMMENT '创建者Id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(255) NOT NULL COMMENT '创建者名',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `update_by` varchar(255) DEFAULT NULL COMMENT '修改者名'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接口信息表' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_account` varchar(256) COLLATE utf8_unicode_ci NOT NULL COMMENT '账号',
  `user_password` varchar(512) COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `user_name` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户昵称',
  `user_avatar` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户头像',
  `user_profile` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户简介',
  `email` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `qq` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'QQ',
  `user_role` varchar(256) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'user' COMMENT '用户角色：user/admin/ban',
  `access_key` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'access_key',
  `secret_key` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'secret_key',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户' ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `user_account`, `user_password`, `user_name`, `user_avatar`, `user_profile`, `email`, `qq`, `user_role`, `access_key`, `secret_key`, `create_time`, `update_time`, `is_delete`) VALUES
(1666439326650609671, 'admin', '$2a$10$457DfrVQKlEW9cRDH/WSu.qRGrKwp/BqP5ajn1o0Oy2JjcuQxUxcq', 'admin', 'https://q1.qlogo.cn/g?b=qq&nk=2571469810&s=100', NULL, NULL, NULL, 'ROLE_admin', '2e5a0d76d6793637d67219f1d5b9623e', '433e33d6d608d2dbc500302b133621b3', '2023-07-19 18:15:33', NULL, 0);

-- --------------------------------------------------------

--
-- 表的结构 `user_interface_info`
--

CREATE TABLE `user_interface_info` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '调用者Id',
  `interface_info_id` bigint(20) NOT NULL COMMENT '被调用接口Id',
  `total_num` int(11) NOT NULL DEFAULT '0' COMMENT '总调用次数',
  `surplus_num` int(11) NOT NULL DEFAULT '100' COMMENT '剩余调用次数',
  `status` int(11) DEFAULT '0' COMMENT '状态，0是正常，1是关闭',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除，0是未删除，1是已删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户接口关联表' ROW_FORMAT=DYNAMIC;

--
-- 转储表的索引
--

--
-- 表的索引 `interface_info`
--
ALTER TABLE `interface_info`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- 表的索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `idx_union_id` (`qq`) USING BTREE;

--
-- 表的索引 `user_interface_info`
--
ALTER TABLE `user_interface_info`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `interface_info`
--
ALTER TABLE `interface_info`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键';

--
-- 使用表AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=1666439326650609672;

--
-- 使用表AUTO_INCREMENT `user_interface_info`
--
ALTER TABLE `user_interface_info`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键';
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
