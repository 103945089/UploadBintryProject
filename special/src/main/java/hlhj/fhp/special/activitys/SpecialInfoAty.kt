package hlhj.fhp.special.activitys

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.tenma.ventures.bean.utils.TMSharedPUtil
import com.tenma.ventures.config.TMServerConfig
import com.tenma.ventures.share.bean.TMLinkShare
import com.tenma.ventures.share.util.TMShareUtil
import hlhj.fhp.special.BaseAty
import hlhj.fhp.special.R
import hlhj.fhp.special.customview.GoLoginDialog
import hlhj.fhp.special.javabean.*
import hlhj.fhp.special.network.Urls
import hlhj.fhp.special.network.UrlsApi
import hlhj.fhp.special.utils.GlideUtls
import hlhj.fhp.special.utils.ToastUtil
import kotlinx.android.synthetic.main.aty_special_info.*
import kotlinx.android.synthetic.main.head_lo.*
import java.util.ArrayList

/**
 * Created by Administrator on 2018\6\13 0013.
 */
class SpecialInfoAty:BaseAty() {
    private var tittleTv=""
    private var des=""
    private var thumb:String?=""
    private var canSend=true
    private var hasMore=false
    private var isFresh=false
    private var isColl=false
    private var sid=""
    private var itemId=-1
    private var extendStr=""
    private lateinit var adp:BaseQuickAdapter<InfoBean.DataBean.CommentBean,BaseViewHolder>
    private val commentDatas=ArrayList<InfoBean.DataBean.CommentBean>()
    private var commentEmptyView:View?=null
    override fun dispatchData() {
        UrlsApi.getInfoDetail(itemId,TMSharedPUtil.getTMToken(this))
                .doOnSubscribe { loadingDialog.show() }
                .subscribe({ data ->
                    loadingDialog.dismiss()
                    tv_Tittle.text=data.data.title
                    tittleTv=data.data.title
                    des=data.data.title
                    tvTittleTextView.text=tittleTv
//                    tv_Time.text="${data.data.create_at}                   浏览量：${data.data.browse+1}"
                    tv_Time.text="${data.data.create_at}"
                    var prompt = data?.data!!.content
                    prompt=prompt.replace("<img", "<img style='max-width:100%;height:auto;'")
                    webView.loadDataWithBaseURL(Urls.BaseUrl, prompt,"text/html", "utf-8", null)
                    tv_auther.text="${data.data.author}"
                    scrollView.scrollTo(0,0)

                    /*评论*/
                    if (data.data.comment.size > 3) {
                        commentDatas.add(data.data.comment[0])
                        commentDatas.add(data.data.comment[1])
                        commentDatas.add(data.data.comment[2])
                        hasMore=true
                        adp.notifyDataSetChanged()
                    } else {
                        commentDatas.addAll(data.data.comment)
                        hasMore=false
                        adp.notifyDataSetChanged()
                    }
                    addHis()
                },{
                    erro->
                    Log.i("okgo",erro.toString())
                    erro.printStackTrace()
                })
    }
    fun disPatchComment(){
        UrlsApi.getInfoDetail(itemId,TMSharedPUtil.getTMToken(this))
                .doOnSubscribe { loadingDialog.show() }
                .subscribe({ data ->
                    loadingDialog.dismiss()
                    if (data.data.comment.size > 3) {
                        commentDatas.add(data.data.comment[0])
                        commentDatas.add(data.data.comment[1])
                        commentDatas.add(data.data.comment[2])
                        hasMore=true
                        if (isFresh)btMore.visibility=View.VISIBLE
                        adp.notifyDataSetChanged()
                    } else {
                        commentDatas.addAll(data.data.comment)
                        hasMore=false
                        if (isFresh)btMore.visibility=View.GONE
                        adp.notifyDataSetChanged()
                    }
                },{
                    erro->
                    Log.i("okgo",erro.toString())
                    erro.printStackTrace()
                })
    }
    override fun getTittle(): String {
        return "详情"
    }

