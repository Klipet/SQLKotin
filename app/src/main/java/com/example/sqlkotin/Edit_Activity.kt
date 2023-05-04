package com.example.sqlkotin

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.sqlkotin.R.id.edTitle
import com.example.sqlkotin.R.id.fbAddImage
import com.example.sqlkotin.R.id.tvTitle
import com.example.sqlkotin.databinding.EditActivityBinding
import com.example.sqlkotin.db.MyDbManager
import com.example.sqlkotin.db.MyIntentConstans


class Edit_Activity : AppCompatActivity() {
    val imageReqestCode = 10
    var tempImageUri = "empty"
    val myDbManager = MyDbManager(this)



    lateinit var binding: EditActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
       //setContentView(R.layout.edit_activity)
        binding.mainImageLayout.visibility = View.GONE
        getMyIntens()

    }
        override fun onResume() {
          super.onResume()
          myDbManager.openDb()
      }


      override fun onDestroy() {
          super.onDestroy()
          myDbManager.closeDb()
       }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == imageReqestCode){
            binding.imMainImage.setImageURI(data?.data)
            tempImageUri = data?.data.toString()
        }
    }

    fun onClickAddImage(view: View) {
        binding.mainImageLayout.visibility = View.VISIBLE
        binding.fbAddImage.visibility = View.GONE
    }

    fun onClickDeleteImages(view: View) {
        binding.mainImageLayout.visibility = View.GONE
        binding.fbAddImage.visibility = View.VISIBLE
    }
    fun onClickChooseImage(view: View) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        startActivityForResult(intent, imageReqestCode)

    }
    fun onClickSave(view: View){
        val myTitle = binding.edTitle.text.toString()
        val myDesc = binding.edDescription.text.toString()

        if (myTitle != "" && myDesc != ""){
            myDbManager.insertToDb(myTitle,myDesc,tempImageUri)
        }

    }
    fun getMyIntens(){
        val i = intent
        if (i != null) {
            if (i.getStringExtra(MyIntentConstans.I_TITLE_KEY) != "null"){
                binding.edTitle.setText(i.getStringExtra(MyIntentConstans.I_TITLE_KEY))
                binding.edDescription.setText(i.getStringExtra(MyIntentConstans.I_DESK_KEY))
                 if (i.getStringExtra(MyIntentConstans.I_URI_KEY) != "empty" ){
                     binding.mainImageLayout.visibility = View.GONE
                     binding.fbAddImage.visibility = View.VISIBLE
                   //  binding.imMainImage.setImageURI(tempImageUri)
                 }
            }
        }
    }

}