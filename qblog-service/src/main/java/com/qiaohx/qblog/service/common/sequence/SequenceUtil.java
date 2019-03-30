package com.qiaohx.qblog.service.common.sequence;

import com.qiaohx.qblog.api.common.redis.RedisService;
import com.qiaohx.util.date.DateFormatRules;
import com.qiaohx.util.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;

/**
 * 序列公共类
 */
@Component
public class SequenceUtil {

    private final static Logger logger = LoggerFactory.getLogger(SequenceUtil.class);
    private final static String REDIS_KEY = "SEQUENCE_KEY";
    private final static Calendar todayEnd = Calendar.getInstance();
    private final static int seq_length = 20;// id长度
    private static SequenceUtil sequenceUtil;

    @Autowired
    private RedisService redisService;

    @PostConstruct
    public void init() {
        sequenceUtil = this;
        sequenceUtil.redisService = this.redisService;
    }

    /**
     * 获取ID
     * @return ID
     */
    public static synchronized String getSequence(){
        return getSequence(seq_length);
    }

    /**
     * 获取ID
     * @return ID
     */
    public static synchronized String getSequence(int length){
        // 1.获取14位当前时间为id基数
        StringBuffer id = new StringBuffer(DateUtil.dateToStr(DateFormatRules.YYYYMMDDHHMMSS));
        logger.debug("当前时间 = " + id);
        // 2.从Redis获取序列
        long incr = sequenceUtil.redisService.getIncrement(REDIS_KEY, getTodayEndTime().getTime());
        logger.debug("获取序列 = " + incr);
        // 3.序列拼接
        id.append(incr);
        logger.debug("拼接序列 = " + id);
        if (id.length() > length){
//            id.replace(14, id.length() - length, "");
//            id.delete(14, id.length() - length);
            logger.debug("id.length() - length = " + (id.length() - length));

            return id.delete(14, 14 + id.length() - length).toString();
        }else{
            while (id.length() < length)
                id.insert(14, 0);
            return id.toString();
        }
    }

    private static Date getTodayEndTime() {
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }
}
