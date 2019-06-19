package com.example.lovestou.bean;

import java.util.List;

public class EducationBean {


    /**
     * code : success
     * items : [{"href":"/thread/1348-13375339.html","img":"http://img1.st001.com/files/2019/5/29/170_120_2019052920111834223.jpg","title":"【e京优选】梅州雁山湖国际花园度假区：赏风景，玩八大游乐项目","addTime":"2019-05-29","reply":"6","clicks":"10831","pdClass":"旅游","iColor":"blue","isHot":"no"},{"href":"/thread/1066-11567778.html","img":"http://img1.st001.com/files/2019/6/4/170_120_2019060415330937866.jpg","title":"网友爆料：一辆小车撞上东厦和天山路交界的安全岛，车头被架空","addTime":"2019-06-04","reply":"2","clicks":"3549","pdClass":"视点","iColor":"green","isHot":"no"},{"href":"/thread/1066-11567761.html","img":"http://img1.st001.com/files/2019/6/4/170_120_2019060414180395116.jpg","title":"网传超豪华跑车现身汕头车管所？！它有多壕，仅车身就2900万？","addTime":"2019-06-04","reply":"2","clicks":"3455","pdClass":"视点","iColor":"red","isHot":"no"},{"href":"/thread/1066-11567759.html","img":"http://img1.st001.com/files/2019/6/4/170_120_2019060411391743458.jpg","title":"天呐！工资低于3760元的潮汕人，这个夏天，你准备怎么生活下去","addTime":"2019-06-04","reply":"1","clicks":"2476","pdClass":"情感","iColor":"green","isHot":"no"},{"href":"/thread/1490-11567750.html","img":"http://img1.st001.com/files/2019/6/4/170_120_2019060410382456544.jpg","title":"心真大！潮汕某地有家长竟让学生在马路中间下车，在车流中逆行","addTime":"2019-06-04","reply":"2","clicks":"2312","pdClass":"视点","iColor":"blue","isHot":"no"},{"href":"/thread/1066-11567748.html","img":"http://img1.st001.com/files/2019/6/4/170_120_2019060410174730865.jpg","title":"5G时代来了！汕头已建有20个5G基站，你们准备好迎接5G了吗？","addTime":"2019-06-04","reply":"2","clicks":"3799","pdClass":"视点","iColor":"green","isHot":"no"},{"href":"/thread/1099-13724109.html","img":"http://img1.st001.com/files/2019/6/4/170_120_2019060410051541769.jpg","title":"星座也有专属潮汕美食？万万没想到处女座居然是糖葱薄饼，准吗","addTime":"2019-06-04","reply":"2","clicks":"1770","pdClass":"美食","iColor":"blue","isHot":"no"},{"href":"/thread/1490-11567745.html","img":"http://img1.st001.com/files/2019/6/4/170_120_2019060409551176472.jpg","title":"潮汕微信群热传一男子被绑在电线杆上打，传因偷抱小孩！真相是","addTime":"2019-06-04","reply":"2","clicks":"2353","pdClass":"视点","iColor":"green","isHot":"no"},{"href":"/thread/1066-11567740.html","img":"http://img1.st001.com/files/2019/6/4/170_120_2019060408263147951.jpg","title":"汕头：丈夫因为违法被拘留，妻子却给交警送了锦旗？原来是因为","addTime":"2019-06-04","reply":"4","clicks":"4198","pdClass":"视点","iColor":"red","isHot":"no"},{"href":"/thread/1541-13976870.html","img":"http://img1.st001.com/files/2019/6/3/170_120_2019060311055740092.jpg","title":"潮汕人物 | 龙实学子：初中的我们，登上世锦赛！年少有为，骄傲","addTime":"2019-06-03","reply":"2","clicks":"1934","pdClass":"视点","iColor":"green","isHot":"no"}]
     * top3 : [{"href":"/thread/1106-11567657.html","img":"http://img1.st001.com/files/2019/6/1/170_120_2019060110242870471.jpg","title":"最新：樟林古港广场乱摆乱卖的摊位已被清理","addTime":"2019-06-01","reply":"3","clicks":"6970","pdClass":"视点","iColor":"green","isHot":"yes"},{"href":"/thread/1066-11567741.html","img":"http://img1.st001.com/files/2019/6/4/170_120_2019060408455395076.jpg","title":"高考期间，汕头交警将严查考点附近违规停车","addTime":"2019-06-04","reply":"4","clicks":"3355","pdClass":"视点","iColor":"green","isHot":"yes"},{"href":"/thread/1066-11567699.html","img":"http://img1.st001.com/files/2019/6/3/170_120_2019060308211472392.jpg","title":"万达广场周围超堵！万达广场公交站点将迁移","addTime":"2019-06-03","reply":"6","clicks":"9581","pdClass":"视点","iColor":"green","isHot":"yes"}]
     */

    private String code;
    private List<ItemsBean> items;
    private List<Top3Bean> top3;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public List<Top3Bean> getTop3() {
        return top3;
    }

    public void setTop3(List<Top3Bean> top3) {
        this.top3 = top3;
    }

    public static class ItemsBean {
        /**
         * href : /thread/1348-13375339.html
         * img : http://img1.st001.com/files/2019/5/29/170_120_2019052920111834223.jpg
         * title : 【e京优选】梅州雁山湖国际花园度假区：赏风景，玩八大游乐项目
         * addTime : 2019-05-29
         * reply : 6
         * clicks : 10831
         * pdClass : 旅游
         * iColor : blue
         * isHot : no
         */

        private String href;
        private String img;
        private String title;
        private String addTime;
        private String reply;
        private String clicks;
        private String pdClass;
        private String iColor;
        private String isHot;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }

        public String getClicks() {
            return clicks;
        }

        public void setClicks(String clicks) {
            this.clicks = clicks;
        }

        public String getPdClass() {
            return pdClass;
        }

        public void setPdClass(String pdClass) {
            this.pdClass = pdClass;
        }

        public String getIColor() {
            return iColor;
        }

        public void setIColor(String iColor) {
            this.iColor = iColor;
        }

        public String getIsHot() {
            return isHot;
        }

        public void setIsHot(String isHot) {
            this.isHot = isHot;
        }
    }

    public static class Top3Bean {
        /**
         * href : /thread/1106-11567657.html
         * img : http://img1.st001.com/files/2019/6/1/170_120_2019060110242870471.jpg
         * title : 最新：樟林古港广场乱摆乱卖的摊位已被清理
         * addTime : 2019-06-01
         * reply : 3
         * clicks : 6970
         * pdClass : 视点
         * iColor : green
         * isHot : yes
         */

        private String href;
        private String img;
        private String title;
        private String addTime;
        private String reply;
        private String clicks;
        private String pdClass;
        private String iColor;
        private String isHot;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }

        public String getClicks() {
            return clicks;
        }

        public void setClicks(String clicks) {
            this.clicks = clicks;
        }

        public String getPdClass() {
            return pdClass;
        }

        public void setPdClass(String pdClass) {
            this.pdClass = pdClass;
        }

        public String getIColor() {
            return iColor;
        }

        public void setIColor(String iColor) {
            this.iColor = iColor;
        }

        public String getIsHot() {
            return isHot;
        }

        public void setIsHot(String isHot) {
            this.isHot = isHot;
        }
    }
}
