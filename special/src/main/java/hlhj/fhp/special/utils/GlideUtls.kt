package hlhj.fhp.special.utils

import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import hlhj.fhp.special.R

/**
 * Created by Administrator on 2018\6\14 0014.
 */
object GlideUtls {
    fun loadImgOption(): RequestOptions {
        return RequestOptions()
                .priority(Priority.IMMEDIATE)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.holder_img)
                .error(R.drawable.holder_img)

    }
    fun loadIconOption(): RequestOptions {
        return RequestOptions()
                .priority(Priority.IMMEDIATE)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_touxiang_ph)
                .error(R.drawable.ic_touxiang_ph)

    }
}