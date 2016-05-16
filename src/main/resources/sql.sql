create table ss_mail_receivers(
	id int(11) NOT NULL AUTO_INCREMENT,
	mailId int(11) NOT NULL,
	mailAddress varchar(50) DEFAULT NULL,
	copyTo varchar(50) DEFAULT NULL,
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT charset utf8 collate utf8_general_ci;


## add by siuvan 2016-01-13 16:21:50
CREATE TABLE `ss_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `operateType` tinyint(4) DEFAULT NULL,
  `module` varchar(100) DEFAULT NULL,
  `operateBy` varchar(100) DEFAULT NULL,
  `operateTime` timestamp NULL DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `filePath` varchar(200) DEFAULT NULL,
  `pKey` varchar(100) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


## add by siuvan 2016-01-15 13:27:45
ALTER TABLE `ss_exchange_rate`
ADD COLUMN `create_by` VARCHAR(64) NULL DEFAULT NULL AFTER `start_date`,
ADD COLUMN `update_by` VARCHAR(64) NULL DEFAULT NULL AFTER `created_at`;

ALTER TABLE `ss_log`
ADD COLUMN `operateUserId` VARCHAR(50) NULL DEFAULT NULL AFTER `operateBy`;

## add by siuvan 2016-01-18 11:34:33
ALTER TABLE `ss_log`
ADD COLUMN `remark1` VARCHAR(1000) NULL DEFAULT NULL AFTER `remark`,
ADD COLUMN `remark2` VARCHAR(1000) NULL DEFAULT NULL AFTER `remark1`;