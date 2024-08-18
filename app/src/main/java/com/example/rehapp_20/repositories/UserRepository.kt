package com.example.rehapp_20.repositories

import android.util.Log
import com.example.rehapp_20.apiservice.UserApiService
import com.example.rehapp_20.models.PatientUserRegisterDTO
import retrofit2.Response
import javax.inject.Inject

class UserRepository@Inject constructor(
    private val userApiService: UserApiService
) {

    private val TAG = "UserRepository"

    suspend fun registerUser(user: PatientUserRegisterDTO): Response<PatientUserRegisterDTO> {
        return try {
            val response = userApiService.createUser(user)
            Log.d(TAG, "registerUser: Respuesta del servidor: $response")
            response
        } catch (e: Exception) {
            Log.e(TAG, "registerUser: Error durante el registro de usuario", e)
            throw e
        }
    }
}