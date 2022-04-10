package com.example.myapp_april_2022.domain

interface LoginApi {

    fun login(login: String, password: String): Boolean
    fun registration(login: String, password: String, mail: String): Boolean
    fun logout(): Boolean
    fun forgotPassword(login: String): Boolean
}