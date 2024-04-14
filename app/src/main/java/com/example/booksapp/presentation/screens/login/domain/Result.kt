package com.example.booksapp.presentation.screens.login.domain

typealias RootError = Error
sealed interface Result<out D, out E:RootError?>{
    data class Success<out D, out E: RootError> (val data: D):
        Result<D, E>
    data class Failure<out D, out E: RootError> (val error: E):
        Result<D, E>
    data class Loading<out D, out E: RootError?> (val data: D):
        Result<D,E>

}