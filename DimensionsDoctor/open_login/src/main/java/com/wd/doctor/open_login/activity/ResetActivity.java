package com.wd.doctor.open_login.activity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.Constant;
import com.wd.doctor.common.utils.RsaCoder;
import com.wd.doctor.common.utils.UIUtils;
import com.wd.doctor.common.utils.Utils;
import com.wd.doctor.open_login.R;
import com.wd.doctor.open_login.presenter.CheckCodePreesenter;
import com.wd.doctor.open_login.presenter.EmailPresenter;
import com.wd.doctor.open_login.presenter.ResetPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = Constant.ACTIVITY_URL_RESET)
public class ResetActivity extends BaseActivity {


    ImageView resetBack;
    EditText resetEmail;
    Button resetYz;
    EditText resetCode;
    Button reNext;
    LinearLayout frist;
    EditText resetNewPwd;
    ImageView resetEye;
    EditText resetSurePwd;
    ImageView reset2Eye;
    Button reSuccess;
    LinearLayout two;
    private ImageView reset2Back;
    private EmailPresenter emailPresenter;
    private String mail;
    private String code;
    private ResetPresenter resetPresenter;
    private String res;
    private String rep;
    private String renew;
    private CheckCodePreesenter checkCodePreesenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        resetBack = findViewById(R.id.reset_back);
        resetEmail = findViewById(R.id.reset_email);
        resetYz = findViewById(R.id.reset_yz);
        resetCode = findViewById(R.id.reset_code);
        reNext = findViewById(R.id.re_next);
        frist = findViewById(R.id.frist);
        resetNewPwd = findViewById(R.id.reset_new_pwd);
        resetEye = findViewById(R.id.reset_eye);
        resetSurePwd = findViewById(R.id.reset_sure_pwd);
        reset2Eye = findViewById(R.id.reset2_eye);
        reSuccess = findViewById(R.id.re_success);
        two = findViewById(R.id.two);
        reset2Back = findViewById(R.id.reset2_back);

        resetBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //获取验证码
        emailPresenter = new EmailPresenter(new emailCall());
        //检验验证码
        checkCodePreesenter = new CheckCodePreesenter(new CheckCall());

        //重置密码
        resetPresenter = new ResetPresenter(new resetCall());
//        点击获取验证码
        resetYz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = resetEmail.getText().toString().trim();

                if (mail.equals("")){
                    UIUtils.showToastSafe("不能为空");
                }else {
                    boolean validEmail = Utils.isValidEmail(mail);
                    if (validEmail){
                        emailPresenter.reqeust(mail);
//                        frist.setVisibility(View.INVISIBLE);
//                        two.setVisibility(View.VISIBLE);
                    }else {
                        UIUtils.showToastSafe("邮箱格式不正确");
                    }

                }
            }
        });
        //第一个小眼睛
        resetEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //显示隐藏密码
                if (resetNewPwd.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
                    resetNewPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                } else {
                    resetNewPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                }
            }
        });
        //第二个小眼睛
        reset2Eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示隐藏密码
                if (resetSurePwd.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
                    resetSurePwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                } else {
                    resetSurePwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                }
            }
        });
        //返回前一个页面
        reset2Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                frist.setVisibility(View.VISIBLE);
                two.setVisibility(View.INVISIBLE);

            }
        });

        //下个页面
        reNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = resetCode.getText().toString().trim();
                checkCodePreesenter.reqeust(mail,code);
                if (code.equals("")) {
                  UIUtils.showToastSafe("不能为空");
                }{
                    frist.setVisibility(View.INVISIBLE);
                    two.setVisibility(View.VISIBLE);
                }
            }
        });

        //完成
        reSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                res = resetSurePwd.getText().toString().trim();
                rep = resetNewPwd.getText().toString().trim();

                if (res.equals("")||rep.equals("")){

                    UIUtils.showToastSafe("不能为空");
                }else {
                    if (rep.equals(res)){
                        try {
                            renew = RsaCoder.encryptByPublicKey(rep);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        resetPresenter.reqeust(mail,res,rep);
                        intentByRouter(Constant.ACTIVITY_URL_LOGIN);

                    }else {
                        UIUtils.showToastSafe("密码输入不一致");
                    }
                }

            }
        });

    }

        @Override
        protected void destoryData() {

        }


    private class emailCall implements Cinterface {
        @Override
        public void success(Object data, Object... args) {
            UIUtils.showToastSafe("发送成功");
        }

        @Override
        public void fail(ApiException data, Object... args) {
            UIUtils.showToastSafe(data.getCode() + "===" + data.getDisplayMessage());

        }
    }

    private class resetCall implements Cinterface {
        @Override
        public void success(Object data, Object... args) {
            UIUtils.showToastSafe("重置成功");

        }

        @Override
        public void fail(ApiException data, Object... args) {
            UIUtils.showToastSafe(data.getCode() + "===" + data.getDisplayMessage());

        }
    }

    private class CheckCall implements Cinterface {
        @Override
        public void success(Object data, Object... args) {
            UIUtils.showToastSafe("验证码正确");
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
