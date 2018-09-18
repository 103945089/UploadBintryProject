package hlhj.fhp.special.javabean;

import java.util.List;

/**
 * Created by Administrator on 2018\6\14 0014.
 */

public class SpecialListBean {

    /**
     * code : 1
     * msg : 获取成功!
     * data : [{"id":1,"subject_name":"这是一个专题","subject_thumb":"/application/hlhjnews/source/upload/20180515/1526356150732.png"}]
     * domain : http://192.168.0.117
     */

    private int code;
    private String msg;
    private String domain;
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * subject_name : 这是一个专题
         * subject_thumb : /application/hlhjnews/source/upload/20180515/1526356150732.png
         */

        private int id;
        private String subject_name;
        private String subject_thumb;
        private String create_at;

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSubject_name() {
            return subject_name;
        }

        public void setSubject_name(String subject_name) {
            this.subject_name = subject_name;
        }

        public String getSubject_thumb() {
            return subject_thumb;
        }

        public void setSubject_thumb(String subject_thumb) {
            this.subject_thumb = subject_thumb;
        }
    }
}
