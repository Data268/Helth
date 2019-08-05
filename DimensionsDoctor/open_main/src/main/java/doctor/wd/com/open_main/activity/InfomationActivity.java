package doctor.wd.com.open_main.activity;

import androidx.appcompat.app.AppCompatActivity;
import doctor.wd.com.open_main.R;
import doctor.wd.com.open_main.presenter.FindDoctorPresenter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.doctor.common.bean.FindDoctorBean;
import com.wd.doctor.common.bean.LoginBean;
import com.wd.doctor.common.bean.be.Cantant;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.db.DaoMaster;
import com.wd.doctor.common.core.db.LoginBeanDao;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.Constant;

import java.util.List;

@Route(path = Constant.ACTIVITY_URL_INFOMATION)
public class InfomationActivity extends BaseActivity {

    private ImageButton back;
    private TextView myname,hospital,keshi,job,jieshao,goods;
    private LoginBeanDao loginBeanDao;
    private List<LoginBean> loginBeans;
    private FindDoctorPresenter findDoctorPresenter;
    private Long id;
    private String sessionId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_infomation;


    }

    @Override
    protected void initView() {
        back = findViewById(R.id.back_f);
        myname = findViewById(R.id.myname);
        hospital = findViewById(R.id.hospitol_n);
        keshi = findViewById(R.id.keshi);
        job = findViewById(R.id.job_c);
        jieshao = findViewById(R.id.jieshao);
        goods = findViewById(R.id.goods_j);

        loginBeanDao = DaoMaster.newDevSession(getBaseContext(), LoginBeanDao.TABLENAME).getLoginBeanDao();
        loginBeans = loginBeanDao.loadAll();
        id = loginBeans.get(0).getId();
        sessionId = loginBeans.get(0).getSessionId();

        myname.setText(loginBeans.get(0).getName());
        hospital.setText(loginBeans.get(0).getInauguralHospital());
        keshi.setText(loginBeans.get(0).getDepartmentName());
        job.setText(loginBeans.get(0).getJobTitle());

        findDoctorPresenter = new FindDoctorPresenter(new findCall());
        findDoctorPresenter.reqeust(id+"",sessionId);

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

    private class findCall implements Cinterface<FindDoctorBean> {


        @Override
        public void success(FindDoctorBean data, Object... args) {
            Log.e("hh",data+"");
            myname.setText(data.getName());
            hospital.setText(data .getInauguralHospital());
            keshi.setText(data.getDepartmentName());
            job.setText(data.getJobTitle());
            jieshao.setText(data.getGoodField());
            goods.setText(data.getGoodField());
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
