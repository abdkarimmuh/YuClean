package andara.heatcliff.yuclean.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import andara.heatcliff.yuclean.MainActivity;
import andara.heatcliff.yuclean.R;
import andara.heatcliff.yuclean.RegistrasiActivity;
import andara.heatcliff.yuclean.SQLiteHandler;


/**
 * Created by irfan on 10/22/2016.
 */
public class LoginActivityTest extends AppCompatActivity {

    Button btnLogin;
    EditText txtNoHp, txtPassword;
    TextView txtRegistrasi;
    SQLiteHandler sqLiteHandler;

    static final String url = "http://yuclean.andara-tech.com/api/auth/login";
    static final String KEY_USERNAME = "username";
    static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtNoHp = (EditText) findViewById(R.id.txtNoHp);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtRegistrasi = (TextView) findViewById(R.id.txtRegistrasi);

        sqLiteHandler = new SQLiteHandler(this);

        txtRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegistrasi = new Intent(LoginActivityTest.this, RegistrasiActivity.class);
                startActivity(intentRegistrasi);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = txtNoHp.getText().toString().trim();
                final String password = txtPassword.getText().toString().trim();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(LoginActivityTest.this, response, Toast.LENGTH_LONG).show();
                                Log.d("Hasil Respon : ", response);

                                Intent intent = new Intent(LoginActivityTest.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Hasil Error : ", error.toString());
                        Toast.makeText(LoginActivityTest.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put(KEY_USERNAME, username);
                        params.put(KEY_PASSWORD, password);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivityTest.this);
                requestQueue.add(stringRequest);
            }
        });
    }
}

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final String username = txtNoHp.getText().toString();
//                final String password = txtPassword.getText().toString();
//
//                Response.Listener<String> responseListener = new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonResponse = new JSONObject(response);
//                            String error = jsonResponse.getString("Your Credential is wrong");
//
//                            if (!error.equals("Your Credential is wrong")){
//                                JSONObject data = jsonResponse.getJSONObject("data");
//                                String id = jsonResponse.getString("id");
//                                String name = jsonResponse.getString("name");
//                                String username = jsonResponse.getString("username");
//                                String registered = jsonResponse.getString("registered");
//                                JSONObject meta = jsonResponse.getJSONObject("meta");
//                                String token = jsonResponse.getString("token");
//
//                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                intent.putExtra("name", name);
//                                intent.putExtra("password", password);
//                                intent.putExtra("username", username);
//                                LoginActivity.this.startActivity(intent);
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                };
//
//                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
//                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
//                queue.add(loginRequest);
//            }
//        });
//
//    }


//        SQLiteHandler sqLiteHandler = new SQLiteHandler(this);
//        final HashMap<String, String> user = sqLiteHandler.getUserDetails();

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(txtNoHp.getText().toString().equals(user.get("no_hp"))){
//                    if(txtPassword.getText().toString().equals(user.get("password"))){
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(),"Maaf password yang anda masukan salah", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else {
//                    Toast.makeText(getApplicationContext(),"Maaf username yang anda masukan salah", Toast.LENGTH_SHORT).show();
//                }
//
//                LoadData();
//            }
//        });


//    private void LoadData(){
//
//        final String username = txtNoHp.getText().toString().trim();
//        final String password = txtPassword.getText().toString().trim();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//
//                        sqLiteHandler.addDatabase("nasabah",
//                                getIntent().getStringExtra("nama"),
//                                getIntent().getStringExtra("password"),
//                                getIntent().getStringExtra("email"),
//                                getIntent().getStringExtra("nohp"),
//                                "500");
//
//
//                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
//                        Log.d("hasilnya", response);
//
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
//
//                        GsonBuilder gsonBuilder = new GsonBuilder();
//                        Gson gson = gsonBuilder.create();
//                        itemObjectLoginTest = gson.fromJson(response, ItemObjectLoginTest.class);
//
//                        JsonParser parser = new JsonParser();
//                        JsonObject data = parser.parse(response).getAsJsonObject();
//                        Response myResponse = gson.fromJson(data.get("response"), Response.class);
//
//                        sqLiteHandler.addDatabase("nasabah",
//                                itemObjectLoginTest.data.get(2).name,
//                                password,
//                                "null",
//                                itemObjectLoginTest.data.get(3).username,
//                                "null"
//                        );
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("hasilnya", error.toString());
//                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
//                    }
//                }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put(KEY_USERNAME,username);
//                params.put(KEY_PASSWORD,password);
//                return params;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }

    /*private void LoadData() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://localhost/api/auth/login",
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                itemObjectLogin = gson.fromJson(response, ItemObjectLogin.class);

                if(itemObjectLogin.yuclean.get(0).username.equals(txtNoHp.getText().toString())){
                    if(itemObjectLogin.yuclean.get(0).password.equals(txtPassword.getText().toString())){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Maaf password yang anda masukan salah", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Maaf username yang anda masukan salah", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }*/


/*    public static StringRequest getRequestLogin(final String url, final String[] login) {
        final StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ServerResponse serverResponse = getServerResponse(
                                url, response,
                                "");
                        if (serverResponse != null) {
                            JSONObject jResponse = null;
                            try {
                                if (!GlobalUtils.isNullOrBlank(serverResponse.getJsonResponse().toString())) {
                                    jResponse = new JSONObject(serverResponse.getJsonResponse());
                                    if (jResponse != null) {
                                        serverResponse.setResponseStatus(ResponseStatus.Valid);
                                    } else {
                                        serverResponse.setResponseStatus(ResponseStatus.Invalid);
                                    }
                                } else {
                                    serverResponse.setResponseStatus(ResponseStatus.Invalid);
                                    serverResponse.setMessage("Error. No data returned.");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (serverResponse.getResponseStatus() == ResponseStatus.Valid) {
                                TaskAll aSynch = new TaskAll(mcontext, serverResponse,
                                        null, null);
                                aSynch.execute();
                            } else {
                                dismissDialog();
                                mOnError.processError(serverResponse);
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String responseBody = "";
                        if (null != error.networkResponse) {
                            try {
                                responseBody = new String(error.networkResponse.data, "utf-8");

                                JSONObject responseJson = new JSONObject(responseBody);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        dismissDialog();
                        mOnError.processError(getErrorServerResponse(error));
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", login[0].toString());
                params.put("password", login[1].toString());
                params.put("imei", login[2].toString());
                return params;
            }
        };
        return stringRequest;
    }*/