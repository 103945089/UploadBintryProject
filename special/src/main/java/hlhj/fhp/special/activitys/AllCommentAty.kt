package hlhj.fhp.special.activitys

import android.graphics.Bitmap
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liaoinstan.springview.container.DefaultFooter
import com.liaoinstan.springview.container.DefaultHeader
import com.liaoinstan.springview.widget.SpringView
import com.lzy.okgo.model.Response
import com.tenma.ventures.bean.utils.TMSharedPUtil
import hlhj.fhp.special.BaseAty
import hlhj.fhp.special.R
import hlhj.fhp.special.customview.GoLoginDialog
import hlhj.fhp.special.javabean.BaseBean
import hlhj.fhp.special.javabean.CommentBean
import hlhj.fhp.special.network.CustomAction
import hlhj.fhp.special.network.LoadingAction
import hlhj.fhp.special.network.Urls
import hlhj.fhp.special.network.UrlsApi
import hlhj.fhp.special.utils.GlideUtls
import hlhj.fhp.special.utils.ToastUtil
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.aty_comment.*
import kotlinx.android.synthetic.main.list_lo.*
import kotlinx.android.synthetic.main.lo_comment.*
import rx.Observable
import java.util.ArrayList

/**
 * Created by Administrator on 2018\6\13 0013.
 */
class AllCommentAty :BaseAty(), SpringView.OnFreshListener {
    private lateinit var adp:BaseQuickAdapter<CommentBean.DataBean,BaseViewHolder>
    private val commentDatas=ArrayList<CommentBean.DataBean>()

    override fun dispatchData() {
        UrlsApi.getAllComment(intent.getIntExtra("id",0),TMSharedPUtil.getTMToken(this))
                .doOnSubscribe(LoadingAction(this))
                        .subscribe(object :CustomAction<Response<CommentBean>>{
                            override fun onResult(t: Response<CommentBean>) {
                                val data = t.body()
                                    loadingDialog.dismiss()
                                    adp.addData(data.data)
                            }
                        })
    }

    override fun getTittle(): String {
        return "全部评论"
    }

    override fun initListener() {
        btSend.setOnClickListener {
            if (etContent.text.isEmpty()){
                ToastUtil.toast("评论内容不能为空")
                return@setOnClickListener
            }
            UrlsApi.sendComment(TMSharedPUtil.getTMToken(this),intent.getIntExtra("id",0),etContent.text.toString())
                    .doOnSubscribe {
                        loadingDialog.show()
                    }
                    .subscribe({
                        data->
                        loadingDialog.dismiss()
                        if (data.code==1){
                            dispatchData()
                            etContent.setText("")
                        }else if (data.code==500){
                            GoLoginDialog(this).show()
                        }
                    },{
                        erro->
                        erro.printStackTrace()
                    })
        }
    }

    override fun initView() {
        btLike.visibility= View.GONE
        /*列表*/
        adp=object :BaseQuickAdapter<CommentBean.DataBean,BaseViewHolder>(R.layout.aty_comment_item,commentDatas)
        {
            override fun convert(helper: BaseViewHolder?, item: CommentBean.DataBean?) {
                Glide.with(mContext).load(Urls.BaseUrl+item?.head_pic)
                        .apply(GlideUtls.loadIconOption())
                        .into(helper?.getView(R.id.itemIcon)!!)

//                helper?.setText(R.id.btZan,item?.laud_num.toString())
                helper?.setText(R.id.btZan,item?.laud_num.toString())
                val btZan = helper?.getView<TextView>(R.id.btZan)
                if (item?.is_laud==1){
                    val drawable = mContext.resources.getDrawable(R.drawable.ic_praise_sected)
                    val view = helper?.getView<TextView>(R.id.btZan)
                    view!!.setCompoundDrawablesWithIntrinsicBounds(drawable,
                            null, null, null);
                }else{
                    val drawable = mContext.resources.getDrawable(R.drawable.ic_praise_normal)
                    val view = helper?.getView<TextView>(R.id.btZan)
                    view!!.setCompoundDrawablesWithIntrinsicBounds(drawable,
                            null, null, null);
                }
                if (item?.member_nickname!!.isEmpty()){
                    helper?.setText(R.id.itemName,item?.member_name)
                }else{
                    helper?.setText(R.id.itemName,item?.member_nickname)
                }
                helper?.setText(R.id.itemTime,item?.time_ago)
                helper?.setText(R.id.itemContnet,item?.content)

                btZan?.setOnClickListener {
                    if (item?.is_laud==1){//已点赞，取消点赞
                        UrlsApi.itsGood(item?.id,TMSharedPUtil.getTMToken(mContext))
                                ?.doOnSubscribe {
                                    loadingDialog.show()
                                }
                                ?.subscribe {
                                    data->
                                    loadingDialog.dismiss()
                                    if (data.code==1){
                                        item?.is_laud=0
                                        item?.laud_num=item?.laud_num-1
                                        notifyDataSetChanged()
                                    }else{
                                        GoLoginDialog(mContext).show()
                                    }
                                }
                    }else{//未点赞，现在点赞
                        UrlsApi.itsGood(item?.id,TMSharedPUtil.getTMToken(mContext))
                                ?.doOnSubscribe {
                                    loadingDialog.show()
                                }
                                ?.subscribe {
                                    data->
                                    loadingDialog.dismiss()
                                    if (data.code==1){
                                        item?.is_laud=1
                                        item?.laud_num=item?.laud_num+1
                                        notifyDataSetChanged()
                                    }else{
                                        GoLoginDialog(mContext).show()
                                    }
                                }
                    }
                }
            }
        }
        recyclerview.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerview.adapter=adp
        
        /*上下拉框架*/
/*        spView.header=DefaultHeader(this)
        spView.footer=DefaultFooter(this)*/
        spView.setListener(this)

        btSend.setOnClickListener {
            if (etContent.text.isEmpty()){
                ToastUtil.toast("评论内容不能为空")
            }else{
                UrlsApi.sendComment(TMSharedPUtil.getTMToken(this),intent.getIntExtra("article_id",0),etContent.text.toString())
                        .doOnSubscribe {
                            loadingDialog.show()
                        }
/*                        .subscribe(object :CustomAction<Response<BaseBean>>{
                            override fun onResult(t: Response<BaseBean>) {
                            }
                        })*/
                        .subscribe {
                            data->
                            loadingDialog.dismiss()
                            if (data.code==1){
                                ToastUtil.toast("评论成功")
                                etContent.setText("")
                            }else if (data.code==500){
                                GoLoginDialog(this@AllCommentAty).show()
                            }else{
                                ToastUtil.toast(data.msg)
                            }
                        }
            }
        }
    }

    override fun onLoadmore() {

    }

    override fun onRefresh() {
        commentDatas.clear()
        dispatchData()
    }

    override fun getContentId(): Int {
        return  R.layout.aty_comment
    }
}

