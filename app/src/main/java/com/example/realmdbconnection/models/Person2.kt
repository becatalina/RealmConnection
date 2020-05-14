package com.example.realmdbconnection.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Person2() {

    var id: Int = 0
        get() = field
        set(value) {
            field = value
        }

    var firstName: String = ""
        get() = field
        set(value) {
            field = value
        }

    var lastName: String = ""
        get() = field
        set(value) {
            field = value
        }

    constructor(i: Int, s: String, s1: String): this(){
        id = i
        firstName = s
        lastName = s1
    }

    fun getName(): String {
        return "$firstName $lastName"
    }



}