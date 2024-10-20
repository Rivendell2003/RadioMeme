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

// Lista de memes
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_radio, container, false)

        val radioGroup = view.findViewById<RadioGroup>(R.id.radio_group)
        val fragmentHeader = view.findViewById<TextView>(R.id.fragment_header)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val nextButton = view.findViewById<Button>(R.id.button_next)

        // pone la primera imagen meme al iniciar
        imageView.setImageResource(memeImages[currentIndex])

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_button_yes -> {
                    fragmentHeader.text = getString(R.string.yes_message)
                }
                R.id.radio_button_no -> {
                    fragmentHeader.text = getString(R.string.no_message)
                }
            }
        }

        nextButton.setOnClickListener {
            // verificaa si se selecciono un RadioButton
            if (radioGroup.checkedRadioButtonId == -1) {
                // pone un mensaje si no se seleccionó nada
                Toast.makeText(context, "Por favor selecciona una opción", Toast.LENGTH_SHORT).show()
            } else {
                // avanza a la siguiente imagen
                showNextMeme(imageView)
                radioGroup.clearCheck() // clean a la selección actual
            }
        }

        return view
    }

    private fun showNextMeme(imageView: ImageView) {
        currentIndex = (currentIndex + 1) % memeImages.size
        imageView.setImageResource(memeImages[currentIndex])

        // un pequeño slide de animación
        imageView.alpha = 0f
        imageView.animate().alpha(1f).setDuration(500).start()
    }
}
