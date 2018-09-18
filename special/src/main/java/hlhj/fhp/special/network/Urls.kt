package hlhj.fhp.special.network
import com.tenma.ventures.config.TMServerConfig
/**
 * Created by Administrator on 2018\6\12 0012.
 */
object Urls {
//    var BaseUrl="http://mx.360tianma.com"
//    var BaseUrl="http://tm.trf9.cn"
    var BaseUrl=TMServerConfig.BASE_URL
/*    val SPECIAL_LIST="/hlhjnews_df/api/subject"
    val SPECIAL_DETAIL="/hlhjnews_df/api/article"
    val INFO="/hlhjnews_df/api/article_detail"
    val ALL_COMMENT="/hlhjnews_df/api/comment_list"
    val SEND_COMMENT="/hlhjnews_df/api/comment"*/
    val SPECIAL_LIST="/hlhjnews/api/subject"
    val SPECIAL_DETAIL="/hlhjnews/api/article"
    val INFO="/hlhjnews/api/article_detail"
    val ALL_COMMENT="/hlhjnews/api/comment_list"
    val SEND_COMMENT="/hlhjnews/api/comment"
    val COLLOCTION="/member/Memberstar/checkIsStar"
    val COLLECT="/member/Memberstar/addStar"
    val CANCLE="/member/Memberstar/deleteStar"
    val His="/member/Memberfootprint/addFootprint/"
    val DZ="/hlhjnews/api/laud"
    val APPID="hlhj.fhp.special"
}