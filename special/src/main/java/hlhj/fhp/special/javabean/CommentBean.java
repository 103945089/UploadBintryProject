package hlhj.fhp.special.javabean;

import java.util.List;

/**
 * Created by Administrator on 2018\6\14 0014.
 */

public class CommentBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : [{"user_id":38,"content":"这是一篇好文章","create_at":1526539443,"time_ago":"33分钟前","head_pic":"uploadsportalluodong20180104318acc067a5c3c218e35f4e35d619530.jpg","real_name":"骆东"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_id : 38
         * content : 这是一篇好文章
         * create_at : 1526539443
         * time_ago : 33分钟前
         * head_pic : uploadsportalluodong20180104318acc067a5c3c218e35f4e35d619530.jpg
         * real_name : 骆东
         */
        private int id;
        private int user_id;
        private String content;
        private String create_at;
        private String time_ago;
        private String head_pic;
        private int laud_num;
        private String real_name;
        private String member_nickname;
        private String member_name;
        private int is_laud;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLaud_num() {
            return laud_num;
        }

        public void setLaud_num(int laud_num) {
            this.laud_num = laud_num;
        }

        public int getIs_laud() {
            return is_laud;
        }

        public void setIs_laud(int is_laud) {
            this.is_laud = is_laud;
        }

        public String getMember_nickname() {
            return member_nickname;
        }

        public void setMember_nickname(String member_nickname) {
            this.member_nickname = member_nickname;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }

        public String getTime_ago() {
            return time_ago;
        }

        public void setTime_ago(String time_ago) {
            this.time_ago = time_ago;
        }

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }
    }
}
