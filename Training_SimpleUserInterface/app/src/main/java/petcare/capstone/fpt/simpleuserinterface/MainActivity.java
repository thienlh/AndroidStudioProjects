package petcare.capstone.fpt.simpleuserinterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "petcare.capstone.fpt.simpleuserinterface.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*  Called when the user clicks the Send button   */
    public void sendMessage(View view) {
        //  Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class); //  Create an intent
        EditText editText = (EditText) findViewById(R.id.edit_message); //  Find the text view
        String message = editText.getText().toString(); //  Get the message
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
