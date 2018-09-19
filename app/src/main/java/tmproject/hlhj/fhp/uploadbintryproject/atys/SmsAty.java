package tmproject.hlhj.fhp.uploadbintryproject.atys;

import android.view.View;
import android.widget.TextView;

import com.example.mymvp.okrx.BaseBean;

import org.jetbrains.annotations.NotNull;

import tmproject.hlhj.fhp.uploadbintryproject.BaseAty;
import tmproject.hlhj.fhp.uploadbintryproject.R;
import tmproject.hlhj.fhp.uploadbintryproject.iview.ISendSms;
import tmproject.hlhj.fhp.uploadbintryproject.presenter.SmsPresenter;

/**
 * Created by Never Fear   on 2018\9\19 0019.
 * Never More....
 */

public class SmsAty extends BaseAty<ISendSms,SmsPresenter> implements ISendSms {

    private TextView textView;

    @Override
    protected int getContentId() {
        return R.layout.aty_sms;
    }

    @NotNull
    @Override
    public SmsPresenter createPresenter() {
        return new SmsPresenter();
    }

    @NotNull
    @Override
    public ISendSms bindView() {
        return this;
    }


    @Override
    public void initView() {
        //todo 初始化视图
        textView = (TextView) findViewById(R.id.btC);
    }

    @Override
    public void setListener() {
        //todo 使能监听
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().sendSMS(SmsAty.this,"");
            }
        });
    }

    @Override
    public void logicStart() {
        //todo 业务开始
    }

    @Override
    public void sendSmsResp(BaseBean baseBean) {
        //todo 发送验证码回调
    }
}
