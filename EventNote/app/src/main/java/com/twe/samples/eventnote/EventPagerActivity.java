package com.twe.samples.eventnote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

/**
 * Created by TUDT on 1/14/2016.
 */
public class EventPagerActivity extends FragmentActivity {
  public static final String EXTRA_EVENT_ID = "com.twe.samples.eventnote.event_id";
  private ViewPager mViewPager;
  private List<Event> mEventList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_event_pager);
    mViewPager = (ViewPager) findViewById(R.id.activity_pager_id);
    mEventList = DataCenter.get(this).getList();
    FragmentManager fragmentManager = getSupportFragmentManager();
    mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
      @Override
      public Fragment getItem(int position) {
        Event e = mEventList.get(position);
        return EventFragment.newInstance(e.getId());
      }

      @Override
      public int getCount() {
        return mEventList.size();
      }
    });
    UUID id = (UUID) getIntent().getSerializableExtra(EXTRA_EVENT_ID);
    for (int i = 0; i < mEventList.size(); ++i)
      if (mEventList.get(i).getId().equals(id)) {
        mViewPager.setCurrentItem(i);
        break;
      }
  }

  public static Intent newIntent(Context context, UUID eventId) {
    return new Intent(context, EventPagerActivity.class)
        .putExtra(EXTRA_EVENT_ID, eventId);
  }
}
