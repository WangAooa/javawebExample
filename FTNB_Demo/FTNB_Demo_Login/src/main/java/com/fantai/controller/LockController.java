package com.fantai.controller;

import com.fantai.dao.DeviceInfoMapper;
import com.fantai.dao.LockInfoMapper;
import com.fantai.entity.DeviceInfo;
import com.fantai.entity.LockInfo;
import com.fantai.entity.UserInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.List;

@Controller
@RequestMapping("/lock")
public class LockController {

    public static final int UPDATE_SUCCESS = 0;

    @Resource
    private LockInfoMapper lockInfoMapper;

    @Resource
    private DeviceInfoMapper deviceInfoMapper;

    @RequestMapping("/findByThing.do")
    public String findByThing(Model model, HttpServletRequest request){
        UserInfo u = (UserInfo)request.getSession().getAttribute("user");
        if(u.getSy_ui_role() == 2){
            List<LockInfo> list = lockInfoMapper.findByUser(u.getSy_ui_id());
            model.addAttribute("module", "lock_list");
            model.addAttribute("lockinfos", list);
            return "lock/findByUser";
        }
        return null;
    }

}
