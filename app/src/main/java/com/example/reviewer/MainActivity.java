package com.example.reviewer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reviewer.Models.ObjectModelgetSelfInfo;
import com.example.reviewer.Retrofit.INodeJS;
import com.example.reviewer.Retrofit.RetrofitClient;
import com.example.reviewer.Models.ObjectModelgetGameInfo;
import com.example.reviewer.Models.ObjectModelgetGameReviews;
import com.example.reviewer.Models.ObjectModelgetGamesList;
import com.example.reviewer.Models.ObjectModelgetProfileReview;
import com.example.reviewer.Models.ObjectModelgetProfileUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity implements RegisterDialog.RegisterDialogListener {

    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    EditText edit_email,edit_password;
    Button register_button,login_button;

    String username_register, password_register, email_register;

    Button debug_button;

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public void applyTexts(String username, String password, String email) {
        username_register = username;
        password_register = password;
        email_register = email;

        if(username != null && password != null && email != null) {
            registerUser(email_register, username_register, password_register);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);

        //Initialize APi
        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);

        //View
        login_button = (Button)findViewById(R.id.login_button);
        register_button = (Button)findViewById(R.id.register_button);

        edit_email = (EditText)findViewById(R.id.edit_email);
        edit_password = (EditText)findViewById(R.id.edit_password);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(edit_email.getText().toString(),edit_password.getText().toString());
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterDialog registerDialog = new RegisterDialog();
                registerDialog.show(getSupportFragmentManager(), "Register Dialog");
                // Actual registration function call is done in applyTexts when Popup is closed and
                // values are entered.
            }
        });




        //Debug stuff
        debug_button = (Button)findViewById(R.id.debug_button);


        debug_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Dummy variables
                String unique_id = "f881a6cc-6ac7-49bd-9bcc-e7dc8be5c9e4";
                String email = "asdf@asdf";
                String password = "asdf";
                String written_review = "This is testing written text";
                String name = "asdf";

                int game_id = 1;
                int review_rating = 3;
                int amount = 4;
                int page = 1;

                //Testing function:
                getSelfInfo(email, password);

            }
        });

    }

    public void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void getSelfInfo(String email, String password){
        compositeDisposable.add(myAPI.getSelfInfo(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("TESTING S VALUE", s);
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<ObjectModelgetSelfInfo>>(){}.getType();
                        List<ObjectModelgetSelfInfo> postsSelf = gson.fromJson(s, listType);
                        Log.d("Please God work", postsSelf.toString());
                    }
                }));
    }

    private void getGameReviews(int amount, int page, int game_id){
        compositeDisposable.add(myAPI.getGameReviews(amount, page, game_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("TESTING S VALUE", s);
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<ObjectModelgetGameReviews>>(){}.getType();
                        List<ObjectModelgetGameReviews> posts = gson.fromJson(s, listType);
                        Log.d("Please God work", posts.toString());
                    }
                }));
    }

    private void getGameInfo(int game_id){
        compositeDisposable.add(myAPI.getGameInfo(game_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("TESTING S VALUE", s);
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<ObjectModelgetGameInfo>>(){}.getType();
                        List<ObjectModelgetGameInfo> posts = gson.fromJson(s, listType);
                        Log.d("Please God work", posts.toString());
                    }
                }));
    }

    private void getProfileReview(String unique_id){
        compositeDisposable.add(myAPI.getProfileReview(unique_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("TESTING S VALUE", s);
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<ObjectModelgetProfileReview>>(){}.getType();
                        List<ObjectModelgetProfileReview> posts = gson.fromJson(s, listType);
                        Log.d("Please God work", posts.toString());
                    }
                }));
    }

    private void getProfileUser(String unique_id){
        compositeDisposable.add(myAPI.getProfileUser(unique_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("TESTING S VALUE", s);
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<ObjectModelgetProfileUser>>(){}.getType();
                        List<ObjectModelgetProfileUser> posts = gson.fromJson(s, listType);
                        Log.d("Please God work", posts.toString());
                    }
                }));
    }

    private void getGamesList(int amount, int page){
        compositeDisposable.add(myAPI.getGamesList(amount, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("TESTING S VALUE", s);
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<ObjectModelgetGamesList>>(){}.getType();
                        List<ObjectModelgetGamesList> posts = gson.fromJson(s, listType);
                        Log.d("Please God work", posts.toString());
                    }
                }));
    }

    private void postReview(int game_id, String email, String password, int review_rating, String written_review, String unique_id, String name){
        compositeDisposable.add(myAPI.postReview(game_id, email, password, review_rating, written_review, unique_id, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(MainActivity.this, ""+s, Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    private void registerUser(String email, String username, String password){
        compositeDisposable.add(myAPI.registerUser(email, username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(MainActivity.this, ""+s, Toast.LENGTH_SHORT).show();
                    }
                }));

    }

    private void loginUser(String email, String password) {
        compositeDisposable.add(myAPI.loginUser(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if(s.contains("encrypted_password")){
                            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            openHomeActivity();
                        } else {
                            Toast.makeText(MainActivity.this, "Login Failure"+s, Toast.LENGTH_SHORT).show();
                        }
                    }
                }));
    }


}
