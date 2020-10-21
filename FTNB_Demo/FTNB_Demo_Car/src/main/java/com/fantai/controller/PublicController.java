package com.fantai.controller;

import com.fantai.dao.CarInfoMapper;
import com.fantai.entity.CarInfo;
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
    private CarInfoMapper carInfoMapper;

    @RequestMapping("/freshCar.do")
    @ResponseBody
    public List<CarInfo> freshCar(HttpServletRequest request){
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");
        String ui_id = userInfo.getSy_ui_id();
        List<CarInfo> list = carInfoMapper.findByUser(ui_id);
        return list;
    }

}
