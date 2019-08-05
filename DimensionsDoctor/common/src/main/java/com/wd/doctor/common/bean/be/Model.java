package com.wd.doctor.common.bean.be;

import com.wd.doctor.common.bean.DataBean;
import com.wd.doctor.common.bean.LoginBean;
import com.wd.doctor.common.utils.http.ApiRequest;
import com.wd.doctor.common.utils.http.NetWorkManager;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Model implements Cantant.showModel {
    @Override
    public void response(String doctorId, String sessionId, CallBack callBack) {

        RetrofitUtils.getRetrofitUtils().getApiService(NetWorkManager.BASE_URL,ApiRequest.class)
                .getLogin(doctorId,sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DataBean<LoginBean>>() {
                    @Override
                    public void accept(DataBean<LoginBean> loginBeanDataBean) throws Exception {
                        int whetherHaveImagePic = loginBeanDataBean.getResult().getWhetherHaveImagePic();
                        callBack.getList(loginBeanDataBean);
                    }

                });
    }
}
