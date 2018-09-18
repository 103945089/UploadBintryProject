package hlhj.fhp.special.javabean;

import java.util.List;

/**
 * Created by Never Fear   on 2018\7\19 0019.
 * Never More....
 */

public class TMBaseBean {

    /**
     * code : 200
     * data : []
     * msg : 收藏成功
     */

    private int code;
    private String msg;
    private List<?> data;

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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
