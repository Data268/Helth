package com.wd.doctor.open_login.frag;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wd.doctor.common.bean.FindSystemImageBean;
import com.wd.doctor.common.bean.LoginBean;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.db.DaoMaster;
import com.wd.doctor.common.core.db.LoginBeanDao;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.UIUtils;
import com.wd.doctor.open_login.R;
import com.wd.doctor.open_login.presenter.ChoosePresenter;
import com.wd.doctor.open_login.presenter.FindSystemImagePresenter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragmenttwo extends Fragment {

    private ImageView girl;
    private LoginBeanDao loginBeanDao;
    private List<LoginBean> loginBeans;
    private Long id;
    private String sessionId;
    private ChoosePresenter choosePresenter;
    private FindSystemImagePresenter findSystemImagePresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.layout_two,null);
        girl = view.findViewById(R.id.girl);
        loginBeanDao = DaoMaster.newDevSession(getContext(), LoginBeanDao.TABLENAME).getLoginBeanDao();
        loginBeans = loginBeanDao.loadAll();
        id = loginBeans.get(0).getId();
        sessionId = loginBeans.get(0).getSessionId();

        //查询系统提供照
        findSystemImagePresenter = new FindSystemImagePresenter(new findImgeCall());
        findSystemImagePresenter.reqeust();

//        choosePresenter = new ChoosePresenter(new Fragmentone.chooseCall());

        girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                choosePresenter.reqeust(id+"",sessionId,girl);
            }
        });

        return view;
    }

    private class findImgeCall implements Cinterface<List<FindSystemImageBean>> {
        @Override
        public void success(List<FindSystemImageBean> data, Object... args) {
            UIUtils.showToastSafe("查询到系统照");
            Log.e("rr",data+"000");
            Glide.with(getActivity()).load(Uri.parse(data.get(0).getImagePic())).into(girl);
        }

        @Override
        public void fail(ApiException data, Object... args) {
            UIUtils.showToastSafe(data.getCode()+"------------"+data.getDisplayMessage());

        }
    }
}
