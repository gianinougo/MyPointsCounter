package com.ugogianino.mypointscounter.utils

import android.app.Activity
import android.content.Context
import android.content.Context.VIBRATOR_MANAGER_SERVICE
import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.ugogianino.mypointscounter.R
import com.ugogianino.mypointscounter.model.MyPoints
import java.io.IOException
import java.io.OutputStreamWriter


class MyUtils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun vibrate(context: Context){

       val vibrator: Vibrator
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
           val vibratorManager = context.getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager;
           vibrator = vibratorManager.defaultVibrator;
           if (!vibrator.hasVibrator()){
               Toast.makeText(context, "no tienes vibrador", Toast.LENGTH_SHORT).show()
           } else {
               Toast.makeText(context, "Vibrando", Toast.LENGTH_SHORT).show()
           }

       } else {
           vibrator = context.getSystemService(VIBRATOR_SERVICE) as Vibrator
           val vibrationEffect = VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE);
           if (!vibrator.hasVibrator()){
               Toast.makeText(context, "no tienes vibrador", Toast.LENGTH_SHORT).show()
           } else {
               Toast.makeText(context, "Vibrando", Toast.LENGTH_SHORT).show()
               vibrator.vibrate(vibrationEffect);
           }
       }


    }


    fun savePoints(context: Context, datos: MyPoints) : Boolean {

        try {
            val salida: OutputStreamWriter

            salida = OutputStreamWriter(
                context.openFileOutput(
                    context.getString(R.string.filename),
                    Activity.MODE_APPEND
                )
            )
            salida.write(datos.toString() + "\n");
            salida.close();

            val toast = Toast.makeText(context, "Guardado Correctamente", Toast.LENGTH_LONG);
            toast.show()
            return true
        } catch (e: IOException) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show();
            return false
        }
        return true

    }


}