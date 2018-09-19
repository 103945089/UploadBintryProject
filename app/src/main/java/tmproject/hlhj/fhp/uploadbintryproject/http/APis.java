package tmproject.hlhj.fhp.uploadbintryproject.http;

import android.content.Context;

import com.example.mymvp.okrx.BaseBean;
import com.example.mymvp.okrx.OkUtils;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import io.reactivex.Observable;

/**
 * Created by Never Fear   on 2018\9\19 0019.
 * Never More....
 */

public class APis {
   static int POST=2;
    static  int GET=1;
    public static Observable<Response<BaseBean>> sendSMS(Context context, String p){
        //构造参数表
        HttpParams params = new HttpParams();
        params.put("phone",p);
        return OkUtils.INSTANCE.<BaseBean>createObservable(params,Urls.SEND_SMS,POST);
    }
}
