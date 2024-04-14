package com.example.booksapp.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.booksapp.BooksAppApplication
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

object SessionDataStore {
    private val TAG_SESSKEY = stringPreferencesKey("TAG_SESSKEY")
    private val TAG_O_U = stringPreferencesKey("TAG_O_U")
    private val TAG_U_C = stringPreferencesKey("TAG_U_C")

    private val Context.terminalDataStore:DataStore<Preferences> by preferencesDataStore(
        name = "terminal_preferences"
    )
    private suspend fun getPreferences():Preferences= BooksAppApplication.BooksAppApplicationContext.terminalDataStore.data.first()

    val sesskey = BooksAppApplication.BooksAppApplicationContext.applicationContext.terminalDataStore.data.map {
        it[TAG_SESSKEY]?:""
    }
    suspend fun getSesskey():String = getPreferences()[TAG_SESSKEY] ?: ""
    suspend fun setSesskey(sesskey:String){
        BooksAppApplication.BooksAppApplicationContext.terminalDataStore.edit {
            it[TAG_SESSKEY] = sesskey
        }
    }

    val ou = BooksAppApplication.BooksAppApplicationContext.applicationContext.terminalDataStore.data.map {
        it[TAG_O_U]?:""
    }
    suspend fun getOu():String = getPreferences()[TAG_O_U] ?: ""
    suspend fun setOu(ou:String){
        BooksAppApplication.BooksAppApplicationContext.terminalDataStore.edit {
            it[TAG_O_U] = ou
        }
    }

    val uc = BooksAppApplication.BooksAppApplicationContext.applicationContext.terminalDataStore.data.map {
        it[TAG_U_C]?:""
    }
    suspend fun getUc():String = getPreferences()[TAG_U_C] ?: ""
    suspend fun setUc(uc:String){
        BooksAppApplication.BooksAppApplicationContext.terminalDataStore.edit {
            it[TAG_U_C] = uc
        }
    }



}