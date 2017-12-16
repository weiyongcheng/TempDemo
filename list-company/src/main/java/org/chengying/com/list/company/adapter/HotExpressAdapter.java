package org.chengying.com.list.company.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.chengying.com.list.company.R;
import org.chengying.com.list.company.entity.Express;

import java.util.List;

/**
 * Created by yuanyuan on 17-12-15.
 */

public class HotExpressAdapter extends BaseAdapter{

    private LayoutInflater mInflater;
    private Context mContext;
    private List<Express> mHotExpresses;

    HotExpressAdapter(Context context, List<Express> hotExpresses) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mHotExpresses = hotExpresses;
    }

    @Override
    public int getCount() {
        return mHotExpresses.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.item_express_cell, null);

        final String title = mHotExpresses.get(position).getTitle();

        TextView express = convertView.findViewById(R.id.express);
        express.setText(title);
        express.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext != null) {
                    Toast.makeText(mContext, title, Toast.LENGTH_LONG).show();
                }
            }
        });
        return convertView;
    }
}
