package org.chengying.com.list.company.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.chengying.com.list.company.R;
import org.chengying.com.list.company.entity.Express;

import java.util.List;

/**
 * Created by yuanyuan on 17-12-15.
 */

public class ExpressListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;

    private List<Express> mAllExpresses;
    private List<Express> mHotExpresses;

    @SuppressWarnings("FieldCanBeLocal")
    private final int VIEW_TYPE_COUNT = 2;

    public ExpressListAdapter(Context context, List<Express> allExpresses, List<Express> hotExpresses) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);

        this.mAllExpresses = allExpresses;
        this.mHotExpresses = hotExpresses;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return position < 1 ? position : 1;
    }

    @Override
    public int getCount() {
        return mAllExpresses.size();
    }

    @Override
    public Object getItem(int position) {
        return mAllExpresses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        if (viewType == 0) {
            //热门城市
            convertView = mInflater.inflate(R.layout.item_express_grid, null);
            final GridView hotGridView = convertView.findViewById(R.id.grid_express);
            hotGridView.setAdapter(new HotExpressAdapter(mContext, this.mHotExpresses));
            TextView hotHint = convertView.findViewById(R.id.Hint);
            hotHint.setText("热门城市");
        } else {
            Holder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_express_list, null);
                holder = new Holder();
                holder.letter = convertView.findViewById(R.id.tv_letter);
                holder.name = convertView.findViewById(R.id.tv_name);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }

            Express express = mAllExpresses.get(position);

            final String title = express.getTitle();
            holder.name.setText(title);


            String sectionId = express.getSectionId();
            String preSectionId = (position - 1) >= 0 ? mAllExpresses.get(position - 1).getSectionId() : " ";
            if (!preSectionId.equals(sectionId)) {
                holder.letter.setVisibility(View.VISIBLE);
                holder.letter.setText(sectionId);
            } else {
                holder.letter.setVisibility(View.GONE);
            }

            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mContext != null) {
                        Toast.makeText(mContext, title, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        return convertView;
    }

    class Holder {
        TextView letter, name;
    }
}
