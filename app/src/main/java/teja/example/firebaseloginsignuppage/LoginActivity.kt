package teja.exampe.firebaseloginsignuppage

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var loginBtn: Button
    lateinit var signupBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        email = findViewById(R.id.loginEmail)
        password = findViewById(R.id.loginPassword)
        loginBtn = findViewById(R.id.loginButton)
        signupBtn = findViewById(R.id.goSignup)

        loginBtn.setOnClickListener {

            val userEmail = email.text.toString()
            val userPassword = password.text.toString()

            if(userEmail.isEmpty() || userPassword.isEmpty()){
                Toast.makeText(this,"Fields cannot be empty",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener{

                    if(it.isSuccessful){

                        Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show()

                        startActivity(Intent(this,MainActivity::class.java))
                        finish()

                    }else{

                        Toast.makeText(this,it.exception?.message,Toast.LENGTH_LONG).show()

                    }

                }

        }

        signupBtn.setOnClickListener {

            startActivity(Intent(this,SignupActivity::class.java))

        }

    }

    override fun onStart() {
        super.onStart()

        if(auth.currentUser != null){

            startActivity(Intent(this,MainActivity::class.java))
            finish()

        }
    }
}