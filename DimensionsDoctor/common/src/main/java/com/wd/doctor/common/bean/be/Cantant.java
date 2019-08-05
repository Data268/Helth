package com.wd.doctor.common.bean.be;

import com.wd.doctor.common.bean.DataBean;
import com.wd.doctor.common.bean.LoginBean;

public interface Cantant {

    interface showView {
        void getData(Bean bean);
    }


    interface showPresenter<showView>{
        void attach(Cantant.showView showView);

        void destroy();

        void requesr(String doctorId, String sessionId);
    }

    interface showModel{
        void response(String doctorId, String sessionId, CallBack callBack);
        interface CallBack {
            void getList(DataBean<LoginBean> bean);
        }
    }
}
