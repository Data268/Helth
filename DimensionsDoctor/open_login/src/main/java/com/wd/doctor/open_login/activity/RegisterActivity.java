package com.wd.doctor.open_login.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.wd.doctor.common.bean.DataBean;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.Constant;
import com.wd.doctor.common.utils.RsaCoder;
import com.wd.doctor.common.utils.UIUtils;
import com.wd.doctor.common.utils.Utils;
import com.wd.doctor.open_login.R;
import com.wd.doctor.open_login.R2;
import com.wd.doctor.open_login.presenter.CheckCodePreesenter;
import com.wd.doctor.open_login.presenter.EmailPresenter;
import com.wd.doctor.open_login.presenter.JoinPresenter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

@Route(path = Constant.ACTIVITY_URL_REGISTER)
public class RegisterActivity extends BaseActivity {

    //    @BindView(R2.id.reg_mailcode)
//    EditText regMailcode;
//    @BindView(R2.id.bt_yz)
//    Button btYz;
    @BindView(R2.id.reg_getyz)
    EditText regGetyz;
    @BindView(R2.id.reg_pwd)
    EditText regPwd;
    @BindView(R2.id.reg_eye)
    ImageView regEye;
    @BindView(R2.id.areg_pwd)
    EditText aregPwd;
    @BindView(R2.id.areg_eye)
    ImageView aregEye;
    @BindView(R2.id.next_page)
    Button nextPage;
    @BindView(R2.id.wansinfo)
    LinearLayout wansinfo;
    @BindView(R2.id.reg_realname)
    EditText regRealname;
    @BindView(R2.id.reg_hospital)
    EditText regHospital;
    @BindView(R2.id.reg_choose_keshi)
    Spinner regChooseKeshi;
    @BindView(R2.id.reg_choose_zhichen)
    Spinner regChooseZhichen;
    @BindView(R2.id.next_page_two)
    Button nextPageTwo;
    @BindView(R2.id.ren_back)
    ImageButton renBack;
    @BindView(R2.id.reg_et_info)
    EditText regEtInfo;
    @BindView(R2.id.reg_et_can)
    EditText regEtCan;
    @BindView(R2.id.gerjianjie)
    LinearLayout gerjianjie;
    @BindView(R2.id.textt)
    TextView textt;
    @BindView(R2.id.bt)
    Button bt;
    @BindView(R2.id.lin)
    LinearLayout lin;
    @BindView(R2.id.dengdchengg)
    LinearLayout dengdchengg;
    Button regCome;
    private String mailcode;
    private String getyz;
    private String regpwd;
    private String argpwd;
    private String rsapwd;
    private LinearLayout getInfo;
    private String realname;
    private String reghospital;
    private String etinfo;
    private String etcan;
    private Button backlog;
    private EmailPresenter emailPresenter;
    private Runnable runnable;
    private String etemail;
    private CheckCodePreesenter checkCodePreesenter;

    private ArrayList<String> title;
    private ArrayList<String> kstitle;
    private ArrayAdapter titleAdapter;
    private String tit;
    private ArrayAdapter ksAdapter;
    private String ks;
    private JoinPresenter joinPresenter;
    private Map<String, String> map;

