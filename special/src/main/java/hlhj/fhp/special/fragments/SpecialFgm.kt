package hlhj.fhp.special.fragments

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liaoinstan.springview.container.DefaultFooter
import com.liaoinstan.springview.container.DefaultHeader
import com.liaoinstan.springview.widget.SpringView
import com.lzy.okgo.OkGo
import com.tenma.ventures.bean.TMUser
import com.tenma.ventures.bean.utils.TMSharedPUtil
import hlhj.fhp.special.BaseFgm
import hlhj.fhp.special.R
import hlhj.fhp.special.activitys.SpecialSpecialDetailAty
import hlhj.fhp.special.javabean.SpecialListBean
import hlhj.fhp.special.network.Urls
import hlhj.fhp.special.network.UrlsApi
import hlhj.fhp.special.utils.GlideUtls
import hlhj.fhp.special.utils.TimeUtil
import hlhj.fhp.special.utils.ToastUtil
import kotlinx.android.synthetic.main.fgm_special.view.*
import kotlinx.android.synthetic.main.list_lo.view.*
import java.util.ArrayList

/**
 * Created by Administrator on 2018\6\19 0019.
 */
class SpecialFgm :BaseFgm(), SpringView.OnFreshListener {
    override fun onLoadmore() {
        page++
        dispatchData()
    }

    override fun onRefresh() {
        page=1
        datas.clear()
        dispatchData()
    }

    private lateinit var adp : BaseQuickAdapter<SpecialListBean.DataBean, BaseViewHolder>
    private val datas= ArrayList<SpecialListBean.DataBean>()

    private var page=1
    override fun initListener() {
        adp.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(context, SpecialSpecialDetailAty::class.java)
            intent.putExtra("subject_id",datas[position].id)
            intent.putExtra("tittle",datas[position].subject_name)
            startActivity(intent)
        }
    }

    override fun initView() {
        /*初始化OKGO*/
        OkGo.getInstance().init(activity?.application)
/*//        模拟数据
        val tmUser = TMUser()
        tmUser.member_id=63
        TMSharedPUtil.saveTMUser(context,tmUser)
//        TMSharedPUtil.save
        TMSharedPUtil.saveTMToken(context,"9A7130C1935680CFC1F863A2B468E007")*/
        ToastUtil.init(context)

        /*列表使能*/
        adp=object :BaseQuickAdapter<SpecialListBean.DataBean,BaseViewHolder>(R.layout.fgm_special_listitem,datas){
            override fun convert(helper: BaseViewHolder?, item: SpecialListBean.DataBean?) {
                Glide.with(mContext).load(Urls.BaseUrl+item?.subject_thumb).apply(GlideUtls.loadImgOption())
                        .into(helper?.getView(R.id.itemImg)!!)
                helper?.setText(R.id.itemTittle,item?.subject_name)
                helper?.setText(R.id.itemTime,"${TimeUtil.timeStampToStr(item?.create_at!!.toLong())}更新")

            }
        }
        rootView.recyclerview.adapter=adp
        rootView.recyclerview.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        /*上下拉框架使能*/

        rootView.spView.header= DefaultHeader(context)
        rootView.spView.footer= DefaultFooter(context)
        rootView.spView.setListener(this)
        dispatchData()

    }
    fun dispatchData() {
        UrlsApi.getSpecialList(page)
                .doOnSubscribe {
                    loadingDialog.show()
                }
                .subscribe ({
                    data->
                    val bean = data.body()
                    if (bean.data.size==0){
                        Toast.makeText(context,"没有更多了",Toast.LENGTH_SHORT).show()
                    }
                    rootView.spView.onFinishFreshAndLoad()
                    Log.e("fhpaa","加载没有？")
                    loadingDialog.dismiss()
                    datas.addAll(bean.data)
                    adp.notifyDataSetChanged()
                },{
                    erro->
                    erro.printStackTrace()
                    Log.i("okgo",erro.toString())
                })
    }
    override fun getContentId(): Int {
        return R.layout.fgm_special
    }
}