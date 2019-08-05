package doctor.wd.com.open_main.presenter;

import com.wd.doctor.common.core.BasePresenter;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.utils.http.ApiRequest;

import io.reactivex.Observable;

public class PublishPresenter extends BasePresenter<ApiRequest> {
    public PublishPresenter(Cinterface dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.getPublish((String)args[0],(String)args[1],(int)args[2],(String)args[3]);
    }
}
