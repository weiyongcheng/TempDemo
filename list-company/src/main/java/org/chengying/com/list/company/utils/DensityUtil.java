package org.chengying.com.list.company.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by yuanyuan on 17-12-15.
 */

public class DensityUtil {

    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources()
            .getDisplayMetrics());
    }
}
