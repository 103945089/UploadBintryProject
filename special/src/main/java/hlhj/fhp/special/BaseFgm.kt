package hlhj.fhp.special

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tenma.ventures.base.TMFragment
import hlhj.fhp.special.customview.LoadingDialog

/**
 * Created by Administrator on 2018\6\12 0012.
 */
abstract class BaseFgm :TMFragment() {
    lateinit var rootView: View
    lateinit var loadingDialog:LoadingDialog
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView=inflater.inflate(getContentId(),null)
        loadingDialog=LoadingDialog(context!!)
        initView()
        initListener()
        return rootView
    }

    abstract fun initListener()

    abstract fun initView()

    abstract fun getContentId():Int

}