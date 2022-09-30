package com.example.contacttracingproject.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.contacttracingproject.network.RetrofitUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val retro: RetrofitUser): UserRepository {
    override fun getUser(nric: Int, onGetUser: (User?) -> Unit) {
        val call: Call<User> = retro.getUser(nric)

        call.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, getResponse: Response<User>) {
                if(getResponse.isSuccessful) {
                    Log.i("getUserRepo", getResponse.body().toString())
                    onGetUser(getResponse.body())
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.i("getUserRepo", t.message.toString())
            }
        })
    }

    override fun editUser(user: User, onEditUser: (User?) -> Unit) {
        val call: Call<User> = retro.editUser(user)

        call.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, editResponse: Response<User>) {
                Log.i("editUserRepo", editResponse.body().toString())
                if(editResponse.isSuccessful) {
//                    Log.i("editUserRepo", editResponse.body().toString())
                    onEditUser(editResponse.body())
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.i("editUserRepo", t.message.toString())
            }
        })
    }

    override fun login(loginModel: LoginModel, onLogin: (LoginResponse?) -> Unit) {
        val call: Call<LoginResponse> = retro.login(loginModel)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, loginResponse: Response<LoginResponse>) {
                Log.i("loginRepo", loginResponse.body().toString())
                if(loginResponse.isSuccessful) {
                    onLogin(loginResponse.body())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.i("loginRepo", t.message.toString())
            }
        })
    }

    override fun register(signUpModel: SignUpModel, onSignUp: (SignUpResponse?) -> Unit) {
        Log.i("signupRepo", signUpModel.toString())
        val call = retro.register(signUpModel)

        call.enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, signUpResponse: Response<SignUpResponse>) {
                if(signUpResponse.isSuccessful) {
                    onSignUp(signUpResponse.body())
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                Log.i("signUpRepo", t.message.toString())
            }
        })
    }
}