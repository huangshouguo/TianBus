package com.bus.tian.tianbus.view.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.model.bean.TextLinkBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hsg on 2016/11/2.
 */

public class SingleTextAdapter extends ArrayAdapter<TextLinkBean> {

    private int resourceId;

    public SingleTextAdapter(Context context, int resource, List<TextLinkBean> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextLinkBean textLinkBean = getItem(position);

        View view;
        ViewHolder viewHolder;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(this.resourceId, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        if (textLinkBean != null) {
            viewHolder.button.setText(textLinkBean.getText());
        }

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.btn_next_item)
        Button button;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
