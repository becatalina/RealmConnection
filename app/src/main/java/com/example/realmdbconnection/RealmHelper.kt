package com.example.realmdbconnection

import android.app.ProgressDialog
import android.os.AsyncTask
import android.util.Log
import com.example.realmdbconnection.models.JsonResponse
import com.example.realmdbconnection.models.Person
import com.example.realmdbconnection.ui.main.MainFragment
import io.realm.Realm
import io.realm.RealmResults
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class RealmHelper(
    var realm: Realm
) {

    //WRITE
    fun save(person: Person) {
        realm.executeTransaction { realm -> val p: Person = realm.copyToRealm(person) }
    }

    //READ
    fun retrieve(): ArrayList<String> {
        val names =
            ArrayList<String>()
        val peopleList: RealmResults<Person> =
            realm.where<Person>(Person::class.java).findAll()
        for (pers in peopleList) {
            pers.getName()?.let { names.add(it) }
        }
        return names
    }

    private fun retrievePerson(name: String): Person? {
        val peopleList: RealmResults<Person> =
            realm.where<Person>(Person::class.java).findAll()

        for (pers in peopleList) {
            if (pers.getName().equals(name))
                return pers
        }
        return null
    }

    //REMOVE
    fun remove(fullName: String){
        realm.executeTransaction {
            retrievePerson(fullName)?.deleteFromRealm()
        }

    }





}