package android.thienle.masterdetail;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class DetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //    If current orientation is landscape then return
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        setContentView(R.layout.activity_detail);

        //  Receive the intent
        Bundle extras = getIntent().getExtras();
        //  Check the extras
        if (extras != null) {
            int position = extras.getInt("index");
            DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.layout.fragment_detail);
            //  Get the selected items
            Item item = ItemList.get(this).get(position);
            //  Update the fragment
            detailFragment.updateView(item);
        }
    }
}
