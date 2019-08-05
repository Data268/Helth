package doctor.wd.com.open_main.activity.wode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import doctor.wd.com.open_main.R;
import doctor.wd.com.open_main.adapter.MySearchAdapter;
import doctor.wd.com.open_main.presenter.SearchCirclePresenter;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.doctor.common.bean.SearchcircleBean;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.Constant;

import java.util.List;

@Route(path = Constant.ACTIVITY_URL_SEARCH)
public class SearchActivity extends BaseActivity {

    private TextView mCircleNullText;
    private LinearLayout mCircleNullId;
    private ImageView mCircleSearchBack;

    private EditText mCircleSearchEdit;

    private TextView mCircleBtnSearch;
    private RelativeLayout mCircleSearchRe1;
    private RecyclerView mCircleSearchRecy;
    private String keyWord;
    private SearchCirclePresenter searchCirclePresenter;
    private MySearchAdapter mySearchAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        mCircleNullText = (TextView) findViewById(R.id.circle_null_text);
        mCircleNullId = (LinearLayout) findViewById(R.id.circle_null_id);
        mCircleSearchBack = (ImageView) findViewById(R.id.circle_search_back);//退出
        mCircleSearchEdit = (EditText) findViewById(R.id.circle_search_edit);//搜索框
        mCircleBtnSearch = (TextView) findViewById(R.id.circle_btn_search); //搜索
        mCircleSearchRe1 = (RelativeLayout) findViewById(R.id.circle_search_re1);
        mCircleSearchRecy = (RecyclerView) findViewById(R.id.circle_search_recy);

        searchCirclePresenter = new SearchCirclePresenter(new searchCall());

        mySearchAdapter = new MySearchAdapter(this);
        mCircleSearchRecy.setLayoutManager(new LinearLayoutManager(this));
        mCircleSearchRecy.setAdapter(mySearchAdapter);
        mCircleSearchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //点击‘搜索’进行查询内容
        mCircleBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyWord = mCircleSearchEdit.getText().toString().trim();
                if (TextUtils.isEmpty(keyWord)){
                    Toast.makeText(SearchActivity.this, "请输入要搜索的内容！", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    searchCirclePresenter.reqeust(keyWord);
                    mySearchAdapter.notifyDataSetChanged();
                }

            }
        });
        //点击返回fragment
        mCircleSearchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void destoryData() {

    }

    private class searchCall implements Cinterface<List<SearchcircleBean>> {
        @Override
        public void success(List<SearchcircleBean> data, Object... args) {

            if (data.size()>0){

                mySearchAdapter.addList(data);
                mySearchAdapter.notifyDataSetChanged();
            }else {
                mCircleNullText.setText("抱歉！没有找到你 “"+keyWord+"” 的相关内容");
                mCircleNullId.setVisibility(View.VISIBLE);
                mCircleSearchRecy.setVisibility(View.GONE);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
