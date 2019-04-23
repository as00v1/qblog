package com.qiaohx.qblog.service.blog.service;

import com.qiaohx.qblog.api.blog.service.GroupAddService;
import com.qiaohx.qblog.api.blog.service.GroupQueryService;
import com.qiaohx.qblog.api.blog.vo.GroupAddRequestVo;
import com.qiaohx.qblog.api.blog.vo.GroupQueryResponseVo;
import com.qiaohx.qblog.api.common.AbstractBaseService;
import com.qiaohx.qblog.service.blog.dao.BlogGroupInfoMapper;
import com.qiaohx.qblog.service.blog.model.BlogGroupInfo;
import com.qiaohx.util.response.BaseDataResponse;
import com.qiaohx.util.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("groupQueryService")
@Transactional(rollbackFor = Exception.class)
public class GroupQueryServiceImpl extends AbstractBaseService implements GroupQueryService {

    @Autowired
    private BlogGroupInfoMapper blogGroupInfoMapper;
    @Autowired
    private GroupAddService groupAddService;

    /**
     * 查询所有分类
     *
     * @param blogId 博客标识
     * @return 所有分类信息
     * @throws Exception 异常
     */
    @Override
    public GroupQueryResponseVo queryGroup(String blogId) throws Exception {
        List<BlogGroupInfo> list = blogGroupInfoMapper.queryByBlogId(blogId);
        if (list.size() == 0){
            logger.info(String.format("博客 %s 没有分类，新建一条", blogId));
            GroupAddRequestVo groupAddRequestVo = new GroupAddRequestVo();
            groupAddRequestVo.setBlogId(blogId);
            groupAddRequestVo.setGroupName("未分类");
            BaseDataResponse baseDataResponse = groupAddService.addGroup(groupAddRequestVo);
            if (baseDataResponse.getCode() != 0){
                return (GroupQueryResponseVo)baseDataResponse;
            }
            // 重新查询
            list = blogGroupInfoMapper.queryByBlogId(blogId);
        }
        GroupQueryResponseVo groupQueryResponseVo = ResponseUtil.success(GroupQueryResponseVo.class);
        groupQueryResponseVo.setList(list);
        return groupQueryResponseVo;
    }
}
