package hlhj.fhp.special.javabean;

/**
 * Created by Never Fear   on 2018\7\20 0020.
 * Never More....
 */

public class CollectBean {

    /**
     * code : 200
     * data : {"star_id":1}
     * msg : 收藏成功
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * star_id : 1
         */

        private int star_id;

        public int getStar_id() {
            return star_id;
        }

        public void setStar_id(int star_id) {
            this.star_id = star_id;
        }
    }
}
