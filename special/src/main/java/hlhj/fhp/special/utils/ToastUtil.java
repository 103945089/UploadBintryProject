package hlhj.fhp.special.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2018\6\12 0012.
 */

public class ToastUtil {
    private static Context context;
    public static void init(Context c){
        context=c;
    }
    public static void toast(String s){
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
    }
}
