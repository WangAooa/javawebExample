package com.fantai.controller;

import com.fantai.dao.LockInfoMapper;
import com.fantai.entity.LockInfo;
import com.fantai.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/public")
public class PublicController {

    @Resource
    private LockInfoMapper lockInfoMapper;

    @RequestMapping("/freshLock.do")
    @ResponseBody
    public List<LockInfo> freshLock(HttpServletRequest request){
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");
        String ui_id = userInfo.getSy_ui_id();
        List<LockInfo> list = lockInfoMapper.findByUser(ui_id);
        return list;
    }

}
