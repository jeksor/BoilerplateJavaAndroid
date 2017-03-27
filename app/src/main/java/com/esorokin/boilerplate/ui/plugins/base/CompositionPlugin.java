package com.esorokin.boilerplate.ui.plugins.base;

public interface CompositionPlugin extends Plugin {
	void attach(Plugin plugin);

	void detach(Plugin plugin);
}
