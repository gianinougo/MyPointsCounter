package com.ugogianino.mypointscounter

import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.ugogianino.mypointscounter.databinding.ActivityMainBinding
import com.ugogianino.mypointscounter.model.MainViewModel
import com.ugogianino.mypointscounter.model.MyPoints
import com.ugogianino.mypointscounter.utils.MyUtils
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding;
    lateinit var mainViewModel: MainViewModel;


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportActionBar()?.hide();
        }

        val myUtils =  MyUtils();

        binding = ActivityMainBinding.inflate(layoutInflater);
        //setContentView(R.layout.activity_main)
        setContentView(binding.root);

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java);

        binding.tvContador.text = mainViewModel.contador.toString();

        //mainViewModel.contador = binding.tvContador.text.toString();


        binding.btSuma.setOnClickListener {
            mainViewModel.contador++;
            if (mainViewModel.contador >= 0){
                binding.tvContador.setTextColor(Color.BLACK);
                binding.tvContador.text = mainViewModel.contador.toString();
                myUtils.vibrate(this);
            }

        }

        binding.btResta.setOnClickListener {
            mainViewModel.contador--;
            if (mainViewModel.contador < 0){
                binding.tvContador.setTextColor(Color.RED);
                binding.tvContador.text = mainViewModel.contador.toString();
                myUtils.vibrate(this);
            }

            binding.tvContador.text = mainViewModel.contador.toString();
            myUtils.vibrate(this);
        }

        binding.btReset.setOnClickListener {
            if ( mainViewModel.contador == 0){
                Snackbar.make(binding.root, R.string.txt_info_reset, Snackbar.LENGTH_LONG).show();
            }
            mainViewModel.contador = 0;
            binding.tvContador.setTextColor(Color.BLACK);
            binding.tvContador.text = mainViewModel.contador.toString();


            myUtils.vibrate(this);
        }

        binding.imageButton.setOnClickListener {


            var date = LocalDate.now();
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            var formattedDate = date.format(formatter)
            var currentDateTime= LocalDateTime.now();
            var time = currentDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))

            var datos = MyPoints(mainViewModel.contador, formattedDate, time);

            println(datos);

            myUtils.savePoints(this, datos);
            myUtils.vibrate(this);


            binding.tvSaved.text =
                resources.getString(R.string.txt_save_info, "$formattedDate - $time");

        }


    }

    @Override
    override fun onSaveInstanceState(outState: Bundle) {

        outState.run{
            putString("Contador", binding.tvContador.text.toString());
            putString("Texto", binding.tvSaved.text.toString());
        }
        super.onSaveInstanceState(outState)
    }

    @Override
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.run {
            binding.tvContador.text = this.getString("Contador");
            binding.tvSaved.text = this.getString("Texto");
        }

    }
}
