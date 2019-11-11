package com.example.reviewer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reviewer.Retrofit.INodeJS;
import com.example.reviewer.Retrofit.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    EditText edit_email,edit_password;
    Button register_button,login_button;

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
                registerUser(edit_email.getText().toString(),edit_password.getText().toString());
            }
        });
    }

    private void registerUser(String email, String password){
        RegisterDialog registerDialog = new RegisterDialog();
        registerDialog.show(getSupportFragmentManager(), "Register Dialog");

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
                } else {
                    Toast.makeText(MainActivity.this, "Login Failure"+s, Toast.LENGTH_SHORT).show();
                }
            }
        }));
    }
}
