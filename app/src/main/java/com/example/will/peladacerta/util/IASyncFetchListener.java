package com.example.will.peladacerta.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.EventListener;

/**
 * Created by irom on 22/03/2016.
 */
public interface IASyncFetchListener extends EventListener {
    void onComplete(JSONObject item);
    void onComplete(JSONArray jsonArray);
    void onError(Throwable error);
    void onError(Throwable error,JSONObject errorResponse);
}