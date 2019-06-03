USE  traffic_optimization;

DROP TABLE IF EXISTS net_connection;
CREATE TABLE `net_connection` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `conn_id` int(11) NOT NULL,
  `conn_from` varchar(50) DEFAULT NULL,
  `conn_to` varchar(50) DEFAULT NULL,
  `fromLane` varchar(50) DEFAULT NULL,
  `toLane` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS net_edge;
CREATE TABLE `net_edge` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `edge_id` varchar(50) NOT NULL,
  `from` varchar(50) DEFAULT NULL,
  `to` varchar(50) DEFAULT NULL,
  `lane_list` varchar(100) DEFAULT NULL,
  `edge_name` varchar(50) DEFAULT NULL,
  `edge_type` varchar(30) DEFAULT NULL,
  `numLanes` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS net_lane;
CREATE TABLE `net_lane` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `lane_id` varchar(50) NOT NULL,
  `speed` float DEFAULT NULL,
  `length` float DEFAULT NULL,
  `lane_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS net_location;

CREATE TABLE `net_location` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `location_id` varchar(50) NOT NULL,
  `location_name` varchar(50) DEFAULT NULL,
  `netOffset` varchar(10) DEFAULT NULL,
  `convBoundary` varchar(50) NOT NULL,
  `origBoundary` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS net_node;

CREATE TABLE `net_node` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `node_id` varchar(50) NOT NULL,
  `x` float DEFAULT NULL,
  `y` float DEFAULT NULL,
  `node_type` varchar(10) DEFAULT NULL,
  `tl_id` varchar(50) DEFAULT NULL,
  `node_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;