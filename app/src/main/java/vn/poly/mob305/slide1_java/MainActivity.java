package vn.poly.mob305.slide1_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.poly.mob305.slide1_java.json.Info;
import vn.poly.mob305.slide1_java.json.Result;
import vn.poly.mob305.slide1_java.json.Root;
import vn.poly.mob305.slide1_java.retrofit.RandomService;

public class MainActivity extends AppCompatActivity {

    String api = "https://jsonplaceholder.typicode.com/todos/1";
    // https://jsonplaceholder.typicode.com : base_url
    // todos : path : địa chỉ - đường dẫn
    // 1 : path

    String apiPost = "https://jsonplaceholder.typicode.com/posts";
    // base_url = "https://jsonplaceholder.typicode.com"
    // posts : path

    String randomUser = "https://api.randomuser.me/?results=10";

    // BASE_URL : https://api.randomuser.me/
    // Params : results = 10 : query param !!!


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnRequest).setOnClickListener(v ->{
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.randomuser.me/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RandomService randomService = retrofit.create(RandomService.class);
            Call<Root> userList = randomService.getListUser(10);
            userList.enqueue(new Callback<Root>() {
                @Override
                public void onResponse(Call<Root> call, Response<Root> response) {
                    Root root = response.body();
                    Info info = root.getInfo();
                    List<Result> userList = root.getResults();

                }

                @Override
                public void onFailure(Call<Root> call, Throwable t) {
                    Toast.makeText(MainActivity.this,
                            t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });



        });
        findViewById(R.id.btnReq2).setOnClickListener(v->{
           /* String api = "https://jsonplaceholder.typicode.com/todos/1";
            // https://jsonplaceholder.typicode.com : base_url
            // todos : path : địa chỉ - đường dẫn
            // 1 : path*/
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RandomService randomService = retrofit.create(RandomService.class);

            Call<ResponseBody> toDO = randomService.getToDo(10);
            toDO.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String ketqua = null;
                    try {
                        ketqua = response.body().string();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Log.e("kq",ketqua);
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("FAIL",t.getMessage());
                }
            });


        });
        findViewById(R.id.btnPost).setOnClickListener(v->{
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RandomService randomService = retrofit.create(RandomService.class);

            Call<ResponseBody> userR = randomService.sendUser(10,"BODY",
                    "TITLEEE");
            userR.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        Log.e("Data",response.body().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("LOI",t.getMessage());
                }
            });


        });
    /*    GetAsyncTask asyncTask = new GetAsyncTask();
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
        asyncTask.execute(randomUser,"GET");*/
    }

}