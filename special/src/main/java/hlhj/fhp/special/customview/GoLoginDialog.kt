package hlhj.fhp.special.customview

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.tenma.ventures.bean.utils.TMSharedPUtil
import hlhj.fhp.special.R
import kotlinx.android.synthetic.main.go_logindialog.*

/**
 * Created by Administrator on 2018\5\18 0018.
 */
class GoLoginDialog(context: Context) : Dialog(context, R.style.CustomDialog) {
    private var listener:OnCancelListener?=null

    public fun setListener(listener:OnCancelListener){
        this.listener=listener
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.go_logindialog)
        btNo.setOnClickListener {
            if (listener==null) run {
                dismiss()
            }else{
                listener!!.cancle()
                dismiss()
            }
        }

        btYes.setOnClickListener {
//            TMSharedPUtil.saveTMToken(context,"ADCB12389F971DD3AD5860AAB7A5A2A9")

            val action = context.packageName + ".usercenter.login"
            val intent = Intent(action)
            context.startActivity(intent)
            dismiss()
        }



    }

    interface OnCancelListener{
        fun cancle()
    }
}