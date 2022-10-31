package com.sw06d120.lesson4

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_walmart_login.*

class MainActivity : AppCompatActivity() {
    private var users: ArrayList<User> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walmart_login)
        supportActionBar?.hide()

        addDummyUsers()

        btnSignIn.setOnClickListener {
            var username = txtFirstname.text
            var password = txtLastname.text

            if(username.isNullOrEmpty() || password.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter valid credentials!", Toast.LENGTH_SHORT).show()
                txtFirstname.requestFocusFromTouch()
            } else {
                if(validUser(username = username.toString(), password = password.toString())) {
                    var shoppingActivity = Intent(this, ShoppingCategory::class.java)
                    shoppingActivity.putExtra("username", username.toString())
                    startActivity(shoppingActivity)
                } else {
                    Toast.makeText(this, "User not found. \nPlease enter valid credentials!", Toast.LENGTH_LONG).show()
                    txtFirstname.setText("")
                    txtLastname.setText("")
                    txtFirstname.requestFocusFromTouch()
                }
            }
        }

        btnCreateAccount.setOnClickListener {
            var createUserActivity = Intent(this, CreateUser::class.java)
            resultHandler.launch(createUserActivity)
        }
    }

    private var resultHandler = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val passedUser = data!!.getSerializableExtra("newUser")
            val newUser = passedUser as User
            users.add(newUser)
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

    fun getUser(username: String): User {
        for (u: User in users) {
            if(u.userName.equals(username)) {
                return u
            }
        }

        return User()
    }

    fun forgotPassword(view: View) {
        var email = txtFirstname.text
        var user = getUser(email.toString())

        if(!user.userName.equals("")) {
            var sendPasswordIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"))
            sendPasswordIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            sendPasswordIntent.putExtra(Intent.EXTRA_SUBJECT, "Sending your password. Please change it asap!")
            sendPasswordIntent.putExtra(Intent.EXTRA_TEXT, "Your password: ${user.password}")
            startActivity(Intent.createChooser(sendPasswordIntent, "Send Email Using: "))
        } else {
            Toast.makeText(this, "User not found. \nPlease enter valid credentials!", Toast.LENGTH_LONG).show()
        }
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