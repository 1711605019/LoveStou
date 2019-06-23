package com.example.lovestou.bean;

import java.util.List;

public class CalendarBean {

    /**
     * status : 0
     * msg : ok
     * result : {"year":"2015","month":"5","day":"2","yangli":"公元2015年05月02日","nongli":"农历二〇一五年三月十四","star":"金牛座","taishen":"房床厕外正南","wuxing":"城头土","chong":"冲（壬申）猴","sha":"煞北","shengxiao":"羊","jiri":"司命（黄道）开日","zhiri":"司命（黄道吉日）","xiongshen":"厌对 招摇 血忌 复日","jishenyiqu":"天赦 阳德 驿马 王日 天后 时阳 生气 六仪 续世 五合 司命","caishen":"正北","xishen":"东南","fushen":"正北","suici":["乙未年","庚辰月","戊寅日"],"yi":["纳采","嫁娶","出行","开市","立券","纳畜","牧养","出火","移徙","入宅"],"ji":["祈福","动土","破土","安葬","入殓"],"eweek":"SATURDAY","emonth":"May","week":"六"}
     */

    private int status;
    private String msg;
    private ResultBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * year : 2015
         * month : 5
         * day : 2
         * yangli : 公元2015年05月02日
         * nongli : 农历二〇一五年三月十四
         * star : 金牛座
         * taishen : 房床厕外正南
         * wuxing : 城头土
         * chong : 冲（壬申）猴
         * sha : 煞北
         * shengxiao : 羊
         * jiri : 司命（黄道）开日
         * zhiri : 司命（黄道吉日）
         * xiongshen : 厌对 招摇 血忌 复日
         * jishenyiqu : 天赦 阳德 驿马 王日 天后 时阳 生气 六仪 续世 五合 司命
         * caishen : 正北
         * xishen : 东南
         * fushen : 正北
         * suici : ["乙未年","庚辰月","戊寅日"]
         * yi : ["纳采","嫁娶","出行","开市","立券","纳畜","牧养","出火","移徙","入宅"]
         * ji : ["祈福","动土","破土","安葬","入殓"]
         * eweek : SATURDAY
         * emonth : May
         * week : 六
         */

        private String year;
        private String month;
        private String day;
        private String yangli;
        private String nongli;
        private String star;
        private String taishen;
        private String wuxing;
        private String chong;
        private String sha;
        private String shengxiao;
        private String jiri;
        private String zhiri;
        private String xiongshen;
        private String jishenyiqu;
        private String caishen;
        private String xishen;
        private String fushen;
        private String eweek;
        private String emonth;
        private String week;
        private List<String> suici;
        private List<String> yi;
        private List<String> ji;

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getYangli() {
            return yangli;
        }

        public void setYangli(String yangli) {
            this.yangli = yangli;
        }

        public String getNongli() {
            return nongli;
        }

        public void setNongli(String nongli) {
            this.nongli = nongli;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getTaishen() {
            return taishen;
        }

        public void setTaishen(String taishen) {
            this.taishen = taishen;
        }

        public String getWuxing() {
            return wuxing;
        }

        public void setWuxing(String wuxing) {
            this.wuxing = wuxing;
        }

        public String getChong() {
            return chong;
        }

        public void setChong(String chong) {
            this.chong = chong;
        }

        public String getSha() {
            return sha;
        }

        public void setSha(String sha) {
            this.sha = sha;
        }

        public String getShengxiao() {
            return shengxiao;
        }

        public void setShengxiao(String shengxiao) {
            this.shengxiao = shengxiao;
        }

        public String getJiri() {
            return jiri;
        }

        public void setJiri(String jiri) {
            this.jiri = jiri;
        }

        public String getZhiri() {
            return zhiri;
        }

        public void setZhiri(String zhiri) {
            this.zhiri = zhiri;
        }

        public String getXiongshen() {
            return xiongshen;
        }

        public void setXiongshen(String xiongshen) {
            this.xiongshen = xiongshen;
        }

        public String getJishenyiqu() {
            return jishenyiqu;
        }

        public void setJishenyiqu(String jishenyiqu) {
            this.jishenyiqu = jishenyiqu;
        }

        public String getCaishen() {
            return caishen;
        }

        public void setCaishen(String caishen) {
            this.caishen = caishen;
        }

        public String getXishen() {
            return xishen;
        }

        public void setXishen(String xishen) {
            this.xishen = xishen;
        }

        public String getFushen() {
            return fushen;
        }

        public void setFushen(String fushen) {
            this.fushen = fushen;
        }

        public String getEweek() {
            return eweek;
        }

        public void setEweek(String eweek) {
            this.eweek = eweek;
        }

        public String getEmonth() {
            return emonth;
        }

        public void setEmonth(String emonth) {
            this.emonth = emonth;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public List<String> getSuici() {
            return suici;
        }

        public void setSuici(List<String> suici) {
            this.suici = suici;
        }

        public List<String> getYi() {
            return yi;
        }

        public void setYi(List<String> yi) {
            this.yi = yi;
        }

        public List<String> getJi() {
            return ji;
        }

        public void setJi(List<String> ji) {
            this.ji = ji;
        }
    }
}
