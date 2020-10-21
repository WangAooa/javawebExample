package com.fantai.controller;

import com.fantai.dao.LockInfoMapper;
import com.fantai.entity.LockInfo;
import com.fantai.entity.UserInfo;
import com.fantai.util.DataMsg;
import com.fantai.util.TCPThreadServerSocket;
import com.fantai.util.TransFunc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

/**
 * Created by xiaoy_000 on 2017/9/12.
 */
@Controller
@RequestMapping("/lock")
public class LockController {

    @Resource
    private LockInfoMapper lockInfoMapper;

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

    @RequestMapping("/openLock.do")
    @ResponseBody
    public void openLock(String sy_di_code) throws IOException {
        System.out.println("开锁");
        DataMsg dataMsg = new DataMsg();
        LockInfo lockInfo = lockInfoMapper.findByCode(sy_di_code);
        String ip = lockInfo.getSy_di_ip();
        String di_port = lockInfo.getSy_di_port();
        int port = Integer.parseInt(di_port);
        int con_type = lockInfo.getSy_di_con_type();
        byte addr = TransFunc.MOTORSEND;
        byte func = TransFunc.LockDeblocking;
        String code = lockInfo.getSy_di_code();
        byte[] controlmsg = dataMsg.controlLock(func, "0000000000000000");
        byte[] sendmsg = dataMsg.sendControlMsg(addr, TransFunc.LOCK, controlmsg);
        System.out.println(sendmsg.length);
        for(Socket s : TCPThreadServerSocket.slist) {
            String ips = s.getInetAddress().toString();
            String op = ips.substring(1, ips.length());
            if(op.equals(ip)) {
                OutputStream out = s.getOutputStream();
                out.write(sendmsg);
                System.out.println("TCP开锁");
            }
        }
    }

    @RequestMapping("/closeLock.do")
    @ResponseBody
    public void closeLock(String sy_di_code) throws Exception{
        System.out.println("关锁");
        DataMsg dataMsg = new DataMsg();
        LockInfo lockInfo = lockInfoMapper.findByCode(sy_di_code);
        String ip = lockInfo.getSy_di_ip();
        String di_port = lockInfo.getSy_di_port();
        int port = Integer.parseInt(di_port);
        int con_type = lockInfo.getSy_di_con_type();
        byte addr = TransFunc.MOTORSEND;
        byte func = TransFunc.LockSealing;
        String code = lockInfo.getSy_di_code();
        byte[] controlmsg = dataMsg.controlLock(func, "0000000000000000");
        byte[] sendmsg = dataMsg.sendControlMsg(addr, TransFunc.LOCK, controlmsg);
        System.out.println(sendmsg.length);
        for(Socket s : TCPThreadServerSocket.slist) {
            String ips = s.getInetAddress().toString();
            String op = ips.substring(1, ips.length());
            if(op.equals(ip)) {
                OutputStream out = s.getOutputStream();
                out.write(sendmsg);
                System.out.println("TCP关锁");
            }
        }
//            for(Channel channel : TcpServiceThread.channels ) {
//                System.out.println(channel.remoteAddress().toString().split(":")[0].substring(1)+","+ip);
//                if(channel.remoteAddress().toString().split(":")[0].substring(1).equals(ip)){
//                    ByteBuf byteBuf = Unpooled.copiedBuffer(sendmsg);
//                    channel.writeAndFlush(byteBuf);
//                    break;
//                }
//            }
//        }
//        else if(con_type == 1){
//            // UDP
//            System.out.println("UDP关锁");
//            UdpServiceThread.SendMsg(ip, port, sendmsg);
//        }
    }

}
