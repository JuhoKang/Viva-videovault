package com.example.viva;

import java.util.ArrayList;

import com.example.viva.util.LogUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//the menufragment which comes out dragging left
public class MenuFragment extends ListFragment {

	ArrayList<String> fraglist;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		LogUtil.v("came");
		//String[] colors = getResources().getStringArray(R.array.color_names);
		fraglist = new ArrayList<String>();
		fraglist.add("전체");
		fraglist.add("유머");
		fraglist.add("게임");
		fraglist.add("감동");
		fraglist.add("기타");
		LogUtil.v("came");
		ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_1, android.R.id.text1, fraglist);
		LogUtil.v("came");
		setListAdapter(colorAdapter);
		LogUtil.v("came");
	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		Fragment newContent = null;
		switch (position) {
		case 0:
			newContent = new FolderFragment(0);
			break;
		case 1:
			newContent = new FolderFragment(1);
			break;
		case 2:
			newContent = new FolderFragment(2);
			break;
		case 3:
			newContent = new FolderFragment(3);
			break;
		case 4:
			newContent = new FolderFragment(4);
			break;
		}
		if (newContent != null)
			switchFragment(newContent);
	}

	// the meat of switching the above fragment
	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;
		
		if (getActivity() instanceof MainActivity) {
			MainActivity fca = (MainActivity) getActivity();
			fca.switchContent(fragment);
		}
	}
	
	public void addFolder()
	{
		
	}


}
