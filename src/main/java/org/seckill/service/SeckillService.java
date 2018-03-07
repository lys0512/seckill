package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillClosedException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * Created by luoyisu on 2018/2/11.
 */
public interface SeckillService {

    //查询所有秒杀
    List<Seckill> getSeckillList();

    //查询单个秒杀
    Seckill getById(long seckillId);

    //秒杀开启时输出秒杀地址，否则输出系统时间和秒杀时间
    Exposer exportSeckillUrl(long seckillId);

    //执行秒杀操作
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException,RepeatKillException,SeckillClosedException;

    //执行秒杀操作 by 存储过程
    SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5)
            throws SeckillException,RepeatKillException,SeckillClosedException;

}
