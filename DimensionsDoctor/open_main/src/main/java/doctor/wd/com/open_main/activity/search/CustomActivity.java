package doctor.wd.com.open_main.activity.search;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import doctor.wd.com.open_main.R;
import doctor.wd.com.open_main.adapter.MySearchAdapter;
import doctor.wd.com.open_main.presenter.SearchCirclePresenter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.doctor.common.bean.SearchcircleBean;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.Constant;

import java.util.List;

@Route(path = Constant.ACTIVITY_URL_CUSTOM)
public class CustomActivity extends BaseActivity {

    String[] name = {"手机", "电脑", "苹果手机", "笔记本电脑", "电饭煲 ", "腊肉", "宝宝", "康佳"};
    private ImageView mSearchBack;
    private ImageView mRelationSearch;
    private View mSearchLine;
    /**
     * 请输入关键词搜索
     */
    private EditText mSearchInputSearch;
    /**
     * 搜索
     */
    private TextView mResultSearch;
    private CustomView mSearchFlowlayout;
    /**
     * 清空记录
     */
    private Button mSearchClear;
    private XRecyclerView mSearchList;
    private TextView textView;
    private SearchCirclePresenter searchCirclePresenter;
    private MySearchAdapter mySearchAdapter;
    private String s;

    private TextView mCircleNullText;
    private LinearLayout mCircleNullId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom;
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView() {
        mCircleNullText = (TextView) findViewById(R.id.circle_null_text);
        mCircleNullId = (LinearLayout) findViewById(R.id.circle_null_id);
        mSearchBack = (ImageView) findViewById(R.id.search_back);
        mRelationSearch = (ImageView) findViewById(R.id.relation_search);
        mSearchLine = (View) findViewById(R.id.search_line);
        mSearchInputSearch = (EditText) findViewById(R.id.search_input_search);
        mResultSearch = (TextView) findViewById(R.id.result_search);
        mSearchFlowlayout = (CustomView) findViewById(R.id.search_flowlayout);
        mSearchClear = (Button) findViewById(R.id.search_clear);
        mSearchList = (XRecyclerView) findViewById(R.id.search_list);


        //搜索的p层
        searchCirclePresenter = new SearchCirclePresenter(new customCall());
        //搜索的适配器
        mySearchAdapter = new MySearchAdapter(this);
        mSearchList.setLayoutManager(new LinearLayoutManager(this));
        mSearchList.setPullRefreshEnabled(true);
        mSearchList.setAdapter(mySearchAdapter);
        mSearchList.setLoadingMoreEnabled(true);
        mSearchList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                searchCirclePresenter.reqeust(s);
                mSearchList.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                searchCirclePresenter.reqeust(s);
                mSearchList.loadMoreComplete();

            }
        });


        mSearchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSearchClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchFlowlayout.removeAllViews();
            }
        });

        //设置默认显示
        for (int i = 0; i < name.length; i++) {
            textView = new TextView(this);
            textView.setText(name[i]);
            //设置背景
            textView.setBackground(getDrawable(R.drawable.addatten_edit));
            //设置内边距
            textView.setPadding(5, 5, 5, 5);
            textView.setTextSize(20);
            //设置颜色
            textView.setTextColor(Color.GRAY);
            //添加数据
            mSearchFlowlayout.addView(textView);
        }
        //点击搜索添加
        mResultSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = mSearchInputSearch.getText().toString();
                textView = new TextView(CustomActivity.this);
                textView.setBackground(getDrawable(R.drawable.addatten_edit));
                textView.setPadding(5, 5, 5, 5);
                textView.setTextSize(20);
                textView.setText(s);
                searchCirclePresenter.reqeust(s);
                mSearchFlowlayout.addView(textView);
            }
        });
    }

    @Override
    protected void destoryData() {

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private class customCall implements Cinterface<List<SearchcircleBean>> {
        @Override
        public void success(List<SearchcircleBean> data, Object... args) {

            if (data.size() > 0) {
                mySearchAdapter.addList(data);
                mySearchAdapter.notifyDataSetChanged();
            } else {
                mCircleNullText.setText("抱歉！没有找到你 “"+s+"” 的相关内容");
                mCircleNullId.setVisibility(View.VISIBLE);
                mSearchList.setVisibility(View.GONE);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
