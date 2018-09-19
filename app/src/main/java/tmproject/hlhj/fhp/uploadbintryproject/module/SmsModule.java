package tmproject.hlhj.fhp.uploadbintryproject.module;

import android.content.Context;

import com.example.mymvp.mvp.BaseModule;
import com.example.mymvp.okrx.BaseBean;
import com.lzy.okgo.model.Response;

import tmproject.hlhj.fhp.uploadbintryproject.http.APis;
import tmproject.hlhj.fhp.uploadbintryproject.http.BaseObserver;
import tmproject.hlhj.fhp.uploadbintryproject.http.LoadingAciton;

/**
 * Created by Never Fear   on 2018\9\19 0019.
 * Never More....
 */

public class SmsModule implements BaseModule {
    public OnSMSRespListener listener;

    public void sendSMS(Context context,String p){
        APis.sendSMS(context, p)
                .doOnSubscribe(new LoadingAciton(context))
                .subscribe(new BaseObserver<Response<BaseBean>>(context) {
                    @Override
                    public void httpSuccess(Response<BaseBean> baseBeanResponse) {
                        if (listener!=null){
                            listener.onSMSResp(baseBeanResponse.body());
                        }
                    }

                });
    }


    public interface OnSMSRespListener{
        void onSMSResp(BaseBean baseBean);
    }
}
