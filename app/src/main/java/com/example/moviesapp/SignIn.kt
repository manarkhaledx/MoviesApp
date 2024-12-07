package com.example.moviesapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moviesapp.databinding.ActivitySignInBinding

class SignIn : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.signInBtn.setOnClickListener {
            binding.userNameEtLayout.error = null
            binding.passwordEtLayout.error = null

            val username = binding.userNameEt.text.toString().trim()
            val password = binding.passwordEt.text.toString()
            when {
                username.isEmpty() && password.isEmpty() -> {
                    binding.userNameEtLayout.error = "Username is required"
                    binding.passwordEtLayout.error = "Password is required"
                }

                username.isEmpty() -> {
                    binding.userNameEtLayout.error = "Username is required"
                }

                password.isEmpty() -> {
                    binding.passwordEtLayout.error = "Password is required"
                }

                !isPasswordValid(password) -> {
                    binding.passwordEtLayout.error =
                        "Password must be at least 8 characters and contain a special character"
                }


                else -> {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("username", binding.userNameEt.text.toString())
                    startActivity(intent)
                }
            }
        }
        binding.userNameEt.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) binding.userNameEtLayout.error = null
        }

        binding.passwordEt.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) binding.passwordEtLayout.error = null
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 8 && password.contains(Regex("[^A-Za-z0-9 ]"))
    }
}






