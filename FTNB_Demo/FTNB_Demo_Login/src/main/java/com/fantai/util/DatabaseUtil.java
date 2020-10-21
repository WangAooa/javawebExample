package com.fantai.util;

import java.sql.*;

public class DatabaseUtil {

    private static String dbDriver = "com.mysql.cj.jdbc.Driver";
    private static String dbUrl = "jdbc:mysql://127.0.0.1:3306/fantainb?serverTimezone=Asia/Shanghai&amp;autoReconnect=true&amp;autoReconnectForPools=true";// 数据库地址
    private static String dbUser = "root";// 用户名
    private static String dbPass = "047578"; // 用户密码
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
        String sql = "select  sy_di_heartbeat_num  from sy_deviceinfo where sy_di_code = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, code);
        ResultSet resultSet = pst.executeQuery();
        if (resultSet.next())
            heartNum = resultSet.getInt(1);
        return heartNum;
    }
    public static void updataGPS() throws  SQLException{
        System.out.println("enter gps");
        if (connection == null || connection.isClosed())
            connection = getConnection();
        String sql = "select * from sc_gpsrecord";
        PreparedStatement pst =  connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            System.out.println(rs.getInt("sc_gr_id"));
        }
    }

}
