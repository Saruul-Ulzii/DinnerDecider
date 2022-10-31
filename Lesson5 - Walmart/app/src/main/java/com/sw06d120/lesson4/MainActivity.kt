package com.sw06d120.lesson4

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_walmart_login.*
import java.time.Duration

class MainActivity : AppCompatActivity() {
    private var users: ArrayList<User> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walmart_login)

        addDummyUsers()

        btnSignIn.setOnClickListener {
            var username = txtEmail.text
            var password = txtPassword.text

            if(username.isNullOrEmpty() || password.isNullOrEmpty()) {
                var toast = Toast.makeText(this, "Please enter valid credentials!", Toast.LENGTH_SHORT)
                toast.show()
                txtEmail.requestFocusFromTouch()
            } else {
                if(validUser(username = username.toString(), password = password.toString())) {
                    var shoppingActivity = Intent(this, ShoppingCategory::class.java)
                    shoppingActivity.putExtra("username", username)
                    startActivity(shoppingActivity)
                } else {
                    var toast = Toast.makeText(this, "User not found. \nPlease enter valid credentials!", Toast.LENGTH_LONG)
                    toast.show()
                    txtEmail.setText("")
                    txtPassword.setText("")
                    txtEmail.requestFocusFromTouch()
                }
            }
        }

        btnCreateAccount.setOnClickListener {
            var shoppingActivity = Intent(this, ShoppingCategory::class.java)
            shoppingActivity.putExtra("username", username)
            startActivity(shoppingActivity)
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