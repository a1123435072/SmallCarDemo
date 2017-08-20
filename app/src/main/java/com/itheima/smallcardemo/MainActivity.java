package com.itheima.smallcardemo;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
	Handler handler=new Handler();
	private ImageView gas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView front= (ImageView) findViewById(R.id.car_front_tire);
		AnimationDrawable frontAnimationDrawable= (AnimationDrawable) front.getDrawable();
		frontAnimationDrawable.start();

		ImageView back= (ImageView) findViewById(R.id.car_back_tire);
		AnimationDrawable backAnimationDrawable= (AnimationDrawable) back.getDrawable();
		backAnimationDrawable.start();

		gas = (ImageView) findViewById(R.id.car_gas);
		handler.postDelayed(new MyRunnable(),300);
	}
	private boolean isVisiable=false;
	private class MyRunnable implements Runnable{

		@Override
		public void run() {
			gas.setVisibility(isVisiable? View.VISIBLE:View.INVISIBLE);
			isVisiable=!isVisiable;
			handler.postDelayed(this,300);
		}
	}
}
