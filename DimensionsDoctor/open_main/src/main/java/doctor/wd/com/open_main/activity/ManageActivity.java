package doctor.wd.com.open_main.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import doctor.wd.com.open_main.R;
import doctor.wd.com.open_main.presenter.FindDoctorPresenter;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
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

import java.io.File;
import java.util.List;

@Route(path = Constant.ACTIVITY_URL_MANAGE)
public class ManageActivity extends BaseActivity implements View.OnClickListener {
    private String file = Environment.getExternalStorageDirectory() + "/t.png";
    private ImageButton backMy;
    private ImageButton massageMy;
    private RelativeLayout change;//展示的个人信息照片
    private TextView loadeMore;
    private LinearLayout myWallet, myRecommendation, myAutomatic, myHistory;
    private PopupWindow mPopWindow;
    private LinearLayout allb;
    private LinearLayout imaglin;


    private ImageButton back;
    private TextView myname, hospital, keshi, job, jieshao, goods;
    private LoginBeanDao loginBeanDao;
    private List<LoginBean> loginBeans;
    private FindDoctorPresenter findDoctorPresenter;
    private Long id;
    private String sessionId;
    private ImageView myage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_manage;
    }

    @Override
    protected void initView() {
        backMy = findViewById(R.id.back_my);
        massageMy = findViewById(R.id.message_my);
        change = findViewById(R.id.changeimg);
        loadeMore = findViewById(R.id.loade_more);
        myHistory = findViewById(R.id.my_history);
        myWallet = findViewById(R.id.my_wallet);
        myAutomatic = findViewById(R.id.my_automatic);
        myRecommendation = findViewById(R.id.my_recommendations);
        allb = findViewById(R.id.all_b);
        imaglin = findViewById(R.id.imag_lin);

        back = findViewById(R.id.back_f);
        myname = findViewById(R.id.myname);
        hospital = findViewById(R.id.hospitol_n);
        keshi = findViewById(R.id.keshi);
        job = findViewById(R.id.job_c);
        jieshao = findViewById(R.id.jieshao);
        goods = findViewById(R.id.goods_j);
        myage = findViewById(R.id.myimage);

        loginBeanDao = DaoMaster.newDevSession(getBaseContext(), LoginBeanDao.TABLENAME).getLoginBeanDao();
        loginBeans = loginBeanDao.loadAll();
        id = loginBeans.get(0).getId();
        sessionId = loginBeans.get(0).getSessionId();
        findDoctorPresenter = new FindDoctorPresenter(new findCalla());
        findDoctorPresenter.reqeust(id + "", sessionId);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allb.setVisibility(View.VISIBLE);
                imaglin.setVisibility(View.GONE);
            }
        });

        myRecommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_AGREAD);
            }
        });
        backMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_MAIN);
                finish();
            }
        });
        loadeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                allb.setVisibility(View.GONE);
                imaglin.setVisibility(View.VISIBLE);
//                intentByRouter(Constant.ACTIVITY_URL_INFOMATION);


            }
        });
        myHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_HISTORY);
            }
        });

        myWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_WALLT);
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CHange();
                findDoctorPresenter.reqeust(id + "", sessionId);

            }
        });

        massageMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_MESS);
            }
        });
        myAutomatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_AUTOMA);
            }
        });
    }

    private void CHange() {
        View contentView = LayoutInflater.from(getBaseContext()).inflate(R.layout.layout_pop, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv1 = (TextView) contentView.findViewById(R.id.pop_computer);
        TextView tv2 = (TextView) contentView.findViewById(R.id.pop_financial);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
//当点击popwindow以外的地方关闭窗口
        mPopWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        mPopWindow.setOutsideTouchable(true);
        View rootview = LayoutInflater.from(getBaseContext()).inflate(R.layout.activity_manage, null);
        //显示的位置
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 10);

    }

    @Override
    protected void destoryData() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.pop_computer) {// 点击事件
//            mPopWindow.dismiss();//关闭窗口

            intentByRouter(Constant.ACTIVITY_URL_IMAG);


        } else if (id == R.id.pop_financial) {// 点击事件
            mPopWindow.dismiss();//关闭窗口
            findDoctorPresenter.reqeust(id + "", sessionId);

        }

    }

    protected   class findCalla implements Cinterface<FindDoctorBean> {


        @Override
        public void success(FindDoctorBean data, Object... args) {
            Log.e("hh", data + "");
            myname.setText(data.getName());
            hospital.setText(data.getInauguralHospital());
            keshi.setText(data.getDepartmentName());
            job.setText(data.getJobTitle());
            jieshao.setText(data.getGoodField());
            goods.setText(data.getGoodField());
            Glide.with(getBaseContext()).load(data.getImagePic()).into(myage);

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
