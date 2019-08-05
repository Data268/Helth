package doctor.wd.com.open_main.activity;

import androidx.appcompat.app.AppCompatActivity;
import doctor.wd.com.open_main.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.utils.Constant;

@Route(path = Constant.ACTIVITY_URL_WITH)
public class WithdrawActivity extends BaseActivity {

    private ImageButton back;
    private TextView jilu;
    private EditText moneyl;
    private CheckBox checkall;
    private Button ti_btn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected void initView() {
        back = findViewById(R.id.back_t);
        jilu = findViewById(R.id.jilu_t);
        moneyl = findViewById(R.id.money_l);
        checkall = findViewById(R.id.check_all);
        ti_btn = findViewById(R.id.tixian_bt);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        jilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_RECORD);
            }
        });
        ti_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void destoryData() {

    }
}
