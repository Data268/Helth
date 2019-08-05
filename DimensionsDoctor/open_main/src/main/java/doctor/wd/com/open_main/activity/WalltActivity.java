package doctor.wd.com.open_main.activity;

import androidx.appcompat.app.AppCompatActivity;
import doctor.wd.com.open_main.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.utils.Constant;

@Route(path = Constant.ACTIVITY_URL_WALLT)
public class WalltActivity extends BaseActivity {

    private ImageButton back;
    private TextView bind;
    private Button tixian;
    private TextView moneyCount;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallt;
    }

    @Override
    protected void initView() {
        back = findViewById(R.id.back_q);
        bind = findViewById(R.id.bind_d);
        tixian = findViewById(R.id.tixian);
        moneyCount = findViewById(R.id.money_count);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_WITH);
            }
        });
        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_BIND);
            }
        });

    }

    @Override
    protected void destoryData() {

    }
}
