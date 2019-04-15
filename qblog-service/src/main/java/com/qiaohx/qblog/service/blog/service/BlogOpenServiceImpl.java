package com.qiaohx.qblog.service.blog.service;

import com.qiaohx.qblog.api.blog.service.BlogOpenService;
import com.qiaohx.qblog.api.blog.vo.BlogOpenRequestVo;
import com.qiaohx.qblog.api.blog.vo.BlogOpenResponseVo;
import com.qiaohx.qblog.api.common.AbstractBaseService;
import com.qiaohx.util.constant.MQConstant;
import com.qiaohx.util.response.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service("blogOpenService")
public class BlogOpenServiceImpl extends AbstractBaseService implements BlogOpenService {

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory",
            bindings = @QueueBinding(
                    value = @Queue(value = MQConstant.QUEUE_BLOG_OPEN_QUEUE, durable = "true", autoDelete="false"),
                    exchange = @Exchange(value = MQConstant.EXCHANGE_AMQ_DIRECT),
                    key = MQConstant.ROUTING_KEY_BLOG_OPEN_ROUTING_KEY)
    )
    public void blogOpen(String content) throws Exception{
        // 开通博客消息
        logger.info(String.format("[%s] receive msg: %s", MQConstant.QUEUE_BLOG_OPEN_QUEUE, content));
        BlogOpenRequestVo blogOpenRequestVo = (BlogOpenRequestVo)JSONObject.toBean(JSONObject.fromObject(content), BlogOpenRequestVo.class);
        openBlog(blogOpenRequestVo);

    }

    /**
     * 开通博客
     *
     * @param blogOpenRequestVo 开通参数
     * @return 开通结果
     * @throws Exception 异常
     */
    @Override
    public BlogOpenResponseVo openBlog(BlogOpenRequestVo blogOpenRequestVo) throws Exception {
        logger.info("收到开通博客请求：" + blogOpenRequestVo.toString());
        BlogOpenResponseVo blogOpenResponseVo = ResponseUtil.success(BlogOpenResponseVo.class);
        blogOpenResponseVo.setBlogTag(blogOpenRequestVo.getUserId());
        return blogOpenResponseVo;
    }
}
