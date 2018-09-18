package hlhj.fhp.special.customview

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import hlhj.fhp.special.R

/**
 * Created by Administrator on 2018\6\14 0014.
 */
class LoadingDialog(context: Context) :Dialog(context, R.style.CustomDialog){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loadingdialog)
    }
}