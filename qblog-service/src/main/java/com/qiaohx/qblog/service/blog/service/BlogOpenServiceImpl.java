package com.qiaohx.qblog.service.blog.service;

import com.qiaohx.qblog.api.blog.service.BlogOpenService;
import com.qiaohx.qblog.api.blog.vo.BlogOpenRequestVo;
import com.qiaohx.qblog.api.blog.vo.BlogOpenResponseVo;
import com.qiaohx.qblog.api.common.AbstractBaseService;
import com.qiaohx.qblog.service.blog.dao.BlogInfoMapper;
import com.qiaohx.qblog.service.blog.dao.UserBlogRelMapper;
import com.qiaohx.qblog.service.blog.model.BlogInfo;
import com.qiaohx.qblog.service.blog.model.UserBlogRel;
import com.qiaohx.qblog.service.common.sequence.SequenceUtil;
import com.qiaohx.util.constant.BaseConstant;
import com.qiaohx.util.constant.MQConstant;
import com.qiaohx.util.response.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("blogOpenService")
@Transactional(rollbackFor = Exception.class)
public class BlogOpenServiceImpl extends AbstractBaseService implements BlogOpenService {


    @Autowired
    private BlogInfoMapper blogInfoMapper;
    @Autowired
    private UserBlogRelMapper userBlogRelMapper;

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
        // 博客信息
        BlogInfo blogInfo = new BlogInfo();
        String blogId = SequenceUtil.getSequence();
        blogInfo.setBlogId(blogId);
        blogInfo.setBolgName(blogOpenRequestVo.getBlogName());
        blogInfo.setIntroduce(blogOpenRequestVo.getIntroduce());
        blogInfo.setFlag(BaseConstant.FLAG_1);
        blogInfo.setLevel(1);
        blogInfo.setCreateDate(new Date());
        blogInfo.setBlogTag(blogOpenRequestVo.getUserId());

        int row = blogInfoMapper.insertSelective(blogInfo);
        if (row == 1){
            // 博客-用户关系
            UserBlogRel userBlogRel = new UserBlogRel();
            userBlogRel.setRelId(SequenceUtil.getSequence());
            userBlogRel.setUserId(blogOpenRequestVo.getUserId());
            userBlogRel.setBlogId(blogId);
            userBlogRel.setCreateDate(new Date());
            userBlogRel.setFlag(BaseConstant.FLAG_1);
            row = userBlogRelMapper.insertSelective(userBlogRel);
            if (row != 1){
                throw new Exception("创建博客关系失败！");
            }
        }else {
            throw new Exception("博客信息插入失败");
        }
        BlogOpenResponseVo blogOpenResponseVo = ResponseUtil.success(BlogOpenResponseVo.class);
        blogOpenResponseVo.setBlogTag(blogOpenRequestVo.getUserId());
        return blogOpenResponseVo;
    }
}
