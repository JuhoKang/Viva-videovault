package com.example.viva;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract.Columns;

import com.example.viva.*;
import com.example.viva.VivaConstants.VivaFrame;
import com.example.viva.util.LogUtil;

//dbopenhelper
public class VivaDataHelper extends SQLiteOpenHelper {

	public VivaDataHelper(Context context) {
		super(context, VivaFrame.DB_NAME, null, VivaFrame.DB_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		/*
		 * String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
		 * + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PH_NO
		 * + " TEXT" + ")"; db.execSQL(CREATE_CONTACTS_TABLE);
		 */
		String CREATE_VIDEOS_TABLE = "CREATE TABLE " + VivaFrame.TABLE_NAME
				+ "(" + VivaFrame.COLUMN_NAME_VI_ID + " INTEGER PRIMARY KEY,"
				+ VivaFrame.COLUMN_NAME_VI_FOLDER + " INTEGER,"
				+ VivaFrame.COLUMN_NAME_VI_NAME + " TEXT,"
				+ VivaFrame.COLUMN_NAME_VI_URL + " TEXT" + ")";
		db.execSQL(CREATE_VIDEOS_TABLE);

	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + VivaFrame.TABLE_NAME);

		// Create tables again
		onCreate(db);
	}

	/**
	 * CRUD ÇÔ¼ö
	 */

	// add new Video
	public void addVideo(Video video) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(VivaFrame.COLUMN_NAME_VI_FOLDER, video.getFolderNumber()); // foldernumber
		values.put(VivaFrame.COLUMN_NAME_VI_NAME, video.getName()); // name
		values.put(VivaFrame.COLUMN_NAME_VI_URL, video.getUrl()); // url

		// Inserting Row
		db.insert(VivaFrame.TABLE_NAME, null, values);
		db.close(); // Closing database connection
	}

	// get video by folder number
	public ArrayList<String> getVideosByFolder(int foldernum) {
		ArrayList<String> videoList = new ArrayList<String>();

		String selectQuery = "SELECT * FROM " + VivaFrame.TABLE_NAME
				+ " WHERE " + VivaFrame.COLUMN_NAME_VI_FOLDER + "=" + foldernum;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				String videoname = new String();
				videoname = cursor.getString(2);
				// Adding to list
				videoList.add(videoname);
			} while (cursor.moveToNext());
		}

		return videoList;
	}

	// get Video by id
	public Video getVideo(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		/*
		 * Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
		 * KEY_NAME, KEY_PH_NO }, KEY_ID + "=?", new String[] {
		 * String.valueOf(id) }, null, null, null, null);
		 */
		Cursor cursor = db.query(VivaFrame.TABLE_NAME, new String[] {
				VivaFrame.COLUMN_NAME_VI_ID, VivaFrame.COLUMN_NAME_VI_FOLDER,
				VivaFrame.COLUMN_NAME_VI_NAME, VivaFrame.COLUMN_NAME_VI_URL },
				VivaFrame.COLUMN_NAME_VI_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Video video = new Video(Integer.parseInt(cursor.getString(0)),
				Integer.parseInt(cursor.getString(1)), cursor.getString(2),
				cursor.getString(3));
		// return contact
		return video;
	}

	// get all video information
	public List<Video> getAllVideos() {
		List<Video> videoList = new ArrayList<Video>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + VivaFrame.TABLE_NAME;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Video video = new Video();
				video.setID(Integer.parseInt(cursor.getString(0)));
				video.setFolderNumber(Integer.parseInt(cursor.getString(1)));
				video.setName(cursor.getString(2));
				video.setPhoneNumber(cursor.getString(3));
				// Adding contact to list
				videoList.add(video);
			} while (cursor.moveToNext());
		}

		// return contact list
		return videoList;
	}

	// get all videos by name
	public ArrayList<String> getAllVideosName() {
		ArrayList<String> videoList = new ArrayList<String>();

		String selectQuery = "SELECT * FROM " + VivaFrame.TABLE_NAME;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				String videoname = new String();
				videoname = cursor.getString(2);
				// Adding to list
				videoList.add(videoname);
			} while (cursor.moveToNext());
		}

		return videoList;
	}

	// update video
	public int updateVideo(Video video) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(VivaFrame.COLUMN_NAME_VI_FOLDER, video.getFolderNumber());
		values.put(VivaFrame.COLUMN_NAME_VI_NAME, video.getName());
		values.put(VivaFrame.COLUMN_NAME_VI_URL, video.getUrl());

		// updating row
		return db.update(VivaFrame.TABLE_NAME, values,
				VivaFrame.COLUMN_NAME_VI_ID + " = ?",
				new String[] { String.valueOf(video.getID()) });
	}

	// delete video
	public void deleteVideo(Video video) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(VivaFrame.TABLE_NAME, VivaFrame.COLUMN_NAME_VI_ID + " = ?",
				new String[] { String.valueOf(video.getID()) });
		db.close();
	}

	// how many videos
	public int getVideosCount() {
		String countQuery = "SELECT  * FROM " + VivaFrame.TABLE_NAME;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

	// get one video by name
	public Video getVideoByName(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		/*
		 * Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
		 * KEY_NAME, KEY_PH_NO }, KEY_ID + "=?", new String[] {
		 * String.valueOf(id) }, null, null, null, null);
		 */
		Cursor cursor = db.query(VivaFrame.TABLE_NAME, new String[] {
				VivaFrame.COLUMN_NAME_VI_ID, VivaFrame.COLUMN_NAME_VI_FOLDER,
				VivaFrame.COLUMN_NAME_VI_NAME, VivaFrame.COLUMN_NAME_VI_URL },
				VivaFrame.COLUMN_NAME_VI_NAME + "=?",
				new String[] { String.valueOf(name) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Video video = new Video(Integer.parseInt(cursor.getString(0)),
				Integer.parseInt(cursor.getString(1)), cursor.getString(2),
				cursor.getString(3));
		// return contact
		return video;
	}

}
