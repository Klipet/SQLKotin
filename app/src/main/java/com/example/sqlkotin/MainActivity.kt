package com.example.sqlkotin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlkotin.databinding.ActivityMainBinding
import com.example.sqlkotin.db.MyDbManager

class MainActivity : AppCompatActivity() {

    val myDbManager = MyDbManager(this)
    val myAdapter = MyAdapter(ArrayList(), this)
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    //setContentView(R.layout.activity_main)
        init()
    }
    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        filAdapter()
    }


    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }


    fun onClickNew(view: View) {
        var i = Intent(this, Edit_Activity::class.java)
        startActivity(i)
    }


    fun init(){
        binding.rcView.layoutManager = LinearLayoutManager(this)
        binding.rcView.adapter = myAdapter
    }
    fun  filAdapter(){

        myAdapter.updateAdapter(myDbManager.readDbData())
    }



}