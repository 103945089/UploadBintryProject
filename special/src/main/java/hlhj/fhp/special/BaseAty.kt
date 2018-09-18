package hlhj.fhp.special

import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.tenma.ventures.base.TMActivity
import com.tenma.ventures.bean.utils.TMSharedPUtil
import hlhj.fhp.special.customview.LoadingDialog

/**
 * Created by Administrator on 2018\6\12 0012.
 */
abstract class BaseAty :TMActivity() {
    private lateinit var loBack:LinearLayout
    open lateinit var loadingDialog:LoadingDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentId())
        loadingDialog=LoadingDialog(this)
        setTittle()

        initView()
        dispatchData()
        initListener()

    }

    private fun setTittle() {
        loBack = findViewById<LinearLayout>(R.id.loBack)
        val tvTittle = findViewById<TextView>(R.id.tvTittle)
        val btExit = findViewById<ImageView>(R.id.btExit)
        if (loBack != null) {
            if (TMSharedPUtil.getTMThemeColor(this).isNotEmpty()) {
                loBack.setBackgroundColor(Color.parseColor(TMSharedPUtil.getTMThemeColor(this)))
            }
        }
        if (tvTittle!=null){
            tvTittle.text=getTittle()
        }
        if (btExit!=null){
            btExit.setOnClickListener {
                finish()
            }
        }
    }

    abstract fun getTittle():String
    abstract fun initListener()

    abstract fun initView()
    abstract fun dispatchData()

    abstract fun getContentId():Int
}