package com.bus.tian.tianbus.view.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bus.tian.tianbus.R;
import com.bus.tian.tianbus.model.bean.CopAnnouncementBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hsg on 2016/10/30.
 */

public class AnnouncementAdapter extends ArrayAdapter<CopAnnouncementBean> {

    private int resourceId;

    public AnnouncementAdapter(Context context, int resource, List<CopAnnouncementBean> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CopAnnouncementBean copAnnouncementBean = getItem(position);

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

        if (copAnnouncementBean != null) {
            viewHolder.textTitle.setText(copAnnouncementBean.getTitle());
            viewHolder.textTime.setText(copAnnouncementBean.getCreateTimeImpl());
            viewHolder.textContent.setText(copAnnouncementBean.getContent());
        }

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.text_announcement_title)
        TextView textTitle;
        @BindView(R.id.text_announcement_time)
        TextView textTime;
        @BindView(R.id.text_announcement_content)
        TextView textContent;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
