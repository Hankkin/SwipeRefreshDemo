package com.hankkin.swiperefreshdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hankkin on 16/4/19.
 */
public class MessageAdapter extends BaseAdapter{

        private Context context;
        private List<MsgBean> data;
        private LayoutInflater inflater;

        public MessageAdapter(Context context, List<MsgBean> data) {
            this.context = context;
            this.data = data;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holoder = null;
            if (convertView==null){
                convertView = inflater.inflate(R.layout.msg_item,null);
                holoder = new ViewHolder();
                holoder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
                holoder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
                holoder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
                holoder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
                convertView.setTag(holoder);
            }
            else {
                holoder = (ViewHolder) convertView.getTag();
            }

            MsgBean item = data.get(position);
            holoder.tvName.setText(item.getName());
            holoder.tvTime.setText(item.getTime());
            holoder.tvContent.setText(item.getContent());
            return convertView;
        }

       public class ViewHolder{
            TextView tvName;
            TextView tvContent;
            TextView tvTime;
            ImageView ivIcon;
        }


}
