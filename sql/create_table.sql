CREATE TABLE `chain_info`
(
    `id`                 bigint unsigned NOT NULL AUTO_INCREMENT,
    `long_chain_url`     varchar(255)                                             DEFAULT NULL COMMENT '长链',
    `sort_chain_url`     varchar(255)                                             DEFAULT NULL COMMENT '短链',
    `status`             char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '1' COMMENT '状态',
    `create_time`        datetime    NOT NULL COMMENT '创建时间',
    `create_update_user` varchar(20) NOT NULL COMMENT '创建人',
    `update_time`        datetime    NOT NULL COMMENT '更新时间',
    `last_update_user`   varchar(20) NOT NULL COMMENT '更新人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;