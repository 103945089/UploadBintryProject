package hlhj.fhp.special.network

import android.content.Context
import hlhj.fhp.special.BaseAty
import rx.functions.Action0

/**
 * Created by Administrator on 2018\6\21 0021.
 */
class LoadingAction(private val context:Context) :Action0 {
    override fun call() {
        if (context!=null){
            val baseAty = context as BaseAty
            baseAty.loadingDialog.show()
        }
    }

}