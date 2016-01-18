package android.thienle.masterdetail;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class DetailFragment extends Fragment {
    EditText editText;
    Item currentItem = null;
    MenuFragment menuFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        //  Get menu fragment
        menuFragment = (MenuFragment) getFragmentManager().findFragmentById(R.id.fragment_menu);
        editText = (EditText) view.findViewById(R.id.etInfo);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentItem.setInfo(s.toString());
                if (menuFragment != null) {
                    menuFragment.notifyChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }

    public void updateView(Item item) {
        currentItem = item;
        editText.setText(currentItem.getInfo());
    }
}
