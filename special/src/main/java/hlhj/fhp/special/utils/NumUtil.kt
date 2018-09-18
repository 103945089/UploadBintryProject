package hlhj.fhp.special.utils

import java.text.DecimalFormat

/**
 * Created by Never Fear   on 2018\9\8 0008.
Never More....
 */
object NumUtil {
    fun formatNum(num:Float,p0:Int): String? {
        var fo="0."
        for(i in 0..p0-1){
            fo=fo+"0"
        }
        val decimalFormat = DecimalFormat(fo)
        val format = decimalFormat.format(num)
        return format
    }
}