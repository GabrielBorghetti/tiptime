package com.example.tiptime

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            val stringInTextField = binding.costOfService.text
            val roundUp = binding.roundUpSwitch.isChecked
            val roundDown = binding.roundDownSwitch.isChecked
            if (stringInTextField.isEmpty()) {
                val builder = androidx.appcompat.app.AlertDialog.Builder(this)
                    builder.setMessage("Cost of Service is needed")
                        builder.setPositiveButton("ok, understood!",
                            { dialogInterface: DialogInterface?, i: Int -> return@setPositiveButton })
                                 builder.show()
            } else if (roundDown && roundUp) {
                val builder = androidx.appcompat.app.AlertDialog.Builder(this)
                    builder.setMessage("You can only choose one option to round it")
                        builder.setPositiveButton("OK, understood!",
                                { dialogInterface: DialogInterface?, i: Int -> return@setPositiveButton })
                                    builder.show()
            } else {
                calculateTip()
            }
        }
        binding.btAbout.setOnClickListener {
            val intent = Intent(applicationContext, About::class.java)
            startActivity(intent)
        }
        binding.SavedTipsButton.setOnClickListener {
            val intent = Intent(applicationContext, SaveTips::class.java)
            startActivity(intent)
        }
        binding.SaveTipButton.setOnClickListener {
            savetip()
        }

    }


    @SuppressLint("StringFormatMatches")
    private fun calculateTip() {

        val stringInTextField = binding.costOfService.text.toString()

        val cost = stringInTextField.toDouble()
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
                R.id.option_twenty_percent -> 0.20
                    R.id.option_eighteen_percent -> 0.18
                        R.id.option_fifteen_percent -> 0.15
                            else -> 0.05
        }
        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked
        val roundDown = binding.roundDownSwitch.isChecked

        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        if (roundDown) {
            tip = kotlin.math.floor(tip)
        }

        NumberFormat.getCurrencyInstance()
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        val finalPrice = tip + cost
        val costCurrency = NumberFormat.getCurrencyInstance().format(cost)
        val finalPriceCurrency = NumberFormat.getCurrencyInstance().format(finalPrice)
            binding.costOfServiceText.text = getString(R.string.cost_of_service_text, costCurrency)
                binding.tipResult.text = getString(R.string.tip_amount_number, formattedTip)
                    binding.finalPrice.text = getString(R.string.finalPrice, finalPriceCurrency)
        var teste = formattedTip
    }

    private fun savetip() {

        if(binding.tipResult.text.toString() == ("Tip Amount: - - - - ")) {
            val builder = androidx.appcompat.app.AlertDialog.Builder(this)
                    builder.setTitle("Error!")
                        builder.setMessage("You must calculate a tip before saving it")
                            builder.setPositiveButton(
                "ok, understood!",
                { dialogInterface: DialogInterface?, i: Int -> return@setPositiveButton })
            builder.show()
        }else{
                val builder = androidx.appcompat.app.AlertDialog.Builder(this)
                        builder.setTitle("Tip Saved!")
                            builder.setMessage("You've saved your tip! Check on 'Saved Tips' to see all of your tips")
                                builder.show()
        }
    }
}






