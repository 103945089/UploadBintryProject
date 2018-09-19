package tmproject.hlhj.fhp.uploadbintryproject.http;

import android.content.Context;

import com.example.mymvp.okrx.BaseAciton;
import com.example.mymvp.okrx.MyException;

import io.reactivex.disposables.Disposable;

/**
 * Created by Never Fear   on 2018\9\19 0019.
 * Never More....
 */

/**
 * 复写父类接口一些方法是因为kotlin和java的差异性，比如onNext和OnError，  可以无视
 * 可自己再封额外的抽象方法在M层调用的地方实例
 * @param <T>
 */
public abstract class BaseObserver<T> implements BaseAciton<T> {
    private Context context;
    public BaseObserver(Context c){
        this.context=c;
    }
    @Override
    public void httpFailed(Throwable throwable) {
        if (throwable instanceof MyException){
            MyException myException = (MyException) throwable;
            //todo 全局的做对应COde的对应操作
        }
    }

    @Override
    public void onNext(T t) {
        DefaultImpls.onNext(this,t);
    }

    @Override
    public void onError(Throwable throwable) {
        DefaultImpls.onError(this,throwable);
    }

    /**
     *
     */
    @Override
    public void onSubscribe(Disposable d) {

    }
    @Override
    public void onComplete() {
        //todo 全局处理http请求完成处理
    }

    @Override
    public void acheSuccess(T t) {

    }
}
