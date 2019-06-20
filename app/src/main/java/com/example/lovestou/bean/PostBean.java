package com.example.lovestou.bean;

import java.util.List;

public class PostBean {

    /**
     * status : 0
     * msg : ok
     * result : [{"province":"广东","city":"汕头","town":"濠江区","address":"青蓝居委瞻前五巷","zipcode":"515071"},{"province":"广东","city":"汕头","town":"濠江区","address":"赤港居委布安巷","zipcode":"515071"},{"province":"广东","city":"汕头","town":"濠江区","address":"达濠西泽园","zipcode":"515071"},{"province":"广东","city":"汕头","town":"濠江区","address":"青蓝居委南福三巷","zipcode":"515071"},{"province":"广东","city":"汕头","town":"濠江区","address":"青蓝居委种德里后巷","zipcode":"515071"}]
     */

    private int status;
    private String msg;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * province : 广东
         * city : 汕头
         * town : 濠江区
         * address : 青蓝居委瞻前五巷
         * zipcode : 515071
         */

        private String province;
        private String city;
        private String town;
        private String address;
        private String zipcode;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }
    }
}
