# SwipeRefreshDemo
自定义组合控件上拉+下拉+左滑删除置顶
###look at the screenshot:

<img src="http://img.blog.csdn.net/20160424143943440" width = "320" height = "640" alt="高仿微信群聊头像" align=center />

###使用方法

###build.gradle文件
```java
compile 'com.hankkin:swiperefresh:1.0.0
```

###xml引用
```java
 <com.hankkin.swiperefresh.RefreshSwipeMenuListView
        android:id="@+id/swipe"
        android:dividerHeight="1dp"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

###创建左滑菜单

```java
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
```
###左滑菜单监听
```java
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
```
###我的博客
---------------------------
<http://blog.csdn.net/lyhhj>

###License
