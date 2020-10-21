package com.fantai.entity;

import java.sql.Timestamp;

public class CarInfo {

    private Integer st_ci_id;
    private String sy_di_code;
    private String st_ci_nfc;
    private String st_ci_carnum;
    private String st_ci_imei;
    private String st_ci_sim;
    private Integer st_ci_status;
    private Double st_ci_money;
    private Integer st_ci_online;
    private Double st_ci_electric;

    public Integer getSt_ci_id() {
        return st_ci_id;
    }
    public void setSt_ci_id(Integer st_ci_id) {
        this.st_ci_id = st_ci_id;
    }

    public String getSy_di_code() {
        return sy_di_code;
    }
    public void setSy_di_code(String sy_di_code) {
        this.sy_di_code = sy_di_code;
    }

    public String getSt_ci_nfc() {
        return st_ci_nfc;
    }
    public void setSt_ci_nfc(String st_ci_nfc) {
        this.st_ci_nfc = st_ci_nfc;
    }

    public String getSt_ci_carnum() {
        return st_ci_carnum;
    }
    public void setSt_ci_carnum(String st_ci_carnum) {
        this.st_ci_carnum = st_ci_carnum;
    }

    public String getSt_ci_imei() {
        return st_ci_imei;
    }
    public void setSt_ci_imei(String st_ci_imei) {
        this.st_ci_imei = st_ci_imei;
    }

    public String getSt_ci_sim() {
        return st_ci_sim;
    }
    public void setSt_ci_sim(String st_ci_sim) {
        this.st_ci_sim = st_ci_sim;
    }

    public Integer getSt_ci_status() {
        return st_ci_status;
    }
    public void setSt_ci_status(Integer st_ci_status) {
        this.st_ci_status = st_ci_status;
    }

    public Double getSt_ci_money() {
        return st_ci_money;
    }
    public void setSt_ci_money(Double st_ci_money) {
        this.st_ci_money = st_ci_money;
    }

    public Integer getSt_ci_online() {
        return st_ci_online;
    }
    public void setSt_ci_online(Integer st_ci_online) {
        this.st_ci_online = st_ci_online;
    }

    public Double getSt_ci_electric() {
        return st_ci_electric;
    }
    public void setSt_ci_electric(Double st_ci_electric) {
        this.st_ci_electric = st_ci_electric;
    }

    private String sy_ui_id;
    private String sy_ui_name;
    private Integer sy_si_id;
    private String sy_si_name;

    public String getSy_ui_id() {
        return sy_ui_id;
    }
    public void setSy_ui_id(String sy_ui_id) {
        this.sy_ui_id = sy_ui_id;
    }

    public String getSy_ui_name() {
        return sy_ui_name;
    }
    public void setSy_ui_name(String sy_ui_name) {
        this.sy_ui_name = sy_ui_name;
    }

    public Integer getSy_si_id() {
        return sy_si_id;
    }
    public void setSy_si_id(Integer sy_si_id) {
        this.sy_si_id = sy_si_id;
    }

    public String getSy_si_name() {
        return sy_si_name;
    }
    public void setSy_si_name(String sy_si_name) {
        this.sy_si_name = sy_si_name;
    }

    private Double st_ci_blance;

    public Double getSt_ci_blance() {
        return st_ci_blance;
    }
    public void setSt_ci_blance(Double st_ci_blance) {
        this.st_ci_blance = st_ci_blance;
    }

    private Timestamp sy_di_reg_time;
    private Timestamp sy_di_con_time;
    private Integer sy_di_con_type;
    private String sy_di_ip;
    private String sy_di_port;
    private Double sy_di_gps_num;

    public Timestamp getSy_di_reg_time() {
        return sy_di_reg_time;
    }
    public void setSy_di_reg_time(Timestamp sy_di_reg_time) {
        this.sy_di_reg_time = sy_di_reg_time;
    }

    public Timestamp getSy_di_con_time() {
        return sy_di_con_time;
    }
    public void setSy_di_con_time(Timestamp sy_di_con_time) {
        this.sy_di_con_time = sy_di_con_time;
    }

    public Integer getSy_di_con_type() {
        return sy_di_con_type;
    }
    public void setSy_di_con_type(Integer sy_di_con_type) {
        this.sy_di_con_type = sy_di_con_type;
    }

    public String getSy_di_ip() {
        return sy_di_ip;
    }
    public void setSy_di_ip(String sy_di_ip) {
        this.sy_di_ip = sy_di_ip;
    }

    public String getSy_di_port() {
        return sy_di_port;
    }
    public void setSy_di_port(String sy_di_port) {
        this.sy_di_port = sy_di_port;
    }

    private Integer sy_di_heartbeat_num;

    public Integer getSy_di_heartbeat_num() {
        return sy_di_heartbeat_num;
    }
    public void setSy_di_heartbeat_num(Integer sy_di_heartbeat_num) {
        this.sy_di_heartbeat_num = sy_di_heartbeat_num;
    }

    public Double getSy_di_gps_num() {
        return sy_di_gps_num;
    }
    public void setSy_di_gps_num(Double sy_di_gps_num) {
        this.sy_di_gps_num = sy_di_gps_num;
    }

}
