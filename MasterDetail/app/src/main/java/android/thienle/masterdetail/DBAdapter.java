package android.thienle.masterdetail;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thienle on 1/19/16.
 */
public class DBAdapter {
    static final String DATABASE_NAME = "versions.sqlite";
    static final String CATEGORY_TABLE = "Version";
    static final int DATABASE_VERSION = 1;
    final Context context;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;


    public DBAdapter(Context context) {
        this.context = context;
        moveDatabaseToDevice();
        dbHelper = new DatabaseHelper(context);
    }

    private void moveDatabaseToDevice() {
        try {
            String destPath = "data/data/" + context.getPackageName() + "/databases";
            File f = new File(destPath);

            if (!f.exists()) {
                f.mkdirs();
                f.createNewFile();
                copyDB(context.getApplicationContext().getAssets().open(DATABASE_NAME), new FileOutputStream(destPath + "/" + DATABASE_NAME));
            }
        } catch (FileNotFoundException e) {
            Log.e("DBAdapter", "moveDatabaseToDevice: ", e);
        } catch (IOException e) {
            Log.e("DBAdapter", "moveDatabaseToDevice: ", e);
        }
    }

    private void copyDB(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }

    public DBAdapter open() throws SQLException {
        dbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public List<Item> getAll() {
        List<Item> results = new ArrayList<Item>();
        try (Cursor cCursor = db.query(CATEGORY_TABLE,
                new String[]{"version", "info"}, //  columns in category table
                null, null, null, null, null, null)) {

            if (cCursor != null) {
                cCursor.moveToFirst();  //  Move the cursor to the first row
                while (!cCursor.isAfterLast()) {
                    //  Get data
                    results.add(new Item(cCursor.getString(cCursor.getColumnIndex("version")), cCursor.getString(cCursor.getColumnIndex("info"))));
                    cCursor.moveToNext();
                }
            }
            //  In case, you know...
            if (cCursor != null) {
                cCursor.close();
            }
        } catch (Exception e) {
            Log.e("DBAdapter", "Error reading SQLite database: ", e);
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
