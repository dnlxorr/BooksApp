package com.example.booksapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SessionKeyResponse(val id:Int, val status:String, val sesskey:String, val createdVNB:String, val req:String)

//@Serializable
//data class SessionKeyResponse(val id:Int, val status:String, val sesskey:String, val createdVNB:String, val req:String)