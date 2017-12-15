package org.chengying.com.list.company.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by yuanyuan on 17-12-15.
 */

public class NoScrollGridView extends GridView{

    public NoScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //设置不滚动
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
