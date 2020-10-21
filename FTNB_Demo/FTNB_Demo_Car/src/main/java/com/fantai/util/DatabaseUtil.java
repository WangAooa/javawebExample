package com.fantai.util;

import java.sql.*;
import java.text.SimpleDateFormat;

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

    public static int getCarInfoState(String code, String cardInfo) throws SQLException {
        if (connection == null || connection.isClosed())
            connection = getConnection();
        String sql = "select st_ci_status from st_carinfo where sy_di_code = ? and st_ci_nfc = ?";
        PreparedStatement pst =  connection.prepareStatement(sql);
        pst.setString(1, code);
        pst.setString(2, cardInfo);
        ResultSet resultSet = pst.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    public static int getCarInfoMoney(String code, String cardInfo) throws SQLException {
        if (connection == null || connection.isClosed())
            connection = getConnection();
        String sql = "select st_ci_money from st_carinfo where sy_di_code = ? and st_ci_nfc = ?";
        PreparedStatement pst =  connection.prepareStatement(sql);
        pst.setString(1, code);
        pst.setString(2, cardInfo);
        ResultSet resultSet = pst.executeQuery();
        if (resultSet.next())
            return (int) resultSet.getDouble(1);
        return 0;
    }

    public static int updateCarInfoEle(int state, double ele, String code, String carInfo) throws SQLException {
        if (connection == null || connection.isClosed())
            connection = getConnection();
        String sql = "update st_carinfo set st_ci_status = ?, st_ci_electric = ?  where sy_di_code = ? and st_ci_nfc = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, state);
        pst.setDouble(2, ele);
        pst.setString(3, code);
        pst.setString(4, carInfo);
        return pst.executeUpdate();
    }

    public static int updateCarInfoMonEle(int state, double money, double ele, String code, String carInfo) throws SQLException {
        if (connection == null || connection.isClosed())
            connection = getConnection();
        String sql = "UPDATE st_carinfo set st_ci_status = ? , st_ci_money = ?,st_ci_electric = ?  where sy_di_code = ? and st_ci_nfc = ?";
        PreparedStatement pst =  connection.prepareStatement(sql);
        pst.setInt(1, state);
        pst.setDouble(2, money);
        pst.setDouble(3, ele);
        pst.setString(4, code);
        pst.setString(5, carInfo);
        return pst.executeUpdate();
    }

    public static String getIcStartTime(String code) throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = getConnection();
        }
        int id = selectICId(code);  //查询id号码
        String sql = "select st_pr_stime from st_parkrecord where st_pr_id = ?";
        PreparedStatement pst =  connection.prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet resultSet = pst.executeQuery();
        if (resultSet.next()) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            return df.format(resultSet.getTimestamp(1));
        }
        return "";
    }

    public static int selectICId(String code) throws SQLException {
        if (connection == null || connection.isClosed())
            connection = getConnection();
        String carNum = getCarNum(code);
        String sql = "select max(st_pr_id) from st_parkrecord where st_ci_carnum = ?";
        PreparedStatement pst =  connection.prepareStatement(sql);
        pst.setString(1, carNum);
        ResultSet resultSet = pst.executeQuery();
        if (resultSet.next())
            return resultSet.getInt(1);
        return 0;
    }

    public static String getCarNum(String code) throws SQLException {
        if (connection == null || connection.isClosed())
            connection = getConnection();
        String sql = "select st_ci_carnum from st_carinfo where sy_di_code = ?";
        PreparedStatement pst =  connection.prepareStatement(sql);
        pst.setString(1, code);
        ResultSet resultSet = pst.executeQuery();
        if (resultSet.next())
            return resultSet.getString(1);
        return "";


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


}
