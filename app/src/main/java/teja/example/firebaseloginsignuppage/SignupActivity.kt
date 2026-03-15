package teja.exampe.firebaseloginsignuppage

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var signupBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        email = findViewById(R.id.signupEmail)
        password = findViewById(R.id.signupPassword)
        signupBtn = findViewById(R.id.signupButton)

        signupBtn.setOnClickListener {

            val userEmail = email.text.toString()
            val userPassword = password.text.toString()

            if(userEmail.isEmpty() || userPassword.isEmpty()){
                Toast.makeText(this,"Fields cannot be empty",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(userPassword.length < 6){
                Toast.makeText(this,"Password must be 6+ characters",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener{

                    if(it.isSuccessful){

                        Toast.makeText(this,"Signup Success",Toast.LENGTH_SHORT).show()

                        startActivity(Intent(this,LoginActivity::class.java))
                        finish()

                    }else{

                        Toast.makeText(this,it.exception?.message,Toast.LENGTH_LONG).show()

                    }

                }

        }

    }
}