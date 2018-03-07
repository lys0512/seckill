package org.seckill.exception;

/**
 * 重复秒杀异常(运行期异常，不用手动try-catch)
 * Spring事务只接受运行期异常回滚
 * Created by luoyisu on 2018/2/11.
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
