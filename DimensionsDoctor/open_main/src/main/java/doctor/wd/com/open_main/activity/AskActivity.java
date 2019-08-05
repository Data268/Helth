package doctor.wd.com.open_main.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import doctor.wd.com.open_main.R;
import doctor.wd.com.open_main.activity.search.CustomActivity;
import doctor.wd.com.open_main.adapter.MyDepartmentAdapter;
import doctor.wd.com.open_main.adapter.MySickAdapter;
import doctor.wd.com.open_main.presenter.FindDepartmentPresenter;
import doctor.wd.com.open_main.presenter.FindSickPresenter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.doctor.common.bean.Department;
import com.wd.doctor.common.bean.FindSickBean;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.Constant;
import com.wd.doctor.common.utils.UIUtils;


import java.util.List;

@Route(path = Constant.ACTIVITY_URL_ASK)
public class AskActivity extends BaseActivity {

    private ImageButton back;
    private ImageButton message_b;
    private RecyclerView titleTop;
    private FindDepartmentPresenter findDepartmentPresenter;
    private MyDepartmentAdapter myDepartmentAdapter;
    private FindSickPresenter findSickPresenter;
    private MySickAdapter mySickAdapter;
    private XRecyclerView listItem;
    private ImageButton search_b;
    private RelativeLayout top_s;
    private LinearLayout edit_q;
    private Intent intent;
    private int idss;
    private String departmentName;
    private TextView kname;
    private ImageButton ling;
    int page = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ask;
    }

    @Override
    protected void initView() {

        back = findViewById(R.id.back_b);
        message_b = findViewById(R.id.message_b);
        titleTop = findViewById(R.id.title_top);
        listItem = findViewById(R.id.list_item_b);
        search_b = findViewById(R.id.search_bt);
        top_s = findViewById(R.id.top_s);
        edit_q = findViewById(R.id.edit_q);
        kname = findViewById(R.id.kname);
        ling = findViewById(R.id.ling);


        //关于科室
        findDepartmentPresenter = new FindDepartmentPresenter(new findCall());
        findDepartmentPresenter.reqeust();
        //病友圈列表展示

        findSickPresenter = new FindSickPresenter(new sickCall());
        findSickPresenter.reqeust(7,2,5);

        if(idss==0){

        }else{
            findSickPresenter.reqeust(idss,page,10);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        titleTop.setLayoutManager(linearLayoutManager);

        listItem.setLayoutManager(new LinearLayoutManager(this));

        listItem.setPullRefreshEnabled(true);
        listItem.setLoadingMoreEnabled(true);

        listItem.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page =1;
                findSickPresenter.reqeust(idss,page,10);
                listItem.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                findSickPresenter.reqeust(idss,page,10);
                listItem.loadMoreComplete();
            }
        });


        search_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kname.setText(departmentName);
//                intentByRouter(Constant.ACTIVITY_URL_SEARCH);
                intentByRouter(Constant.ACTIVITY_URL_CUSTOM);

            }
        });

        ling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_MESS);
            }
        });
        message_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_MESS);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    @Override
    protected void destoryData() {

    }

    private class findCall implements Cinterface<List<Department>> {
        @Override
        public void success(List<Department> data, Object... args) {
            UIUtils.showToastSafe("成功");
            myDepartmentAdapter = new MyDepartmentAdapter(getBaseContext(), data);

            titleTop.setAdapter(myDepartmentAdapter);
            myDepartmentAdapter.setOnClickListr(new MyDepartmentAdapter.OnClickListener() {
                @Override
                public void onClick(Department department) {
                    idss = department.getId();
                    departmentName = department.getDepartmentName();
                    findSickPresenter.reqeust(idss,2,10);
                    kname.setText(departmentName);
                    myDepartmentAdapter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class sickCall implements Cinterface<List<FindSickBean>> {
        @Override
        public void success(List<FindSickBean> data, Object... args) {
            mySickAdapter = new MySickAdapter(getBaseContext(), data);
            listItem.setAdapter(mySickAdapter);
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }



}
