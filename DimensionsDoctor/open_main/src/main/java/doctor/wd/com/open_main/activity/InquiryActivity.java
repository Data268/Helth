package doctor.wd.com.open_main.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import doctor.wd.com.open_main.R;
import doctor.wd.com.open_main.adapter.MyInquiryAdapter;
import doctor.wd.com.open_main.presenter.InquityPresenter;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.doctor.common.bean.InquiryBean;
import com.wd.doctor.common.bean.LoginBean;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.db.DaoMaster;
import com.wd.doctor.common.core.db.LoginBeanDao;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.Constant;

import com.wd.doctor.common.core.BaseActivity;

import java.util.List;

@Route(path = Constant.ACTIVITY_URL_INQUIRY)
public class InquiryActivity extends BaseActivity {

    private InquityPresenter inquityPresenter;
    private ImageButton back;
    private RecyclerView receitem;
    private MyInquiryAdapter myInquiryAdapter;
    private LoginBeanDao loginBeanDao;
    private List<LoginBean> loginBeans;
    private Long id;
    private String sessionId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inquiry;
    }

    @Override
    protected void initView() {
        back = findViewById(R.id.back_in);
        receitem = findViewById(R.id.recy_item);

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

        inquityPresenter = new InquityPresenter(new InquityCall());
        inquityPresenter.reqeust(id+"",sessionId);

        receitem.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    protected void destoryData() {

    }

    private class InquityCall implements Cinterface<List<InquiryBean>> {
        @Override
        public void success(List<InquiryBean> data, Object... args) {

            myInquiryAdapter = new MyInquiryAdapter(InquiryActivity.this,data);
            receitem.setAdapter(myInquiryAdapter);

            myInquiryAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
