package com.twe.samples.eventnote;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class EventListFragment extends Fragment {
  private RecyclerView mRecyclerView;
  private EventAdapter mAdapter;
  private int mPosition;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_event_list, container, false);
    mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_id);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    updateUI();
    return view;
  }

  @Override
  public void onResume() {
    super.onResume();
    updateUI();
  }

  private void updateUI() {
    if (mAdapter == null) {
      List<Event> mEventList = DataCenter.get(getActivity()).getList();
      mAdapter = new EventAdapter(mEventList);
      mRecyclerView.setAdapter(mAdapter);
    } else {
      mAdapter.notifyItemChanged(mPosition);
    }
  }

  private class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mTitleTextView;
    private TextView mDateTextView;
    private CheckBox mSolvedCheckBox;

    private Event mEvent;

    public EventHolder(View itemView) {
      super(itemView);
      itemView.setOnClickListener(this);
      mTitleTextView = (TextView) itemView.findViewById(R.id.item_title_id);
      mDateTextView = (TextView) itemView.findViewById(R.id.item_date_id);
      mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.item_solved_id);
    }

    public void bindViewHolder(Event event) {
      this.mEvent = event;
      mTitleTextView.setText(mEvent.getTitle());
      DateFormat df = new DateFormat();
      mDateTextView.setText(df.format("EEE, MMM dd, yyyy", mEvent.getDate()));
      mSolvedCheckBox.setChecked(mEvent.isSolved());
    }

    @Override
    public void onClick(View v) {
      mPosition = getAdapterPosition();
      Intent intent = EventPagerActivity.newIntent(getActivity(), mEvent.getId());
      startActivity(intent);
    }
  }

  private class EventAdapter extends RecyclerView.Adapter<EventHolder> {
    private List<Event> mEventList;

    public EventAdapter(List<Event> mEventList) {
      this.mEventList = mEventList;
    }

    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(getActivity());
      View view = inflater.inflate(R.layout.item_list_event, parent, false);
      return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(EventHolder holder, int position) {
      Event event = mEventList.get(position);
      holder.bindViewHolder(event);
    }

    @Override
    public int getItemCount() {
      return mEventList.size();
    }
  }

}
