package com.fantai.controller;

import com.fantai.dao.DeviceInfoMapper;
import com.fantai.dao.SensorInfoMapper;
import com.fantai.entity.DeviceInfo;
import com.fantai.entity.LockInfo;
import com.fantai.entity.SensorInfo;
import com.fantai.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/sensor")
public class SensorController {

    @Resource
    private SensorInfoMapper sensorInfoMapper;

    @Resource
    private DeviceInfoMapper deviceInfoMapper;

    @RequestMapping("/findByThing.do")
    public String findByThing(Model model, HttpServletRequest request){
        UserInfo u = (UserInfo)request.getSession().getAttribute("user");
        if(u.getSy_ui_role() == 2){
            List<SensorInfo> list = sensorInfoMapper.findByUser(u.getSy_ui_id());
            model.addAttribute("module", "sensor_list");
            model.addAttribute("sensorinfos", list);
            return "sensor/findByUser";
        }
        return null;
    }

}
