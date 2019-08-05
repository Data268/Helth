package doctor.wd.com.open_main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.utils.Constant;

@Route(path = Constant.ACTIVITY_URL_RECORD)
public class RecordActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_record;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void destoryData() {

    }
}
