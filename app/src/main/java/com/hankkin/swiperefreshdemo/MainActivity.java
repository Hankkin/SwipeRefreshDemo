package com.hankkin.swiperefreshdemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.hankkin.library.RefreshSwipeMenuListView;
import com.hankkin.library.SwipeMenu;
import com.hankkin.library.SwipeMenuCreator;
import com.hankkin.library.SwipeMenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RefreshSwipeMenuListView.OnRefreshListener{

    private RefreshSwipeMenuListView rsmLv;
    private List<MsgBean> data;
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rsmLv = (RefreshSwipeMenuListView) findViewById(R.id.swipe);
        data = new ArrayList<>();


        initData();

        adapter = new MessageAdapter(MainActivity.this,data);

        rsmLv.setAdapter(adapter);
        rsmLv.setListViewMode(RefreshSwipeMenuListView.HEADER);
        rsmLv.setOnRefreshListener(this);

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // 创建滑动选项
                SwipeMenuItem rejectItem = new SwipeMenuItem(
                        getApplicationContext());
                // 设置选项背景
                rejectItem.setBackground(new ColorDrawable(getResources().getColor(R.color.top)));
                // 设置选项宽度
                rejectItem.setWidth(dp2px(80,getApplicationContext()));
                // 设置选项标题
                rejectItem.setTitle("置顶");
                // 设置选项标题
                rejectItem.setTitleSize(16);
                // 设置选项标题颜色
                rejectItem.setTitleColor(Color.WHITE);
                // 添加选项
                menu.addMenuItem(rejectItem);

                // 创建删除选项
                SwipeMenuItem argeeItem = new SwipeMenuItem(getApplicationContext());
                argeeItem.setBackground(new ColorDrawable(getResources().getColor(R.color.del)));
                argeeItem.setWidth(dp2px(80, getApplicationContext()));
                argeeItem.setTitle("删除");
                argeeItem.setTitleSize(16);
                argeeItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(argeeItem);
            }
        };

        rsmLv.setMenuCreator(creator);

        rsmLv.setOnMenuItemClickListener(new RefreshSwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0: //第一个选项
                        Toast.makeText(MainActivity.this,"您点击的是置顶",Toast.LENGTH_SHORT).show();
                        break;
                    case 1: //第二个选项
                        del(position,rsmLv.getChildAt(position+1-rsmLv.getFirstVisiblePosition()));
                        break;

                }
            }
        });
    }


    private void initData(){
        for (int i=0;i<15;i++){
            MsgBean msgBean = new MsgBean();
            msgBean.setName("张某某"+i);
            msgBean.setContent("你好，在么？"+i);
            msgBean.setTime("上午10:30");
            data.add(msgBean);
        }
    }

    /**
     * 删除item动画
     * @param index
     * @param v
     */
    private void del(final int index, View v){
        final Animation animation = (Animation) AnimationUtils.loadAnimation(v.getContext(), R.anim.list_anim);
        animation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {}
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationEnd(Animation animation) {
                data.remove(index);
                adapter.notifyDataSetChanged();
                animation.cancel();
            }
        });

        v.startAnimation(animation);
    }


    @Override
    public void onRefresh() {
        rsmLv.postDelayed(new Runnable() {
            @Override
            public void run() {
                rsmLv.complete();
                Toast.makeText(MainActivity.this,"已完成",Toast.LENGTH_SHORT).show();
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
        rsmLv.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    MsgBean msgBean = new MsgBean();
                    msgBean.setName("张某某"+i);
                    msgBean.setContent("你好，在么？"+i);
                    msgBean.setTime("上午10:30");
                    data.add(msgBean);
                }
                rsmLv.complete();
                adapter.notifyDataSetChanged();
            }
        },2000);

    }

    public  int dp2px(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }
}
