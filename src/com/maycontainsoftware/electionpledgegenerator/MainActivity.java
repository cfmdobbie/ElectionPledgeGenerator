package com.maycontainsoftware.electionpledgegenerator;

import java.util.Random;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;

/**
 * The sole activity in the project.
 * 
 * @author Charlie
 */
public class MainActivity extends ActionBarActivity {

	/** A simple on-screen instruction. */
	private TextView tapMessage;

	/** The view that will contain the generated pledge. */
	private TextView pledge;

	/** The surrounding view (in this case the layout) that changes color. */
	private View panel;

	/** Whether or not the instruction message is still visible. */
	private boolean tapMessageVisible = true;

	/** An enumeration that encapsulates colors of political parties along with text color that works with them. */
	private static enum ColorPair {
		Red(R.color.red, R.color.white),
		Blue(R.color.blue, R.color.white),
		Green(R.color.green, R.color.white),
		Orange(R.color.orange, R.color.white),
		Purple(R.color.purple, R.color.white),
		Grey(R.color.grey, R.color.white);

		/** The background color resource id. */
		private final int background;

		/** The foreground color resource id. */
		private final int foreground;

		/**
		 * Construct a new ColorPair.
		 * 
		 * @param background
		 *            The background color resource id.
		 * @param foreground
		 *            The foreground color resource id.
		 */
		private ColorPair(final int background, final int foreground) {
			this.background = background;
			this.foreground = foreground;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set up the screen layout
		setContentView(R.layout.activity_main);

		// Locate references to UI components
		tapMessage = (TextView) findViewById(R.id.tapMessage);
		pledge = (TextView) findViewById(R.id.pledge);
		panel = (View) findViewById(R.id.panel);

		// Generate the first pledge
		update();
	}

	/**
	 * Method to call when any View is tapped.
	 * 
	 * @param v
	 *            The specific View that was tapped - of no interest to us here.
	 */
	public void tap(final View v) {
		update();

		// If the tap message was still visible, fade it out
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
				// Hide the message from the layout when the animation has finished
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
	private ColorPair pickNewColorTheme() {
		final ColorPair[] themes = ColorPair.values();
		return themes[new Random().nextInt(themes.length)];
	}

	/** Method to update the app with a new pledge. */
	private void update() {
		updateColor();
		updatePledge();
	}

	/** Update the View colors wrt a random ColorTheme. */
	private void updateColor() {
		final ColorPair theme = pickNewColorTheme();
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
