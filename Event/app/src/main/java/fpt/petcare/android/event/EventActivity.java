package fpt.petcare.android.event;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class EventActivity extends AppCompatActivity implements EventFragment.OnFragmentInteractionListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_event);

    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentById(R.id.fragment_container);

    if (fragment == null) {
      fragment = new EventFragment();
      fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
    }

  }

  @Override
  public void onFragmentInteraction(Uri uri) {

  }

}
