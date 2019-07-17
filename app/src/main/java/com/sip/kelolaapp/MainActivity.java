package com.sip.kelolaapp;

import android.animation.Animator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
//import com.crashlytics.android.Crashlytics;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//import io.fabric.sdk.android.Fabric;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    boolean doubleBackToExitPressedOnce = false;
    private ImageView bookIconImageView;
    private EditText usernameEdt, passwordEdt;
    private TextView bookITextView;
    private ProgressBar loadingProgressBar;
    private SessionManager session;
    private ProgressDialog pDialog;
    private RelativeLayout rootView, afterAnimationView;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initViews();
        new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                bookITextView.setVisibility(GONE);
                loadingProgressBar.setVisibility(GONE);
                rootView.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorSplashText));
                bookIconImageView.setImageResource(R.drawable.logo);
                startAnimation();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        //Fabric.with(this, new Crashlytics());

        session = new SessionManager(getApplicationContext());
        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            if(session.isUser().equals("1")) {
                // User is already logged in. Take him to main activity
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }else if(session.isUser().equals("2")){
                Intent intent = new Intent(MainActivity.this, DashboardHospital.class);
                startActivity(intent);
                finish();
            }else if(session.isUser().equals("3")){
                Intent intent = new Intent(MainActivity.this, DashboardOperator.class);
                startActivity(intent);
                finish();
            }else if(session.isUser().equals("4")){
                Intent intent = new Intent(MainActivity.this, DashboardDriver.class);
                startActivity(intent);
                finish();
            }else if(session.isUser().equals("5")){
                Intent intent = new Intent(MainActivity.this, DashboardUmkm.class);
                startActivity(intent);
                finish();
            }else if(session.isUser().equals("5")){
                Intent intent = new Intent(MainActivity.this, DashboardUmkm.class);
                startActivity(intent);
                finish();
            }
            else if(session.isUser().equals("6")){
                Intent intent = new Intent(MainActivity.this, DashboardVendor.class);
                startActivity(intent);
                finish();
            }
            else if(session.isUser().equals("7")){
                Intent intent = new Intent(MainActivity.this, DashboardLandfill.class);
                startActivity(intent);
                finish();
            }
        }

        usernameEdt = (EditText) findViewById(R.id.emailEditText);
        passwordEdt = (EditText) findViewById(R.id.passwordEditText);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btnLogin = (Button) findViewById(R.id.loginButton);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEdt.getText().toString().trim();
                String password = passwordEdt.getText().toString().trim();

                // Check for empty data in the form
                if (!username.isEmpty() && !password.isEmpty()) {
                    // login user
                    Log.e("isi",username+password);
                    checkLogin(username, password);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    private void initViews() {
        bookIconImageView = findViewById(R.id.bookIconImageView);
        bookITextView = findViewById(R.id.bookITextView);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        rootView = findViewById(R.id.rootView);
        afterAnimationView = findViewById(R.id.afterAnimationView);
    }

    private void startAnimation() {
        ViewPropertyAnimator viewPropertyAnimator = bookIconImageView.animate();
        viewPropertyAnimator.x(50f);
        viewPropertyAnimator.y(100f);
        viewPropertyAnimator.setDuration(1000);
        viewPropertyAnimator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                afterAnimationView.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * function to verify login details in mysql db
     * */
    private void checkLogin(final String username, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e(TAG, "obj: " + jObj.toString());
                    String error = jObj.getString("status");
                    Log.e(TAG, "obj: " + error);
                    // Check for error node in json
                    if (error != "1") {
                        // user successfully logged in
                        // Create login session
                        session.setLogin(true);

                        // Now store the user in SQLite
                        String username = jObj.getString("username");
                        String role = jObj.getString("id");

                        session.setStatus(role);

                        // Launch main activity
                        if(role.equals("1")) {
                            Intent intent = new Intent(MainActivity.this,
                                    DashboardActivity.class);
                            startActivity(intent);
                            finish();
                        }else if(role.equals("2")){
                            Intent intent = new Intent(MainActivity.this,
                                    DashboardHospital.class);
                            startActivity(intent);
                            finish();
                        }else if(role.equals("3")){
                            Intent intent = new Intent(MainActivity.this,
                                    DashboardOperator.class);
                            startActivity(intent);
                            finish();
                        }else if(role.equals("4")){
                            Intent intent = new Intent(MainActivity.this,
                                    DashboardDriver.class);
                            startActivity(intent);
                            finish();
                        }else if(role.equals("5")){
                            Intent intent = new Intent(MainActivity.this,
                                    DashboardUmkm.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("message");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                /*Intent intent = new Intent(LoginActivity.this,
                        MainActivity.class);
                startActivity(intent);
                finish();*/
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "Login Failed", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
