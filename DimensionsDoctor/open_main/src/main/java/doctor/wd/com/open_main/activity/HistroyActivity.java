package doctor.wd.com.open_main.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import doctor.wd.com.open_main.R;
import doctor.wd.com.open_main.adapter.MyHistroyAdapter;
import doctor.wd.com.open_main.presenter.HistroyPresenter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.doctor.common.bean.HistroyBean;
import com.wd.doctor.common.bean.LoginBean;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.db.DaoMaster;
import com.wd.doctor.common.core.db.LoginBeanDao;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.Constant;

import java.util.List;

@Route(path = Constant.ACTIVITY_URL_HISTORY)
public class HistroyActivity extends BaseActivity {

    private ImageButton back;
    private TextView gets;//获取礼物

    private HistroyPresenter histroyPresenter;
    private LoginBeanDao loginBeanDao;
    private List<LoginBean> loginBeans;
    private Long id;
    private String sessionId;
    private XRecyclerView histroy;
    int page = 1;
    private MyHistroyAdapter myHistroyAdapter;
    private LinearLayout lin1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_histroy;
    }

    @Override
    protected void initView() {
        back = findViewById(R.id.back_hi);
        gets = findViewById(R.id.get_d);
        histroy = findViewById(R.id.recehistory);
        lin1 = findViewById(R.id.histroy_lin1);


        loginBeanDao = DaoMaster.newDevSession(getBaseContext(), LoginBeanDao.TABLENAME).getLoginBeanDao();
        loginBeans = loginBeanDao.loadAll();
        id = loginBeans.get(0).getId();
        sessionId = loginBeans.get(0).getSessionId();

        //presenter历史问诊记录
        histroyPresenter = new HistroyPresenter(new hiCall());
        histroyPresenter.reqeust(id+"",sessionId,page,5);

        histroy.setLayoutManager(new LinearLayoutManager(this));

        histroy.setLoadingMoreEnabled(true);
        histroy.setPullRefreshEnabled(true);

        histroy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                histroyPresenter.reqeust(id+"",sessionId,page,5);
                histroy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                histroyPresenter.reqeust(id+"",sessionId,page,5);
                histroy.loadMoreComplete();

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

    private class hiCall implements Cinterface<List<HistroyBean>> {
        @Override
        public void success(List<HistroyBean> data, Object... args) {
            myHistroyAdapter = new MyHistroyAdapter(getBaseContext(), data);
            histroy.setAdapter(myHistroyAdapter);
            if (data == null){
                lin1.setVisibility(View.VISIBLE);
            }else {
                histroyPresenter.reqeust(id+"",sessionId,page,5);
            }

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
