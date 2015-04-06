package com.maycontainsoftware.electionpledgegenerator;

import java.util.Random;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private TextView tapMessage;
	private TextView pledge;
	private View panel;

	private boolean tapMessageVisible = true;

	/** An enumeration that encapsulates colors of political parties along with text color that works with them. */
	private static enum ColorTheme {
		RedW(R.color.red, R.color.white),
		RedB(R.color.red, R.color.black),
		BlueW(R.color.blue, R.color.white),
		BlueB(R.color.blue, R.color.black),
		GreenW(R.color.green, R.color.white),
		GreenB(R.color.green, R.color.black),
		OrangeW(R.color.orange, R.color.white),
		OrangeB(R.color.orange, R.color.black),
		Purple(R.color.purple, R.color.white),
		Grey(R.color.grey, R.color.black);

		private final int background;
		private final int foreground;

		private ColorTheme(final int background, final int foreground) {
			this.background = background;
			this.foreground = foreground;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// getSupportActionBar().hide();

		setContentView(R.layout.activity_main);

		tapMessage = (TextView) findViewById(R.id.tapMessage);
		pledge = (TextView) findViewById(R.id.pledge);
		panel = (View) findViewById(R.id.panel);

		updateColor();
		updatePledge();
	}

	/**
	 * Method to call when any View is tapped.
	 * 
	 * @param v
	 *            The specific View that was tapped - of no interest to us here.
	 */
	public void tap(View v) {
		updateColor();
		updatePledge();

		if (tapMessageVisible) {
			tapMessageVisible = false;
			fadeOutTapMessage();
		}
	}

	/** Fade out the instructional message. */
	private void fadeOutTapMessage() {
		// Animate the fade out using an AlphaAnimation, not a ViewPropertyAnimator, as that requires API 12
		// Animate alpha from 1 to 0
		final AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
		// Over 1000ms
		anim.setDuration(1000L);
		// Start animation
		tapMessage.startAnimation(anim);
		// Detect when the animation finishes
		anim.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				// Hide the message frm the layout when the animation has finished
				tapMessage.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}
		});
	}

	/**
	 * Pick a random item from the ColorTheme enum.
	 * 
	 * @return A random ColorTheme
	 */
	private ColorTheme pickNewColorTheme() {
		final ColorTheme[] themes = ColorTheme.values();
		return themes[new Random().nextInt(themes.length)];
	}

	/** Update the View colors wrt a random ColorTheme. */
	private void updateColor() {
		final ColorTheme theme = pickNewColorTheme();
		panel.setBackgroundColor(getResources().getColor(theme.background));
		pledge.setTextColor(getResources().getColor(theme.foreground));
	}

	/** Update the pledge text. */
	private void updatePledge() {
		pledge.setText(generateNewPledge());
	}

	/**
	 * Generate a new election pledge.
	 * 
	 * @return A new election pledge.
	 */
	private String generateNewPledge() {
		return PledgeGenerator01.generate();
	}
}
