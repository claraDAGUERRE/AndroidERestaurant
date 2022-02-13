package fr.isen.daguerre.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.daguerre.androiderestaurant.databinding.ActivitySignUpBinding
import org.json.JSONObject

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnRegister.setOnClickListener {

            if (validForm()) {
                val name = binding.userNameReg.text.toString()
                val email = binding.userEmailAddress.text.toString()
                val password = binding.userPasswordReg.text.toString()
                val surname = binding.userSurnamereg.text.toString()
                val address = binding.userAdressreg.text.toString()

                //request post
                val queue = Volley.newRequestQueue(this)
                val url = "http://test.api.catering.bluecodegames.com/user/register"
                val jsonObject = JSONObject()
                jsonObject.put("id_shop", 1)
                jsonObject.put("firstname", name)
                jsonObject.put("lastname", surname)
                jsonObject.put("address", address)
                jsonObject.put("email", email)
                jsonObject.put("password", password)

                val request = JsonObjectRequest(
                    Request.Method.POST, url, jsonObject,
                    { response ->
                        var gson = Gson()
                        //var newAccountResult = gson.fromJson(response.toString(), NewAccountResultModel::class.java)
                        Log.d("", "$response")
                        //faire qqc de la requete


                    }, {
                        // Error in request
                        Log.e("", "Volley error: $it")
                    })

                // Volley request policy, only one time request to avoid duplicate transaction
                request.retryPolicy = DefaultRetryPolicy(
                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                    // 0 means no retry
                    0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
                    1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )

                // Add the volley post request to the request queue
                queue.add(request)

                val text = "Incription finie"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }

            binding.alreadyAccount.setOnClickListener {
                val intent = Intent(this, ConnexionActivity::class.java)
                startActivity((intent))
            }

        }
    }


    private fun validForm(): Boolean{
        val errorEmptyField = "Champ vide"
        val errorPasswordNotEnoughLength = "8 caractères minimum"
        val errorInvalidEmail = "email invalide"
        var error = true
        //si le champ est vide
        //Surname
        if (binding.userSurnamereg.text.toString().trim().isEmpty()){
            binding.userSurnamereg.error = errorEmptyField
            error = false
        }else{
            binding.userSurnamereg.error = null
        }
        //Name
        if (binding.userNameReg.text.toString().trim().isEmpty()){
            binding.userNameReg.error = errorEmptyField
            error = false
        }else{
            binding.userEmailAddress.error = null
        }
        //Address
        if (binding.userAdressreg.text.toString().trim().isEmpty()){
            binding.userAdressreg.error = errorEmptyField
            error = false
        }else{
            binding.userAdressreg.error = null
        }
        //Email
        if (binding.userEmailAddress.text.toString().trim().isEmpty()){
            binding.userEmailAddress.error = errorEmptyField
            error = false
        }else{
            binding.userEmailAddress.error = null
        }
        //Password
        if (binding.userPasswordReg.text.toString().trim().isEmpty()){
            binding.userPasswordReg.error = errorEmptyField
            error = false
        }else{
            binding.userPasswordReg.error = null
        }

        //if email form is invalid
        if (""".+\@.+\..+""".toRegex().matches(binding.userEmailAddress.text.toString())){

            // Clear error text
            binding.userEmailAddress.error = null
        }else{
            // Set error text
                binding.userEmailAddress.error = errorInvalidEmail
            error = false
        }

        //Not enough character password
        if (binding.userPasswordReg.text.toString().length < 8){
            binding.userPasswordReg.error = errorPasswordNotEnoughLength
            error = false
        }else{
            binding.userPasswordReg.error = null
        }
        return error
    }


}

