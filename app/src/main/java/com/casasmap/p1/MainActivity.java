package com.casasmap.p1;

import java.util.Random;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.casasmap.p1.ShakeDetector.OnShakeListener;

public class MainActivity extends Activity {
	// private CristalBall mCristalBall = new CristalBall();
	public String string = "";
	public TextView mTextView;
	public Button mMagicButton;
	public ImageView mCristalBallImage;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private ShakeDetector mShakeDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Declare our view variables
		mTextView = (TextView) findViewById(R.id.textViewMain1);
		mMagicButton = (Button) findViewById(R.id.buttonMain1);
		mCristalBallImage = (ImageView) findViewById(R.id.imageView1);
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mShakeDetector = new ShakeDetector(new OnShakeListener() {

			@Override
			public void onShake() {
				// TODO Auto-generated method stub
				handleNewAnswer();

			}
		});

		// some magic with the button when clicked on
		mMagicButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				handleNewAnswer();
			}
		});
	Toast.makeText(this, R.string.toast_message, Toast.LENGTH_LONG).show();
	}

	//@override
	public void onResume() {
		super.onResume();
		mSensorManager.registerListener(mShakeDetector, mAccelerometer,
				SensorManager.SENSOR_DELAY_UI);

	}

	//@override
	public void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(mShakeDetector);
	}

	private void animateCrystalBall() {

		mCristalBallImage.setImageResource(R.drawable.ball_animation);
		AnimationDrawable ballAnimation = (AnimationDrawable) mCristalBallImage
				.getDrawable();
		if (ballAnimation.isRunning()) {
			ballAnimation.stop();
		}
		ballAnimation.start();

	}

	private void animateAnswer() {
		AlphaAnimation fadeInAnimation = new AlphaAnimation(0, 1);
		fadeInAnimation.setDuration(1500);
		fadeInAnimation.setFillAfter(true);
		mTextView.setAnimation(fadeInAnimation);
	}

	private void playSound() {
		MediaPlayer player = MediaPlayer.create(this, R.raw.crystal_ball);
		player.start();
		player.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void handleNewAnswer() {
		String[] answers = getResources().getStringArray(R.array.the_answers);
		;
		Random random = new Random();
		int randomGenerated = random.nextInt(answers.length);
		string = answers[randomGenerated];
		mTextView.setText(string);
		animateCrystalBall();
		animateAnswer();
		playSound();
	}

}
