package android.thienle.masterdetail;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

      FragmentManager fragmentManager = getFragmentManager();
      Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_menu);
      if (fragment == null) {
          fragment = createFragment();
          fragmentManager.beginTransaction()
                  .add(R.id.fragment_menu, fragment)
                  .commit();
      }
  }

    private Fragment createFragment() {
        return new MenuFragment();
    }
}
