package android.thienle.masterdetail;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by thienlh on 1/18/2016.
 */
public class CustomAdapter extends ArrayAdapter {

  public CustomAdapter(Context context, int resource, Object[] objects) {
    super(context, resource, objects);
  }

  @Override
  public void notifyDataSetChanged() {
    super.notifyDataSetChanged();
  }
}
