package com.wd.doctor.common.bean.be;

public class PresenterMpl implements Cantant.showPresenter {
    Cantant.showView showView;
    @Override
    public void attach(Cantant.showView showView) {
        this.showView = showView;
        
    }

    @Override
    public void destroy() {

    }

    @Override
    public void requesr(String doctorId, String sessionId) {

    }
}
