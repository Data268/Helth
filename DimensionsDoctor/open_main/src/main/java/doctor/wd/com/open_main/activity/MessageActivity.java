package doctor.wd.com.open_main.activity;

import androidx.appcompat.app.AppCompatActivity;
import doctor.wd.com.open_main.R;
import doctor.wd.com.open_main.activity.wode.BubbleView;
import doctor.wd.com.open_main.presenter.AllStatusPresenter;
import doctor.wd.com.open_main.presenter.ReadNumPresenter;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.doctor.common.bean.DataBean;
import com.wd.doctor.common.bean.LoginBean;
import com.wd.doctor.common.bean.ReadNotBean;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.db.DaoMaster;
import com.wd.doctor.common.core.db.LoginBeanDao;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.Constant;
import com.wd.doctor.common.utils.UIUtils;

import java.util.List;

@Route(path = Constant.ACTIVITY_URL_MESS)
public class MessageActivity extends BaseActivity {

    private ImageButton back;
    private TextView yidu;
    private LinearLayout lin;
    private Button close;
    private Button startm;
    private RelativeLayout systemitem;
    private TextView red_b1;
    private RelativeLayout inItem;
    private TextView redb2;
    private RelativeLayout hitem;
    private TextView red_b3;
    private ReadNumPresenter readNumPresenter;
    private LoginBeanDao loginBeanDao;
    private List<LoginBean> loginBeans;
    private Long id;
    private String sessionId;
    private AllStatusPresenter allStatusPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initView() {
        back = findViewById(R.id.back_m);
        yidu = findViewById(R.id.already_d);
        lin = findViewById(R.id.line);
        close = findViewById(R.id.close_message);
        startm = findViewById(R.id.start_m);
        systemitem = findViewById(R.id.system_item);
        red_b1 = findViewById(R.id.red_b1);
        inItem = findViewById(R.id.in_item);
        redb2 = findViewById(R.id.red_b2);
        hitem = findViewById(R.id.h_item);
        red_b3 = findViewById(R.id.red_b3);
        loginBeanDao = DaoMaster.newDevSession(getBaseContext(), LoginBeanDao.TABLENAME).getLoginBeanDao();
        loginBeans = loginBeanDao.loadAll();
        id = loginBeans.get(0).getId();
        sessionId = loginBeans.get(0).getSessionId();
        //修改消息状态为全部已读
        allStatusPresenter = new AllStatusPresenter(new allCall());

        //未读的消息数量
        readNumPresenter = new ReadNumPresenter(new ReadCall());
        readNumPresenter.reqeust(id + "", sessionId);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lin.setVisibility(View.INVISIBLE);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        systemitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_SYSTEM);
            }
        });
        inItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_ASKMESSAGE);
            }
        });
        hitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_HMONEY);
            }
        });

        red_b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allStatusPresenter.reqeust(id + "", sessionId);

            }
        });
        redb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allStatusPresenter.reqeust(id + "", sessionId);

            }
        });
        red_b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allStatusPresenter.reqeust(id + "", sessionId);

            }
        });

        yidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allStatusPresenter.reqeust(id + "", sessionId);
            }
        });

    }

    @Override
    protected void destoryData() {

    }

    private class ReadCall implements Cinterface<List<ReadNotBean>> {
        @Override
        public void success(List<ReadNotBean> data, Object... args) {
            Log.e("rrr1", data.toString() + "");
            if (data.get(0).getNoticeType() == 1) {
                red_b1.setText(data.get(0).getNotReadNum() + "");
                Log.e("rrr2", data.get(0).getNotReadNum() + "");
                Toast.makeText(MessageActivity.this, "rrr", Toast.LENGTH_SHORT).show();
            }
            if (data.get(1).getNoticeType() == 2) {
                redb2.setText(data.get(1).getNotReadNum() + "");
            }
            if (data.get(2).getNoticeType() == 3) {
                red_b3.setText(data.get(2).getNotReadNum() + "");
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {
            UIUtils.showToastSafe(data.getCode() + "" + data.getDisplayMessage());
        }
    }

    private class allCall implements Cinterface<DataBean> {
        @Override
        public void success(DataBean data, Object... args) {
            UIUtils.showToastSafe("成功");
        }

        @Override
        public void fail(ApiException data, Object... args) {
            UIUtils.showToastSafe(data.getCode() + "=====" + data.getDisplayMessage());
        }
    }
}
