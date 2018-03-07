CREATE database seckill;

use seckill;

--创建表格
CREATE TABLE seckill(
`seckill_id` bigint NOT NULL auto_increment comment '商品库存ID',
`name` VARCHAR (120) NOT NULL comment '商品名称',
`number` INT NOT NULL comment '库存数量',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
`start_time` TIMESTAMP NOT NULL comment '秒杀开启时间',
`end_time` TIMESTAMP NOT NULL comment '秒杀结束时间',
PRIMARY KEY (seckill_id),
KEY idx_start_time(start_time),
KEY idx_end_time(end_time),
KEY idx_create_time(create_time)
)engine=innodb auto_increment=1000 DEFAULT charset=utf8 comment='秒杀库存表' ;


--初始化数据
INSERT INTO
  seckill(name,number,start_time,end_time)
VALUES
  ('1000元秒杀iPhone6',100,'2018-2-10 00:00:00','2018-2-11 00:00:00'),
  ('500元秒杀iPad2',200,'2018-2-10 00:00:00','2018-2-11 00:00:00'),
  ('300元秒杀小米4',300,'2018-2-10 00:00:00','2018-2-11 00:00:00'),
  ('200元秒杀红米note',400,'2018-2-10 00:00:00','2018-2-11 00:00:00');


--秒杀成功明细表
CREATE TABLE success_killed(
`seckill_id` bigint NOT NULL comment '秒杀商品ID',
`user_phone` bigint NOT NULL comment '用户手机号',
`state` tinyint NOT NULL DEFAULT -1 comment '状态标示：-1：无效 0：成功 1：已付款 2：已发货',
`create_time` TIMESTAMP NOT NULL comment '创建时间',
PRIMARY KEY (seckill_id,user_phone),
KEY idx_create_time(create_time)
)engine=innodb DEFAULT charset=utf8 comment='秒杀成功明细表' ;