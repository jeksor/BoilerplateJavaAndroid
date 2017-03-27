package com.esorokin.boilerplate.ui.plugins;

import android.app.Dialog;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.esorokin.boilerplate.R;
import com.esorokin.boilerplate.presentation.errors.UserError;

public class DialogErrorPlugin extends ErrorPlugin<ContextProvider> {
	private AlertDialog dialog;

	public DialogErrorPlugin(ContextProvider delegate) {
		super(delegate);
	}

	@Override
	public void showUiError(UserError userError) {
		showUiError(userError, null);
	}

	@Override
	public void showUiError(UserError userError, @Nullable Dialog.OnClickListener hideErrorListener) {
		hideUiError();
		dialog = new AlertDialog.Builder(getDependency().getContext())
				.setTitle(userError.getTitle())
				.setMessage(userError.getMessage())
				.setCancelable(true)
				.setPositiveButton(R.string.ok, (dialogInterface, i) -> {
					if (hideErrorListener != null) {
						hideErrorListener.onClick(dialogInterface, i);
					}
				})
				.show();
	}

	@Override
	public void hideUiError() {
		if (dialog != null) {
			dialog.dismiss();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hideUiError();
	}
}
