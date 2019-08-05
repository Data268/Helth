package doctor.wd.com.open_main.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import doctor.wd.com.open_main.R;
import doctor.wd.com.open_main.adapter.MyCurrAdapter;
import doctor.wd.com.open_main.presenter.CurrencyNotPresenter;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.doctor.common.bean.CurrencyNotBean;
import com.wd.doctor.common.bean.LoginBean;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.db.DaoMaster;
import com.wd.doctor.common.core.db.LoginBeanDao;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.Constant;

import java.util.List;

@Route(path = Constant.ACTIVITY_URL_HMONEY)
public class Hmoney2Activity extends BaseActivity {

    private ImageButton back;
    private LinearLayout honeyLin;
    private RecyclerView receh;
    private LoginBeanDao loginBeanDao;
    private List<LoginBean> loginBeans;
    private Long id;
    private String sessionId;
    private CurrencyNotPresenter currencyNotPresenter;
    private MyCurrAdapter myCurrAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hmoney2;
    }

    @Override
    protected void initView() {
        back = findViewById(R.id.back_h);
        honeyLin = findViewById(R.id.hmoney_lin1);
        receh = findViewById(R.id.rece_h);
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

        currencyNotPresenter = new CurrencyNotPresenter(new curNotCall());
        receh.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }

    @Override
    protected void destoryData() {

    }

    private class curNotCall implements Cinterface<List<CurrencyNotBean>> {
        @Override
        public void success(List<CurrencyNotBean> data, Object... args) {
            myCurrAdapter = new MyCurrAdapter(getBaseContext(), data);
            receh.setAdapter(myCurrAdapter);
            if (data == null){
                honeyLin.setVisibility(View.VISIBLE);
            }else {
                currencyNotPresenter.reqeust(id+"",sessionId,1,5);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
