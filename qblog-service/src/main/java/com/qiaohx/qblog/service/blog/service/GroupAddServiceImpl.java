package com.qiaohx.qblog.service.blog.service;

import com.qiaohx.qblog.api.blog.service.GroupAddService;
import com.qiaohx.qblog.api.blog.vo.GroupAddRequestVo;
import com.qiaohx.qblog.api.common.AbstractBaseService;
import com.qiaohx.qblog.api.common.redis.RedisService;
import com.qiaohx.qblog.service.blog.dao.BlogGroupInfoMapper;
import com.qiaohx.qblog.service.blog.model.BlogGroupInfo;
import com.qiaohx.qblog.service.common.sequence.SequenceUtil;
import com.qiaohx.util.constant.BaseConstant;
import com.qiaohx.util.response.BaseDataResponse;
import com.qiaohx.util.response.ErrorCodeEnums;
import com.qiaohx.util.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("groupAddService")
@Transactional(rollbackFor = Exception.class)
public class GroupAddServiceImpl extends AbstractBaseService implements GroupAddService {

    @Autowired
    private BlogGroupInfoMapper blogGroupInfoMapper;
    @Autowired
    private RedisService redisService;


    /**
     * 添加分组
     *
     * @param groupAddRequestVo 添加请求参数
     * @return 结果
     * @throws Exception 异常
     */
    @Override
    public BaseDataResponse addGroup(GroupAddRequestVo groupAddRequestVo) throws Exception {
        String blogId = groupAddRequestVo.getBlogId();
        String lockKey = "LOCK_ADD_GROUP_" + blogId;
        long time = System.currentTimeMillis() + BaseConstant.TIME_OUT;
        // 加一个redis锁，防止连续提交
        boolean flag = redisService.lock(lockKey, String.valueOf(time));
        if (flag) {
            BlogGroupInfo blogGroupInfo = new BlogGroupInfo();
            blogGroupInfo.setGroupId(SequenceUtil.getSequence());
            blogGroupInfo.setBlogId(blogId);
            blogGroupInfo.setGroupName(groupAddRequestVo.getGroupName());
            blogGroupInfo.setFlag(BaseConstant.FLAG_1);
            blogGroupInfo.setCreateDate(new Date());
            int row = blogGroupInfoMapper.insertSelective(blogGroupInfo);
            // 解锁
            redisService.unlock(lockKey, String.valueOf(time));
            if (row != 1) {
                throw new Exception("新增分类出错！");
            }
        }else {
            return ResponseUtil.result(ErrorCodeEnums.SYSTEM_BUSY, BaseDataResponse.class);
        }
        return ResponseUtil.success();
    }
}
