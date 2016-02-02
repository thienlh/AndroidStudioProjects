package com.twe.android.master_detail;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class DataCenter {
  private static DataCenter singleton;
  private ArrayList<Version> list = new ArrayList<>();
  private Context context;

  private DataCenter(Context context) {
    this.context = context;
    load1();
  }

  public static DataCenter get(Context context) {
    if (singleton == null) {
      singleton = new DataCenter(context);
    }
    return singleton;
  }

  private Bitmap getBitmapFromAsset(String strName) {
    AssetManager assetManager = context.getAssets();
    InputStream is = null;
    try {
      is = assetManager.open(strName);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return BitmapFactory.decodeStream(is);
  }

  private void load1() {
    Version[] versions = {
        new Version("Cupcake", "1.5", getBitmapFromAsset("cupcake.jpg")),
        new Version("Donut", "1.6", getBitmapFromAsset("donut.jpg")),
        new Version("Eclair", "2.0", getBitmapFromAsset("eclair.jpg")),
        new Version("Froyo", "2.2", getBitmapFromAsset("froyo.jpg")),
        new Version("Gingerbread", "2.3", getBitmapFromAsset("gingerbread.jpg")),
        new Version("Honeycomb", "3.0", getBitmapFromAsset("honeycomb.jpg")),
        new Version("Icecream", "4.0", getBitmapFromAsset("icecream.jpg")),
        new Version("Jellybean", "4.1", getBitmapFromAsset("jellybean.jpg")),
        new Version("Kitkat", "4.4", getBitmapFromAsset("kitkat.jpg")),
        new Version("Lollipop", "1.5", getBitmapFromAsset("lollipop.jpg"))
    };
    list.addAll(Arrays.asList(versions));
  }

  // getters
  public Version getVersion(String sUUID) {
    UUID uuid = UUID.fromString(sUUID);
    for (Version v : list) {
      if (v.getId().equals(uuid)) return v;
    }
    return null;
  }

  public Version getItem(int position) {
    return list.get(position);
  }

  public ArrayList<Version> getList() {
    return list;
  }

//  private void load() {
//    DBAdapter database = new DBAdapter(context);
//    database.open();
//    list = database.getAll();
//    database.close();
//  }
}
