package com.example.testio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText etName,etUsername,etCode,etPassword,etConfPswd;
    Spinner  spinnerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_register);

        etName = (EditText)findViewById(R.id.et_StudentName);
        etUsername = (EditText)findViewById(R.id.et_Level);
        etCode = (EditText)findViewById(R.id.et_StudentCode);
        etPassword = (EditText)findViewById(R.id.et_ParentName);
        etConfPswd = (EditText)findViewById(R.id.et_ParentPhoneNo);
        spinnerType=(Spinner) findViewById(R.id.sp_userType);

        findViewById(R.id.btnCreateAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        findViewById(R.id.tv_AlreadyRegistered).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });
    }

    private void registerUser() {
        final String name = etName.getText().toString().trim();
        final String code = etCode.getText().toString().trim();
        final String username = etUsername.getText().toString().trim();
        final String type = spinnerType.getSelectedItem().toString().trim();
        final String password = etPassword.getText().toString().trim();
        final String confPassword = etConfPswd.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            etName.setError("please enter your full name");
            etName.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(code)){
            etCode.setError("please enter your code");
            etCode.requestFocus();
            return;
        }
        if(type.equals("Select your category")){
            Toast.makeText(getApplicationContext(),"please select your Category",Toast.LENGTH_LONG).show();
            spinnerType.setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
            return;
        }
        if (TextUtils.isEmpty(username)) {
            etUsername.setError("please enter your username");
            etUsername.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password)){
            etPassword.setError("please enter your password");
            etPassword.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(confPassword)){
            etConfPswd.setError("please enter your confirmation password");
            etConfPswd.requestFocus();
            return;
        }

        if (!password.equals(confPassword)){
            etPassword.setError("passwords must match");
            etConfPswd.setError("password must match");
            etPassword.requestFocus();
            return;
        }

        Toast.makeText(getApplicationContext(),name+"\n"+username+"\n"+code+"\n"+type,Toast.LENGTH_LONG).show();

        class RegisterUser extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("code", code);
                params.put("password", password);
                params.put("type", type);

                //returning the response
                return requestHandler.sendPostRequest(URLs.URL_REGISTER, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressBar = (ProgressBar) findViewById(R.id.progressBar2);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                progressBar.setVisibility(View.GONE);

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");

                        //creating a new user object
                        User user = new User(
                                userJson.getInt("id"),
                                userJson.getString("username"),
                                userJson.getString("code"),
                                userJson.getString("type")
                        );

                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        RegisterUser ru = new RegisterUser();
        ru.execute();

    }
}