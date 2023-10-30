package vn.poly.mob305.slide1_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncTask task1 = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                // cau lenh viet trong nay !!!!
                // Main Thread
                int x = 10;
                for (int i = 0; i < 10000000; i++) {
                    x++;
                }
                return String.valueOf(x);
            }

            @Override
            protected void onPostExecute(Object text) {
                super.onPostExecute(text);
                TextView tvText1 = findViewById(R.id.tvText1);
                tvText1.setText((String) text);
            }
        };
        task1.execute();// thuc thi luong nay

        AsyncTask task2 = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                int y = 10;
                for (int i = 0; i < 10000; i++) {
                    y++;
                }
                // tuong tac vs main thread -> error
                return String.valueOf(y);
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                TextView tvText2 = findViewById(R.id.tvText2);
                tvText2.setText(String.valueOf(o));
            }
        };
        task2.execute();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // thuc hien 1 task sau thoi  gian la 2000 milisecond
            }
        },2000);



    }

}