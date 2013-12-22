package com.example.viva;

import java.util.ArrayList;

import com.example.viva.*;

import java.util.List;

import com.example.viva.util.LogUtil;

import android.annotation.SuppressLint;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class FolderFragment extends Fragment {

	int count = 0;
	ArrayList<String> list;
	ArrayAdapter<String> adapter;
	ListView lv;
	private SQLiteDatabase sdb;
	private SimpleCursorAdapter mAdapter;
	private String iCode = null;
	ImageButton btnaddvideo;
	ImageButton btnaddfolder;
	Button btnedit;
	private int mFoldernum;
	EditText editName;
	EditText editUrl;
	String eN;
	String eU;
	AlertDialog aDia;
	TextView folderName;

	private int mColorRes = -1;

	public FolderFragment() {
		this(R.color.white);
	}

	public FolderFragment(int foldernum) {
		mFoldernum = foldernum;
		setRetainInstance(true);
	}

	public FolderFragment(int colorRes, int foldernum) {
		mColorRes = colorRes;
		mFoldernum = foldernum;
		setRetainInstance(true);
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LogUtil.v("here!");

		View FragmentView = inflater.inflate(R.layout.fraglist, container,
				false);
		LogUtil.v("here!");
		btnaddvideo = (ImageButton) FragmentView
				.findViewById(R.id.btn_add_video);

		LogUtil.v("here!");
		btnaddvideo.setOnClickListener(mClickListener);
		btnedit = (Button) FragmentView.findViewById(R.id.btn_edit);
		btnedit.setOnClickListener(mClickListener);

		folderName = (TextView) FragmentView.findViewById(R.id.tVfoldername);
		if (mFoldernum == 0) {
			folderName.setText("전체");
		} else if (mFoldernum == 1) {
			folderName.setText("유머");
		} else if (mFoldernum == 2) {
			folderName.setText("게임");
		} else if (mFoldernum == 3) {
			folderName.setText("감동");
		} else if (mFoldernum == 4) {
			folderName.setText("기타");
		}

		// listview
		/*
		 * ArrayList<String> items = new ArrayList<String>();
		 * ArrayAdapter<String> adapter = new
		 * ArrayAdapter<String>(getActivity(),
		 * android.R.layout.simple_list_item_1, items); ListView listview =
		 * (ListView) getActivity().findViewById(R.id.vidlist);
		 * listview.setAdapter(adapter);
		 * listview.setOnItemClickListener(mItemClickListener);
		 */

		// construct the RelativeLayout
		// inflater =
		// (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// LogUtil.v("come1");
		// LinearLayout rll = (LinearLayout)inflater.inflate(R.layout.fraglist,
		// null);
		// LogUtil.v("come2");
		// rll.setBackgroundColor(color);
		LogUtil.v("come3");

		View layout = inflater.inflate(R.layout.dialog, null);
		AlertDialog.Builder aDialog = new AlertDialog.Builder(getActivity());
		aDialog.setTitle("추가");
		aDialog.setView(layout);
		aDialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		aDialog.setPositiveButton("추가", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				editName = (EditText) ((AlertDialog) dialog)
						.findViewById(R.id.etVideoName);
				editUrl = (EditText) ((AlertDialog) dialog)
						.findViewById(R.id.etURL);
				eN = editName.getText().toString();
				eU = editUrl.getText().toString();
				Video n_video = new Video(mFoldernum, eN, eU);
				VivaDataHelper db = new VivaDataHelper(getActivity());
				db.addVideo(n_video);

				checkdb();
				LogUtil.v(list.get(0));
				adapter.notifyDataSetChanged();
				adapter = new ArrayAdapter<String>(getActivity(),
						android.R.layout.simple_list_item_1, list);
				lv.setAdapter(adapter);
				lv.invalidateViews();
				LogUtil.v(list.get(0));
			}
		});
		aDia = aDialog.create();

		return FragmentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		list = new ArrayList<String>();

		VivaDataHelper db = new VivaDataHelper(getActivity());
		// 샘플데이타 넣기
		// Log.d("Insert: ","Inserting ..");
		// db.addVideo(new Video(1,"Ravi", "9100000000"));
		// db.addVideo(new Video(2,"Srinivas", "9199999999"));
		// db.addVideo(new Video(3,"Tommy", "9522222222"));
		// db.addVideo(new Video(4,"Karthik", "9533333333"));

		// 집어넣은 데이타 다시 읽어들이기
		// Log.d("Reading: ", "Reading all contacts..");

		// get all videos
		checkdb();

		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, list);
		LogUtil.v("come3");

		LogUtil.v("come4");

		adapter.notifyDataSetChanged();

		lv = (ListView) getView().findViewById(R.id.vidlist);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(mItemClickListener);

		// dialog

	}

	public void checkdb() {
		VivaDataHelper db = new VivaDataHelper(getActivity());
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
	}

	@Override
	public void onResume() {
		super.onResume();
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("mColorRes", mColorRes);
	}

	// listview onclicklistener
	AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView parent, View view, int position,
				long id) {
			Video nvideo;
			LogUtil.v("here i am!");
			VivaDataHelper db = new VivaDataHelper(getActivity());
			LogUtil.v("here i am!");
			String name = list.get(position);
			LogUtil.v("here i am!");
			nvideo = db.getVideoByName(name);
			LogUtil.v("here i am!");
			Uri uri = Uri.parse(nvideo.getUrl());
			LogUtil.v(nvideo.getUrl());
			LogUtil.v("here i am!");
			Intent it = new Intent(Intent.ACTION_VIEW, uri);
			LogUtil.v("here i am!");
			startActivity(it);
			LogUtil.v("here i am!");

		}
	};
	// button onclicklistner
	Button.OnClickListener mClickListener = new View.OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_add_video:
				if (mFoldernum == 0) {
					Toast toast = Toast.makeText(getActivity(),
							"전체폴더는 추가가 불가능합니다", Toast.LENGTH_SHORT);
					toast.show();
				}
				if (mFoldernum != 0) {
					LogUtil.v("clicked!");
					aDia.show();
				}

				break;
			case R.id.btn_edit:
				LogUtil.v("clicked");
				Intent intent = new Intent(getActivity(),
						EditFolderFragment.class);
				startActivity(intent);
				LogUtil.v("SHITE");
				getActivity().finish();

				break;
			}
		}
	};
}
