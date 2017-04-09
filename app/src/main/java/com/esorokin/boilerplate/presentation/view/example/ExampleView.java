package com.esorokin.boilerplate.presentation.view.example;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.esorokin.boilerplate.model.data.ExampleItem;
import com.esorokin.boilerplate.presentation.view.ErrorView;
import com.esorokin.boilerplate.presentation.view.LoadingView;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ExampleView extends MvpView, LoadingView, ErrorView {
	void setExampleData(ExampleItem exampleItem);
}
