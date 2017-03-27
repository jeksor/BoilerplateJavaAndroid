package com.esorokin.boilerplate.ui.activity;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.esorokin.boilerplate.presentation.presenter.LaunchPresenter;
import com.esorokin.boilerplate.presentation.view.LaunchView;

public class LaunchActivity extends BaseActivity implements LaunchView {
	@InjectPresenter
	LaunchPresenter presenter;
}
