package com.fantai.util;

import java.sql.*;

public class DatabaseUtil {

    private static String dbDriver = "com.mysql.jdbc.Driver";
    private static String dbUrl = "jdbc:mysql://www.ftiotcloud.cn:3306/fantainb?autoReconnect=true&amp;autoReconnectForPools=true";// 数据库地址
    private static String dbUser = "root";// 用户名
    private static String dbPass = "test123"; // 用户密码
    static Connection connection = null;

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = C3P0Util.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static int UpdateCodeIP(String code, String ip, String port) throws SQLException {
        if (connection == null || connection.isClosed())
            connection = getConnection();
        String sql = "update sy_deviceinfo set sy_di_ip = ?, sy_di_port = ? where sy_di_code = ?";
        PreparedStatement pst =  connection.prepareStatement(sql);
        pst.setString(1, ip);
        pst.setString(2, port);
        pst.setString(3, code);
        return pst.executeUpdate();

    }

    public static Timestamp getHeartTimeByCode(String code) throws SQLException {
        if (connection == null || connection.isClosed())
            connection = getConnection();
        String sql = "select max(sc_hbr_time) from  sc_heartbeatrecord where sy_di_code = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, code);
        ResultSet resultSet = pst.executeQuery();
        if (resultSet.next()) {
            return resultSet.getTimestamp(1);
        }
        return null;
    }

    public static void updateDeviceState(byte codeId, String code, int state) throws SQLException {
        if (connection == null || connection.isClosed())
            connection = getConnection();
        String sql = "";
        switch (codeId) {
            case (byte) 0x0B: // 锁状态信息
                sql = "update sl_lockinfo set sl_li_online = ? where sy_di_code = ?";
                break;
            case (byte) 0x0F:  //农业信息
                sql = "update se_sensorinfo set se_si_online = ? where sy_di_code = ?";
                break;
            case (byte) 0x12:  //停车场信息
                sql = "update st_carinfo set st_ci_online = ? where sy_di_code = ?";
                break;
        }
        PreparedStatement pst =  connection.prepareStatement(sql);
        pst.setInt(1, state);
        pst.setString(2, code);
        pst.executeUpdate();
        System.out.println(code + ":数据库更改状态" + state);
    }

    public static int getDeivceHeartNumByCode(String code) throws SQLException {
        int heartNum = 10;
        if (connection == null || connection.isClosed())
            connection = getConnection();
        String sql = "select sy_di_heartbeat_num  from sy_deviceinfo where sy_di_code = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, code);
        ResultSet resultSet = pst.executeQuery();
        if (resultSet.next())
            heartNum = resultSet.getInt(1);
        return heartNum;
    }

    public static int updateLock(String code, byte[] src) throws SQLException {
        int contentLen = (src[21] & 0xFF) + 31;
        System.out.println(src.length + ";" + contentLen);
        if (src.length == ((src[21] & 0xFF) + 31)) {
            System.out.println("智能锁回复");
            if (src[0] == (byte) '*' && src[1] == (byte) 0x5A && src[src.length - 1] == (byte) '#') {
                if (connection == null || connection.isClosed())
                    connection = getConnection();
                if (src[2] == (byte) 0x7B) {
                    int state = -1;
                    String stateValue = "";
                    switch (src[20]) {
                        case (byte) 0x42: //施封
                            if (src[24] == 0x01) {
                                state = 0;
                                stateValue = "施封";
                            }
                            break;
                        case (byte) 0x44: //解封
                            if (src[24] == 0x01) {
                                stateValue = "解封";
                                state = 1;
                            }
                            break;
                        case (byte) 0x46: //验封
                            if (src[24] == 0x01) {
                                state = 2;
                                stateValue = "验封";
                            }
                            break;
                        case (byte) 0x48: //解除报警
                            switch (src[22]) {
                                case 0x01:
                                    state = 3;
                                    stateValue = "解除报警";
                                    break;
                                case 0x02:
                                    break;
                            }
                            break;
                    }
                    if (state != -1) {
                        byte[] fromCode = new byte[4];
                        byte[] time = new byte[7];
                        //20 17 09 30 12 14 25
                        switch (state) {
                            case 0:
                            case 1:
                            case 2:
                                System.arraycopy(src, 25, time, 0, 7);
                                break;
                            case 3:
                                System.arraycopy(src, 24, time, 0, 7);
                                break;
                        }
                        System.arraycopy(src, 16, fromCode, 0, 4);
                        String strFromCode = DataFormat.bytes2HexString(fromCode).toUpperCase(); //来自平台ID 吗

                        if (!strFromCode.substring(0, 2).equals("13")) {
                            strFromCode = "平台操作";
                        } else {
                            strFromCode = "PDA操作：" + strFromCode;
                        }
                        String strTime = DataFormat.bytes2HexString(time);
                        String timeValue = String.format("%s-%s-%s %s:%s:%s", strTime.substring(0, 4), strTime.substring(4, 6),
                                strTime.substring(6, 8), strTime.substring(8, 10), strTime.substring(10, 12), strTime.substring(12, 14));  //截取时间数值
                        System.out.println("timeValue:" + timeValue);
                        double V = DataFormat.getV(src[23] );
                        String sql = "update sl_lockinfo set sl_li_status = ?,sl_li_electric = ?,sl_li_time = ? where  sy_di_code = ?";
                        PreparedStatement pstLockInfo =  connection.prepareStatement(sql);
                        pstLockInfo.setInt(1, state);
                        pstLockInfo.setDouble(2, V);
                        pstLockInfo.setTimestamp(3, Timestamp.valueOf(timeValue));
                        pstLockInfo.setString(4, code);
                        if (pstLockInfo.executeUpdate() > 0) {
                            String sqlInsert = "insert into sl_controlrecord(sl_cr_time,sy_di_code,sl_cr_action,sl_cr_electric,sl_cr_cont_code) values(?,?,?,?,?)";
                            PreparedStatement pst =  connection.prepareStatement(sqlInsert);
                            pst.setTimestamp(1, Timestamp.valueOf(timeValue));
                            pst.setString(2, code);
                            pst.setString(3, stateValue);
                            pst.setDouble(4, V);
                            pst.setString(5, strFromCode);
                            return pst.executeUpdate();
                        }
                    }
                }
            }
        }
        return 0;
    }


}
