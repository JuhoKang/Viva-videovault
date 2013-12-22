package com.example.viva;

import java.util.List;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.viva.VivaConstants.VivaFrame;
import com.example.viva.util.LogUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

// uses jfeinstein10/SlidingMenu
public class MainActivity extends BaseActivity {

	private Fragment mContent;
	private SQLiteDatabase sdb;
	// find if this is new
	// because after editfolderfragment this activity is called
	int newnum = 10;

	public MainActivity() {
		super(R.string.changing_fragments);
	}

	private SlidingMenu menu;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// ------------------------------------------------------------------
		if (FindOldNew.oldnewnum != newnum) {
			super.onCreate(savedInstanceState);

			// set the Above View
			if (savedInstanceState != null)
				mContent = getSupportFragmentManager().getFragment(
						savedInstanceState, "mContent");
			if (mContent == null)
				mContent = new FolderFragment(FindOldNew.oldnewnum);

			// set the Above View
			setContentView(R.layout.content_frame);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, mContent).commit();

			// set the Behind View
			setBehindContentView(R.layout.menu_frame);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.menu_frame, new MenuFragment()).commit();

			// customize the SlidingMenu
			getSlidingMenu()
					.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		}

		// ------------------------------------------------------------------
		if (FindOldNew.oldnewnum == newnum) {

			super.onCreate(savedInstanceState);

			startActivity(new Intent(this, SplashActivity.class));
			// set the Above View
			if (savedInstanceState != null)
				mContent = getSupportFragmentManager().getFragment(
						savedInstanceState, "mContent");
			if (mContent == null)
				mContent = new FolderFragment(0);

			// set the Above View
			setContentView(R.layout.content_frame);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, mContent).commit();

			// set the Behind View
			setBehindContentView(R.layout.menu_frame);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.menu_frame, new MenuFragment()).commit();

			// customize the SlidingMenu
			getSlidingMenu()
					.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		}
		// ------------------------------------------------------------------

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}

	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		getSlidingMenu().showContent();
	}

}
