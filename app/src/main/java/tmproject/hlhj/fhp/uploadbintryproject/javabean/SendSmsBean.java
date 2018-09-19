package tmproject.hlhj.fhp.uploadbintryproject.javabean;

/**
 * Created by Never Fear   on 2018\9\19 0019.
 * Never More....
 */

public class SendSmsBean {

    /**
     * code : 200
     * msg : 成功
     * data : {"about_us":"<h1>\r\n\t<strong>关于我们！<\/strong> \r\n<\/h1>\r\n关于我们<br />"}
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
         * about_us : <h1>
         <strong>关于我们！</strong>
         </h1>
         关于我们<br />
         */

        private String about_us;

        public String getAbout_us() {
            return about_us;
        }

        public void setAbout_us(String about_us) {
            this.about_us = about_us;
        }
    }
}