    private EditText regMailcode;
    private String email;
    private Button button;
    private String email2;

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            // 拦截返回键，重写代码
            return true;
            //这里不需要执行父类的点击事件，所以直接return
            }return super.dispatchKeyEvent(event);
        //继续执行父类的其他点击事件}
        }


            @Override
            protected int getLayoutId () {
                return R.layout.activity_register;
            }

            @Override
            protected void initView () {
                ButterKnife.bind(this);
                regCome = findViewById(R.id.reg_tenter);
                getInfo = findViewById(R.id.getinfo);
                backlog = findViewById(R.id.reg_back_log);
                regMailcode = findViewById(R.id.reg_mailcode);
                button = findViewById(R.id.bt_yz);


                //邮箱
                emailPresenter = new EmailPresenter(new EmailCall());

                //验证码
                checkCodePreesenter = new CheckCodePreesenter(new CodeCall());

                //申请入驻
                joinPresenter = new JoinPresenter(new joinCall());


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        email = regMailcode.getText().toString().trim();
                        if (email.equals("")) {
                            Toast.makeText(RegisterActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            boolean emai = Utils.isValidEmail(email);
                            if (emai) {
                                emailPresenter.reqeust(email);
                            } else {
                                UIUtils.showToastSafe("邮箱格式不正确");
                            }
                        }

                    }
                });

                regEye.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //显示隐藏密码
                        if (regPwd.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
                            regPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                        } else {
                            regPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                        }
                    }
                });


                aregEye.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //显示隐藏密码
                        if (aregPwd.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
                            aregPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                        } else {
                            aregPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                        }
                    }
                });

                backlog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intentByRouter(Constant.ACTIVITY_URL_LOGIN);
                        finish();
                    }
                });


                renBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gerjianjie.setVisibility(View.INVISIBLE);
                        getInfo.setVisibility(View.VISIBLE);
                    }
                });

                nextPage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                wansinfo.setVisibility(View.INVISIBLE);
//                getInfo.setVisibility(View.VISIBLE);

