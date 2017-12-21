/*表结构插入*/
DROP TABLE
IF EXISTS `u_permission`;

CREATE TABLE `u_permission` (
  `id` BIGINT (20) NOT NULL AUTO_INCREMENT,
  `url` VARCHAR (256) DEFAULT NULL COMMENT 'url地址',
  `name` VARCHAR (64) DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 21 DEFAULT CHARSET = utf8;

/*Table structure for table `u_role` */
DROP TABLE
IF EXISTS `u_role`;

CREATE TABLE `u_role` (
  `id` BIGINT (20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR (32) DEFAULT NULL COMMENT '角色名称',
  `type` VARCHAR (10) DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 5 DEFAULT CHARSET = utf8;

/*Table structure for table `u_role_permission` */
DROP TABLE
IF EXISTS `u_role_permission`;

CREATE TABLE `u_role_permission` (
  `rid` BIGINT (20) DEFAULT NULL COMMENT '角色ID',
  `pid` BIGINT (20) DEFAULT NULL COMMENT '权限ID'
) ENGINE = INNODB DEFAULT CHARSET = utf8;

/*Table structure for table `u_user` */
DROP TABLE
IF EXISTS `u_user`;

CREATE TABLE `u_user` (
  `id` BIGINT (20) NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR (20) DEFAULT NULL COMMENT '用户昵称',
  `email` VARCHAR (128) DEFAULT NULL COMMENT '邮箱|登录帐号',
  `pswd` VARCHAR (32) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` BIGINT (1) DEFAULT '1' COMMENT '1:有效，0:禁止登录',
  PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 15 DEFAULT CHARSET = utf8;

/*Table structure for table `u_user_role` */
DROP TABLE
IF EXISTS `u_user_role`;

CREATE TABLE `u_user_role` (
  `uid` BIGINT (20) DEFAULT NULL COMMENT '用户ID',
  `rid` BIGINT (20) DEFAULT NULL COMMENT '角色ID'
) ENGINE = INNODB DEFAULT CHARSET = utf8;