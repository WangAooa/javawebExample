package com.fantai.controller;

import com.fantai.dao.CarInfoMapper;
import com.fantai.dao.DeviceInfoMapper;
import com.fantai.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

    public static final int NFC_ERROR = 1;
    public static final int NFC_SUCCESS = 0;

    @Resource
    private CarInfoMapper carInfoMapper;

    @Resource
    private DeviceInfoMapper deviceInfoMapper;

    @RequestMapping("/findByThing.do")
    public String findByThing(Model model, HttpServletRequest request){
        UserInfo u = (UserInfo)request.getSession().getAttribute("user");
        if(u.getSy_ui_role() == 2){
            List<CarInfo> list = carInfoMapper.findByUser(u.getSy_ui_id());
            model.addAttribute("module", "car_list");
            model.addAttribute("carinfos", list);
            return "car/findByUser";
        }
        return null;
    }

}
