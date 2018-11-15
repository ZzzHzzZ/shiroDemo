-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.13 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 shiro.u_permission 结构
CREATE TABLE IF NOT EXISTS `u_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(256) DEFAULT NULL COMMENT 'url地址',
  `name` varchar(64) DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  shiro.u_permission 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `u_permission` DISABLE KEYS */;
INSERT INTO `u_permission` (`id`, `url`, `name`) VALUES
	(1, '/user/select', '用户查询'),
	(2, '/admin/add', '管理员添加'),
	(3, '/admin/delete', '管理员删除');
/*!40000 ALTER TABLE `u_permission` ENABLE KEYS */;


-- 导出  表 shiro.u_role 结构
CREATE TABLE IF NOT EXISTS `u_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `type` varchar(10) DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  shiro.u_role 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `u_role` DISABLE KEYS */;
INSERT INTO `u_role` (`id`, `name`, `type`) VALUES
	(1, 'admin', '1'),
	(2, 'user', '1'),
	(3, 'visitor', '1');
/*!40000 ALTER TABLE `u_role` ENABLE KEYS */;


-- 导出  表 shiro.u_role_permission 结构
CREATE TABLE IF NOT EXISTS `u_role_permission` (
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `pid` bigint(20) DEFAULT NULL COMMENT '权限ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  shiro.u_role_permission 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `u_role_permission` DISABLE KEYS */;
INSERT INTO `u_role_permission` (`rid`, `pid`) VALUES
	(1, 3),
	(1, 2),
	(2, 1);
/*!40000 ALTER TABLE `u_role_permission` ENABLE KEYS */;


-- 导出  表 shiro.u_user 结构
CREATE TABLE IF NOT EXISTS `u_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱|登录帐号',
  `pswd` varchar(32) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` bigint(1) DEFAULT '1' COMMENT '1:有效，0:禁止登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在导出表  shiro.u_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `u_user` DISABLE KEYS */;
INSERT INTO `u_user` (`id`, `nickname`, `email`, `pswd`, `create_time`, `last_login_time`, `status`) VALUES
	(1, 'admin', NULL, '123456', '2017-05-10 20:22:59', NULL, 1),
	(2, 'user', NULL, '123456', NULL, NULL, 1);
/*!40000 ALTER TABLE `u_user` ENABLE KEYS */;


-- 导出  表 shiro.u_user_role 结构
CREATE TABLE IF NOT EXISTS `u_user_role` (
  `uid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  shiro.u_user_role 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `u_user_role` DISABLE KEYS */;
INSERT INTO `u_user_role` (`uid`, `rid`) VALUES
	(1, 1),
	(2, 2),
	(1, 2);
/*!40000 ALTER TABLE `u_user_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
