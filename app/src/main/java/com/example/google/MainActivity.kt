package com.example.google

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clearbtn.setOnClickListener {
            First_name.setText("")
            Last_name.setText("")
        }

        sendbtn.setOnClickListener {
            val first_text = First_name.text.toString()
            val second_text = Last_name.text.toString()
            val sendfirebase = FirebaseDatabase.getInstance()
            val ref = sendfirebase.getReference("employee")
            val id: String? = ref.push().key
            val employee = employee(id.toString(), first_text, second_text)
            ref.child(id.toString()).setValue(employee).addOnCompleteListener {
                Toast.makeText(applicationContext, "Complete", Toast.LENGTH_LONG).show()
                First_name.setText("")
                Last_name.setText("")
            }
        }
    }
}