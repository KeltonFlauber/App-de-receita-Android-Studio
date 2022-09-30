package com.example.receitasrapidas

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.receitasrapidas.databinding.ActivityTelaCadastroBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class tela_cadastro : AppCompatActivity() {

    private lateinit var binding: ActivityTelaCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_tela_cadastro)
        supportActionBar?.hide()

        binding.button.setOnClickListener { cadastrarConta() }
    }

    private fun cadastrarConta(){
        val userName = binding.editTextUserName.text.toString()
        val email = binding.editTextEmail2.text.toString()
        val password = binding.editTextTextPassword2.text.toString()

        if (userName.isEmpty() || email.isEmpty() || password.isEmpty()){
            val snackbar: Snackbar = Snackbar.make(binding.layout,
                "Preencha todos os campos", Snackbar.LENGTH_SHORT)
            snackbar.setBackgroundTint(Color.WHITE)
            snackbar.setTextColor(Color.BLACK)
            snackbar.show()
        } else{
            cadastrarUsuario()
        }
    }

    private fun cadastrarUsuario(){
        val email = binding.editTextEmail2.text.toString()
        val password = binding.editTextTextPassword2.text.toString()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    val snackbar = Snackbar.make(binding.layout, "Sucesso!", Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.WHITE)
                    snackbar.setTextColor(Color.BLACK)
                    snackbar.show()

                    FirebaseAuth.getInstance().signOut()
                }
            }.addOnFailureListener {
                val error = when(it){
                    is FirebaseAuthWeakPasswordException -> "A senha precisa ter pelo menos 6 caracteres!"
                    is FirebaseAuthInvalidCredentialsException -> "Digite um Email válido!"
                    is FirebaseAuthUserCollisionException -> "Conta ja cadastrada!"
                    is FirebaseNetworkException -> "Sem conexão!"
                    else -> "Erro ao cadastrar usuário!"
                }
                val snackbar = Snackbar.make(binding.layout, error, Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            }

        val hashMap = hashMapOf("userName" to binding.editTextUserName.text.toString())
        FirebaseFirestore.getInstance().collection("Users").document("UserName")
            .set(hashMap)
    }
}