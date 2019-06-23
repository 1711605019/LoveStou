package com.example.lovestou.bean;

public class OilBean {
    /**
     * status : 0
     * msg : ok
     * result : {"province":"广东","oil89":"7.43","oil92":"6.77","oil95":"7.34","oil98":"8.34","oil0":"6.40","oil90":"7.43","oil93":"6.77","oil97":"7.34","updatetime":"2019-06-23 00:00:00"}
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
         * province : 广东
         * oil89 : 7.43
         * oil92 : 6.77
         * oil95 : 7.34
         * oil98 : 8.34
         * oil0 : 6.40
         * oil90 : 7.43
         * oil93 : 6.77
         * oil97 : 7.34
         * updatetime : 2019-06-23 00:00:00
         */

        private String province;
        private String oil89;
        private String oil92;
        private String oil95;
        private String oil98;
        private String oil0;
        private String oil90;
        private String oil93;
        private String oil97;
        private String updatetime;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getOil89() {
            return oil89;
        }

        public void setOil89(String oil89) {
            this.oil89 = oil89;
        }

        public String getOil92() {
            return oil92;
        }

        public void setOil92(String oil92) {
            this.oil92 = oil92;
        }

        public String getOil95() {
            return oil95;
        }

        public void setOil95(String oil95) {
            this.oil95 = oil95;
        }

        public String getOil98() {
            return oil98;
        }

        public void setOil98(String oil98) {
            this.oil98 = oil98;
        }

        public String getOil0() {
            return oil0;
        }

        public void setOil0(String oil0) {
            this.oil0 = oil0;
        }

        public String getOil90() {
            return oil90;
        }

        public void setOil90(String oil90) {
            this.oil90 = oil90;
        }

        public String getOil93() {
            return oil93;
        }

        public void setOil93(String oil93) {
            this.oil93 = oil93;
        }

        public String getOil97() {
            return oil97;
        }

        public void setOil97(String oil97) {
            this.oil97 = oil97;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }
    }
}
