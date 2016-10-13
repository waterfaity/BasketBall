package com.huizetime.basketball.model;

import com.huizetime.basketball.presenter.HomePreparePresenter;
import com.huizetime.basketball.presenter.HomePreparePresenterListener;

/**
 * Created by water_fairy on 2016/10/13.
 */
public class HomePrepareModelSimple implements HomePrepareModel {
    private HomePreparePresenterListener mListener;

    public HomePrepareModelSimple(HomePreparePresenter homePreparePresenter) {
        mListener = homePreparePresenter;
    }
}
