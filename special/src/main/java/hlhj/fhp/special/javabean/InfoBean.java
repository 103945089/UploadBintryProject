package hlhj.fhp.special.javabean;

import java.util.List;

/**
 * Created by Administrator on 2018\6\14 0014.
 */

public class InfoBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"id":1,"title":"这个是一个很神奇的标题","content":"这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长","author":"齐白石","browse":0,"edit_person":"某某","approval_person":"某某某","create_at":1526537404,"subject_id":1,"comment":[{"user_id":38,"content":"这是一篇好文章","create_at":1526539443,"time_ago":"19分钟前","head_pic":"uploadsportalluodong20180104318acc067a5c3c218e35f4e35d619530.jpg","real_name":"骆东"}]}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * title : 这个是一个很神奇的标题
         * content : 这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长这个内容很长很长
         * author : 齐白石
         * browse : 0
         * edit_person : 某某
         * approval_person : 某某某
         * create_at : 1526537404
         * subject_id : 1
         * comment : [{"user_id":38,"content":"这是一篇好文章","create_at":1526539443,"time_ago":"19分钟前","head_pic":"uploadsportalluodong20180104318acc067a5c3c218e35f4e35d619530.jpg","real_name":"骆东"}]
         */

        private int id;
        private String title;
        private String content;
        private String author;
        private int browse;
        private String edit_person;
        private String approval_person;
        private String create_at;
        private int subject_id;
        private List<CommentBean> comment;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getBrowse() {
            return browse;
        }

        public void setBrowse(int browse) {
            this.browse = browse;
        }

        public String getEdit_person() {
            return edit_person;
        }

        public void setEdit_person(String edit_person) {
            this.edit_person = edit_person;
        }

        public String getApproval_person() {
            return approval_person;
        }

        public void setApproval_person(String approval_person) {
            this.approval_person = approval_person;
        }

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }

        public int getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(int subject_id) {
            this.subject_id = subject_id;
        }

        public List<CommentBean> getComment() {
            return comment;
        }

        public void setComment(List<CommentBean> comment) {
            this.comment = comment;
        }

        public static class CommentBean {
            /**
             * user_id : 38
             * content : 这是一篇好文章
             * create_at : 1526539443
             * time_ago : 19分钟前
             * head_pic : uploadsportalluodong20180104318acc067a5c3c218e35f4e35d619530.jpg
             * real_name : 骆东
             */
            private int id;
            private int user_id;
            private String content;
            private String create_at;
            private String time_ago;
            private String head_pic;
            private String real_name;
            private String member_name;
            private String member_nickname;
            private int is_laud;
            private int laud_num;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIs_laud() {
                return is_laud;
            }

            public void setIs_laud(int is_laud) {
                this.is_laud = is_laud;
            }

            public int getLaud_num() {
                return laud_num;
            }

            public void setLaud_num(int laud_num) {
                this.laud_num = laud_num;
            }

            public String getMember_name() {
                return member_name;
            }

            public void setMember_name(String member_name) {
                this.member_name = member_name;
            }

            public String getMember_nickname() {
                return member_nickname;
            }

            public void setMember_nickname(String member_nickname) {
                this.member_nickname = member_nickname;
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
}
