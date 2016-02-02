package com.twe.android.master_detail;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
  public static final String VERSION_INDEX = "index";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
      finish();
      return;
    }
    setContentView(R.layout.activity_detail);
    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      String s = extras.getString(VERSION_INDEX);
      DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager()
          .findFragmentById(R.id.fragment_detail);
      Version version = DataCenter.get(this).getVersion(s);
      if (version != null) detailFragment.updateView(version);
    }
  }
}
