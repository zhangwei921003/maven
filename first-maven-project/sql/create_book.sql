CREATE DATABASE `study`;
USE `study`;

CREATE TABLE `book`
(
    `id`          bigint(18) NOT NULL AUTO_INCREMENT,
    `title`       varchar(256) COMMENT '名称',
    `author`      varchar(256) COMMENT '作者',
    `price`       decimal(13, 2) COMMENT '价格',
    `create_date` datetime   NOT NULL COMMENT '创建时间',
    `update_date` datetime   NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;