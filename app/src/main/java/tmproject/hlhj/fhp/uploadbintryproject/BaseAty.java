package tmproject.hlhj.fhp.uploadbintryproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mymvp.mvp.BaseMvpAty;
import com.example.mymvp.mvp.BasePresenter;
import com.example.mymvp.mvp.BaseView;
import com.example.mymvp.mvp.Presenter;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Never Fear   on 2018\9\19 0019.
 * Never More....
 */

public abstract class BaseAty<V extends BaseView,P extends BasePresenter<?, V>> extends BaseMvpAty<V,P> {
    /**
     * 一般配置
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentId());
        getPresenter().initView();
        getPresenter().setListener();
        getPresenter().logicStart();
    }

    protected abstract int getContentId();
}
