package com.example.thienlh.question;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    String[] questions = getResources().getStringArray(R.array.questions);
    int current = 0;

    List<Question> questionList = new ArrayList<Question>();
    for (int i = 0; i<questions.length; i++)  {
      //  Get string
      String s = questions[i];
      //  Substring
      String[] parts = s.split(";");
      String q = parts[0];
      boolean a = Boolean.parseBoolean(parts[1]);
      //  Add to pojo
      questionList.add(new Question(q, a));
    }

    Toast.makeText(this, "Question 1: " + questionList.get(0), Toast.LENGTH_SHORT).show();
  }
}
