package teja.exampe.firebaseloginsignuppage

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var logoutBtn: Button
    lateinit var emailText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        logoutBtn = findViewById(R.id.logoutBtn)
        emailText = findViewById(R.id.userEmail)

        val user = auth.currentUser

        emailText.text = user?.email

        logoutBtn.setOnClickListener {

            auth.signOut()

            startActivity(Intent(this,LoginActivity::class.java))
            finish()

        }

    }
}