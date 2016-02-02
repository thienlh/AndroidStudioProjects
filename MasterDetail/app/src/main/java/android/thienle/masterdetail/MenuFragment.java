package android.thienle.masterdetail;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the
 * interface.
 */
public class MenuFragment extends ListFragment {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MenuFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ItemList itemList = new ItemList();
        List<Item> items = itemList.getList();
        CustomAdapter adapter = new CustomAdapter(items, getActivity());
        setListAdapter(adapter);
        DetailFragment detailFragment = (DetailFragment)
                getFragmentManager().findFragmentById(R.id.fragment_detail);
        if (detailFragment != null && detailFragment.isInLayout()) { //Landscape
            Item item = ItemList.get(getSelectedItemPosition());
            detailFragment.updateView(item);
        }
        return view;
    }

    //  Call when notify change
    public void notifyChanged() {
        ((CustomAdapter) getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.fragment_detail);

        if (detailFragment != null && detailFragment.isInLayout()) {    //  landscape mode
            //  Load items and update view
            Item item = ItemList.get(getActivity()).get(position);
            detailFragment.updateView(item);
        } else {
            //  Send intent to Detail Activity, with enclosed position
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("index", position);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }


    private class CustomAdapter extends BaseAdapter {
        private List<Item> items;
        private Context mContext;

        public CustomAdapter(List<Item> items, Context mContext) {
            this.items = items;
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater mInflater = (LayoutInflater) mContext
                        .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = mInflater.inflate(R.layout.fragment_menu, null);
            }

            TextView versionName = (TextView) convertView.findViewById(R.id.id);
            TextView versionInfo = (TextView) convertView.findViewById(R.id.etInfo);
            Item curr = (Item) getItem(position);
            versionName.setText(curr.getVersion());
            versionInfo.setText(curr.getInfo());

            return convertView;
        }
    }
}
