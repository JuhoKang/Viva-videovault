package com.example.viva;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

// activity called when starting application
public class SplashActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				finish();
			}
		};

		handler.sendEmptyMessageDelayed(0, 3000);
	}

}
