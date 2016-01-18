package com.twe.samples.eventnote;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DBAdapter {
  static final String DATABASE_NAME = "events.sqlite";
  static final String CATEGORY_TABLE = "Event";
  static final int DATABASE_VERSION = 1;
  final Context context;
  DatabaseHelper dbHelper;
  SQLiteDatabase db;

  public DBAdapter(Context ctx) {
    this.context = ctx;
    moveDatabaseToDevice();
    dbHelper = new DatabaseHelper(context);
  }

  public DBAdapter open() throws SQLException {
    db = dbHelper.getReadableDatabase();
    return this;
  }

  public void close() {
    dbHelper.close();
  }

  public List<Event> getAll() {
    List<Event> results = new ArrayList<>();
    try {
      Cursor cCursor = db.query(CATEGORY_TABLE,
          new String[] {"id", "title", "details", "date", "solved"}, // các columns trong bảng categories
          null, null, null, null, null);
      if (cCursor != null) {
        cCursor.moveToFirst();
        SimpleDateFormat format = new SimpleDateFormat("EEE, MMM dd, yyyy");
        while (!cCursor.isAfterLast()) {
          results.add(new Event(
              UUID.fromString(cCursor.getString(cCursor.getColumnIndex("id"))),
              format.parse(cCursor.getString(cCursor.getColumnIndex("date"))))
                .setTitle(cCursor.getString(cCursor.getColumnIndex("title")))
                .setDetails(cCursor.getString(cCursor.getColumnIndex("details")))
                .setSolved(new Boolean(cCursor.getString(cCursor.getColumnIndex("solved")))));
          cCursor.moveToNext();
        }
      }
      if (cCursor!= null) {
        cCursor.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return results;
  }

  private void moveDatabaseToDevice() {
    try {
      String destPath = "/data/data/" + context.getPackageName() + "/databases";
      File f = new File(destPath);
      if (!f.exists()) {
        f.mkdirs();
        f.createNewFile();
        copyDB(context.getApplicationContext().getAssets().open(DATABASE_NAME), new FileOutputStream(destPath + "/" + DATABASE_NAME));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected void copyDB(InputStream inputStream, OutputStream outputStream) throws IOException {
    byte[] buffer = new byte[1024];
    int length;
    while ((length = inputStream.read(buffer)) > 0) {
      outputStream.write(buffer, 0, length);
    }
    inputStream.close();
    outputStream.close();
  }

  private static class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context ctx) {
      super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
  }
}
