package com.mypointscounter.utils

import android.app.Activity
import android.content.Context
import android.provider.Settings.Global.getString
import android.widget.Toast
import com.mypointscounter.R
import com.mypointscounter.model.MyPoints
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.PrintWriter
import kotlin.io.path.Path

class MyUtils {

    fun vibrate(context: Context){


    }


    fun savePoints(context: Context, datos: MyPoints) : Boolean {

        try {
            val stream: FileOutputStream = context.openFileOutput(
                R.string.filename.toString(), Context.MODE_PRIVATE
            )
            val file = PrintWriter(stream)
            file.println(datos)
            file.close()
            return true;
        } catch (e: Exception) {
            e.printStackTrace();
            return false
        }
        return true

    }


}