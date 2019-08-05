package doctor.wd.com.open_main.presenter;

import com.wd.doctor.common.core.BasePresenter;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.utils.http.ApiRequest;

import io.reactivex.Observable;

public class DoctorBankCardByIdPersenter extends BasePresenter<ApiRequest> {
    public DoctorBankCardByIdPersenter(Cinterface dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.getBank((String)args[0],(String)args[1]);
    }
}
