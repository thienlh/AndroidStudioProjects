package com.twe.samples.eventnote;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by TUDT on 1/12/2016.
 */
public class DataCenter {
  private static DataCenter singleton;
  private List<Event> list = new ArrayList<>();

  private DataCenter(Context context) {
    load(context);
  }

  private void load(Context context) {
    Event[] events = {
        new Event().setTitle("Gap Suu nhi").setDetails("Ca phe voi Suu nhi Man Huynh Khuong.").setSolved(true),
        new Event().setTitle("Gap Soai ca").setDetails("Chem gio voi Soai ca Nguyen Nhat Nguyen.").setSolved(false),
        new Event().setTitle("Gap Ngu muoi").setDetails("Danh thuc Ngu muoi Giang Song Truc.").setSolved(true),
    };
    list = Arrays.asList(events);
  }

//  private void load(Context context) {
//    DBAdapter dbAdapter = null;
//    try {
//      dbAdapter = new DBAdapter(context);
//      dbAdapter = dbAdapter.open();
//      list = dbAdapter.getAll();
//    } catch (SQLException ex) {
//      list = new ArrayList<>();
//    } finally {
//      if (dbAdapter != null) {
//        dbAdapter.close();
//      }
//    }
//  }

  public static DataCenter get(Context context) {
    if (singleton == null) {
      singleton = new DataCenter(context);
    }
    return singleton;
  }

  public Event getAt(int position) {
    return list.get(position);
  }

  public Event getEvent(UUID id) {
    for (Event e : list) {
      if (e.getId().equals(id)) return e;
    }
    return null;
  }

  public List<Event> getList() {
    return list;
  }
}
