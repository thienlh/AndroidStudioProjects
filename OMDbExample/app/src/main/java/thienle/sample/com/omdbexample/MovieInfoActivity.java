package thienle.sample.com.omdbexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MovieInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        Intent intent = getIntent();
        TextView title = (TextView) findViewById(R.id.movie_title);
        TextView year = (TextView) findViewById(R.id.movie_year);
        TextView director = (TextView) findViewById(R.id.movie_director);
        TextView actors = (TextView) findViewById(R.id.movie_actors);
        TextView awards = (TextView) findViewById(R.id.movie_awards);
        TextView rating = (TextView) findViewById(R.id.movie_rating);
        TextView plot = (TextView) findViewById(R.id.movie_plot);
        ImageView poster = (ImageView) findViewById(R.id.movie_poster_img);

        title.setText(intent.getStringExtra("movie_title"));
        rating.setText(intent.getStringExtra("movie_rating"));
        plot.setText(intent.getStringExtra("movie_plot"));
        year.setText(intent.getStringExtra("movie_year"));
        director.setText(intent.getStringExtra("movie_director"));
        actors.setText(intent.getStringExtra("movie_actors"));
        awards.setText(intent.getStringExtra("movie_awards"));
        poster.setImageBitmap((Bitmap) intent.getParcelableExtra("movie_poster"));
    }
}
