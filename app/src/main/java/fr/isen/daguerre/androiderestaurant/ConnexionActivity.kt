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
import fr.isen.daguerre.androiderestaurant.databinding.ActivityConnexionBinding
import org.json.JSONObject

class ConnexionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConnexionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConnexionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            if (binding.userName.text.trim().isNotEmpty() || binding.userPassword.text.trim()
                    .isNotEmpty()
            ) {
                Toast.makeText(this, "Entrée un identifiant", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Entrée un mot de passe", Toast.LENGTH_LONG).show()
            }

            val email = binding.userName.text.toString()
            val password = binding.userPassword.text.toString()

            val queue = Volley.newRequestQueue(this)
            val url = "http://test.api.catering.bluecodegames.com/user/login"
            val jsonObject = JSONObject()
            jsonObject.put("id_shop", 1)
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

        }

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity((intent))
        }
    }

}
