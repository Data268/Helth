package com.wd.doctor.open_login.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.doctor.common.bean.FindSystemImageBean;
import com.wd.doctor.common.bean.LoginBean;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.db.DaoMaster;
import com.wd.doctor.common.core.db.LoginBeanDao;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.Constant;
import com.wd.doctor.common.utils.UIUtils;
import com.wd.doctor.open_login.R;
import com.wd.doctor.open_login.frag.Fragmentone;
import com.wd.doctor.open_login.frag.Fragmenttwo;
import com.wd.doctor.open_login.presenter.ChoosePresenter;

import java.util.ArrayList;
import java.util.List;

@Route(path = Constant.ACTIVITY_URL_VISUALIZE)
public class VisualizeActivity extends BaseActivity {

    private ViewPager vpager;
    private Button surebtn;
    private ArrayList<Fragment> imglist;
    private RadioButton rb1,rb2,rb3,rb4;
    private RadioGroup rg;
    private SharedPreferences mfirst;
    private ChoosePresenter choosePresenter;
    private LoginBeanDao loginBeanDao;
    private List<LoginBean> loginBeans;
    private Long id;
    private String sessionId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_visualize;
    }

    @Override
    protected void initView() {
        vpager = findViewById(R.id.vpage);
        surebtn = findViewById(R.id.sure_btn);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 =findViewById(R.id.rb3);
        rb4 =findViewById(R.id.rb4);
        rg = findViewById(R.id.rg);

        loginBeanDao = DaoMaster.newDevSession(getBaseContext(), LoginBeanDao.TABLENAME).getLoginBeanDao();
        loginBeans = loginBeanDao.loadAll();
        id = loginBeans.get(0).getId();
        sessionId = loginBeans.get(0).getSessionId();

        //选择证件照
        choosePresenter = new ChoosePresenter(new chooseCall());
//        choosePresenter.reqeust(id+"",sessionId,);




        mfirst = getSharedPreferences("mfirst", MODE_PRIVATE);
        boolean flag = mfirst.getBoolean("flag", false);
        if (flag){
            intentByRouter(Constant.ACTIVITY_URL_MAIN);
            finish();
        }

        surebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mfirst.edit().putBoolean("flag",true).commit();
//                choosePresenter.reqeust(id +"", sessionId,boy);

                intentByRouter(Constant.ACTIVITY_URL_MAIN);
                finish();
            }
        });

        imglist = new ArrayList<>();
        imglist.add(new Fragmentone());
        imglist.add(new Fragmenttwo());



        vpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return imglist.get(position);
            }

            @Override
            public int getCount() {
                return imglist.size();
            }
        });


        vpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private boolean flag;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rg.check(R.id.rb1);
                        break;
                    case 1:
                        rg.check(R.id.rb2);
                        break;
                    case 2:
                        rg.check(R.id.rb3);
                        break;
                    case 3:
                        rg.check(R.id.rb4);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state){
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        flag=false;
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        flag=true;
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (vpager.getCurrentItem()==vpager.getAdapter().getCount() -1 && !flag){
                            intentByRouter(Constant.ACTIVITY_URL_IMAG);
                            finish();
                        }
                        flag=true;
                        break;
                }
            }
        });

    }

    @Override
    protected void destoryData() {

    }


    public static class chooseCall implements Cinterface {
        @Override
        public void success(Object data, Object... args) {
            UIUtils.showToastSafe("选择了系统形象照");

        }

        @Override
        public void fail(ApiException data, Object... args) {
            UIUtils.showToastSafe(data.getCode() + "===" + data.getDisplayMessage());

        }
    }
}
