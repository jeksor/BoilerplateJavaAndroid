package com.esorokin.boilerplate.ui.plugins.base;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;

public class BasePlugin implements Plugin {
	@Override
	public void onCreate() {}

	@Override
	public void onViewCreated(final View view) {}

	@Override
	public void onStart() {}

	@Override
	public void onResume() {}

	@Override
	public void onPause() {}

	@Override
	public void onStop() {}

	@Override
	public void onDestroy() {}

	@Override
	public void onOptionsItemSelected(MenuItem item) {}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {}

	@Override
	public void onBackPressed() {}
}