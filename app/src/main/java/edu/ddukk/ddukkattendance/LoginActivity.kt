package edu.ddukk.ddukkattendance

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import edu.ddukk.ddukkattendance.dao.UserDAO
import edu.ddukk.ddukkattendance.database.AppDatabase
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    val btnLogin: Button by lazy { findViewById(R.id.btnLogin_Login) }
    val db: AppDatabase by lazy { AppDatabase.getDatabase(this) }
    val userDAO: UserDAO by lazy { db.userDAO() }

    val etLogin: EditText by lazy { findViewById(R.id.etUserName_Login) }
    val etPass: EditText by lazy { findViewById(R.id.etPass_Login) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets

        }
        btnLogin.setOnClickListener {

            startActivity(
                Intent(
                    applicationContext,
                    AdminHomeActivity::class.java
//                    UserHomeActivity::class.java
                )
            )


            if (CredentialValid()) {
                lifecycleScope.launch {
                    val res = userDAO.getUsersByEmail(etLogin.text.toString())
                    if (res != null) {
                        if (res.password.equals(etPass.text.toString()))
                            startActivity(
                                Intent(
                                    applicationContext,
                                    UserHomeActivity::class.java
                                )
                            )
                        else
                            Toast.makeText(
                                applicationContext,
                                "Invalid Password",
                                Toast.LENGTH_LONG
                            ).show()
                    } else
                        Toast.makeText(
                            applicationContext, "Invalid User " +
                                    "Credentials", Toast.LENGTH_LONG
                        ).show()
                }

            } else
                Toast.makeText(
                    applicationContext, "Enter email and " +
                            "password", Toast.LENGTH_SHORT
                ).show()

        }
    }

    private fun CredentialValid(): Boolean {
        if (etLogin.text.isNullOrEmpty()) {
            etLogin.setError("Enter Email")
            return false
        }
        if (etPass.text.isNullOrEmpty()) {
            etPass.setError("Enter Password")
            return false
        }
        return true
    }
}