package com.example.lovestou.bean;

public class IpBean {

    /**
     * status : 0
     * msg : ok
     * result : {"ip":"61.142.174.196","area":"广东 佛山","type":"电信","country":"中国","province":"广东","city":"佛山","town":null}
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
         * ip : 61.142.174.196
         * area : 广东 佛山
         * type : 电信
         * country : 中国
         * province : 广东
         * city : 佛山
         * town : null
         */

        private String ip;
        private String area;
        private String type;
        private String country;
        private String province;
        private String city;
        private Object town;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

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

        public Object getTown() {
            return town;
        }

        public void setTown(Object town) {
            this.town = town;
        }
    }
}
