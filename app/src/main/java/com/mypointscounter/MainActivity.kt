package com.mypointscounter

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mypointscounter.databinding.ActivityMainBinding
import com.mypointscounter.model.MainViewModel
import com.mypointscounter.model.MyPoints

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding;
    lateinit var mainViewModel: MainViewModel;
    //var contador : Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


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
            }
        }

        binding.btResta.setOnClickListener {
            mainViewModel.contador--;
            if (mainViewModel.contador < 0){
                binding.tvContador.setTextColor(Color.RED);
                binding.tvContador.text = mainViewModel.contador.toString();
            }
            binding.tvContador.text = mainViewModel.contador.toString();
        }

        binding.btReset.setOnClickListener {
            mainViewModel.contador = 0;
            binding.tvContador.setTextColor(Color.BLACK);
            binding.tvContador.text = mainViewModel.contador.toString();
        }

        binding.imageButton.setOnClickListener {
            binding.tvSaved.text =
                resources.getString(R.string.txt_save_info, "*");

        }


    }

    @Override
    override fun onSaveInstanceState(outState: Bundle) {

        outState.run{
            putString("Contador", binding.tvContador.text.toString());
        }
        super.onSaveInstanceState(outState)
    }

    @Override
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.run {
            binding.tvContador.text = this.getString("Contador");
        }

    }
}
