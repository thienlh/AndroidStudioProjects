package thienle.sample.com.omdbexample;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import thienle.sample.com.omdbexample.pojo.Movie;

public class MainActivity extends AppCompatActivity {
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
}

/**
 *
 * @param view
 */
public void getMovieInfo(View view) {
        //  Get the text
        EditText editText = (EditText) findViewById(R.id.promt_title);
        String movieTitle = editText.getText().toString();
        //  Call OMDb API http://www.omdbapi.com/?t=Spirited+Away
        String url = "http://www.omdbapi.com/?%s=%s";

        try {
                String urlString = String.format(url, "t", URLEncoder.encode(movieTitle, "UTF-8"));
                new BackgroundTask(this).execute(urlString);
        } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
        }
}

/**
 *
 */
private class BackgroundTask extends AsyncTask<String, Void, Movie> {
private final ProgressDialog dialog;
private HttpURLConnection connection;
private Bitmap bitmap;
private Context context;

public BackgroundTask(Context context) {
        this.context = context;
        dialog = new ProgressDialog(context);
}

@Override
protected void onPreExecute() {
        super.onPreExecute();

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (manager == null) {
                Toast.makeText(context, "Not connected to the Internet", Toast.LENGTH_SHORT).show();
                this.cancel(true);
        } else {
                this.dialog.setMessage("Wait..for...it...");
                this.dialog.show();
        }
}

private Bitmap loadImage(String url) throws IOException {
        Bitmap bitmap;
//            inputStream = new URL(url).openStream();
        InputStream inputStream = this.openHttpConnection(url);
        bitmap = BitmapFactory.decodeStream(inputStream);
        inputStream.close();
        return bitmap;
}

private Movie readAndParseJSON(String input) {
        Movie movie = null;
        try {
                JSONObject jsonMovie = new JSONObject(input);

                if (jsonMovie.getString("Response").equals("True")) {
                        movie = new Movie();
                        movie.setTitle(jsonMovie.getString("Title"));
                        movie.setYear(jsonMovie.getString("Year"));
                        movie.setActors(jsonMovie.getString("Actors"));
                        movie.setAwards(jsonMovie.getString("Awards"));
                        movie.setPoster(jsonMovie.getString("Poster"));
                        movie.setDirector(jsonMovie.getString("Director"));
                        movie.setPlot(jsonMovie.getString("Plot"));
                        movie.setImdbRating(jsonMovie.getString("imdbRating"));
                }
        } catch (JSONException e) {
                e.printStackTrace();
        }

        return movie;
}

/**
 * @param sUrl
 * @return
 * @throws IOException
 */
private InputStream openHttpConnection(String sUrl) throws IOException {
        InputStream inputStream;
        int response;

        URL url = new URL(sUrl);
        connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);       //  Use for GET and POST
        connection.setAllowUserInteraction(true);
        connection.setInstanceFollowRedirects(true);
        connection.connect();
        response = connection.getResponseCode();

        if (response == HttpURLConnection.HTTP_OK) {        //  200 :v
                inputStream = connection.getInputStream();
                return inputStream;
        }

        return null;
}

@Override
protected Movie doInBackground(String ... params) {
        Movie movie = null;
        try {
//                InputStream inputStream = new URL(params[0]).openStream();
                InputStream inputStream = openHttpConnection(params[0]);
                if (inputStream != null) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        movie = readAndParseJSON(reader.readLine());
                        reader.close();
                        inputStream.close();

                        if (movie != null) {
                                //  Load the poster
                                bitmap = loadImage(movie.getPoster());
                        }
                }
        } catch (IOException e) {
                e.printStackTrace();
        }

        return movie;
}

@Override
protected void onPostExecute(Movie movie) {
//            super.onPostExecute(movie);
        if (movie != null) {
                //  Set the views here!!!
                Intent intent = new Intent(context, MovieInfoActivity.class);
                intent.putExtra("movie_title", movie.getTitle());
                intent.putExtra("movie_rating", movie.getImdbRating());
                intent.putExtra("movie_year", movie.getYear());
                intent.putExtra("movie_director", movie.getDirector());
                intent.putExtra("movie_actors", movie.getActors());
                intent.putExtra("movie_awards", movie.getAwards());
                intent.putExtra("movie_plot", movie.getPlot());

                if (bitmap != null) {
                        intent.putExtra("movie_poster", bitmap);
                }

                startActivity(intent);
        } else {
                Toast.makeText(context, "Movie not found!", Toast.LENGTH_SHORT).show();
        }

        if (connection != null) {
                connection.disconnect();
        }

        if (this.dialog.isShowing()) {
                this.dialog.dismiss();
        }
}
}
}
