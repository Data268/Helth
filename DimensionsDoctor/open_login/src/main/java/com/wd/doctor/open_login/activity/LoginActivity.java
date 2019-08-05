package com.wd.doctor.open_login.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.doctor.common.bean.DataBean;
import com.wd.doctor.common.bean.LoginBean;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.db.DaoMaster;
import com.wd.doctor.common.core.db.LoginBeanDao;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.Constant;
import com.wd.doctor.common.utils.RsaCoder;
import com.wd.doctor.common.utils.UIUtils;
import com.wd.doctor.open_login.R;
import com.wd.doctor.open_login.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = Constant.ACTIVITY_URL_LOGIN)
public class LoginActivity extends BaseActivity {


    private Button logGo;;
    private Button logReg;
    private Button logRem;
    private EditText logPwd;
    private  ImageView logLock;
    private EditText logEdit;
    private ImageView logMail;
    private ImageView logeye;
    private LoginPresenter loginPresenter;
    private String ss;
    private SharedPreferences getfirst;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        logGo = findViewById(R.id.log_go);
        logReg = findViewById(R.id.log_reg);
        logRem = findViewById(R.id.log_rem);
        logPwd = findViewById(R.id.log_pwd);
        logLock = findViewById(R.id.log_lock);
        logEdit = findViewById(R.id.log_edit);
        logMail = findViewById(R.id.log_mail);
        logeye = findViewById(R.id.log_eye);

        //登录接口
        loginPresenter = new LoginPresenter(new logCall());

        logeye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                logPwd.setText("000");
                //显示隐藏密码

                if (logPwd.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
                    logPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                } else {
                    logPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                }
            }
        });
        logReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_REGISTER);
            }
        });

        logRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_RESET);
            }
        });

        logGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                intentByRouter(Constant.ACTIVITY_URL_MAIN);
                String email = logEdit.getText().toString().trim();
                String pwd = logPwd.getText().toString().trim();
                if (email.equals("")||pwd.equals("")){
                    UIUtils.showToastSafe("请输入正确的账号和密码");
                }{
                    try {
                        ss = RsaCoder.encryptByPublicKey(pwd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    loginPresenter.reqeust(email,ss);

                }


            }
        });

        getfirst = getSharedPreferences("firsht", MODE_PRIVATE);
        boolean flag = getfirst.getBoolean("flag", false);
        if (flag){
            intentByRouter(Constant.ACTIVITY_URL_MAIN);
            finish();
        }

    }

    @Override
    protected void destoryData() {

    }


    private class logCall implements Cinterface<LoginBean> {
        @Override
        public void success(LoginBean data, Object... args) {
            UIUtils.showToastSafe("成功");
            String email = logEdit.getText().toString();
            String pwd = logPwd.getText().toString();
            data.setStatus(1);
            LoginBeanDao loginBeanDao = DaoMaster.newDevSession(getBaseContext(), LoginBeanDao.TABLENAME).getLoginBeanDao();
            loginBeanDao.deleteAll();
            loginBeanDao.insertOrReplace(data);
            getfirst.edit().putBoolean("flag",true).commit();
            intentByRouter(Constant.ACTIVITY_URL_VISUALIZE);
            finish();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            UIUtils.showToastSafe(data.getCode() + "===" + data.getDisplayMessage());

        }
    }
}
