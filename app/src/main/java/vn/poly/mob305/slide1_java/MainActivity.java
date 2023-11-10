package vn.poly.mob305.slide1_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vn.poly.mob305.slide1_java.json.Root;

public class MainActivity extends AppCompatActivity {

    String api = "https://jsonplaceholder.typicode.com/todos/1";
    String apiPost = "https://jsonplaceholder.typicode.com/posts";

    String randomUser = "https://api.randomuser.me/?results=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetAsyncTask asyncTask = new GetAsyncTask();
        asyncTask.setMyListener(new MyListener() {
            @Override
            public void onUpdate(int progress) {
                // chua thiet lap nen chua chay 1!!!!!
            }

            @Override
            public void onFinished(String result) {
                //Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();

                Root rootM = new Gson().fromJson(result,Root.class);
                Toast.makeText(MainActivity.this, rootM.getInfo().getVersion() + "",
                        Toast.LENGTH_SHORT).show();


                try {
                    JSONObject root = new JSONObject(result);
                    JSONObject info = root.getJSONObject("info");
                    String seed = info.getString("seed");
                    int page = info.getInt("page");

                    JSONArray results = root.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject item = results.getJSONObject(i);
                        // lay ra cac thong so ben trong item !!!!

                    }
                    Toast.makeText(MainActivity.this, "" + results.length(), Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this,
                            "Sai format JSON!!!", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onStart() {
                Toast.makeText(MainActivity.this, "Starting...", Toast.LENGTH_SHORT).show();
            }
        });
        asyncTask.execute(randomUser,"GET");
    }

}