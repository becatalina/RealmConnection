package com.example.realmdbconnection.models

import android.os.AsyncTask
import android.util.Log
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


@JsonIgnoreProperties(ignoreUnknown = true)
class JsonResponse(val id: String, val name: String): JSONObject() {
}


