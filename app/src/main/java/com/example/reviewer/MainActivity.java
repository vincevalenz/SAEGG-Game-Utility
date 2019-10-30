package com.example.reviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button register, log_in;
    EditText First_Name, Last_Name, Email, Password;
    String F_Name_Holder, L_Name_Holder, EmailHolder, PasswordHolder;
    String finalResult;
    String HttpURL = "https://localhost/UserRegistration.php";
    Boolean CheckEditText;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign Id's
        First_Name = (EditText)findViewById(R.id.editTextF_Name);
        Last_Name = (EditText)findViewById(R.id.editTextL_Name);
        Email = (EditText)findViewById(R.id.editTextEmail);
        Password = (EditText)findViewById(R.id.editTextPassword);

        register = (Button)findViewById(R.id.Submit);
        log_in = (Button)findViewById(R.id.Login);

        // Click Listener on Button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Confirm Filled out Form
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){
                    // If completed form
                    UserRegisterFunction(F_Name_Holder,L_Name_Holder, EmailHolder, PasswordHolder);
                }
                else {
                    // Failed to complete form will create warning
                    Toast.makeText(MainActivity.this, "Please fill all form fields.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Goes to Login Window under UserLoginActivity
                Intent intent = new Intent(MainActivity.this,UserLoginActivity.class);
                startActivity(intent);
            }
        });
    }

        public void CheckEditTextIsEmptyOrNot(){
            F_Name_Holder = First_Name.getText().toString();
            L_Name_Holder = Last_Name.getText().toString();
            EmailHolder = Email.getText().toString();
            PasswordHolder = Password.getText().toString();

            if(TextUtils.isEmpty(F_Name_Holder) || TextUtils.isEmpty(L_Name_Holder)
                    || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){
                CheckEditText = false;
            }
            else {
                CheckEditText = true;
            }
        }

        public void UserRegisterFunction(final String F_Name, final String L_Name,
                                         final String email, final String password) {

            class UserRegisterFunctionClass extends AsyncTask<String, Void, String> {

                // Function executing before process is ran do display to user that the Registration
                // is occuring
                @Override
                protected void onPreExecute() {
                    // Super used to refer to parent
                    super.onPreExecute();
                    // This shows the user the Registration is being attempted
                    progressDialog = ProgressDialog.show(MainActivity.this,
                            "Loading Data",null, true, true);
                }

                // Actual Execution
                @Override
                protected void onPostExecute(String httpResponseMsg){
                    super.onPostExecute(httpResponseMsg);

                    // Gets rid of progressdialog from preexecute function
                    progressDialog.dismiss();

                    // Toast is a method of displaying simple feedback about an operation, in this
                    // case, the completion of Registration
                    Toast.makeText(MainActivity.this,httpResponseMsg.toString(),
                            Toast.LENGTH_LONG).show();
                }

                // doInBackground takes the registration parameters, puts them into a hashMap, and
                // then sends the data along with the HttpURL letting our httpParse object know what
                // type of request it is. After we get the inforamtion from the backend we get our
                // final result being a completed registration.
                @Override
                protected String doInBackground(String... params){
                    hashMap.put("F_name",params[0]);
                    hashMap.put("L_name",params[1]);
                    hashMap.put("email",params[2]);
                    hashMap.put("password",params[3]);

                    finalResult = httpParse.postRequest(hashMap, HttpURL);

                    return finalResult;
                }
            }
        }
    }
