package com.example.booksapp.domain.model

import kotlinx.serialization.Serializable

@Serializable

data class OAuthKeyResponse(val id:Int, val status:String, val oauthkey:String, val o_u:String, val createdVNB:String, val req:String)
//@Serializable
//data class OAutKeyResponse(val id:Int, val status:String, val oauthkey:String, val o_u:String, val createdVNB:String, val req:String)