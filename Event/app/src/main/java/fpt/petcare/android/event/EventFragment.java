package fpt.petcare.android.event;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventFragment extends Fragment {
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//  private static final String ARG_PARAM1 = "param1";
//  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
//  private String mParam1;
//  private String mParam2;

  private OnFragmentInteractionListener mListener;
  private Event mEvent;
  private EditText mTitle;
  private EditText mDetail;
  private Button mButton;
  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment EventFragment.
   */
  // TODO: Rename and change types and number of parameters
//  public static EventFragment newInstance(String param1, String param2) {
//    EventFragment fragment = new EventFragment();
//    Bundle args = new Bundle();
//    args.putString(ARG_PARAM1, param1);
//    args.putString(ARG_PARAM2, param2);
//    fragment.setArguments(args);
//    return fragment;
//  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment EventFragment.
   */
  // TODO: Rename and change types and number of parameters
//  public static EventFragment newInstance(String param1, String param2) {
//    EventFragment fragment = new EventFragment();
//    Bundle args = new Bundle();
//    args.putString(ARG_PARAM1, param1);
//    args.putString(ARG_PARAM2, param2);
//    fragment.setArguments(args);
//    return fragment;
//  }  public EventFragment() {
//    // Required empty public constructor
//  }
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mEvent = new Event();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    View view = inflater.inflate(R.layout.fragment_event, container, false);

    mTitle = (EditText) view.findViewById(R.id.title_id);

    TextWatcher textWatcher = textWatcher();

    mTitle.addTextChangedListener(textWatcher);

    //  listener for mTitle


    mDetail = (EditText) view.findViewById(R.id.detail_id);

    //  listener for mDetail

    mButton = (Button) view.findViewById(R.id.button);

    DateFormat df = new DateFormat();
    mButton.setText(df.format("EEE, MMM dd, yyyy", mEvent.getDate()));
    return view;
  }

  private TextWatcher textWatcher() {
    TextWatcher textWatcher = new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 0) {
          mTitle.setVisibility(View.VISIBLE);
        } else {
          mTitle.setText("You have entered: ");
        }

      }

      @Override
      public void afterTextChanged(Editable s) {
        mTitle.setVisibility(View.GONE);
      }
    };

    return textWatcher;
  }

  // TODO: Rename method, update argument and hook method into UI event
//  public void onButtonPressed(Uri uri) {
//    if (mListener != null) {
//      mListener.onFragmentInteraction(uri);
//    }
//  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
          + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   * <p/>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }


  private void showEvent(View view) {
    Toast.makeText(getActivity(), mEvent.toString(), Toast.LENGTH_SHORT).show();
  }
}
