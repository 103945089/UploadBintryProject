package hlhj.fhp.special.javabean;

import java.util.List;

/**
 * Created by Administrator on 2018\6\14 0014.
 */

public class SpecialDetailBean {

    /**
     * code : 1
     * msg : 获取成功!
     * data : {"subject_thumb":"/application/hlhjnews/source/upload/20180515/1526356150732.png","article":[{"id":1,"title":"这个是一个很神奇的标题","author":"齐白石","browse":0,"create_at":1526537404,"thumb":"/application/hlhjnews/source/upload/20180515/1526354464158.png","subject_id":1,"time_ago":"21分钟前"}]}
     * domain : http://192.168.0.117
     */

    private int code;
    private String msg;
    private DataBean data;
    private String domain;

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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public static class DataBean {
        /**
         * subject_thumb : /application/hlhjnews/source/upload/20180515/1526356150732.png
         * article : [{"id":1,"title":"这个是一个很神奇的标题","author":"齐白石","browse":0,"create_at":1526537404,"thumb":"/application/hlhjnews/source/upload/20180515/1526354464158.png","subject_id":1,"time_ago":"21分钟前"}]
         */

        private String subject_thumb;
        private List<ArticleBean> article;

        public String getSubject_thumb() {
            return subject_thumb;
        }

        public void setSubject_thumb(String subject_thumb) {
            this.subject_thumb = subject_thumb;
        }

        public List<ArticleBean> getArticle() {
            return article;
        }

        public void setArticle(List<ArticleBean> article) {
            this.article = article;
        }

        public static class ArticleBean {
            /**
             * id : 1
             * title : 这个是一个很神奇的标题
             * author : 齐白石
             * browse : 0
             * create_at : 1526537404
             * thumb : /application/hlhjnews/source/upload/20180515/1526354464158.png
             * subject_id : 1
             * time_ago : 21分钟前
             */

            private int id;
            private String title;
            private String author;
            private int browse;
            private String create_at;
            private String thumb;
            private int subject_id;
            private String time_ago;

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

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public int getSubject_id() {
                return subject_id;
            }

            public void setSubject_id(int subject_id) {
                this.subject_id = subject_id;
            }

            public String getTime_ago() {
                return time_ago;
            }

            public void setTime_ago(String time_ago) {
                this.time_ago = time_ago;
            }
        }
    }
}
