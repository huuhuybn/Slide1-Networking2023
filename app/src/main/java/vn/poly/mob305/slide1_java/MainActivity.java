package vn.poly.mob305.slide1_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.setListener(new MyListener() {
            @Override
            public void onUpdate(int progress) {
                Toast.makeText(MainActivity.this,"Progress " + progress,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinished(String result) {
                Toast.makeText(MainActivity.this,"Finished",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStart() {
                Toast.makeText(MainActivity.this,"Starting...",
                        Toast.LENGTH_SHORT).show();
            }
        });
        myAsyncTask.execute("TEST"); // bat dau chay
    }

}