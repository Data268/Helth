package doctor.wd.com.open_main.activity.wode;

import android.Manifest;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.wd.doctor.common.bean.DoctorBankBean;
import com.wd.doctor.common.bean.DoctorIdCardInfo;
import com.wd.doctor.common.bean.LoginBean;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.db.DaoMaster;
import com.wd.doctor.common.core.db.LoginBeanDao;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.Constant;
import com.wd.doctor.common.utils.RsaCoder;

import java.util.List;

import doctor.wd.com.open_main.R;
import doctor.wd.com.open_main.activity.BindiDentityCard;
import doctor.wd.com.open_main.activity.idcard.BankScanActivity;
import doctor.wd.com.open_main.presenter.DoctorBankCardByIdPersenter;
import doctor.wd.com.open_main.presenter.DoctorIdCardInfoPersenter;
import doctor.wd.com.open_main.presenter.IdCardPresenter;

@Route(path = Constant.ACTIVITY_URL_BIND)
public class BindActivity extends BaseActivity {


    TextView bindIdCard;
    LinearLayout idCardLayoutHide;
    TextView bindBankCard;
    LinearLayout bankLayoutHide;
    TextView cordName;
    TextView cordSex;
    TextView cordZu;

    TextView cordId;
    LinearLayout idCardLayoutShow;
    TextView cordAddress;
    TextView cordType;
    TextView cordNumber;
    LinearLayout bankLayoutShow;
    private boolean hasGotToken;

    private String names;

    /* 相机请求码 */
    private static final int REQUEST_CAMERA = 0;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private TextView nameid,zuid, sexid, codeid;
    private IdCardPresenter idCardPresenter;
    private LoginBeanDao loginBeanDao;
    private List<LoginBean> loginBeans;
    private Long id;
    private String sessionId;
    private DoctorIdCardInfoPersenter doctorIdCardInfoPersenter;
    private DoctorBankCardByIdPersenter doctorBankCardByIdPersenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind;
    }

    @Override
    protected void initView() {
        bindIdCard = findViewById(R.id.bind_id_card);
        idCardLayoutHide = findViewById(R.id.idCard_layout_hide);
        bindBankCard  =findViewById(R.id.bind_bank_card);
        bankLayoutHide =findViewById(R.id.bank_layout_hide);
        cordName =findViewById(R.id.cord_name);
        cordSex =findViewById(R.id.cord_sex);
        cordZu=findViewById(R.id.cord_zu);
        cordId =findViewById(R.id.cord_id);

        idCardLayoutShow =findViewById (R.id.idCard_layout_show);
        cordAddress =findViewById(R.id.cord_address);
        cordType =findViewById(R.id.cord_type);

        cordNumber = findViewById(R.id.cord_number);
        bankLayoutShow = findViewById(R.id.bank_layout_show);
        doctorIdCardInfoPersenter = new DoctorIdCardInfoPersenter(new DoctorInfoCallBack());
        doctorBankCardByIdPersenter = new DoctorBankCardByIdPersenter(new DoctorIdbankCallBalk());

        // 初始化
        getOcrSing();
//        doctorBankCardByIdPersenter = new DoctorBankCardByIdPersenter(new DoctorIdbankCallBalk());

        loginBeanDao = DaoMaster.newDevSession(getBaseContext(), LoginBeanDao.TABLENAME).getLoginBeanDao();
        loginBeans = loginBeanDao.loadAll();
        id = loginBeans.get(0).getId();
        sessionId = loginBeans.get(0).getSessionId();

        bindIdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(BindiDentityCard.class);
            }
        });
        bindBankCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(BankScanActivity.class);
            }
        });
    }

    private void getOcrSing() {
        OCR.getInstance().initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                String token = result.getAccessToken();
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
            }
        }, getApplicationContext(), "Hsmv9WQWdLPUuv4EAITNBpGe", "5alEsZVxCP2G7dNEzhY329hTduTaoYGt");
    }

    @Override
    protected void destoryData() {

    }


    private class DoctorIdbankCallBalk implements Cinterface<DoctorBankBean> {
        @Override
        public void success(DoctorBankBean data, Object... args) {
            if (data==null){
                bankLayoutHide.setVisibility(View.GONE);

            }else {

                bankLayoutShow.setVisibility(View.VISIBLE);
                bankLayoutHide.setVisibility(View.GONE);
                cordAddress.setText(data.getBankName());

                cordNumber.setText(data.getBankCardNumber());
                if (data.getBankCardType()== 1){
                    cordType.setText("借记卡");
                }else {
                    cordType.setText("信用卡");
                }

            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class DoctorInfoCallBack implements Cinterface<DoctorIdCardInfo> {
        private String sexs;
        private String numbers;
        private String nations;
        @Override
        public void success(DoctorIdCardInfo data, Object... args) {
            if (data==null){
                idCardLayoutHide.setVisibility(View.INVISIBLE);
            }else {
                idCardLayoutHide.setVisibility(View.GONE);
                idCardLayoutShow.setVisibility(View.VISIBLE);
                try {
                    names = RsaCoder.decryptByPublicKey(data.getName());
                    sexs = RsaCoder.decryptByPublicKey(data.getSex());
                    numbers = RsaCoder.decryptByPublicKey(data.getIdNumber());
                    nations = RsaCoder.decryptByPublicKey(data.getNation());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cordName.setText(names);
                cordSex.setText(sexs);
                cordZu.setText(nations);
                cordId.setText(numbers);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}