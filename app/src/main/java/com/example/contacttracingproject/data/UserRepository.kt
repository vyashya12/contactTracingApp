package com.example.contacttracingproject.data

import androidx.lifecycle.MutableLiveData

interface UserRepository {
    fun getUser(nric: Int, onGetUser: (User?) -> Unit)

    fun editUser(user: User, onEditUser: (User?) -> Unit)

    fun login(loginModel: LoginModel, onLogin: (LoginResponse?) -> Unit)

    fun register(signUpModel: SignUpModel, onSignUp: (SignUpResponse?) -> Unit)
}