USE  traffic_optimization;

DROP TABLE IF EXISTS task;
CREATE TABLE task (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `add_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `handler_name` varchar(256) NOT NULL DEFAULT '',
  `executor_param` varchar(256) NOT NULL,
  `executor_timeout` int(11) NOT NULL,
  `executor_fail_retry_count` int(11) NOT NULL,
  `task_status` varchar(256) NOT NULL DEFAULT '',
  `task_execute_result` varchar(256) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS road_link;
CREATE TABLE road_link (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `link_id` varchar(256) NOT NULL,
  `node_id_begin` varchar(256) NOT NULL DEFAULT '',
  `node_id_end` varchar(256) NOT NULL DEFAULT '',
  `road_id` varchar(256) NOT NULL DEFAULT '',
  `area_id` varchar(256) NOT NULL DEFAULT '',
  `lines` varchar(256) NOT NULL DEFAULT '',
  `link_length` DOUBLE NOT NULL,
  `link_dir_id` int(11) NOT NULL,
  `link_speed_limit` int(11) NOT NULL,
  `road_speed_limit` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS road_node;
CREATE TABLE road_node (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `node_id` varchar(256) NOT NULL,
  `road_name` varchar(256) NOT NULL DEFAULT '',
  `node_type` int(11) NOT NULL,
  `node_type_disp` varchar(256) NOT NULL DEFAULT '',
  `longitude` DOUBLE NOT NULL,
  `latitude` DOUBLE NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS traffic_data;
CREATE TABLE traffic_data (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `link_id` varchar(256) NOT NULL DEFAULT '',
  `timestamp` TIMESTAMP NOT NULL ,
  `start_hour_and_minute` varchar(256) NOT NULL DEFAULT '',
  `duration` int(11) NOT NULL,
  `time_range` int(11) NOT NULL,
  `cur_phase_id` int(11) NOT NULL,
  `volume_q` FLOAT NOT NULL,
  `theta_t` FLOAT NOT NULL,
  `velocity_v` FLOAT NOT NULL,
  `avg_queue_length` FLOAT NOT NULL,
  `avg_queue_time` FLOAT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS traffic_light_plan;
CREATE TABLE traffic_light_plan (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `traffic_light_id` varchar(256) NOT NULL DEFAULT '',
   `junction_id` varchar(256) NOT NULL DEFAULT '',
   `time_period` int(11) NOT NULL,
   `link_id_list` varchar(256) NOT NULL DEFAULT '',
   `phase_id_list` TEXT NOT NULL ,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS traffic_light_phase;
CREATE TABLE traffic_light_phase (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `phase_id` int(11) unsigned NOT NULL ,
   `duration` int(11) unsigned NOT NULL ,
   `is_left` tinyint(1) NOT NULL ,
   `is_right` tinyint(1) NOT NULL ,
   `phase_state` varchar(256) NOT NULL DEFAULT '',
   PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统用户';



DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `user_id` int(11) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';


DROP TABLE IF EXISTS `simulation_config`;
CREATE TABLE `simulation_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(100) NOT NULL COMMENT 'token',
  `add_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仿真配置文件';


# DROP TABLE IF EXISTS `scats_link_param`;
# CREATE TABLE `scats_link_param` (
#   `id` int(11) NOT NULL AUTO_INCREMENT,
#   `file_name` varchar(100) NOT NULL COMMENT 'token',
#   `add_time` datetime DEFAULT NULL COMMENT '过期时间',
#   PRIMARY KEY (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仿真配置文件';
