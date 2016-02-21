package manmarper.com.sqlitedatabasegeneric.Generic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GenAdapter extends SQLiteOpenHelper {

	private static final String TAG = "DBAdapter";
	public static final String DATABASE_NAME = "sqliteGenericDB";
	private static final int DATABASE_VERSION = 1;

	private static SQLiteDatabase db;
	public static final String KEY_ID = "id";
	public static final String[] columnsCount = new String[] { "count(*)" };
	public static final String[] columnsSum = new String[] { "sum(*)" };

	private static final String users_create_script = "create table users (id integer primary key, name text);";

	private static GenAdapter instance;

	public GenAdapter(Context ctx) {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public GenAdapter(Context ctx, String dbName, String sql, String tableName,
			int ver) {

		super(ctx, dbName, null, ver);
		Log.i(TAG, "Creating or opening database [ " + dbName + " ].");
	}

	public static GenAdapter getInstance(Context ctx) {
		if (instance == null) {
			instance = new GenAdapter(ctx);
			try {
				db = instance.getWritableDatabase();
			} catch (SQLiteException se) {
				Log.e(TAG, "Cound not create and/or open the database [ "
						+ DATABASE_NAME
						+ " ] that will be used for reading and writing.", se);
			}
		}
		return instance;
	}

	public static GenAdapter getInstance(Context ctx, String dbName,
			String sql, String tableName, int ver) {
		if (instance == null) {
			instance = new GenAdapter(ctx, dbName, sql, tableName, ver);
			try {
				Log.i(TAG, "Creating or opening the database [ " + dbName
						+ " ].");
				db = instance.getWritableDatabase();
			} catch (SQLiteException se) {
				Log.e(TAG, "Cound not create and/or open the database [ "
						+ dbName
						+ " ] that will be used for reading and writing.", se);
			}
		}
		return instance;
	}

	public void close() {
		if (instance != null) {
			Log.i(TAG, "Closing the database [ " + DATABASE_NAME + " ].");
			db.close();
			instance = null;
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(TAG, "Trying to create database table if it isn't existed.");
		try {

			db.execSQL(users_create_script);

			// db.execSQL(icon_create_script);

		} catch (SQLException se) {
			Log.e(TAG,
					"Cound not create the database table according to the SQL statement ",
					se);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(TAG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		try {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");

			db.execSQL("drop table users");

			onCreate(db);
		} catch (SQLException se) {
			Log.e(TAG, "Cound not drop the database table ", se);
		}
	}

	public long insert(String table, ContentValues values) {
		return db.insert(table, null, values);
	}

	public Cursor get(String table, String[] columns, String where,
			String orderBy, long id) {
		Cursor cursor = db.query(true, table, columns, createWhere(where, id),
				null, null, null, orderBy, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		return cursor;
	}

	public int delete(String table, String where, long id) {
		return db.delete(table, createWhere(where, id), null);
	}

	public int update(String table, long id, ContentValues values, String where) {
		return db.update(table, values, createWhere(where, id), null);
	}

	private String createWhere(String where, long id) {
		String whereFinal = where;
		if (id != -1) {
			if (where != null) {
				whereFinal = where + ", " + KEY_ID + "=" + id;
			} else {
				whereFinal = KEY_ID + "=" + id;
			}
		}
		return whereFinal;
	}
}
