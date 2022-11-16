package com.example.receitasrapidas.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.receitasrapidas.R
import com.example.receitasrapidas.databinding.ActivityLoginScreenBinding
import com.example.receitasrapidas.tela_cadastro
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginScreen : AppCompatActivity() {

    private lateinit var binding: ActivityLoginScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_screen)
        supportActionBar?.hide()

        binding.cadastro.setOnClickListener { registerScreen() }

        binding.btnEntrar.setOnClickListener { mainScreen() }

        binding.senhaVisibility.setOnCheckedChangeListener { compoundButton, b ->  passwordVisibility() }

        binding.bntLoginGoogle.setOnClickListener { signin() }
    }

    private fun signin() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail().build()

        val googleSingInClient : GoogleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions)

        val intent = googleSingInClient.signInIntent

        openActivity.launch(intent)
    }

    var openActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            val intent = it.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
            try {
                val account = task.getResult(ApiException::class.java)
                loginWithGoogle(account.idToken)
            } catch (exception : ApiException){

            }
        }
    }

    private fun loginWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this, "Sucesso!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, NaviDraw::class.java)
                showProgressBar()
                startActivity(intent)
            }else{
                Toast.makeText(this, "Error inesperado!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun passwordVisibility(){
        if(binding.senhaVisibility.isChecked){
            binding.senhaEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.senhaVisibility.setBackgroundResource(R.drawable.ic_baseline_visibility_24)
        }else {
            binding.senhaEditText.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.senhaVisibility.setBackgroundResource(R.drawable.ic_baseline_visibility_off_24)
        }
    }

    private fun registerScreen() {
        val intent: Intent = Intent(this@LoginScreen, tela_cadastro::class.java)
        showProgressBar()
        startActivity(intent)
    }

    private fun mainScreen() {

        if (binding.emailEntry.text.isEmpty() || binding.senhaEditText.text.isEmpty()) {
            val snackbar = Snackbar.make(
                binding.constLayout,
                "Preencha todos os dados!",
                Snackbar.LENGTH_SHORT
            )
            snackbar.show()
        } else {

            FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.emailEntry.text.toString(),
                binding.senhaEditText.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this@LoginScreen, NaviDraw::class.java)
                        showProgressBar()
                        startActivity(intent)
                        finish()
                    }else{
                        val snackbar = Snackbar.make(binding.constLayout,
                            "Senha e/ou Email incorreto(s)", Snackbar.LENGTH_SHORT)
                        snackbar.show()
                    }
            }
        }
    }

    private fun showProgressBar(){
        binding.progressCircular.isVisible = true
    }

    override fun onStart() {
        super.onStart()

        binding.progressCircular.isGone = true

        val currentUser = FirebaseAuth.getInstance().currentUser

        if(currentUser != null){
            val intent = Intent(this@LoginScreen, NaviDraw::class.java)
            startActivity(intent)
            finish()
        }
    }
}