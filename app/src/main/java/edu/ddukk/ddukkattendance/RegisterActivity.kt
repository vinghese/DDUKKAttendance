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
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import edu.ddukk.ddukkattendance.dao.UserDAO
import edu.ddukk.ddukkattendance.database.AppDatabase
import edu.ddukk.ddukkattendance.models.User
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RegisterActivity : AppCompatActivity() {

    private val db: AppDatabase by lazy { AppDatabase.getDatabase(this) }
    private val userDAO: UserDAO by lazy {
        db.userDAO()
    }
    val btnLogin: Button by lazy { findViewById(R.id.btnToLogin_User) }
    val btnRegister: Button by lazy { findViewById(R.id.btnRegister_User) }

    val etFirstName: EditText by lazy { findViewById(R.id.etFirstName_User) }
    val etLastName: EditText by lazy { findViewById(R.id.etLastName_User) }
    val etEmail: EditText by lazy { findViewById(R.id.etEmail_User) }
    val etCollegeId: EditText by lazy { findViewById(R.id.etStudentId_User) }
    val etPassword: EditText by lazy { findViewById(R.id.etPassword_User) }
    val etConfirmPass: EditText by lazy { findViewById(R.id.etConfirmPassword_User) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
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


        etConfirmPass.doAfterTextChanged { confirmPass ->
            if (etPassword.text.toString() == confirmPass.toString())
                btnRegister.isEnabled = true
            else
                btnRegister.isEnabled = false
        }

        //btnRegister = findViewById(R.id.btnRegister_User)
        btnRegister.setOnClickListener {
            lifecycleScope.launch {
                var checkAllFields: Boolean = CheckAll()

                if (!checkAllFields) {
                    Toast.makeText(
                        applicationContext,
                        "Please enter all relevant fields",
                        Toast
                            .LENGTH_LONG
                    ).show()
                } else {

                    val res = userDAO.insertUser(
                        User(
                            firstName = etFirstName.text.toString(),
                            lastName = etLastName.text.toString(),
                            email = etEmail.text.toString(),
                            collegeId = etCollegeId.text.toString(),
                            password = etPassword.text.toString()
                        )
                    )
                    Toast.makeText(
                        applicationContext,
                        "Data Inserted",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        //    btnLogin = findViewById(R.id.btnToLogin_User)

        btnLogin.setOnClickListener {
            //  Read()
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }


    }

    private fun CheckAll(): Boolean {
        if (etFirstName.length() == 0) {
            etFirstName.setError("Enter First Name")
            return false
        }
        if (etLastName.length() == 0) {
            etLastName.setError("Enter First Name")
            return false
        }
        if (etEmail.length() == 0) {
            etEmail.setError("Enter First Name")
            return false
        }
        if (etCollegeId.length() == 0) {
            etCollegeId.setError("Enter First Name")
            return false
        }
        if (etPassword.length() == 0) {
            etPassword.setError("Enter First Name")
            return false
        }
        return true
    }

    fun Insert() = runBlocking {
        launch {
            userDAO.insertUser(
                User(
                    firstName = etFirstName.text.toString(),
                    lastName = etLastName.text.toString(),
                    email = etEmail.text.toString(),
                    collegeId = etCollegeId.text.toString(),
                    password = etPassword.text.toString()
                )
            )
            Toast.makeText(
                applicationContext,
                "Data Inserted",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun Read() = runBlocking {
        launch {
            val res: List<User> = userDAO.getUsers()
            for (item in res.iterator()) {
                Toast.makeText(
                    applicationContext,
                    "ID: " + item.id + " Name: " + item.firstName + "\t " +
                            " Email: " + item.email,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}