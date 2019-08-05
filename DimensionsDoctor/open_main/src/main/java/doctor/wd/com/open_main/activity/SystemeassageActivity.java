package doctor.wd.com.open_main.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import doctor.wd.com.open_main.R;
import doctor.wd.com.open_main.adapter.MySysAdapter;
import doctor.wd.com.open_main.presenter.SystemmessagePresenter;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.doctor.common.bean.LoginBean;
import com.wd.doctor.common.bean.SystemmessageBean;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.db.DaoMaster;
import com.wd.doctor.common.core.db.LoginBeanDao;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.Constant;

import java.util.List;

@Route(path = Constant.ACTIVITY_URL_SYSTEM)
public class SystemeassageActivity extends BaseActivity {

    private LinearLayout syslin;
    private RecyclerView sys_rc;
    private SystemmessagePresenter systemmessagePresenter;
    private LoginBeanDao loginBeanDao;
    private List<LoginBean> loginBeans;
    private Long id;
    private String sessionId;
    private MySysAdapter mySysAdapter;
    private ImageButton back;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_systemeassage;
    }

    @Override
    protected void initView() {
        syslin = findViewById(R.id.sys_lin1);
        sys_rc = findViewById(R.id.rece_sys);
        back = findViewById(R.id.back_sys);
        loginBeanDao = DaoMaster.newDevSession(getBaseContext(), LoginBeanDao.TABLENAME).getLoginBeanDao();
        loginBeans = loginBeanDao.loadAll();
        id = loginBeans.get(0).getId();
        sessionId = loginBeans.get(0).getSessionId();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        systemmessagePresenter = new SystemmessagePresenter(new sMyCall());
        sys_rc.setLayoutManager(new LinearLayoutManager(getBaseContext()));


    }

    @Override
    protected void destoryData() {

    }

    private class sMyCall implements Cinterface<List<SystemmessageBean>> {
        @Override
        public void success(List<SystemmessageBean> data, Object... args) {
            mySysAdapter = new MySysAdapter(getBaseContext(), data);
            sys_rc.setAdapter(mySysAdapter);
            if (data == null){
                syslin.setVisibility(View.VISIBLE);
            }else {
                systemmessagePresenter.reqeust(id+"",sessionId,1,5);
            }

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
