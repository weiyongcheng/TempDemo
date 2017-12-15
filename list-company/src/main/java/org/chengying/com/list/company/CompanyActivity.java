package org.chengying.com.list.company;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import org.chengying.com.list.company.adapter.ExpressListAdapter;
import org.chengying.com.list.company.entity.Express;
import org.chengying.com.list.company.view.LetterListView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuanyuan on 17-12-15.
 */

public class CompanyActivity extends AppCompatActivity implements AbsListView.OnScrollListener{
    private Toolbar mToolbar;

    private ListView mExpressContainer;
    private LetterListView mLatterContainer;

    private List<Express> mAllExpresses = new ArrayList<>();
    private List<Express> mHotExpresses = new ArrayList<>();
    private List<Express> mExpressesData;
    private Map<String, Integer> mLetterIndex = new HashMap<>();
    private ExpressListAdapter mExpressListAdapter;

    private TextView mLetterOverlay;//对话框首字母text view
    private OverlayThread mOverlayThread;

    private boolean isScroll;
    private boolean isOverlayReady;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_activity);
        mToolbar = findViewById(R.id.toolbar);
        mExpressContainer = findViewById(R.id.express_container);
        mLatterContainer = findViewById(R.id.letter_container);

        handler = new Handler();
        setupActionBar();

        initExpress();
        initHotExpress();
    }

    private void initExpress() {
        Express express = new Express("热门", "0");
        mAllExpresses.add(express);
        mExpressesData = getExpressList();
        mAllExpresses.addAll(mExpressesData);
    }

    private void initHotExpress() {
        mHotExpresses.add(new Express("顺丰快递", "2"));
        mHotExpresses.add(new Express("顺丰快递", "2"));
        mHotExpresses.add(new Express("顺丰快递", "2"));
        mHotExpresses.add(new Express("顺丰快递", "2"));
        mHotExpresses.add(new Express("顺丰快递", "2"));
        mHotExpresses.add(new Express("顺丰快递", "2"));
        mHotExpresses.add(new Express("顺丰快递", "2"));
        mHotExpresses.add(new Express("顺丰快递", "2"));
        mHotExpresses.add(new Express("顺丰快递", "2"));
        mHotExpresses.add(new Express("顺丰快递", "2"));
        mHotExpresses.add(new Express("顺丰快递", "2"));
        mHotExpresses.add(new Express("顺丰快递", "2"));
        mHotExpresses.add(new Express("顺丰快递", "2"));
        mHotExpresses.add(new Express("顺丰快递", "2"));
        mHotExpresses.add(new Express("顺丰快递", "2"));
        mHotExpresses.add(new Express("顺丰快递", "2"));
        mHotExpresses.add(new Express("顺丰快递", "2"));
    }

    private void setupView() {
        mExpressContainer.setOnScrollListener(this);
        mLe
    }

    private void setupActionBar() {
        TextView mTitle = mToolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("外卖公司");
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    public List<Express> getExpressList() {
        List<Express> list = new ArrayList<>();
        try {
            InputStreamReader isr = new InputStreamReader(getAssets().open("expresses.json"),"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuilder builder = new StringBuilder();
            while((line = br.readLine()) != null){
                builder.append(line);
            }
            br.close();
            isr.close();
            JSONObject testjson = new JSONObject(builder.toString());//builder读取了JSON中的数据。
            //直接传入JSONObject来构造一个实例
            JSONArray array = testjson.getJSONArray("datas");         //从JSONObject中取出数组对象
            for (int i = 0; i < array.length(); i++) {
                JSONObject data = array.getJSONObject(i);    //取出数组中的对象
                JSONArray arrayDetail = data.getJSONArray("expresses");
                for (int j = 0; j < arrayDetail.length(); ++j) {
                    JSONObject jsonObject = arrayDetail.getJSONObject(j);
                    String title = jsonObject.getString("title");
                    Express express = new Express(title, "2");
                    list.add(express);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_TOUCH_SCROLL || scrollState == SCROLL_STATE_FLING) {
            isScroll = true;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (!isScroll) {
            return;
        }

    }

    private class OverlayThread implements Runnable {

        @Override
        public void run() {
            mLetterOverlay.setVisibility(View.GONE);
        }
    }
}
