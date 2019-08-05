package doctor.wd.com.open_main.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import doctor.wd.com.open_main.R;
import doctor.wd.com.open_main.adapter.MyAdopadapter;
import doctor.wd.com.open_main.presenter.MyAdoptedPresenter;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.doctor.common.bean.LoginBean;
import com.wd.doctor.common.bean.MyAdoptedBean;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.db.DaoMaster;
import com.wd.doctor.common.core.db.LoginBeanDao;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.Constant;

import java.util.List;

@Route(path = Constant.ACTIVITY_URL_AGREAD)
public class AgreadActivity extends BaseActivity {

    private ImageButton back;
    private LinearLayout agread;
    private RecyclerView receagread;
    private MyAdoptedPresenter myAdoptedPresenter;
    private LoginBeanDao loginBeanDao;
    private List<LoginBean> loginBeans;
    private Long id;
    private String sessionId;
    private MyAdopadapter myAdopadapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_agread;
    }

    @Override
    protected void initView() {
        back = findViewById(R.id.back_gread);
        agread = findViewById(R.id.agread_lin1);
        receagread = findViewById(R.id.rece_agread);

        loginBeanDao = DaoMaster.newDevSession(getBaseContext(), LoginBeanDao.TABLENAME).getLoginBeanDao();
        loginBeans = loginBeanDao.loadAll();
        id = loginBeans.get(0).getId();
        sessionId = loginBeans.get(0).getSessionId();


        myAdoptedPresenter = new MyAdoptedPresenter(new myAdopteCall());
        myAdoptedPresenter.reqeust(id+"",sessionId,1,5);

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

    private class myAdopteCall implements Cinterface<List<MyAdoptedBean>> {
        @Override
        public void success(List<MyAdoptedBean> data, Object... args) {

            receagread.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            myAdopadapter = new MyAdopadapter(getBaseContext(), data);
            receagread.setAdapter(myAdopadapter);
            if (data==null){
                agread.setVisibility(View.VISIBLE);
            }else {
                myAdoptedPresenter.reqeust(id+"",sessionId,1,5);
            }

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
