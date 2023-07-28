package com.MyFlashlight.flashlight

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    private lateinit var cameraManage : CameraManager
    private lateinit var powerButton : Button
    var isFlash = false

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        powerButton = findViewById(R.id.powerBtn)
        cameraManage = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        powerButton.setOnClickListener {
            flashLightOnOff(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun flashLightOnOff(v: View?) {
        if(!isFlash){
            val cameraListId = cameraManage.cameraIdList[0]
            cameraManage.setTorchMode(cameraListId,true)
            isFlash = true
            powerButton.setText(R.string.on)
            Toast.makeText(this,"Flash Light is On",Toast.LENGTH_SHORT).show()
        }
        else{
            val cameraListId = cameraManage.cameraIdList[0]
            cameraManage.setTorchMode(cameraListId,false)
            isFlash = false
            powerButton.setText(R.string.off)
            Toast.makeText(this,"Flash Light is off",Toast.LENGTH_SHORT).show()
        }
    }


}