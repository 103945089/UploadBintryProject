package tmproject.hlhj.fhp.uploadbintryproject.presenter;

import android.content.Context;

import com.example.mymvp.mvp.BasePresenter;
import com.example.mymvp.okrx.BaseBean;

import tmproject.hlhj.fhp.uploadbintryproject.iview.ISendSms;
import tmproject.hlhj.fhp.uploadbintryproject.module.SmsModule;

/**
 * Created by Never Fear   on 2018\9\19 0019.
 * Never More....
 */

public class SmsPresenter extends BasePresenter<SmsModule,ISendSms> implements SmsModule.OnSMSRespListener {
    @Override
    public void createModule() {
        module=new SmsModule();
        module.listener=this;
    }
    @Override
    public void onSMSResp(BaseBean baseBean) {
        getView().sendSmsResp(baseBean);
    }

    public void sendSMS(Context context,String p){
        module.sendSMS(context, p);
    }
}
