package com.twe.android.master_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class VersionFragment extends ListFragment
    implements  SearchView.OnQueryTextListener, SearchView.OnCloseListener {
  private SearchView searchView = null;
  private CustomAdapter adapter;

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    View view = inflater.inflate(R.layout.fragment_version, container, false);
    adapter = new CustomAdapter(getActivity(), DataCenter.get(getActivity()).getList());
    setListAdapter(adapter);
    return view;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    configureSearchView(menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  private void configureSearchView(Menu menu) {
    MenuItem search = menu.findItem(R.id.action_search);
    searchView = (SearchView) search.getActionView();
    searchView.setOnQueryTextListener(this);
    searchView.setOnCloseListener(this);
    searchView.setSubmitButtonEnabled(false);
    searchView.setQueryHint("Search by version name");
    searchView.setIconifiedByDefault(true);
    search.collapseActionView();
  }

  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);
    DetailFragment detailfragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.fragment_detail);
    if (detailfragment != null && detailfragment.isInLayout()) { // landscape
      detailfragment.updateView(DataCenter.get(getActivity()).getItem(position));
    } else { // portrait, send intent to DetailActivity
      Intent intent = new Intent(v.getContext(), DetailActivity.class);
      Bundle bundle = new Bundle();
      bundle.putString(DetailActivity.VERSION_INDEX, adapter.getItem(position).getId().toString());
      intent.putExtras(bundle);
      startActivity(intent);
    }
  }

  @Override
  public boolean onClose() {
    adapter.getFilter().filter("");
    searchView.onActionViewCollapsed();
    return true;
  }

  @Override
  public boolean onQueryTextSubmit(String query) {
    return false;
  }

  @Override
  public boolean onQueryTextChange(String newText) {
    if (TextUtils.isEmpty(newText)) {
      adapter.getFilter().filter("");
    } else {
      adapter.getFilter().filter(newText);
    }
    return true;
  }
}
