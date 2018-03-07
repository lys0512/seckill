package org.seckill.dto;

import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnums;

/**
 * 封装秒杀后的结果
 * Created by luoyisu on 2018/2/11.
 */
public class SeckillExecution {

    private long seckillId;

    //秒杀执行结果状态
    private int state;

    //秒杀状态表示
    private String stateInfo;

    //秒杀成功对象
    private SuccessKilled successKilled;

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }

    public SeckillExecution(long seckillId, SeckillStateEnums seckillStateEnums, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = seckillStateEnums.getState();
        this.stateInfo = seckillStateEnums.getStateInfo();
        this.successKilled = successKilled;
    }

    public SeckillExecution(long seckillId, SeckillStateEnums seckillStateEnums) {
        this.seckillId = seckillId;
        this.state = seckillStateEnums.getState();
        this.stateInfo = seckillStateEnums.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }
}
