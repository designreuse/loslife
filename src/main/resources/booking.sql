# 给[广告主]添加[货币类型、是否跨区域]字段
ALTER TABLE `clients`
ADD COLUMN `currency_id int(11) DEFAULT NULL AFTER `industry_id`；

# 给[广告主]添加[状态]字段
ALTER TABLE `clients`
ADD COLUMN `client_status` varchar(20) DEFAULT 'Active',

# 创建[货币类型]表
CREATE TABLE `currencies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `remarks` text,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

# 插入[货币类型]初始数据
INSERT INTO `currencies` (`id`, `name`, `remarks`, `created_at`, `updated_at`)
VALUES
	(1, 'HKD', NULL, '2011-07-04 10:52:23', '2011-07-04 10:52:23'),
	(2, 'RMB', NULL, '2011-07-04 10:52:23', '2011-07-04 10:52:23'),
	(3, 'USD', NULL, '2011-07-04 10:52:23', '2011-07-04 10:52:23'),
	(4, 'SGD', NULL, '2011-07-04 10:52:23', '2011-07-04 10:52:23'),
	(5, 'TWD', NULL, '2011-07-04 10:52:23', '2011-07-04 10:52:23'),
	(6, 'KRW', '', '2011-07-12 03:29:46', '2011-07-12 03:29:46'),
	(8, 'JPY', '', '2011-07-12 11:08:44', '2011-07-12 11:08:44'),
	(9, 'MYR', NULL, '2012-08-15 06:58:58', '2012-08-15 06:58:58'),
	(10, 'GBP', NULL, '2012-08-15 06:58:58', '2012-08-15 06:58:58'),
	(11, 'EUR', '', '2012-11-29 10:14:25', '2012-11-29 10:14:25'),
	(12, 'AUD', NULL, '2013-07-22 08:12:55', '2013-07-22 08:12:55'),
	(13, 'THB', '', '2015-02-26 07:04:40', '2015-02-26 07:04:40'),
	(14, 'RUB', '', '2015-02-26 07:04:51', '2015-02-26 07:04:51'),
	(15, 'IDR', '', '2015-02-26 07:05:01', '2015-02-26 07:05:01');
	
# 创建[广告主联系人]表
CREATE TABLE `client_contacts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) DEFAULT NULL,
  `contact_person` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `is_delete` smallint(1) DEFAULT 1,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
