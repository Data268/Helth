package doctor.wd.com.open_main.activity;

import androidx.appcompat.app.AppCompatActivity;
import doctor.wd.com.open_main.R;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.utils.Constant;

@Route(path = Constant.ACTIVITY_URL_AUTOMA)
public class AutomaticActivity extends BaseActivity {

    private ImageButton back;
    private Switch swith;
    private TextView asking;
    private RadioButton askingbt;
    private TextView detailPlace;
    private RadioButton detailpleasebt;
    private TextView timeHint;
    private RadioButton timehintBt;
    private EditText myEditCustom;
    private RadioButton custombt;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_automatic;
    }

    @Override
    protected void initView() {
        back = findViewById(R.id.back_a);
        swith = findViewById(R.id.start_s);
        asking = findViewById(R.id.ask_ing);
        askingbt = findViewById(R.id.ask_ing_b);
        detailPlace = findViewById(R.id.detail_please);
        detailpleasebt = findViewById(R.id.detail_please_bt);
        timeHint = findViewById(R.id.time_hint);
        timehintBt = findViewById(R.id.time_hint_bt);
        myEditCustom = findViewById(R.id.my_edit_custom);
        custombt = findViewById(R.id.custom_bt);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void destoryData() {

    }
}