    override fun initListener() {
        btBack.setOnClickListener {
            finish()
        }
        btColl.setOnClickListener {
            val tmUser = TMSharedPUtil.getTMUser(this)
            if (!isColl){
                UrlsApi.collect(tmUser.member_code,tittleTv,Urls.APPID,itemId.toString()
                        ,TMSharedPUtil.getTMToken(this@SpecialInfoAty),thumb,
                        1,extendStr
                        )
                        .doOnSubscribe {
                            loadingDialog.show()
                        }
                        .subscribe {
                            data->
                            loadingDialog.dismiss()
                            if (data.code==200){
                                val gson = Gson()
                                val bean = gson.fromJson(gson.toJson(data), CollBean::class.java)
                                sid=bean.data.star_id.toString()
                                isColl=true
                                btColl.setImageResource(R.drawable.ic_sc_select)
                            }else{
                                if (data.code==500){
                                    GoLoginDialog(this@SpecialInfoAty).show()
                                }else{
                                    Toast.makeText(this@SpecialInfoAty,data.msg,Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
            }else{
                UrlsApi.cancleCollect(sid,TMSharedPUtil.getTMToken(this@SpecialInfoAty))
                        .doOnSubscribe { loadingDialog.show() }
                        .subscribe {
                            data->
                            loadingDialog.dismiss()
                            if (data.code==200){
                                btColl.setImageResource(R.drawable.ic_sc_normal)
                                isColl=false
                            }else{
                                Toast.makeText(this@SpecialInfoAty,data.msg,Toast.LENGTH_SHORT).show()
                            }
                        }
            }

        }
        btGoShare.setOnClickListener {
            val tmLinkShare = TMLinkShare()
            tmLinkShare.title=tittleTv
            tmLinkShare.thumb=Urls.BaseUrl+thumb
            tmLinkShare.description=des
//            tmLinkShare.url=TMServerConfig.BASE_URL+"/hlhjnews/api/article_detail?article_id=${itemId}"
            tmLinkShare.url=Urls.BaseUrl+"/application/hlhjnews/asset/articles.html?article_id=${itemId}"
            TMShareUtil.getInstance(this).shareLink(tmLinkShare)
        }
        etContent.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                numCurrent.text=s?.length.toString()+"/"+100
                canSend = s?.length!! <= 100
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        btMore.setOnClickListener {
            val i = Intent(this, AllCommentAty::class.java)
            i.putExtra("id",itemId)
            startActivity(i)
        }
        btSend.setOnClickListener {
            if (etContent.text.isEmpty()){
                ToastUtil.toast("评论内容不能为空")
                return@setOnClickListener
            }
            if (!canSend){
                ToastUtil.toast("字数超过限制")
                return@setOnClickListener
            }
            UrlsApi.sendComment(TMSharedPUtil.getTMToken(this),itemId,etContent.text.toString())
                    .doOnSubscribe {
                        loadingDialog.show()
                    }
                    .subscribe {
                        data->
                        loadingDialog.dismiss()
                        if (data.code==1){
                            isFresh=true
                            ToastUtil.toast("评论成功")
                            commentDatas.clear()
                            disPatchComment()
                            etContent.setText("")
                        }else if (data.code==500){
                            GoLoginDialog(this@SpecialInfoAty).show()
                        }else{
                            ToastUtil.toast(data.msg)
                        }
                    }
        }
    }
    override fun initView() {
        controlKeyboardLayout(root,etContent)
        commentEmptyView=LayoutInflater.from(this).inflate(R.layout.comment_empty,null)

        itemId=intent.getIntExtra("article_id",-1)
        thumb=intent.getStringExtra("thumb")

        if (TMSharedPUtil.getTMThemeColor(this).isNotEmpty()) {
            dv1.setBackgroundColor(Color.parseColor(TMSharedPUtil.getTMThemeColor(this)))
        }
        if (itemId==-1){
            val jsonStr = intent.getStringExtra("paramStr")
            jsonStr?.let {
                val pBean = Gson().fromJson(it, PBean::class.java)
                itemId=pBean.article_id
                thumb=pBean.thumb
            }

        }

        val pBean1 = PBean()
        pBean1.article_id=itemId
        pBean1.thumb=thumb
        val extendBean = ExtendBean()
        val androidInfoBean = ExtendBean.AndroidInfoBean()

        androidInfoBean.isNativeX=true
        androidInfoBean.paramStr="${Gson().toJson(pBean1)}"
        androidInfoBean.src="hlhj.fhp.special.activitys.SpecialInfoAty"
        androidInfoBean.wwwFolder=""

        val iosInfoBean = ExtendBean.IosInfoBean()

        iosInfoBean.isNativeX=true
        iosInfoBean.src="HLHJNewDetailsController"
        iosInfoBean.paramStr="${Gson().toJson(pBean1)}"
        iosInfoBean.wwwFolder=""

        extendBean.androidInfo=androidInfoBean
        extendBean.iosInfo=iosInfoBean

        extendStr=Gson().toJson(extendBean)
        Log.e("fhp","包装的Str=${extendStr}")


        /*列表*/

        adp=object :BaseQuickAdapter<InfoBean.DataBean.CommentBean,BaseViewHolder>(R.layout.aty_comment_item,commentDatas){
            override fun convert(helper: BaseViewHolder?, item: InfoBean.DataBean.CommentBean?) {
                Glide.with(mContext).load(Urls.BaseUrl+item?.head_pic).apply(GlideUtls.loadIconOption())
                        .into(helper?.getView(R.id.itemIcon)!!)
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
//                    helper?.setText(R.id.itemName,"321")
                }else{
                    helper?.setText(R.id.itemName,item?.member_nickname)
                }
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
                helper?.setText(R.id.itemTime,item?.time_ago)
                helper?.setText(R.id.itemContnet,item?.content)

            }
        }
        adp.emptyView=commentEmptyView
        comment_list.adapter=adp

        comment_list.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        /*webView*/
        webView.settings.javaScriptEnabled=true
        webView.settings.domStorageEnabled=true
        webView.settings.loadWithOverviewMode=true
        webView.settings.pluginState=WebSettings.PluginState.ON
        /*    webSetting.setJavaScriptEnabled(true);  //这句话必须保留。。不解释
    webSetting.setDomStorageEnabled(true);//这句话必须保留。。否则无法播放优酷视频网页。。其他的可以  */
        webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN;
        webView.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webView.webViewClient=object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                Log.e("fhp","开始 ")
                super.onPageStarted(view, url, favicon)

            }

            override fun onPageFinished(view: WebView?, url: String?) {
                tv_1.visibility= View.VISIBLE
                comment_list.visibility=View.VISIBLE
                if (hasMore){
                    btMore.visibility=View.VISIBLE
                }else{
                    btMore.visibility=View.GONE
                }
                super.onPageFinished(view, url)

            }
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

                return true
            }
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                Log.e("fhp","跳转 ")
                return true
//                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        judgeColl()
    }
    private fun addHis(){
        val tmUser = TMSharedPUtil.getTMUser(this)

        UrlsApi.addHis(
                tmUser.member_code,tittleTv,Urls.APPID,itemId.toString()
                ,TMSharedPUtil.getTMToken(this@SpecialInfoAty),thumb,
                1,extendStr
        )?.let {
            it.doOnSubscribe {  }
                    .subscribe { data-> }
        }
    }
    private fun judgeColl() {
        val tmUser = TMSharedPUtil.getTMUser(this)
        UrlsApi.isColl(tmUser.member_code,Urls.APPID,itemId.toString(),TMSharedPUtil.getTMToken(this))
                .doOnSubscribe { loadingDialog.show() }
                .subscribe({
                    data->
                    loadingDialog.dismiss()
                    try {
                        val bean = Gson().fromJson(data, CollBean::class.java)
                        if (bean.code==200){
                            sid=bean.data.star_id.toString()
                            btColl.setImageResource(R.drawable.ic_sc_select)
                            isColl=true
                        }
                    }catch (e :Exception){
                        val bean = Gson().fromJson(data, CollBean2::class.java)
                        if (bean.code==200){
                            btColl.setImageResource(R.drawable.ic_sc_normal)
                            isColl=false
                        }
                    }

                })
    }
    /**
     * @param root 最外层布局，需要调整的布局
     * @param scrollToView 被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     */
    private fun controlKeyboardLayout(root: View, scrollToView: View) {
        root.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            //获取root在窗体的可视区域
            root.getWindowVisibleDisplayFrame(rect)
            //获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
            val rootInvisibleHeight = root.rootView.height - rect.bottom
            //若不可视区域高度大于100，则键盘显示
            if (rootInvisibleHeight > 100) {
                val location = IntArray(2)
                //获取scrollToView在窗体的坐标
                scrollToView.getLocationInWindow(location)
                //计算root滚动高度，使scrollToView在可见区域
                val srollHeight = location[1] + scrollToView.height - rect.bottom
                root.scrollTo(0, srollHeight)
            } else {
                //键盘隐藏
                root.scrollTo(0, 0)
            }
        }
    }
    override fun getContentId(): Int {
        return R.layout.aty_special_info
    }
}