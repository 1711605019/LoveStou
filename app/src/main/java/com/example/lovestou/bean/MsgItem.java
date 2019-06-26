package com.example.lovestou.bean;

/**
 * Created by mummyding on 15-7-21.
 */
public class MsgItem {

    /**
     * status : 0
     * msg : ok
     * result : {"type":"无回复","content":"好吧，跟骨灰级大师聊天真心感觉压力好大呀，您老人家就不能来点简单点的么~","relquestion":null}
     */

    private String myself;

    public String getMyself() {
        return myself;
    }

    public void setMyself(String myself) {
        this.myself = myself;
    }

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
         * type : 无回复
         * content : 好吧，跟骨灰级大师聊天真心感觉压力好大呀，您老人家就不能来点简单点的么~
         * relquestion : null
         */

        private String type;
        private String content;
        private Object relquestion;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getRelquestion() {
            return relquestion;
        }

        public void setRelquestion(Object relquestion) {
            this.relquestion = relquestion;
        }
    }
}
