package com.example.realmdbconnection.adapters

import android.content.Context
import android.provider.Contacts
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.realmdbconnection.R
import com.example.realmdbconnection.models.Person
import com.example.realmdbconnection.models.Person2
import kotlinx.android.synthetic.main.item_person.view.*
import kotlinx.android.synthetic.main.main_fragment.view.*
import java.util.*

class PersonAdapter(
    private val peopleList: ArrayList<String>
) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate (R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(peopleList[position])
    }


    inner class PersonViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(name: String){
            view.nameTxt.text = name
        }
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }



}