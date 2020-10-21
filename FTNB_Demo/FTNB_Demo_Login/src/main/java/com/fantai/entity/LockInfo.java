package com.fantai.entity;

import java.sql.Timestamp;

public class LockInfo {

    private Integer sl_li_id;
    private String sy_di_code;
    private String sl_li_imei;
    private String sl_li_sim;
    private Double sl_li_electric;
    private Integer sl_li_status;
    private Integer sl_li_online;
    private Timestamp sl_li_time;

    public Integer getSl_li_id() {
        return sl_li_id;
    }
    public void setSl_li_id(Integer sl_li_id) {
        this.sl_li_id = sl_li_id;
    }

    public String getSy_di_code() {
        return sy_di_code;
    }
    public void setSy_di_code(String sy_di_code) {
        this.sy_di_code = sy_di_code;
    }

    public String getSl_li_imei() {
        return sl_li_imei;
    }
    public void setSl_li_imei(String sl_li_imei) {
        this.sl_li_imei = sl_li_imei;
    }

    public String getSl_li_sim() {
        return sl_li_sim;
    }
    public void setSl_li_sim(String sl_li_sim) {
        this.sl_li_sim = sl_li_sim;
    }

    public Double getSl_li_electric() {
        return sl_li_electric;
    }
    public void setSl_li_electric(Double sl_li_electric) {
        this.sl_li_electric = sl_li_electric;
    }

    public Integer getSl_li_status() {
        return sl_li_status;
    }
    public void setSl_li_status(Integer sl_li_status) {
        this.sl_li_status = sl_li_status;
    }

    public Integer getSl_li_online() {
        return sl_li_online;
    }
    public void setSl_li_online(Integer sl_li_online) {
        this.sl_li_online = sl_li_online;
    }

    public Timestamp getSl_li_time() {
        return sl_li_time;
    }
    public void setSl_li_time(Timestamp sl_li_time) {
        this.sl_li_time = sl_li_time;
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

    public Double getSy_di_gps_num() {
        return sy_di_gps_num;
    }
    public void setSy_di_gps_num(Double sy_di_gps_num) {
        this.sy_di_gps_num = sy_di_gps_num;
    }

    private Integer sy_di_heartbeat_num;

    public Integer getSy_di_heartbeat_num() {
        return sy_di_heartbeat_num;
    }
    public void setSy_di_heartbeat_num(Integer sy_di_heartbeat_num) {
        this.sy_di_heartbeat_num = sy_di_heartbeat_num;
    }

}
