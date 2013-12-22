package com.example.viva;

import android.provider.BaseColumns;

//constants of vivadb
public final class VivaConstants {

	public VivaConstants() {
	}

	public static abstract class VivaFrame implements BaseColumns {

		// DB name,version
		public static final String DB_NAME = "Viva";
		public static final int DB_VERSION = 1;
		// table name
		public static final String TABLE_NAME = "vivadb";
		// column name
		public static final String COLUMN_NAME_VI_ID = "id";
		public static final String COLUMN_NAME_VI_FOLDER = "vifoldernum";
		public static final String COLUMN_NAME_VI_NAME = "viname";
		public static final String COLUMN_NAME_VI_URL = "viurl";
	}

}


