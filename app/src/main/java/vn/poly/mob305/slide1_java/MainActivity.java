package vn.poly.mob305.slide1_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String api = "https://jsonplaceholder.typicode.com/todos/1";
    String apiPost = "https://jsonplaceholder.typicode.com/posts";

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
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStart() {
                Toast.makeText(MainActivity.this, "Starting...", Toast.LENGTH_SHORT).show();
            }
        });
        asyncTask.execute(apiPost,"POST");
    }

}