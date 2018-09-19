package tmproject.hlhj.fhp.uploadbintryproject.http;

import android.content.Context;

import com.example.mymvp.okrx.LoadingConsum;

/**
 * Created by Never Fear   on 2018\9\19 0019.
 * Never More....
 */

public class LoadingAciton extends LoadingConsum {
    public LoadingAciton(Context c) {
        super(c);
    }

    @Override
    public void doBeforeHttp(Context context) {
        //todo 你要做的全局的请求前操作
    }
}
