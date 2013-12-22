package com.example.viva;

import java.util.ArrayList;

import com.example.viva.*;

import java.util.List;

import com.example.viva.util.LogUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;

@SuppressLint("ValidFragment")
public class EditFolderFragment extends Activity {

	int count = 0;
	ArrayList<String> list;
	ArrayAdapter<String> adapter;
	ListView lv;
	private SQLiteDatabase sdb;
	private SimpleCursorAdapter mAdapter;
	private String iCode = null;
	Button btndelete;
	private int mFoldernum;
	EditText editName;
	EditText editUrl;
	String eN;
	String eU;
	AlertDialog aDia;
	InputMethodManager imm;

	private int mColorRes = -1;

	public EditFolderFragment() {
		this(R.color.white);
	}

	public EditFolderFragment(int colorRes) {
		mColorRes = colorRes;
	}

	public EditFolderFragment(int colorRes, int foldernum) {
		mColorRes = colorRes;
		mFoldernum = foldernum;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editfraglist);
		
		LogUtil.v("come3");
		btndelete = (Button) findViewById(R.id.btn_delete);
		btndelete.setOnClickListener(mClickListener);
		LogUtil.v("come3");
		LogUtil.v("come3");
		list = new ArrayList<String>();

		VivaDataHelper db = new VivaDataHelper(this);

		checkdb();

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_single_choice, list);
		LogUtil.v("come3");
		lv = (ListView) findViewById(R.id.videditlist);
		LogUtil.v("come4");
		imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

		lv.setAdapter(adapter);
		LogUtil.v("come4");

	}

	public void checkdb() {
		VivaDataHelper db = new VivaDataHelper(this);
		list.clear();
		if (mFoldernum == 0) {
			list = db.getAllVideosName();
		}
		else if (mFoldernum == 1) {
			list = db.getVideosByFolder(mFoldernum);
		} else if (mFoldernum == 2) {
			list = db.getVideosByFolder(mFoldernum);
		} else if (mFoldernum == 3) {
			list = db.getVideosByFolder(mFoldernum);
		} else if (mFoldernum == 4) {
			list = db.getVideosByFolder(mFoldernum);
		}
		else
		{
			list = db.getVideosByFolder(mFoldernum);
		}
		list.add("");
	}
	
	

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("mColorRes", mColorRes);
	}

	// listview onclicklistener

	// button onclicklistner
	Button.OnClickListener mClickListener = new View.OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_delete:
				VivaDataHelper db = new VivaDataHelper(EditFolderFragment.this);

				int pos = lv.getCheckedItemPosition();
				LogUtil.v("" + pos);
				if (pos != ListView.INVALID_POSITION) {
					LogUtil.i(list.get(pos));
					LogUtil.v("" + pos);
					String name = list.get(pos);

					LogUtil.v("" + pos);
					Video eVideo;
					LogUtil.v("" + pos);
					eVideo = db.getVideoByName(name);
					LogUtil.i(list.get(pos));
					db.deleteVideo(eVideo);
					LogUtil.i(list.get(pos));
					list.remove(pos);
					LogUtil.i(list.get(pos));
					LogUtil.v("" + pos);

					LogUtil.v("" + pos);

					adapter.notifyDataSetChanged();
					FindOldNew.oldnewnum = mFoldernum;
					Intent intent = new Intent(EditFolderFragment.this,
							MainActivity.class);
					startActivity(intent);
					finish();
					break;
				}
			}
		}
	};

}