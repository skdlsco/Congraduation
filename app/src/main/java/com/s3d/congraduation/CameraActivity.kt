package com.s3d.congraduation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CameraActivity : AppCompatActivity() {


    private var imagePath = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), CAMERA_REQ_CODE)
        getImage()
    }


    private fun changeImage() {
        val bitmap = BitmapFactory.decodeFile(imagePath)
        Log.e("asdf", imagePath)
        imageView.setImageBitmap(bitmap)
    }

    private fun getImage() {
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(packageManager) != null) {
                val image = createImageFile()

                val imageUri = FileProvider.getUriForFile(this, packageName, image)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                startActivityForResult(intent, CAMERA_REQ_CODE)
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun createImageFile(): File {

        val storage = File(Environment.getExternalStorageDirectory().absolutePath + "/s3d").apply {
            if (!exists())
                mkdirs()
        }
        val imageName = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date()) + ".png"

        File(storage.absolutePath + "/" + imageName).let {
            Log.e("adsf", it.absolutePath)
            imagePath = it.absolutePath
            return it
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == CAMERA_REQ_CODE) {
            changeImage()
        }
    }

    companion object {
        const val CAMERA_REQ_CODE = 1000
    }
}
