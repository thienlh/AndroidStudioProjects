package com.example.thienlh.substract;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
Bundle bundle;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    bundle = getIntent().getExtras();
    int a = bundle.getInt("num1");
    int b = bundle.getInt("num2");

    bundle.putInt("sum", a+b);
    finish();
  }

  @Override
  public void finish() {
    Intent intentResult = new Intent();
    intentResult.putExtras(bundle);
    setResult(RESULT_OK, intentResult);
    super.finish();
  }
}
