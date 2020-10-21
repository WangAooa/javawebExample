package com.fantai.util;

import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DataMsg {

    private final static int FuncData = 3;  //子功能数据长度大小
    private final static int RandomRange = 1000; //随机数范围获取
    private static Map<String,HeartCheck> m_CodeIdMap = new HashMap<>();

    private void HandleHeart(byte codeId,String code,int time) {
        if(m_CodeIdMap.containsKey(code)){ //判断是否含有该codeId
            m_CodeIdMap.get(code).setRunning(false);
            m_CodeIdMap.get(code).interrupt();
            HeartCheck heartCheck = new HeartCheck(codeId,code,time);
            heartCheck.start();
            m_CodeIdMap.put(code,heartCheck);
        } else {
            HeartCheck heartCheck = new HeartCheck(codeId,code,time);
            heartCheck.start();
            m_CodeIdMap.put(code,heartCheck);
        }
    }

    public byte[] buildRandom(int d) {
        int random = (int) (Math.random() * d);
        byte[] buf = new byte[2];
        buf[0] = (byte) random;
        buf[1] = (byte) (random >> 8);  //高位
        return buf;
    }

    public byte[] controlLock(byte func, String code) {

        byte[] bCode = DataFormat.hexStringToBytes(code);   // 获取十六进制字符码
        byte[] buf = new byte[42];    //数据内容总长度为42位
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        byte[] time = DataFormat.hexStringToBytes(df.format(new Date()));  //时间
        byte bcd = getXor(time);
        byte[] key = {(byte) 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x30};
        buf[0] = (byte) 0x2A;  //*
        buf[1] = (byte) 0x5A;  // 固定值
        buf[2] = (byte) 0x7B;
        buf[3] = (byte) 0x02;
        System.arraycopy(bCode, 0, buf, 8, bCode.length);   //赋值id吗
        System.arraycopy(time, 0, buf, 32, time.length);  //赋值时间码
        System.arraycopy(key, 0, buf, 22, key.length);  //赋值密钥
        buf[39] = bcd;   //时间bcd值校验
        buf[21] = (byte) 0x12;  //参数内容长度
        buf[20] = func; //内容
        byte[] crc = CRC16.crc16(buf, 2, buf.length - 4);  //crc校验
        buf[40] = crc[0];  //crc校验 高位
        buf[41] = crc[1]; //crc校验 低位
        return buf;
    }

    public static byte getXor(byte[] datas) {
        byte temp = datas[0];
        for (int i = 1; i < datas.length; i++) {
            temp ^= datas[i];
        }
        return temp;
    }

    public byte[] sendControlMsg(byte addr, byte func, byte[] content) {
        byte[] buf = null;
        try {
            // FE(1) + 地址位（1） + 功能码（1） + 数据量（1） + 子功能内容（1+2）+ 数据长度（1） + 数据内容（n） + crc(1) + FF(1)
            byte crc = 0x00;
            int len = 7 + FuncData;
            if (content != null)
                len = 7 + FuncData + content.length;
            System.out.println("时间：" + DataFormat.bytes2HexString(content));
            buf = new byte[len];
            buf[0] = (byte) 0xFE;
            buf[1] = addr; //地址
            buf[2] = func;  //功能码
            buf[3] = FuncData;  //子功能码数据长度  默认 3
            byte[] random = buildRandom(RandomRange);
            buf[5] = random[0]; //随机数
            buf[6] = random[1]; //随机数
            buf[7] = (byte) (len - 2);  //数据长度位
            switch (func) {
                case TransFunc.GPSUPDATE: //gps手动更新
                    buf[4] = 0x02; //终端需要回复内容信息
                    break;
                case TransFunc.LOCK: //电磁锁操作
//                    buf[4] = (byte) 0x2D;
//                    System.arraycopy(content, 0, buf, 8, content.length);  //内容数据复制
//                    break;
                case TransFunc.GPSTIME://gps上传时间
                case TransFunc.GIVETIME://平台授时
                case TransFunc.LOGIN:  //平台登录
                case TransFunc.ICACCREDIT:  //ic卡授权
                case TransFunc.SENSORTIME: //传感器间隔信息设置
                    buf[4] = 0x01;  //回复ack即可
                    System.arraycopy(content, 0, buf, 8, content.length);  //内容数据复制
                    break;
            }
            buf[len - 1 - 1] = CRC8.calcCrc8(buf, 1, len - 3, crc);
            buf[len - 1] = (byte) 0xFF;
        } catch (Exception e) {
        }
        return buf;
    }

    public byte[] timeCalendar() {
        SimpleDateFormat df = new SimpleDateFormat("yy/MM/dd,HH:mm:ss+08");//设置日期格式
        String time = df.format(new Date());// new Date()为获取当前系统时间
        byte[] timeBuf = time.getBytes();
        int len = timeBuf.length + 3;
        byte[] buf = new byte[len];
        System.arraycopy(timeBuf, 0, buf, 0, timeBuf.length);
        buf[len - 1] = 0x00;  //最后三位补充
        buf[len - 2] = 0x00;
        buf[len - 3] = 0x01;  //保留为格式
        return buf;
    }

    public void RecieveDataMsg(byte[] buffer, int len, int tranModel, Socket s, OutputStream out) throws Exception {
        System.out.println("recMsg:" + DataFormat.bytes2HexString(buffer));
        if (buffer[0] == (byte) 0xFE && buffer[buffer.length - 1] == (byte) 0xFF) {  //0位
            byte[] code = new byte[4]; // id码
            System.arraycopy(buffer, 1, code, 0, 4); //取出信息码  到底4位
            System.out.println("code码：" + DataFormat.bytes2HexString(code));
            byte[] Content = null;
            byte[] ack = {(byte) 0xFE, 0x21, 0x01, 0x03, 0x00, 0x00, 0x00, 0x08, 0x00, (byte) 0xFF};
            switch (buffer[5]) { //地址码 0x03 0x04
                case (byte) 0x03: //基础消息回复
                    switch (buffer[6]){  //功能码
                        case (byte) 0x01:  //基础ack 回复  01
                            System.out.println("控制成功接收");
                            break;
                        case TransFunc.LOCK:  //智能锁信息  2d
                            Content = new byte[len - 14];
                            System.arraycopy(buffer, 12, Content, 0, len - 14);
                            System.out.println("0x2d");
                            if(DatabaseUtil.updateLock(DataFormat.bytes2HexString(code),Content) > 0) {

                            }
                            System.out.println("suo");
                            ack[5] = buffer[9]; //随机数
                            ack[6] = buffer[10]; //随机数
                            ack[8] = CRC8.calcCrc8(ack, 1, 7);
                            if (tranModel == 0)
                                out.write(ack);
                            System.out.println("功能锁控制成功");
                            break;
                    }
                    break;   //基础验证
                case (byte) 0x05: //登陆  更新ip地址和gps信息   测试成功
                    Content = new byte[len - 14];
                    System.arraycopy(buffer, 12, Content, 0, len - 14);
                    String socketAddress = "";
                    if (tranModel == 0)
                        socketAddress = s.getInetAddress().toString();

                        String op = socketAddress.substring(1, socketAddress.length());
                    System.out.println("socketAddress:" + op);
                    if (DatabaseUtil.UpdateCodeIP(DataFormat.bytes2HexString(code), op, "28888") > 0){ //更新ip地址
                        int heartTime = DatabaseUtil.getDeivceHeartNumByCode(DataFormat.bytes2HexString(code));
                        DatabaseUtil.updateDeviceState(code[0], DataFormat.bytes2HexString(code), 1);  //更新状态
                        System.out.println("heartTime:" + heartTime);
                        HandleHeart(code[0], DataFormat.bytes2HexString(code), heartTime);
                        ack[5] = buffer[9];
                        ack[6] = buffer[10];
                        ack[8] = CRC8.calcCrc8(ack, 1, 7);
                        if (tranModel == 0)
                            out.write(ack);
                        Thread.sleep(1000);
                        byte[] sendTime = sendControlMsg((byte) 0x21, TransFunc.GIVETIME, timeCalendar());
                        if (tranModel == 0)
                            out.write(sendTime);
                    }
                    break;  //登陆验证
            }
        }
    }
}
