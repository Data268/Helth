package doctor.wd.com.open_main.activity;

import androidx.appcompat.app.AppCompatActivity;
import doctor.wd.com.open_main.R;
import doctor.wd.com.open_main.presenter.FindDoctorPresenter;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.wd.doctor.common.bean.FindDoctorBean;
import com.wd.doctor.common.bean.LoginBean;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.db.DaoMaster;
import com.wd.doctor.common.core.db.LoginBeanDao;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.Constant;

import java.util.List;

@Route(path = Constant.ACTIVITY_URL_MAIN)
public class MainActivity extends BaseActivity {

    private Button inquiryPage;
    private Button askPage;
    private Button managerPage;
    private ImageView fpresonl;
    private TextView kn, phsician,hospitolName, doctorId;
    private List<LoginBean> loginBeans;
    private Long id;
    private String sessionId;

    private String name;
    private String inauguralHospital;
    private String jobTitle;
    private String departmentName;
    private ImageView messages;
    private String imagePic;
    private FindDoctorPresenter findDoctorPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        inquiryPage = findViewById(R.id.inquiry_page);
        askPage = findViewById(R.id.ask_page);
        messages = findViewById(R.id.message_s);
        managerPage = findViewById(R.id.manager_page);
        fpresonl = findViewById(R.id.f_presonl);
        doctorId = findViewById(R.id.doctor_id);
        hospitolName = findViewById(R.id.hospitol_name);
        phsician = findViewById(R.id.physician_n);
        kn = findViewById(R.id.k_n);
        LoginBeanDao loginBeanDao = DaoMaster.newDevSession(getBaseContext(), LoginBeanDao.TABLENAME).getLoginBeanDao();
        loginBeans = loginBeanDao.loadAll();
        id = loginBeans.get(0).getId();
        sessionId = loginBeans.get(0).getSessionId();

        findDoctorPresenter = new FindDoctorPresenter(new findCall());

        findDoctorPresenter.reqeust(id + "", sessionId);

        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_MESS);
            }
        });

        managerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_MANAGE);
            }
        });
        askPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_ASK);
            }
        });
        inquiryPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    intentByRouter(Constant.ACTIVITY_URL_INQUIRY);
            }
        });

    }

    @Override
    protected void destoryData() {

    }

    private class findCall implements Cinterface<FindDoctorBean> {
        @Override
        public void success(FindDoctorBean data, Object... args) {
            Glide.with(getBaseContext()).load(Uri.parse(data.getImagePic())).into(fpresonl);
            doctorId.setText(data.getName());
            hospitolName.setText(data.getInauguralHospital());
            phsician.setText(data.getJobTitle());
            kn.setText(data.getDepartmentName());

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
