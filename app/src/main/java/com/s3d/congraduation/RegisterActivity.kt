package com.s3d.congraduation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class RegisterActivity : AppCompatActivity() {

    private val genders = ArrayList<TextView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)

        genders.add(gender1)
        genders.add(gender2)
        genders.add(gender3)

        genders.forEachIndexed { index, it -> it.onClick { genderSelect(index) } }
    }

    private fun genderSelect(position: Int) {
        genders.forEachIndexed { i, it ->
            it.isSelected = position == i
        }
    }
}
