package com.example.realmdbconnection.ui.main

import android.app.AlertDialog
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.realmdbconnection.R
import com.example.realmdbconnection.RealmHelper
import com.example.realmdbconnection.adapters.PersonAdapter

import com.example.realmdbconnection.models.Person
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.realm.Realm
import kotlinx.android.synthetic.main.main_fragment.*
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()

    }

    var peopleList: ArrayList<String>? = null
    var realmHelper: RealmHelper? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectToRealm()
        btnAdd.setOnClickListener {
            val input = input_firstName.text.toString() + " "+ input_lastName.text.toString()
            if (peopleList?.contains(input)!!){
                showExistsDialog(view)
                return@setOnClickListener
            }

            realmHelper?.save(Person((3..100).random(),
                input_firstName.text.toString(), input_lastName.text.toString()
            ))
            input_firstName.setText("")
            input_lastName.setText("")
            displayListOfPeople()
        }

        btnRemove.setOnClickListener {
            val input = input_firstName.text.toString() + " "+ input_lastName.text.toString()
            if (!peopleList?.contains(input)!!){
                showNonExistentDialog(view)
                return@setOnClickListener
            }

            realmHelper?.remove(input)
            input_firstName.setText("")
            input_lastName.setText("")
            displayListOfPeople()
        }

        btnSync.setOnClickListener {
            //val task = JsonTask()
            //val result = task.execute()
            //val mapper = jacksonObjectMapper()


            //val people: List<JsonResponse> = mapper.readValue(result)
        }

        displayListOfPeople()
    }

    fun connectToRealm(){

        Realm.init(context!!)
        realmHelper = RealmHelper(Realm.getDefaultInstance())
    }

    fun displayListOfPeople(){
        peopleList = realmHelper?.retrieve()
        val layoutManager = LinearLayoutManager(context!!)
        val personAdapter = peopleList?.let { PersonAdapter(it) }
        person_recycler_view.layoutManager = layoutManager
        person_recycler_view.adapter = personAdapter
    }

    private fun showExistsDialog(view:View?){
        val builder = AlertDialog.Builder(context!!)
        builder.setTitle("Invald Input")
        builder.setMessage("The name already exists")
        builder.show()
    }

    private fun showNonExistentDialog(view:View?){
        val builder = AlertDialog.Builder(context!!)
        builder.setTitle("Invald Input")
        builder.setMessage("The name does not exist")
        builder.show()
    }




}
