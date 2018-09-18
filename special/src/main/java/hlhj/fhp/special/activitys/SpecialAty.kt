package hlhj.fhp.special.activitys

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liaoinstan.springview.container.DefaultFooter
import com.liaoinstan.springview.container.DefaultHeader
import com.liaoinstan.springview.widget.SpringView
import hlhj.fhp.special.BaseAty
import hlhj.fhp.special.R
import hlhj.fhp.special.javabean.SpecialListBean
import hlhj.fhp.special.network.Urls
import hlhj.fhp.special.utils.GlideUtls
import hlhj.fhp.special.utils.ToastUtil
import kotlinx.android.synthetic.main.aty_spcial.*
import kotlinx.android.synthetic.main.head_lo.*
import kotlinx.android.synthetic.main.list_lo.*
import java.util.ArrayList

/**
 * Created by Administrator on 2018\6\12 0012.
 */
class SpecialAty :BaseAty(), SpringView.OnFreshListener {

    private lateinit var adp :BaseQuickAdapter<SpecialListBean.DataBean,BaseViewHolder>
    private val datas=ArrayList<SpecialListBean.DataBean>()
    private var page=1
    override fun getTittle(): String {
        return "专题"
    }

    override fun initListener() {
        adp.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(this, SpecialSpecialDetailAty::class.java)
            intent.putExtra("subject_id",datas[position].id)
            intent.putExtra("tittle",datas[position].subject_name)
            startActivity(intent)
        }
    }

    override fun initView() {
        ToastUtil.init(this)
        btExit.visibility=View.GONE
        /*列表使能*/
        adp=object :BaseQuickAdapter<SpecialListBean.DataBean,BaseViewHolder>(R.layout.aty_special_special_listitem,datas){
            override fun convert(helper: BaseViewHolder?, item: SpecialListBean.DataBean?) {
                Glide.with(mContext).load(Urls.BaseUrl+item?.subject_thumb).apply(GlideUtls.loadImgOption())
                        .into(helper?.getView(R.id.itemImg)!!)
                helper?.setText(R.id.itemTittle,item?.subject_name)
            }
        }
        recyclerview.adapter=adp
        recyclerview.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        /*上下拉框架使能*/

        spView.header=DefaultHeader(this)
        spView.footer=DefaultFooter(this)
        spView.setListener(this)
    }
    override fun dispatchData() {
        /*UrlsApi.getSpecialList(page)
                .doOnSubscribe {
                    loadingDialog.show()
                }
                .subscribe ({
                    data->
                    spView.onFinishFreshAndLoad()
                    loadingDialog.dismiss()
                    datas.addAll(data.data)
                    adp.notifyDataSetChanged()
                },{
                    erro->
                    erro.printStackTrace()
                    Log.i("okgo",erro.toString())
                })*/
    }

    override fun onLoadmore() {
        page++
        dispatchData()
    }

    override fun onRefresh() {
        datas.clear()
        page=1
        dispatchData()
    }

    override fun getContentId(): Int = R.layout.aty_spcial
}