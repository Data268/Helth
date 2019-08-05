package com.wd.doctor.open_login.presenter;

import android.util.Log;
import android.widget.Toast;

import com.wd.doctor.common.core.BasePresenter;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.utils.http.ApiRequest;

import io.reactivex.Observable;

public class EmailPresenter extends BasePresenter<ApiRequest> {
    public EmailPresenter(Cinterface dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.getEmail((String)args[0]);
    }
}
