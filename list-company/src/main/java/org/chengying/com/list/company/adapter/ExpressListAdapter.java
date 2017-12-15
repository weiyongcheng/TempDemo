package org.chengying.com.list.company.adapter;

import android.content.Context;
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
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by yuanyuan on 17-12-15.
 */

public class ExpressListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Express> mAllExpresses;
    private List<Express> mHotExpresses;
    private String[] mFirstLetterArray;
    private Map<String, Integer> mLetterIndex;
    private final int VIEW_TYPE = 2;

    public ExpressListAdapter(Context context, List<Express> allExpresses, List<Express> hotExpresses,
                              Map<String, Integer> letterIndex) {
        this.mContext = context;
        this.mAllExpresses = allExpresses;
        this.mHotExpresses = hotExpresses;
        this.mLetterIndex = letterIndex;
        mInflater = LayoutInflater.from(context);
    }

    private void setup() {
        final int size = mAllExpresses.size();
        mFirstLetterArray = new String[size];
        for (int i = 0; i < size; ++i) {
            //当前汉语拼音字母
            String currentStr = getAlpha(mAllExpresses.get(i).getPinyin());
            //上一个汉语拼音首字母，如果不存在为空串
            String previewStr = (i - 1) >= 0 ? getAlpha(mAllExpresses.get(i - 1).getPinyin()) : " ";
            if (!previewStr.equals(currentStr)) {
                String name = getAlpha(mAllExpresses.get(i).getPinyin());
                mLetterIndex.put(name, i);
                mFirstLetterArray[i] = name;
            }
        }
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE;
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
            convertView = mInflater.inflate(R.layout.item_express_grid, parent);
            final GridView hotExpress = convertView.findViewById(R.id.grid_express);
            hotExpress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, mHotExpresses.get(position).getTitle(), Toast.LENGTH_LONG).show();
                }
            });
            hotExpress.setAdapter(new HotExpressAdapter(mContext, this.mHotExpresses));
            TextView hotHint = convertView.findViewById(R.id.recentHint);
            hotHint.setText("热门城市");
        } else {
            Holder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_express_list, parent);
                holder = new Holder();
                holder.letter = convertView.findViewById(R.id.tv_letter);
                holder.name = convertView.findViewById(R.id.tv_name);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.name.setText(mAllExpresses.get(position).getTitle());
            String currentStr = getAlpha(mAllExpresses.get(position).getPinyin());
            String previewStr = (position - 1) >= 0 ? getAlpha(mAllExpresses.get(position - 1).getPinyin()) : " ";
            if (!previewStr.equals(currentStr)) {
                holder.letter.setVisibility(View.VISIBLE);
                holder.letter.setText(currentStr);
            } else {
                holder.letter.setVisibility(View.GONE);
            }
        }
        return convertView;
    }

    class Holder {
        TextView letter, name;
    }

    private String getAlpha(String str) {
        if (str == null) {
            return "#";
        }
        if (str.trim().length() == 0) {
            return "#";
        }
        char c = str.trim().substring(0, 1).charAt(0);
        //正则表达式，判断首字母是否为英文字母
        Pattern pattern = Pattern.compile("$[A-Za-z]+$");
        if (pattern.matcher(c + "").matches()) {
            return (c + "").toUpperCase();
        } else if (str.equals("0")) {
            return "热门";
        } else {
            return "#";
        }
    }
}
