package com.example.callpermissions

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    val REQUEST_CALL = 1
    val telNumber = "0732974429"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val telNumber = findViewById<EditText>(R.id.etnumber)
        val call = findViewById<Button>(R.id.bcall)
        call.setOnClickListener {
            call()
        }
    }

    private fun call() {
        if (telNumber.trim { it <= ' ' }.isNotEmpty()) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    REQUEST_CALL
                )
            } else {
                val Intent = Intent(Intent.ACTION_CALL)
                Intent.data = Uri.parse("tel:" + telNumber)
                startActivity(Intent)
            }
        } else {
            Toast.makeText(this@MainActivity, "Enter Phone Number", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CALL) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.w(TAG, "onRequestPermissionsResult: Permission granted!")
                call()
            } else {
                Log.w(TAG, "onRequestPermissionsResult: Permission denied!")
            }
        } else {
            Log.w(TAG, "onRequestPermissionsResult: $requestCode")
        }
    }
}