package com.sw06d120.lesson4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_user.*
import kotlinx.android.synthetic.main.activity_walmart_login.*
import kotlinx.android.synthetic.main.activity_walmart_login.btnCreateAccount
import kotlinx.android.synthetic.main.activity_walmart_login.txtFirstname
import kotlinx.android.synthetic.main.activity_walmart_login.txtLastname

class CreateUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        supportActionBar?.hide()

        btnCreateAccount.setOnClickListener {
            if(txtFirstname.text.isNullOrEmpty() ||
                txtLastname.text.isNullOrEmpty() ||
                txtEmail.text.isNullOrEmpty() ||
                txtPassword3.text.isNullOrEmpty()
            ) {
                Toast.makeText(this, "Please fill all required fields!", Toast.LENGTH_SHORT).show()
            } else {
                var newUser = User(txtFirstname.text.toString(), txtLastname.text.toString(), txtEmail.text.toString(), txtPassword3.text.toString())

                val intent = Intent()
                intent.putExtra("newUser", newUser)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}

private fun Intent.putExtra(name: String, newUser: User) {

}
