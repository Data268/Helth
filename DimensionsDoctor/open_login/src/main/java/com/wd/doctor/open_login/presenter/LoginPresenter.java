package com.wd.doctor.open_login.presenter;

import com.wd.doctor.common.bean.DataBean;
import com.wd.doctor.common.core.BasePresenter;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.utils.http.ApiRequest;

import io.reactivex.Observable;

public class LoginPresenter extends BasePresenter<ApiRequest> {
    public LoginPresenter(Cinterface dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.getLogin((String)args[0],(String)args[1]);
    }
}
