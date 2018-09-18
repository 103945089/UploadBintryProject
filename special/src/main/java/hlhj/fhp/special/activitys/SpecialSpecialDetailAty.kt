package hlhj.fhp.special.activitys

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liaoinstan.springview.container.DefaultFooter
import com.liaoinstan.springview.container.DefaultHeader
import com.liaoinstan.springview.widget.SpringView
import hlhj.fhp.special.BaseAty
import hlhj.fhp.special.R
import hlhj.fhp.special.javabean.SpecialDetailBean
import hlhj.fhp.special.network.Urls
import hlhj.fhp.special.network.UrlsApi
import hlhj.fhp.special.utils.GlideUtls
import hlhj.fhp.special.utils.NumUtil
import hlhj.fhp.special.utils.TimeUtil
import hlhj.fhp.special.utils.ToastUtil
import kotlinx.android.synthetic.main.aty_special_detail_2.*
import kotlinx.android.synthetic.main.list_lo.*
import java.util.ArrayList

/**
 * Created by Administrator on 2018\6\12 0012.
 */
class SpecialSpecialDetailAty :BaseAty(), SpringView.OnFreshListener {
    private lateinit var adp :BaseQuickAdapter<SpecialDetailBean.DataBean.ArticleBean,BaseViewHolder>
    private val datas=ArrayList<SpecialDetailBean.DataBean.ArticleBean>()
    private var page=1;
    override fun dispatchData() {
        UrlsApi.getSpecialDetail(page,intent.getIntExtra("subject_id",0))
                .doOnSubscribe {
                    loadingDialog.show()
                }
                .subscribe ({
                    data->
                    Glide.with(this@SpecialSpecialDetailAty).load(Urls.BaseUrl+data.data.subject_thumb)
                            .apply(GlideUtls.loadImgOption())
                            .into(headImg)
                    loadingDialog.dismiss()
                    spView.onFinishFreshAndLoad()
                    datas.addAll(data.data.article)
                    adp.notifyDataSetChanged()
                },{
                    erro->
                    erro.printStackTrace()
                    Log.i("info",erro.toString())
                })
    }

    override fun getTittle(): String {
        return intent.getStringExtra("tittle")
    }

    override fun initListener() {
        btExit.setOnClickListener {

            finish()
        }
        adp.setOnItemClickListener { adapter, view, position ->

            val intent = Intent(this, SpecialInfoAty::class.java)
            intent.putExtra("thumb",datas[position].thumb)
            intent.putExtra("article_id",datas[position].id)
            startActivity(intent)
            recyclerview.postDelayed({
                datas[position].browse=datas[position].browse+1
                adp.notifyDataSetChanged()
            },1000)
        }
    }

    override fun initView() {
        tvTittle.text=intent.getStringExtra("tittle")
        /*上下拉框架*/
        spView.header=DefaultHeader(this)
        spView.footer=DefaultFooter(this)
        spView.setListener(this)
        /*列表*/
        adp=object :BaseQuickAdapter<SpecialDetailBean.DataBean.ArticleBean,BaseViewHolder>(R.layout.special_special_detail_item,datas){
            override fun convert(helper: BaseViewHolder?, item: SpecialDetailBean.DataBean.ArticleBean?) {
                    Glide.with(mContext).load(Urls.BaseUrl+item?.thumb).apply(GlideUtls.loadImgOption())
                            .into(helper?.getView(R.id.itemImg)!!)
                    helper?.setText(R.id.itemTittle,item?.title)
                if (item?.browse!!>=1000){
                    helper?.setText(R.id.itemViewNum,"${NumUtil.formatNum((item?.browse/1000f),1)}")
                }else{

                    helper?.setText(R.id.itemViewNum,"${item?.browse}")
                }
                    helper?.setText(R.id.itemTime,TimeUtil.timeStampToMD(item?.create_at?.toLong()!!))
            }
        }
        recyclerview.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerview.adapter=adp
        val loadMoreListener= BaseQuickAdapter.RequestLoadMoreListener {
            recyclerview.postDelayed({
                Log.e("fhp","加载一次")
                page++
                UrlsApi.getSpecialDetail(page,intent.getIntExtra("subject_id",0))
                        .doOnSubscribe {
                        }
                        .subscribe ({ data->
                            if (data.code==1){
                                if (data.data.article.size<5){
                                    adp.loadMoreEnd()
                                }else{
                                    adp.loadMoreComplete()
                                }
                                adp.setEnableLoadMore(true)
                                Glide.with(this@SpecialSpecialDetailAty).load(Urls.BaseUrl+data.data.subject_thumb)
                                        .apply(GlideUtls.loadImgOption())
                                        .into(headImg)
                                datas.addAll(data.data.article)
                                adp.notifyDataSetChanged()
                            }else{
                                adp.loadMoreFail()
                                ToastUtil.toast(data.msg)
                            }
                        },{ erro->
                            erro.printStackTrace()
                            Log.i("info",erro.toString())
                        })
                //                    ToastUtil.toast("加载")
            },3000)
        }
//        adp.setOnLoadMoreListener(loadMoreListener,recyclerview)

    }

    override fun onLoadmore() {
        page++
        dispatchData()
    }

    override fun onRefresh() {
        page=1
        datas.clear()
        dispatchData()
    }

    override fun getContentId(): Int {
        return R.layout.aty_special_detail_2
    }
}