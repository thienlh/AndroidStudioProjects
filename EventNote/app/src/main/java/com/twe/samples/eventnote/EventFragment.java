package com.twe.samples.eventnote;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

public class EventFragment extends Fragment {
  public static String ARG_EVENT_ID = "event_id";
  ;
  private Event mEvent;
  private EditText mTitle;
  private EditText mDetails;
  private CheckBox mSolved;
  private Button mButton;

  public static EventFragment newInstance(UUID eventId) {
    Bundle args = new Bundle();
    args.putSerializable(ARG_EVENT_ID, eventId);
    EventFragment fragment = new EventFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    UUID id = (UUID) getArguments().getSerializable(ARG_EVENT_ID);
    mEvent = DataCenter.get(getActivity()).getEvent(id);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_event, container, false);
    mTitle = (EditText) view.findViewById(R.id.title_id);
    mTitle.setText(mEvent.getTitle());
    mTitle.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        mEvent.setTitle(s.toString());
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });

    mDetails = (EditText) view.findViewById(R.id.details_id);
    mDetails.setText(mEvent.getDetails());
    mDetails.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        mEvent.setDetails(s.toString());
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });

    mSolved = (CheckBox) view.findViewById(R.id.solved_id);
    mSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mEvent.setSolved(isChecked);
      }
    });
    mSolved.setChecked(mEvent.isSolved());

    mButton = (Button) view.findViewById(R.id.button_id);
    DateFormat df = new DateFormat();
    mButton.setText(df.format("EEE, MMM dd, yyyy", mEvent.getDate()));
    mButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(getActivity(), mEvent.toString(), Toast.LENGTH_SHORT).show();
      }
    });
    return view;
  }
}
