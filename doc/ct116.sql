# Host: localhost  (Version: 5.5.53)
# Date: 2017-08-27 02:23:46
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "admin"
#

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='管理员表';

#
# Data for table "admin"
#


#
# Structure for table "category"
#

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL COMMENT '父栏目id',
  `name` varchar(45) NOT NULL COMMENT '栏目名称',
  PRIMARY KEY (`id`),
  KEY `fk_category_category1_idx` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COMMENT='栏目分类表';

#
# Data for table "category"
#

INSERT INTO `category` VALUES (2,2,'电器'),(3,3,'书籍'),(4,4,'文具'),(5,5,'交通工具'),(6,6,'电脑相关'),(7,7,'手机相关'),(21,2,'电风扇'),(22,2,'空调'),(23,2,'电冰箱'),(24,2,'电饭锅'),(25,2,'电磁炉'),(26,2,'电灯'),(27,2,'电吹风'),(31,3,'教科书'),(32,3,'技术类书籍'),(33,3,'小说'),(34,3,'历史文集'),(35,3,'诗词散曲小令集'),(36,3,'儿童画册'),(41,4,'笔'),(42,4,'书包'),(51,5,'自行车'),(52,5,'摩托车'),(61,6,'显示屏'),(62,6,'键盘'),(63,6,'鼠标'),(64,6,'U盘'),(65,6,'移动硬盘'),(66,6,'固态硬盘'),(67,6,'机械硬盘'),(71,7,'数据线'),(72,7,'移动电源'),(73,7,'充电插头');

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(45) NOT NULL COMMENT '手机号',
  `username` varchar(45) NOT NULL COMMENT '用户名',
  `password` varchar(45) NOT NULL COMMENT '密码',
  `address` varchar(100) NOT NULL COMMENT '联系地址',
  `qq` varchar(45) DEFAULT NULL COMMENT 'QQ号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_UNIQUE` (`phone`),
  UNIQUE KEY `name_UNIQUE` (`username`),
  KEY `phone_INDEX` (`phone`),
  KEY `name_INDEX` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='用户表：包括卖家、买家';

#
# Data for table "user"
#


#
# Structure for table "order"
#

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) NOT NULL COMMENT '买家id',
  `seller_id` int(11) NOT NULL COMMENT '卖家id',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `status` int(11) NOT NULL COMMENT '0：买家已下单；1：卖家已确认收款，交易成功；2：卖家拒绝订单，交易失败',
  PRIMARY KEY (`id`),
  KEY `fk_order_user1_idx` (`seller_id`),
  KEY `fk_order_user2_idx` (`buyer_id`),
  CONSTRAINT `fk_order_user1` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_order_user2` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

#
# Data for table "order"
#


#
# Structure for table "goods"
#

DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL COMMENT '所属栏目',
  `seller_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL COMMENT '商品名称',
  `price` decimal(8,2) NOT NULL COMMENT '单价',
  `quantity` int(11) NOT NULL,
  `status` int(11) NOT NULL COMMENT '0：无；1：未上架；2：已上架；3：已删除',
  `main_image` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`id`),
  KEY `fk_goods_category1_idx` (`category_id`),
  KEY `fk_goods_user1_idx` (`seller_id`),
  CONSTRAINT `fk_goods_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_goods_user1` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='商品表';

#
# Data for table "goods"
#


#
# Structure for table "order_item"
#

DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `origin_goods_id` int(11) NOT NULL COMMENT '此项下订商品原id',
  `price` decimal(8,2) NOT NULL COMMENT '此项商品的下订单价',
  `quantity` int(11) NOT NULL COMMENT '此项商品的下订数量',
  PRIMARY KEY (`id`),
  KEY `fk_order_item_goods1_idx` (`origin_goods_id`),
  KEY `order_id_INDEX` (`order_id`),
  CONSTRAINT `fk_order_item_goods1` FOREIGN KEY (`origin_goods_id`) REFERENCES `goods` (`id`),
  CONSTRAINT `fk_order_item_order1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单项表';

#
# Data for table "order_item"
#


#
# Structure for table "goods_image"
#

DROP TABLE IF EXISTS `goods_image`;
CREATE TABLE `goods_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `image_url` varchar(255) NOT NULL COMMENT '图片路径',
  `order_no` int(11) NOT NULL COMMENT '图片序号',
  PRIMARY KEY (`id`),
  KEY `fk_goods_image_goods1_idx` (`goods_id`),
  CONSTRAINT `fk_goods_image_goods1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='商品图片表';

#
# Data for table "goods_image"
#


#
# Structure for table "cart_item"
#

DROP TABLE IF EXISTS `cart_item`;
CREATE TABLE `cart_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) NOT NULL COMMENT '买家id',
  `goods_id` int(11) NOT NULL COMMENT '此项商品id',
  `quantity` int(11) NOT NULL COMMENT '此项商品数量',
  PRIMARY KEY (`id`),
  KEY `fk_cart_item_goods1_idx` (`goods_id`),
  KEY `buyer_id_INDEX` (`buyer_id`),
  CONSTRAINT `fk_cart_item_goods1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_cart_item_user` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车项表';

#
# Data for table "cart_item"
#

