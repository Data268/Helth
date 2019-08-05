package doctor.wd.com.open_main.activity.wode;

import androidx.appcompat.app.AppCompatActivity;
import doctor.wd.com.open_main.R;
import doctor.wd.com.open_main.presenter.DetailInfoPresenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.doctor.common.bean.DetailBean;
import com.wd.doctor.common.bean.LoginBean;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.db.DaoMaster;
import com.wd.doctor.common.core.db.LoginBeanDao;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.DateUtils;
import com.wd.doctor.common.utils.UIUtils;

import java.text.ParseException;
import java.util.List;

public class UserInfoActivity extends AppCompatActivity {

    private ImageButton back;
    private SimpleDraweeView hand;
    private TextView name;
    private TextView height;
    private TextView weight;
    private TextView age;
    private TextView ill;
    private TextView illnow;
    private TextView illago;
    private TextView illyes;
    private ImageView illpic;
    private DetailInfoPresenter detailInfoPresenter;
    private LoginBeanDao loginBeanDao;
    private List<LoginBean> loginBeans;
    private Long id;
    private String sessionId;
    private Intent intent;
    private int userIds;
    private TextView adress;
    private TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        back = findViewById(R.id.back_info);
        hand = findViewById(R.id.hand_info);
        name = findViewById(R.id.user_info);
        height = findViewById(R.id.height_info);
        weight = findViewById(R.id.weight_info);
        age = findViewById(R.id.age_info);
        ill = findViewById(R.id.ill_info);
        illnow = findViewById(R.id.ill_now_info);
        illago = findViewById(R.id.ill_ago_info);
        illyes = findViewById(R.id.ill_yes_info);
        illpic = findViewById(R.id.ill_picture_info);
        adress = findViewById(R.id.adress_info);
        time = findViewById(R.id.time_info);

        loginBeanDao = DaoMaster.newDevSession(getBaseContext(), LoginBeanDao.TABLENAME).getLoginBeanDao();
        loginBeans = loginBeanDao.loadAll();
        id = loginBeans.get(0).getId();
        sessionId = loginBeans.get(0).getSessionId();

        intent = getIntent();
        userIds = intent.getIntExtra("userIds", 0);


        detailInfoPresenter = new DetailInfoPresenter(new infoCall());
        detailInfoPresenter.reqeust(id+"",sessionId,userIds);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private class infoCall implements Cinterface<DetailBean> {

        @Override
        public void success(DetailBean data, Object... args) {
            UIUtils.showToastSafe("成功oo");
            Glide.with(getBaseContext()).load(data.getUserHeadPic()).into(hand);
            name.setText(data.getNickName());
            height.setText(data.getHeight()+"");
            weight.setText(data.getWeight()+"");
            age.setText(data.getAge()+"");
            ill.setText(data.getDiseaseMain());
            illnow.setText(data.getDiseaseNow());
            illago.setText(data.getDiseaseBefore());
            illyes.setText(data.getTreatmentProcess());
            adress.setText(data.getTreatmentHospitalRecent());
            try {
                time.setText(DateUtils.dateTransformer(data.getTreatmentEndTime(),DateUtils.MINUTE_PATTERN));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void fail(ApiException data, Object... args) {
            UIUtils.showToastSafe(data.getCode()+""+data.getDisplayMessage());
        }
    }
}
