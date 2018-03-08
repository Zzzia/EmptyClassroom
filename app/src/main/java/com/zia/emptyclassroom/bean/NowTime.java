package com.zia.emptyclassroom.bean;

/**
 * Created by zia on 2018/3/7.
 */
public class NowTime {


    /**
     * status : 400
     * info : 请用post方法请求该页面,参数:week, day, start, end
     * week : 1
     * day : 4
     * lastUpdate : 2018-03-08 17:22:40
     * dataInfo : 正在更新数据
     * version : 1.0
     * apk : http://qiniu.zzzia.net/emptyClass_zzzia.apk
     */

    private int status = 400;
    private String info = "";
    private String week = "";
    private String day = "";
    private String lastUpdate = "";
    private String dataInfo = "";
    private String version = "";
    private String apk = "";

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getDataInfo() {
        return dataInfo;
    }

    public void setDataInfo(String dataInfo) {
        this.dataInfo = dataInfo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApk() {
        return apk;
    }

    public void setApk(String apk) {
        this.apk = apk;
    }
}
