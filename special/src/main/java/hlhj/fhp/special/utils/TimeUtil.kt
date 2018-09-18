package hlhj.fhp.special.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

/**
 * Created by Never Fear   on 2018\9\8 0008.
Never More....
 */
object TimeUtil {
    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param timeStamp
     * @return
     */
    fun convertTimeToFormat(timeStamp: Long): String {
        var timeStamp = timeStamp
        val times = timeStamp.toString() + ""
        if (times.length >= 11) {
            timeStamp = java.lang.Long.parseLong(times) / 1000.toLong()
        }
        val curTime = System.currentTimeMillis() / 1000.toLong()

        val time = curTime - timeStamp

        return if (time < 60 && time >= 0) {
            "刚刚"
        } else if (time >= 60 && time < 3600) {
            (time / 60).toString() + "分钟前"
        } else if (time >= 3600 && time < 3600 * 24) {
            (time / 3600).toString() + "小时前"
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 10) {
            (time / 3600 / 24).toString() + "天前"
        } else {
            timeStampToStr(timeStamp)
        }
    }

    /**
     * 时间戳转化为时间格式
     *
     * @param timeStamp
     * @return
     */
    fun timeStampToStr(timeStamp: Long): String {
        var timeStamp = timeStamp
        val times = timeStamp.toString()
        if (times.length >= 11) {
            timeStamp = java.lang.Long.parseLong(times) / 1000.toLong()
        }
        @SuppressLint("SimpleDateFormat") val sdf = SimpleDateFormat("yyyy-MM-dd ")
        return sdf.format(timeStamp * 1000)
    }
    /**
     * 时间戳转化为时间格式
     *
     * @param timeStamp
     * @return
     */
    fun timeStampToMD(timeStamp: Long): String {
        var timeStamp = timeStamp
        val times = timeStamp.toString()
        if (times.length >= 11) {
            timeStamp = java.lang.Long.parseLong(times) / 1000.toLong()
        }
        @SuppressLint("SimpleDateFormat") val sdf = SimpleDateFormat("MM月dd日")
        return sdf.format(timeStamp * 1000)
    }
}