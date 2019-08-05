package doctor.wd.com.open_main.presenter;

import com.wd.doctor.common.core.BasePresenter;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.utils.http.ApiRequest;

import io.reactivex.Observable;

public class BindBankCardPresenter extends BasePresenter<ApiRequest> {
    public BindBankCardPresenter(Cinterface dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.getBankCard((String)args[0],(String)args[1],(String)args[2],(String)args[3],(String)args[4]);
    }
}
