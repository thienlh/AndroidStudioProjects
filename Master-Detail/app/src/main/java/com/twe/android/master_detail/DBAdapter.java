package com.twe.android.master_detail;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DBAdapter {
  static final String DATABASE_NAME = "versions.sqlite";
  static final String DATA_TABLE = "version";
  static final int DATABASE_VERSION = 1;
  final Context context;
  DatabaseHelper databaseHelper;
  SQLiteDatabase database;

  public DBAdapter(Context context) {
    this.context = context;
    databaseHelper = new DatabaseHelper(context);
  }

  public DBAdapter open() throws SQLException {
    database = databaseHelper.getReadableDatabase();
    return this;
  }

  public void close() {
    databaseHelper.close();
  }

  private Bitmap getBitmapFromAsset(String strName) throws IOException {
    AssetManager mAssetManager = context.getAssets();
    InputStream is = mAssetManager.open(strName);
    Bitmap mBitmap = BitmapFactory.decodeStream(is);
    is.close();
    return mBitmap;
  }

  public ArrayList<Version> getAll() {
    ArrayList<Version> results = new ArrayList<>();
    Cursor vCursor = database.query(DATA_TABLE,
        new String[]{"code", "level", "icon"}, null, null, null, null, null);
    if (vCursor != null) {
      vCursor.moveToFirst();
      while (!vCursor.isAfterLast()) {
        Bitmap mBitmap = null;
        try {
          mBitmap = getBitmapFromAsset(
              vCursor.getString(vCursor.getColumnIndex("icon")) + ".jpg");
        } catch (IOException ioe) {
          ioe.printStackTrace();
        }
        Version version = new Version(
            vCursor.getString(vCursor.getColumnIndex("code")),
            vCursor.getString(vCursor.getColumnIndex("level")),
            mBitmap
        );
        results.add(version);
        vCursor.moveToNext();
      }
      vCursor.close();
    }
    return results;
  }

  private static class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
  }
}
