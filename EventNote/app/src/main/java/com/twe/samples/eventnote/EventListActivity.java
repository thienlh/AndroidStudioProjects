package com.twe.samples.eventnote;

import android.support.v4.app.Fragment;

/**
 * Created by TUDT on 1/13/2016.
 */
public class EventListActivity extends SingleFragmentActivity {
  @Override
  public Fragment createFragment() {
    return new EventListFragment();
  }
}
