package com.sw06d120.cv_builder_app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sw06d120.cv_builder_app.model.User
import com.sw06d120.cv_builder_app.ui.main.MainScreen
import kotlinx.android.synthetic.main.resume_login.*

class MainActivity : AppCompatActivity() {
    private var users: ArrayList<User> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resume_login)
        supportActionBar?.hide()

        addDummyUsers()

        txtUsername.setText("jenglish@gmail.com")
        txtPassword.setText("pass1234")

        btnSign.setOnClickListener {
            var username = txtUsername.text
            var password = txtPassword.text

            if(username.isNullOrEmpty() || password.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter valid credentials!", Toast.LENGTH_SHORT).show()
                txtUsername.requestFocusFromTouch()
            } else {
                if(validUser(username = username.toString(), password = password.toString())) {
                    var resumeActivity = Intent(this, MainScreen::class.java)
//                    resumeActivity.putExtra("username", username.toString())
                    startActivity(resumeActivity)
                } else {
                    Toast.makeText(this, "User not found. \nPlease enter valid credentials!", Toast.LENGTH_LONG).show()
                    txtUsername.setText("")
                    txtPassword.setText("")
                    txtUsername.requestFocusFromTouch()
                }
            }
        }
    }

    fun validUser(username: String, password: String): Boolean {
        for(u: User in users) {
            if(u.userName.equals(username) && u.password.equals(password)) {
                return true
            }
        }

        return false
    }
    fun addDummyUsers() {
        var user1: User = User("Johny", "English", "jenglish@gmail.com", "pass1234")
        var user2: User = User("David", "Albert", "dalbert@gmail.com", "pass3456")
        var user3: User = User("Marks", "Twin", "mtwin@gmail.com", "pass4672")
        var user4: User = User("Jack", "London", "jlondon@gmail.com", "pass7261")
        var user5: User = User("Thomas", "Edison", "tedison@gmail.com", "pass5714")

        users.add(user1)
        users.add(user2)
        users.add(user3)
        users.add(user4)
        users.add(user5)

        print("users: " + users)
    }
}