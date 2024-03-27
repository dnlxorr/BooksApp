package com.example.booksapp.domain.model

import kotlinx.serialization.Serializable

@Serializable

data class AppKeyResponse(val id:Int, val status:String, val appkey:String, val createdVNB:String, val req:String)
//@Serializable
//data class AuthResponse(val id:Int, val status:String, val appkey:String, val createdVNB:String, val req:String)