package com.example.tiptime

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AlertDialogLayout
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.stream.DoubleStream.builder
import java.util.stream.IntStream.builder

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            val stringInTextField = binding.costOfService.text
            if(stringInTextField.isEmpty()){
                val builder = androidx.appcompat.app.AlertDialog.Builder(this)
                builder.setTitle("Necessário informar o custo do serviço")
                builder.setPositiveButton("OK, entendi!",{ dialogInterface: DialogInterface?, i: Int -> return@setPositiveButton})
                builder.show()
            }
            else {
                calculateTip()
            }
        }
    }


    private fun calculateTip() {

        val stringInTextField = binding.costOfService.text.toString()

        val cost = stringInTextField.toDouble()
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked
        val roundDown = binding.roundDownSwitch.isChecked

        if (roundUp){
            tip = kotlin.math.ceil(tip)
        }
        if (roundDown){
            tip = kotlin.math.ceil(tip - 1)
        }
        if(roundDown && roundUp){
            val builder = androidx.appcompat.app.AlertDialog.Builder(this)
            builder.setTitle("Pode selecionar apenas uma opção de aredondar")
            builder.setPositiveButton("OK, entendi!",{ dialogInterface: DialogInterface?, i: Int -> return@setPositiveButton})
            builder.show()
        }
        NumberFormat.getCurrencyInstance()
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount_number, formattedTip)
    }
}


