package com.example.mobilecalculator

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mobilecalculator.databinding.FragmentBaseCalculatorBinding
import com.example.mobilecalculator.viewmodel.MyViewModel


class BaseCalculator : Fragment() {
    private lateinit var myViewModel: MyViewModel


    // voice input
    private val SPEECH_REQUEST_CODE = 0

    // text to speech
    //  private lateinit var textToSpeech: TextToSpeech

    // Represent whether the lastly pressed key is numeric or not
    var lastNumeric: Boolean = false

    // If true, do not allow to add another DOT
    var lastDot: Boolean = false

    //initializing view binding
    private lateinit var binding: FragmentBaseCalculatorBinding

    private var tvInput: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBaseCalculatorBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        tvInput = binding.textView5
        binding.button9.setOnClickListener { onDigit(view, 9) }
        binding.button8.setOnClickListener { onDigit(view, 8) }
        binding.button7.setOnClickListener { onDigit(view, 7) }
        binding.button6.setOnClickListener { onDigit(view, 6) }
        binding.button5.setOnClickListener { onDigit(view, 5) }
        binding.button4.setOnClickListener { onDigit(view, 4) }
        binding.button3.setOnClickListener { onDigit(view, 3) }
        binding.button2.setOnClickListener { onDigit(view, 2) }
        binding.button1.setOnClickListener { onDigit(view, 1) }
        binding.button0.setOnClickListener { onDigit(view, 0) }

        binding.buttonDivide.setOnClickListener { onOperator(view, "/") }
        binding.buttonMultiply.setOnClickListener { onOperator(view, "*") }
        binding.buttonPlus.setOnClickListener { onOperator(view, "+") }
        binding.buttonMinus.setOnClickListener { onOperator(view, "-") }

        binding.buttonClear.setOnClickListener { onClear(view) }
        binding.buttonDecimal.setOnClickListener { onDecimalPoint(view) }
        binding.buttonEquals.setOnClickListener { onEqual(view) }

        myViewModel = ViewModelProvider(this@BaseCalculator).get(MyViewModel::class.java)


         myViewModel.toggleStateOfInputVoice.observe(viewLifecycleOwner) {isToggleButtonOn ->
            if (isToggleButtonOn) {


                binding.micBtn.visibility = View.VISIBLE
            } else {

                binding.micBtn.visibility = View.GONE
            }
        }

        setHasOptionsMenu(true)
        return view
    }



    private fun onDigit(view: View, num: Int) {
        tvInput?.append(num.toString())
        lastNumeric = true

    }


    private fun onClear(view: View) {
        tvInput?.text = ""
        lastNumeric = false
        lastDot = false
    }

    //Append . to the TextView

    private fun onDecimalPoint(view: View) {

        // If the last appended value is numeric then append(".") or don't.
        if (lastNumeric && !lastDot) {
            tvInput?.append(".")
            lastNumeric = false // Update the flag
            lastDot = true // Update the flag
        }
    }


    // Append +,-,*,/ operators to the TextView as per the Button.Text

    private fun onOperator(view: View, operator: String) {
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvInput?.append(operator)
                lastNumeric = false // Update the flag
                lastDot = false    // Reset the DOT flag
            }
        }

    }

    //Calculate the output

    private fun onEqual(view: View) {
        // If the last input is a number only, solution can be found.
        if (lastNumeric) {
            // Read the textView value
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {

                // Here if the value starts with '-' then we will separate it and perform the calculation with value.
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                // If the inputValue contains the Division operator
                when {
                    tvValue.contains("/") -> {
                        // Will split the inputValue using Division operator
                        val splitedValue = tvValue.split("/")

                        var one = splitedValue[0] // Value One
                        val two = splitedValue[1] // Value Two

                        if (prefix.isNotEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                            one = prefix + one
                        }

                        /*Here as the value one and two will be calculated based on the operator and
                                if the result contains the zero after decimal point will remove it.
                                And display the result to TextView*/
                        var text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                        tvInput?.text = text
                        // textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                    }
                    tvValue.contains("*") -> {
                        // If the inputValue contains the Multiplication operator
                        // Will split the inputValue using Multiplication operator
                        val splitedValue = tvValue.split("*")

                        var one = splitedValue[0] // Value One
                        val two = splitedValue[1] // Value Two

                        if (prefix.isNotEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                            one = prefix + one
                        }

//                         Here as the value one and two will be calculated based on the operator and
//                                if the result contains the zero after decimal point will remove it.
//                                And display the result to TextView

                        var text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                        tvInput?.text = text
                        // textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                    }
                    tvValue.contains("-") -> {

                        // If the inputValue contains the Subtraction operator
                        // Will split the inputValue using Subtraction operator

                        val splitedValue = tvValue.split("-")

                        var one = splitedValue[0] // Value One
                        val two = splitedValue[1] // Value Two

                        if (prefix.isNotEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                            one = prefix + one
                        }

//                         Here as the value one and two will be calculated based on the operator and
//                                if the result contains the zero after decimal point will remove it.
//                                And display the result to TextView

                        var text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                        tvInput?.text = text
                        // textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                    }
                    tvValue.contains("+") -> {
                        // If the inputValue contains the Addition operator
                        // Will split the inputValue using Addition operator
                        val splitedValue = tvValue.split("+")

                        var one = splitedValue[0] // Value One
                        val two = splitedValue[1] // Value Two

                        if (prefix.isNotEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                            one = prefix + one
                        }

//                        Here as the value one and two will be calculated based on the operator and
//                                if the result contains the zero after decimal point will remove it.
//                                And display the result to TextView

                        var text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                        tvInput?.text = text
                        // textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                    }
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }


    //Remove the zero after decimal point

    private fun removeZeroAfterDot(result: String): String {

        var value = result

        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }

        return value
    }


    // It is used to check whether any of the operator is used or not.

    private fun isOperatorAdded(value: String): Boolean {


//        Here first we will check that if the value starts with "-" then will ignore it.
//         As it is the result value and perform further calculation.
//

        return if (value.startsWith("-")) {
            false
        } else {
            (value.contains("/")
                    || value.contains("*")
                    || value.contains("-")
                    || value.contains("+"))
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.menu_voice -> {
                view?.findNavController()?.navigate(R.id.action_baseCalculator_to_voiceAssistance)
            }


        }

        return true
    }


}