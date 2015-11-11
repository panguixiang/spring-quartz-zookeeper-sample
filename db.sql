##quartz持久化表

CREATE TABLE `tab_yilai_quartz_bean` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `job_name` varchar(100) NOT NULL COMMENT '任务名称',
  `trigger_name` varchar(100) NOT NULL COMMENT '触发器名称',
  `class_package` varchar(200) NOT NULL COMMENT '实现Job接口的类全名',
  `cron` varchar(20) NOT NULL COMMENT '执行规则',
  `remarks` varchar(80) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;  