//                mailcode = regMailcode.getText().toString().trim();

                        getyz = regGetyz.getText().toString().trim();
                        regpwd = regPwd.getText().toString().trim();
                        argpwd = aregPwd.getText().toString().trim();
                        email2 = regMailcode.getText().toString().trim();
                        checkCodePreesenter.reqeust(email2,getyz);
                        boolean email = Utils.isValidEmail(email2);
                        if (email) {
                            UIUtils.showToastSafe("邮箱格式正确!!!");
                        } else {
                            UIUtils.showToastSafe("邮箱格式不正确");
                        }

                        if (regpwd.equals(argpwd)) {
                            try {
                                rsapwd = RsaCoder.encryptByPublicKey(regpwd);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

//                            wansinfo.setVisibility(View.GONE);
//                            getInfo.setVisibility(View.VISIBLE);
                        } else {
                            UIUtils.showToastSafe("密码输入不一致");
                        }

                if (email2.isEmpty() || getyz.isEmpty() || regpwd.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }else {
                         wansinfo.setVisibility(View.GONE);
                        getInfo.setVisibility(View.VISIBLE);
                }
//                  else {
//
//                    if (regpwd.equals(argpwd)) {
//                        try {
//                            rsapwd = RsaCoder.encryptByPublicKey(regpwd);
////                            checkCodePreesenter.reqeust(mailcode,getyz);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                        wansinfo.setVisibility(View.GONE);
//                        getInfo.setVisibility(View.VISIBLE);
//
//                    } else {
//                        Toast.makeText(RegisterActivity.this, "密码不一致,重新输入", Toast.LENGTH_SHORT).show();
//
//                    }
//                }

                    }
                });

                kstitle = new ArrayList<>();
                //        内科、眼科、骨科、小儿科、传染病科、皮肤性病、耳鼻喉科、精神病科
                kstitle.add("内科");
                kstitle.add("眼科");
                kstitle.add("骨科");
                kstitle.add("小儿科");
                kstitle.add("传染病科");
                kstitle.add("皮肤性病");
                kstitle.add("耳鼻喉科");
                kstitle.add("精神病科");

                ksAdapter = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_spinner_item, kstitle);
                ksAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                regChooseKeshi.setAdapter(ksAdapter);
                //选择科室
                regChooseKeshi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

                {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                                               long id) {
                        ks = (String) regChooseKeshi.getSelectedItem();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                title = new ArrayList<>();
                title.add("主治医师");
                title.add("副主任医师");
                title.add("主任医师");

                titleAdapter = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_spinner_item, title);
                titleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                regChooseZhichen.setAdapter(titleAdapter);
                //选择职称
                regChooseZhichen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

                {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                                               long id) {
                        tit = (String) regChooseZhichen.getSelectedItem();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                nextPageTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        realname = regRealname.getText().toString().trim();
                        reghospital = regHospital.getText().toString().trim();

//                        if (realname.equals("")) {
//                            Toast.makeText(RegisterActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
//                        } else {
//                            getInfo.setVisibility(View.INVISIBLE);
//                        }
                      if (realname.equals("") || reghospital.equals("")) {
                            Toast.makeText(RegisterActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            getInfo.setVisibility(View.INVISIBLE);
                            gerjianjie.setVisibility(View.VISIBLE);

                        }
                    }
                });


                regCome.setOnClickListener(new View.OnClickListener()

                {
                    @Override
                    public void onClick(View v) {
//                gerjianjie.setVisibility(View.INVISIBLE);
//                dengdchengg.setVisibility(View.VISIBLE);
                        etinfo = regEtInfo.getText().toString().trim();
                        etcan = regEtCan.getText().toString().trim();

                        if (etcan.equals("") || etinfo.equals("")) {
                            Toast.makeText(RegisterActivity.this, "请按要求填写", Toast.LENGTH_SHORT).show();

                        } else {
                            gerjianjie.setVisibility(View.INVISIBLE);
                            dengdchengg.setVisibility(View.VISIBLE);

                            Map<String, String> map = new HashMap<>();

//                        @Field("email")String email,@Field("code")String code,
//                        @Field("pwd1")String pwd1,@Field("pwd2")String pwd2,
//                        @Field("name")String name,@Field("inauguralHospital")String inauguralHospital,
//                        @Field("departmentName")String departmentName,@Field("jobTitle")String jobTitle,
//                        @Field("personalProfile")String personalProfile,@Field("goodField")String goodField
                            map.put("email", email2);
                            map.put("code", getyz);
                            map.put("pwd1", regpwd);
                            map.put("pwd2", regpwd);
                            map.put("name", "木子李");
                            map.put("inauguralHospital", "清华附属");
                            map.put("departmentName", "小儿科");
                            map.put("jobTitle", "主治医生");
                            map.put("personalProfile", "主治医生");
                            map.put("goodField", "小儿科");

                            Gson gson = new Gson();
                            String toJson = gson.toJson(map);
                            Log.e("ddd", "eeeeeeeeee" + toJson);
                            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), toJson);
//
                            joinPresenter.reqeust(requestBody);


//                    joinPresenter.reqeust(mailcode, getyz, regpwd, argpwd, realname, reghospital, ks, tit, etinfo, etcan);
                        }

                    }
                });
            }

            @Override
            protected void destoryData () {

            }

            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    int num = msg.what;
                    if (num == 1) {
                        int time = (int) msg.obj;
                        if (time > -1) {
                            button.setText(time + "s" + "后重新获取");
                        } else if (time == 0) {
                            button.setText("请重新获取验证码");
                        }
                    }
                }
            };

            private class EmailCall implements Cinterface {

                @Override
                public void success(Object data, Object... args) {
//            if (data.getStatus().equals("0000")){
                    UIUtils.showToastSafe("发送成功");


                    Thread thread = new Thread(runnable);
                    thread.start();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int i = 10;
                            while (true) {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                Message message = handler.obtainMessage();
                                message.obj = i;
                                message.what = 1;
                                handler.sendMessage(message);
                                i--;
                                if (i < 0) {
                                    break;
                                }

                            }
                        }
                    }).start();
//            }else {
//                UIUtils.showToastSafe(data.getMessage()+"");
//            }
                }

                @Override
                public void fail(ApiException data, Object... args) {
                    UIUtils.showToastSafe(data.getCode() + "===" + data.getDisplayMessage());
                }
            }

            private class CodeCall implements Cinterface {
                @Override
                public void success(Object data, Object... args) {
                   checkCodePreesenter.reqeust(mailcode,getyz);
                    UIUtils.showToastSafe("验证通过");
                }

                @Override
                public void fail(ApiException data, Object... args) {
                    UIUtils.showToastSafe(data.getCode() + "===" + data.getDisplayMessage());

                }
            }

            //申请入驻
            private class joinCall implements Cinterface<DataBean> {

                @Override
                public void success(DataBean data, Object... args) {
                    UIUtils.showToastSafe("等待申请结果");
                    Toast.makeText(RegisterActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void fail(ApiException data, Object... args) {
                    UIUtils.showToastSafe(data.getCode() + "===" + data.getDisplayMessage());

                }
            }
        }
