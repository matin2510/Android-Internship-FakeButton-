package nyc.c4q.mustafizurmatin.fakebuttonchallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import nyc.c4q.mustafizurmatin.fakebuttonchallenge.adapters.UserAdapter;
import nyc.c4q.mustafizurmatin.fakebuttonchallenge.models.User;
import nyc.c4q.mustafizurmatin.fakebuttonchallenge.networking.FakeButtonApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText name;
    EditText email;
    Button createUser;


    RecyclerView userRV;
    UserAdapter userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        createUser = findViewById(R.id.create_user);



        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().equals("") && !email.getText().toString().equals("")) {
                    User newUser = new User(name.getText().toString(), email.getText().toString());

                    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://fake-button.herokuapp.com/")
                            .addConverterFactory(GsonConverterFactory.create()).build();

                    FakeButtonApi fakeButtonApi = retrofit.create(FakeButtonApi.class);
                    Call<User> userCall = fakeButtonApi.createUser(newUser);
                    userCall.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                           /* User user = response.body();
                            Log.d(TAG, "onResponse: " +  user.getName());*/


                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                }
            }
        });

        userRV = findViewById(R.id.rv_users);


        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://fake-button.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        FakeButtonApi fakeButtonApi = retrofit.create(FakeButtonApi.class);
        Call<List<User>> userListCall = fakeButtonApi.getUser("bpn123");
        userListCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList = response.body();

                Log.d(TAG, "onResponse: " + userList.size());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);


                userAdapter = new UserAdapter(userList);
                userRV.setLayoutManager(linearLayoutManager);
                userRV.setAdapter(userAdapter);


            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }


}

