package com.qiaohx.qblog.qblogweb.controller.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private final static Logger logger = LoggerFactory.getLogger(BlogController.class);

    @RequestMapping("/{blogTag}")
    public void goBlog(@PathVariable(name="blogTag") String blogTag,
                       HttpServletRequest request, HttpServletResponse response){
        try {
            response.sendRedirect("https://www.qiaohx.com");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("重定向到博客异常 " + blogTag, e);
        }
    }
}
