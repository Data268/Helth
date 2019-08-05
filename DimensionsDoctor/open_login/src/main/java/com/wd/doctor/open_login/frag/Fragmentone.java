package com.wd.doctor.open_login.frag;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wd.doctor.common.bean.FindSystemImageBean;
import com.wd.doctor.common.bean.LoginBean;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.db.DaoMaster;
import com.wd.doctor.common.core.db.LoginBeanDao;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.RealPathFromUriUtils;
import com.wd.doctor.common.utils.UIUtils;
import com.wd.doctor.open_login.R;
import com.wd.doctor.open_login.activity.VisualizeActivity;
import com.wd.doctor.open_login.presenter.ChoosePresenter;
import com.wd.doctor.open_login.presenter.FindSystemImagePresenter;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragmentone extends Fragment {

    private ImageView boy;
    private ChoosePresenter choosePresenter;
    private LoginBeanDao loginBeanDao;
    private List<LoginBean> loginBeans;
    private Long id;
    private String sessionId;
    private FindSystemImagePresenter findSystemImagePresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.layout_one,null);
        boy = view.findViewById(R.id.boy);


        //查询系统提供照
        findSystemImagePresenter = new FindSystemImagePresenter(new findImgCall());
        findSystemImagePresenter.reqeust();
//        //选择证件照
//        choosePresenter = new ChoosePresenter(new chooseCall());

        boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findSystemImagePresenter.reqeust();

            }
        });


        choosePresenter = new ChoosePresenter(new chooseCall());




        return view;
    }


    public  class findImgCall implements Cinterface<List<FindSystemImageBean>> {
        @Override
        public void success(List<FindSystemImageBean> data, Object... args) {

            UIUtils.showToastSafe("查询到系统照");
            Log.e("rr",data+"000");

            Glide.with(getActivity()).load(Uri.parse(data.get(0).getImagePic())).into(boy);
//            findSystemImagePresenter.reqeust();

        }

        @Override
        public void fail(ApiException data, Object... args) {
            UIUtils.showToastSafe(data.getCode()+"------------"+data.getDisplayMessage());
        }
    }
    public static class chooseCall implements Cinterface {
        @Override
        public void success(Object data, Object... args) {
            UIUtils.showToastSafe("选择了系统形象照");

        }

        @Override
        public void fail(ApiException data, Object... args) {
            UIUtils.showToastSafe(data.getCode() + "===" + data.getDisplayMessage());

        }
    }
}
