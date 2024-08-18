package com.example.rehapp_20.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rehapp_20.models.PatientUserRegisterDTO
import com.example.rehapp_20.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _registrationResult = MutableLiveData<Response<PatientUserRegisterDTO>>()
    val registrationResult: LiveData<Response<PatientUserRegisterDTO>> = _registrationResult

    private val TAG = "UserViewModel"

    fun registerUser(user: PatientUserRegisterDTO) {
        Log.d(TAG, "registerUser: Iniciando registro de usuario con DTO: $user")
        viewModelScope.launch {
            try {
                val response = userRepository.registerUser(user)
                Log.d(TAG, "registerUser: Respuesta del servidor: $response")
                _registrationResult.postValue(response)
            } catch (e: Exception) {
                Log.e(TAG, "registerUser: Error durante el registro de usuario", e)
            }
        }
    }
}