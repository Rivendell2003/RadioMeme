package com.example.radiomeme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class RadioFragment : Fragment() {
    private val memeImages = listOf(
        R.drawable.meme1,
        R.drawable.meme2,
        R.drawable.meme3,
        R.drawable.meme4,
        R.drawable.meme5,
        R.drawable.meme6,
        R.drawable.meme8,
        R.drawable.meme9,
        R.drawable.meme10,
        R.drawable.meme11,
        R.drawable.meme12
    )

    private var currentIndex = 0
    private var knownCount = 0
    private var unknownCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // restauerar el estado si existe
        savedInstanceState?.let {
            currentIndex = it.getInt("currentIndex", 0)
            knownCount = it.getInt("knownCount", 0)
            unknownCount = it.getInt("unknownCount", 0)
        }

        val view = inflater.inflate(R.layout.fragment_radio, container, false)

        val radioGroup = view.findViewById<RadioGroup>(R.id.radio_group)
        val fragmentHeader = view.findViewById<TextView>(R.id.fragment_header)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val nextButton = view.findViewById<Button>(R.id.button_next)

        // muestra a la primera imagen meme al iniciar o al restaurar
        imageView.setImageResource(memeImages[currentIndex])

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            fragmentHeader.text = when (checkedId) {
                R.id.radio_button_yes -> getString(R.string.yes_message)
                R.id.radio_button_no -> getString(R.string.no_message)
                else -> ""
            }
        }

        nextButton.setOnClickListener {
            // chekea si se seleccionó un RadioButton
            if (radioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(context, "Por favor selecciona una opción", Toast.LENGTH_SHORT).show()
            } else {
                // ups el conteo según la selección
                if (radioGroup.checkedRadioButtonId == R.id.radio_button_yes) {
                    knownCount++
                } else {
                    unknownCount++
                }

                // goto a la siguiente imagen
                currentIndex++
                if (currentIndex >= memeImages.size) {
                    showResults()
                } else {
                    showNextMeme(imageView)
                }

                radioGroup.clearCheck() // reset la selección actual
            }
        }

        return view
    }

    private fun showNextMeme(imageView: ImageView) {
        imageView.setImageResource(memeImages[currentIndex])
        // un pequeño efecto de desvanecimiento slide
        imageView.alpha = 0f
        imageView.animate().alpha(1f).setDuration(500).start()
    }

    private fun showResults() {
        val resultsFragment = ResultsFragment().apply {
            setResults(knownCount, unknownCount)
        }

        // replace el fragmento en el contenedor
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, resultsFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentIndex", currentIndex)
        outState.putInt("knownCount", knownCount)
        outState.putInt("unknownCount", unknownCount)
    }
}
