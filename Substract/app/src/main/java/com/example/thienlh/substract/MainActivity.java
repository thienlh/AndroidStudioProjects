package com.example.thienlh.substract;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  public static final int REQUEST_CODE = 9394 ;
  Button mButton;
  int num1 = Integer.parseInt(findViewById(R.id.editText).toString());
  int num2 = Integer.parseInt(findViewById(R.id.editText2).toString());

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mButton = (Button) findViewById(R.id.button);
    mButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, SubActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("num1", num1);
        bundle.putInt("num2", num2);

        intent.putExtras(bundle);
        //  REQUEST_CODE for identify activities
        startActivityForResult(intent, REQUEST_CODE);
      }
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
Bundle bundle = data.getExtras();
      Toast.makeText(this, "Result: " + bundle.getInt("num1") + "+" + bundle.getInt("num2") + "=" + bundle.getInt("sum"), Toast.LENGTH_SHORT).show();
    }
  }
}
