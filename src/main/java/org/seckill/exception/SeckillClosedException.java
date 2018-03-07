package org.seckill.exception;

/**
 *秒杀关闭异常
 * Created by luoyisu on 2018/2/11.
 */
public class SeckillClosedException extends SeckillException {

    public SeckillClosedException(String message) {
        super(message);
    }

    public SeckillClosedException(String message, Throwable cause) {
        super(message, cause);
    }
}
