package hlhj.fhp.special.network

import android.content.Context
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.model.Response
import com.lzy.okrx.adapter.ObservableBody
import com.lzy.okrx.adapter.ObservableResponse
import com.tenma.ventures.bean.utils.TMSharedPUtil
import hlhj.fhp.special.javabean.*
import rx.Observable
import rx.functions.Action0
import rx.schedulers.Schedulers

/**
 * Created by Administrator on 2018\6\12 0012.
 */
object UrlsApi {
    /*专题列表*/
    fun getSpecialList(page:Int): Observable<Response<SpecialListBean>> {
        val get = OkGo.get<SpecialListBean>(Urls.BaseUrl + Urls.SPECIAL_LIST)
        if (page==1){
             return get
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .params("page",page)
                .converter(object :JsonConvert<SpecialListBean>(){})
                .adapt(ObservableResponse<SpecialListBean>())
                .subscribeOn(Schedulers.io())
        }else{
             return get
                .params("page",page)
                .converter(object :JsonConvert<SpecialListBean>(){})
                .adapt(ObservableResponse<SpecialListBean>())
                .subscribeOn(Schedulers.io())
        }

    }
    /*专题详情*/
    fun getSpecialDetail(page: Int,subject_id:Int): Observable<SpecialDetailBean> {
        return OkGo.get<SpecialDetailBean>(Urls.BaseUrl+Urls.SPECIAL_DETAIL)
                .params("page",page)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .params("subject_id",subject_id)
                .converter(object :JsonConvert<SpecialDetailBean>(){})
                .adapt(ObservableBody())
                .subscribeOn(Schedulers.io())
    }
    /*资讯详情*/
    fun getInfoDetail(article_id:Int,token:String?): Observable<InfoBean> {
       return OkGo.get<InfoBean>(Urls.BaseUrl+Urls.INFO)
                .params("article_id",article_id)
                .params("token",token)

                .converter(object :JsonConvert<InfoBean>(){})
                .adapt(ObservableBody())
                .subscribeOn(Schedulers.io())
    }
    /*全部评论列表*/
    fun getAllComment(article_id:Int,token: String): Observable<Response<CommentBean>> {
        return OkGo.get<CommentBean>(Urls.BaseUrl+Urls.ALL_COMMENT)
                .params("article_id",article_id)
                .params("token",token)
                .converter(object :JsonConvert<CommentBean>(){})
                .adapt(ObservableResponse<CommentBean>())
//                .adapt(ObservableBody())
                .subscribeOn(Schedulers.io())
    }
    /*发布评论*/
    fun sendComment(token:String,article_id:Int,content:String): Observable<BaseBean> {
        return OkGo.get<BaseBean>(Urls.BaseUrl+Urls.SEND_COMMENT)
                .params("token",token)
                .params("article_id",article_id)
                .params("content",content)
                .converter(object :JsonConvert<BaseBean>(){})
                .adapt(ObservableBody())
                .subscribeOn(Schedulers.io())
    }
    /*判断是否收藏*/
    fun isColl(member_code:String?,app_id:String,article_id:String,token: String): Observable<String> {
        return OkGo.post<String>(Urls.BaseUrl+Urls.COLLOCTION)
                .headers("token", token)
                .params("member_code",member_code ?: "")
                .params("app_id",app_id)
                .params("article_id",article_id)
                .converter(object :JsonConvert<String>(){})
                .adapt(ObservableBody())
                .subscribeOn(Schedulers.io())
    }

    /*收藏*/
    fun collect(member_code:String?,title:String,app_id:String,article_id:String,token:String
    ,pic:String?,type:Int,extend:String): Observable<CollBaseBean> {
        return OkGo.post<CollBaseBean>(Urls.BaseUrl+Urls.COLLECT)
                .headers("token",token)
                .params("member_code",member_code ?: "")
                .params("title",title)
                .params("app_id",app_id)
                .params("article_id",article_id)
                .params("intro",title)
                .params("pic",pic)
                .params("type",type)
                .params("tag","")
                .params("extend",extend)
                .converter(object :JsonConvert<CollBaseBean>(){})
                .adapt(ObservableBody())
                .subscribeOn(Schedulers.io())
    }
    fun addHis(member_code:String?,title:String,app_id:String,article_id:String,token:String
               ,pic:String?,type:Int,extend:String): Observable<CollBaseBean> {
        return OkGo.post<CollBaseBean>(Urls.BaseUrl+Urls.His)
                .headers("token",token)
                .params("member_code",member_code ?: "")
                .params("title",title)
                .params("app_id",app_id)
                .params("article_id",article_id)
                .params("intro",title)
                .params("pic",pic)
                .params("type",type)
                .params("tag","")
                .params("extend",extend)
                .converter(object :JsonConvert<CollBaseBean>(){})
                .adapt(ObservableBody())
                .subscribeOn(Schedulers.io())
    }
    /*取消收藏*/
    fun cancleCollect(star_id:String,token:String): Observable<TMBaseBean> {
        return OkGo.post<TMBaseBean>(Urls.BaseUrl+Urls.CANCLE)
                .headers("token",token)
                .params("star_id",star_id)
                .converter(object :JsonConvert<TMBaseBean>(){})
                .adapt(ObservableBody())
                .subscribeOn(Schedulers.io())

    }

    /**
     * 点赞
     */
    fun itsGood(id:Int,token: String?): Observable<BaseBean>? {
        return OkGo.post<BaseBean>(Urls.BaseUrl+Urls.DZ)
                .params("id",id)
                .headers("token",token)
                .params("token",token)
                .converter(object :JsonConvert<BaseBean>(){})
                .adapt(ObservableBody<BaseBean>())
                .subscribeOn(Schedulers.io())
    }
}