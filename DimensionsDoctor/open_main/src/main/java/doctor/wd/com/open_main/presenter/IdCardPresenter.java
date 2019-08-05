package doctor.wd.com.open_main.presenter;

import com.wd.doctor.common.core.BasePresenter;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.utils.http.ApiRequest;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public class IdCardPresenter extends BasePresenter<ApiRequest> {
    public IdCardPresenter(Cinterface dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.getIdCard((String)args[0],(String)args[1],(RequestBody)args[2]);
    }
}
