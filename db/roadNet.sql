USE  traffic_optimization;

DROP TABLE IF EXISTS net_connection;
CREATE TABLE `net_connection` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `conn_from` varchar(50) DEFAULT NULL,
  `conn_to` varchar(50) DEFAULT NULL,
  `fromLane` varchar(50) DEFAULT NULL,
  `toLane` varchar(50) DEFAULT NULL,
  `tl` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS net_edge;
CREATE TABLE `net_edge` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `lane_id` varchar(50) NOT NULL,
  `speed` float DEFAULT NULL,
  `length` float DEFAULT NULL,
  `lane_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS net_lane;
CREATE TABLE `net_lane` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `lane_id` varchar(50) NOT NULL,
  `speed` float DEFAULT NULL,
  `length` float DEFAULT NULL,
  `lane_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS net_location;
CREATE TABLE `net_location` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `location_id` varchar(50) DEFAULT NULL,
  `location_name` varchar(50) DEFAULT NULL,
  `netOffset` varchar(10) DEFAULT NULL,
  `convBoundary` varchar(50) NOT NULL,
  `origBoundary` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS net_node;

CREATE TABLE `net_node` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `node_id` varchar(50) NOT NULL,
  `x` varchar(10) DEFAULT NULL,
  `y` varchar(10) DEFAULT NULL,
  `node_type` varchar(20) DEFAULT NULL,
  `tl_id` varchar(50) DEFAULT NULL,
  `node_name` varchar(50) DEFAULT NULL,
  `incLanes` varchar(255) DEFAULT NULL,
  `intLanes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tl_group;
CREATE TABLE `tl_group` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `group_id` varchar(50) NOT NULL,
  `logic_list` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tl_logic;
CREATE TABLE `tl_logic` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tl_id` varchar(50) NOT NULL,
  `offset` varchar(5) DEFAULT NULL,
  `phase_list` varchar(100) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `program_id` varchar(45) DEFAULT NULL,
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tl_phase;
CREATE TABLE `tl_phase` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `phase_id` varchar(50) NOT NULL,
  `duration` varchar(5) DEFAULT NULL,
  `state` char(18) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;