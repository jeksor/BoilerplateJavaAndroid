package com.esorokin.boilerplate.presentation.navigation.animation;

import com.art.alligator.AnimationData;
import com.art.alligator.Screen;
import com.art.alligator.TransitionAnimation;
import com.art.alligator.TransitionAnimationProvider;
import com.art.alligator.TransitionType;
import com.art.alligator.animations.transition.SimpleTransitionAnimation;
import com.esorokin.boilerplate.R;

public final class WizardAnimationProvider implements TransitionAnimationProvider {
	@Override
	public TransitionAnimation getAnimation(TransitionType transitionType, Class<? extends Screen> screenClassFrom, Class<? extends Screen> screenClassTo, boolean isActivity, AnimationData animationData) {
		switch (transitionType) {
			case FORWARD:
			case REPLACE:
			case RESET:
				return new SimpleTransitionAnimation(R.anim.slide_in_right, R.anim.slide_out_left);

			case BACK:
				return new SimpleTransitionAnimation(R.anim.slide_in_left, R.anim.slide_out_right);

			default:
				return TransitionAnimation.DEFAULT;
		}
	}
}
