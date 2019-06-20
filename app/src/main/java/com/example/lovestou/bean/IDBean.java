package com.example.lovestou.bean;

public class IDBean {
    /**
     * status : 0
     * msg : ok
     * result : {"province":"河南","city":"周口","town":"鹿邑县","area":"河南省 周口市 鹿邑县","lastflag":0,"sex":"男","birth":"1980年01月02日"}
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
         * province : 河南
         * city : 周口
         * town : 鹿邑县
         * area : 河南省 周口市 鹿邑县
         * lastflag : 0
         * sex : 男
         * birth : 1980年01月02日
         */

        private String province;
        private String city;
        private String town;
        private String area;
        private int lastflag;
        private String sex;
        private String birth;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getTown() {
            return town;
        }

        public void setTown(String town) {
            this.town = town;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getLastflag() {
            return lastflag;
        }

        public void setLastflag(int lastflag) {
            this.lastflag = lastflag;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }
    }
}
