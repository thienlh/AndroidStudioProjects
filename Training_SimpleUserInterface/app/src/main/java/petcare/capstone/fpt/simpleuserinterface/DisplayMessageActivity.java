package petcare.capstone.fpt.simpleuserinterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        //  Get the intent
        Intent intent = getIntent();
        //  Get the message
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        //  Display the message
        TextView textView = new TextView(this);
        //  Set the text view's properties
        textView.setTextSize(40);
        //  Set text
        textView.setText(message);

        /*   Add the TextView to the RelativeLayout identified by R.id.content.   */
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.content);
        relativeLayout.addView(textView);
    }
}
