package com.esorokin.boilerplate.ui.plugins;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esorokin.boilerplate.ui.plugins.base.BasePlugin;

public class BindingPlugin<T extends ViewDataBinding> extends BasePlugin {
	@Nullable
	private final Activity activity;
	private final int layoutId;
	private T binding;

	/**
	 * Use for activity.
	 */
	public BindingPlugin(@Nullable Activity activity, @LayoutRes int layoutId) {
		super();
		this.activity = activity;
		this.layoutId = layoutId;
	}

	/**
	 * Use for fragments and views.
	 */
	public BindingPlugin(@LayoutRes int layoutId) {
		super();
		this.activity = null;
		this.layoutId = layoutId;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		if (activity != null) {
			binding = DataBindingUtil.setContentView(activity, layoutId);
		}
	}

	public View inflate(LayoutInflater inflater, ViewGroup container) {
		binding = DataBindingUtil.inflate(inflater, layoutId, container, false);
		return binding.getRoot();
	}

	public T get() {
		return binding;
	}
}
