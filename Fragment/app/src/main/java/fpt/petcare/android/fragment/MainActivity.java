package fpt.petcare.android.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
    android.support.v4.app.Fragment fragment = fm.findFragmentById(R.id.fragment_container);

    if(fragment != null)  {
      fragment = new ItemFragment();
      fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
    }
  }

  @Override
  public void onListFragmentInteraction(Dish item) {
    Toast.makeText(this, item.getViName(), Toast.LENGTH_SHORT).show();
  }
}
