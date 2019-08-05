package com.wd.doctor.open_login.presenter;

import com.wd.doctor.common.core.BasePresenter;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.utils.http.ApiRequest;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ChoosePresenter extends BasePresenter<ApiRequest> {
    public ChoosePresenter(Cinterface dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        File arg = (File) args[2];
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("image",arg.getName(),
                RequestBody.create(MediaType.parse("multipart/octet-stream"),
                        arg));
        return iRequest.getChoose((String)args[0],(String)args[1],builder.build());
    }
}
