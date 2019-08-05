package doctor.wd.com.open_main.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import doctor.wd.com.open_main.R;
import doctor.wd.com.open_main.adapter.MyAskNotAdapter;
import doctor.wd.com.open_main.presenter.InquiryNotPresenter;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.doctor.common.bean.InquiryBean;
import com.wd.doctor.common.bean.InquiryNotBean;
import com.wd.doctor.common.bean.LoginBean;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.db.DaoMaster;
import com.wd.doctor.common.core.db.LoginBeanDao;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.Constant;

import java.util.List;

@Route(path = Constant.ACTIVITY_URL_ASKMESSAGE)
public class AskmessageActivity extends BaseActivity {

    private ImageButton back;
    private RecyclerView receask;
    private LinearLayout askLin;
    private InquiryNotPresenter inquiryNotPresenter;
    private LoginBeanDao loginBeanDao;
    private List<LoginBean> loginBeans;
    private Long id;
    private String sessionId;
    private MyAskNotAdapter myAskNotAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_askmessage;
    }

    @Override
    protected void initView() {
        back = findViewById(R.id.back_ask);
        receask = findViewById(R.id.rece_ask);
        askLin = findViewById(R.id.ask_lin1);

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
        inquiryNotPresenter = new InquiryNotPresenter(new askNotCall());
        receask.setLayoutManager(new LinearLayoutManager(getBaseContext()));

    }

    @Override
    protected void destoryData() {

    }

    private class askNotCall implements Cinterface<List<InquiryNotBean>> {
        @Override
        public void success(List<InquiryNotBean> data, Object... args) {
            myAskNotAdapter = new MyAskNotAdapter(getBaseContext(),data);
            receask.setAdapter(myAskNotAdapter);
            if (data == null){
                askLin.setVisibility(View.VISIBLE);
            }else {
                inquiryNotPresenter.reqeust(id+"",sessionId,1,5);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
