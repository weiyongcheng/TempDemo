package org.chengying.com.list.company;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import org.chengying.com.list.company.adapter.ExpressListAdapter;
import org.chengying.com.list.company.entity.Express;
import org.chengying.com.list.company.utils.DensityUtil;
import org.chengying.com.list.company.utils.PinYinUtil;
import org.chengying.com.list.company.view.LetterListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by yuanyuan on 17-12-15.
 */

public class CompanyActivity extends AppCompatActivity implements LetterListView.OnTouchingLetterChangedListener {
    private Toolbar mToolbar;

    private ListView mExpressContainer;
    private LetterListView mLetterContainer;

    private List<Express> mAllExpresses = new ArrayList<>();

    HashMap<String, Integer> valueFirstIndexMap = GeneratorDatas.getValueFirstIndex();

    private TextView mLetterOverlay;//对话框首字母text view
    private OverlayThread mOverlayThread;

    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_activity);
        mToolbar = findViewById(R.id.toolbar);
        mExpressContainer = findViewById(R.id.express_container);
        mLetterContainer = findViewById(R.id.letter_container);

        handler = new Handler();
        setupActionBar();

        initExpress();
        setupView();
        initOverlay();
    }

    private void setupActionBar() {
        TextView mTitle = mToolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("外卖公司");
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    private void initExpress() {
        Express express = new Express("热门", "无效");
        mAllExpresses.add(express);
        mAllExpresses.addAll(GeneratorDatas.getAllExpresses());
    }

    private void setupView() {
        mLetterContainer.setLetters(new String[]{"热门", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"});
        mLetterContainer.setOnTouchingLetterChangedListener(this);

        ExpressListAdapter mExpressListAdapter = new ExpressListAdapter(this, mAllExpresses, GeneratorDatas.getHotExpresses());
        mExpressContainer.setAdapter(mExpressListAdapter);
    }

    private void initOverlay() {
        mOverlayThread = new OverlayThread();
        LayoutInflater inflater = LayoutInflater.from(this);
        mLetterOverlay = (TextView) inflater.inflate(R.layout.v_letter_overlay, null);
        mLetterOverlay.setVisibility(View.INVISIBLE);

        int width = DensityUtil.dp2px(this, 65);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                width, width,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(mLetterOverlay, lp);
    }

    @Override
    public void onTouchingLetterChanged( String value) {
        int position;
        if (value.equals("热门")) {
            position = 0;
        } else {
            Integer integer = valueFirstIndexMap.get(value);
            if (integer == null) {
                Log.d("spy", "integer为null" + ", value: " + value);
                return;
            }
            position = integer;
        }
        mExpressContainer.setSelection(position);
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(value).matches()) {
            mLetterOverlay.setTextSize(40);
        } else {
            mLetterOverlay.setTextSize(20);
        }
        mLetterOverlay.setText(value);
        mLetterOverlay.setVisibility(View.VISIBLE);
        handler.removeCallbacks(mOverlayThread);
        handler.postDelayed(mOverlayThread, 1000);
    }

    private class OverlayThread implements Runnable {
        @Override
        public void run() {
            mLetterOverlay.setVisibility(View.GONE);
        }
    }
}
