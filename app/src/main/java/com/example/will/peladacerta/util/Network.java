package com.example.will.peladacerta.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rafaelcadena on 14/06/17.
 */

public class Network {

    private PersistentCookieStore myCookieStore;
    IASyncFetchListener fetchListener = null;
    AsyncHttpClient client;
    private Context context;
    private String message;
    private ProgressDialog mDialog;
    private String QUERY_LOGIN = "https://pelada-certa.herokuapp.com/users/sign_in.json";


    public Network(Context context, String message) {
        this.context = context;
        this.message = message;

        myCookieStore = new PersistentCookieStore(this.context);
        client = new AsyncHttpClient();
    }

    public void setListener(IASyncFetchListener listener) {
        this.fetchListener = listener;
    }

    public void login(String email, String password) {
        mDialog = new ProgressDialog(context);
        mDialog.setMessage(message);
        mDialog.show();

        RequestParams requestParams = new RequestParams();
        Map<String, String> map = new HashMap<String, String>();
        map.put("email", email);
        map.put("password", password);
//        map.put("remember_me", "true");
        requestParams.put("user", map);


        if (myCookieStore == null) {
            myCookieStore = new PersistentCookieStore(context);
        }
        client.setCookieStore(myCookieStore);
        client.setTimeout(5000);
        client.post(QUERY_LOGIN, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                fetchListener.onComplete(jsonObject);
                mDialog.dismiss();

            }

            @Override
            public void onFailure(int statusCode, Throwable throwable, JSONObject error) {
                mDialog.dismiss();
                (new PersistentCookieStore(context)).clear(); // limpa os cookies

                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                try {
                    if (throwable == null || throwable.getMessage() == null) {
                        alert.setTitle("Falha na conexão, tente novamente.");
                    } else if (throwable != null) {
                        if (throwable.getMessage().contains("Unable to resolve host")) {//
                            alert.setTitle("Sem internet!");
                        } else {
                            alert.setTitle("Erro ao autenticar Fiscal!");
                        }
                    }
                } catch (NullPointerException e) {
                }

                if (error != null){
                    try {
                        alert.setTitle("Erro!");
                        alert.setMessage(error.getString("error"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                alert.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });

                alert.show();
                fetchListener.onError(throwable);
            }

            @Override
            public void onFailure(Throwable e, JSONObject errorResponse) {
                mDialog.dismiss();
                (new PersistentCookieStore(context)).clear();

                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                if (e == null || e.getMessage() == null) {
                    alert.setTitle("Falha na conexão, tente novamente.");
                } else if (e.getMessage().contains("Unable to resolve host")) {
                    alert.setTitle("Sem internet!");
                } else {
                    alert.setTitle("Erro ao autenticar Fiscal!");
                }

                alert.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });
                alert.show();
                fetchListener.onError(e);
            }
        });
    }
}
