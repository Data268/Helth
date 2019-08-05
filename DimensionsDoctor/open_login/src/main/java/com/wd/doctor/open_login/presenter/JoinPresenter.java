package com.wd.doctor.open_login.presenter;

import com.wd.doctor.common.core.BasePresenter;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.utils.http.ApiRequest;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public class JoinPresenter extends BasePresenter<ApiRequest> {
    public JoinPresenter(Cinterface dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
//        return iRequest.getJoin((String)args[0],(String)args[1],(String)args[2],(String)args[3],(String)args[4],(String)args[5],(String)args[6],(String)args[7],(String)args[8],(String)args[9]);
        return iRequest.getJoin((RequestBody)args[0]);
    }
}
