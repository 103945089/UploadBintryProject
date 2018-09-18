package hlhj.fhp.special.network

import android.util.Log
import com.google.gson.Gson
import com.lzy.okgo.model.Response
import hlhj.fhp.special.javabean.BaseBean
import hlhj.fhp.special.utils.ToastUtil
import rx.Observer

/**
 * Created by Administrator on 2018\6\21 0021.
 */
interface CustomAction<T> :Observer<T> {
    override fun onCompleted() {
    }

    override fun onNext(t: T) {
        val resp = t as Response<T>
        val json = Gson().toJson(resp.body())
        val baseBean = Gson().fromJson(json, BaseBean::class.java)
        if (baseBean.code!=null){
            if (baseBean.code==1){
                onResult(t)
            }else{
                ToastUtil.toast(baseBean.msg)
            }
        }
    }

    override fun onError(e: Throwable?) {
        ToastUtil.toast(e.toString())
    }
    fun onResult(t:T)
}