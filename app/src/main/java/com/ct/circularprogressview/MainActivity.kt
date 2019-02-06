package com.ct.circularprogressview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cicView = findViewById<CircularProgressView>(R.id.circle_progress_bar);
        cicView.updateProgressWithProgressText("Time", 65f, "Date")
    }
}
