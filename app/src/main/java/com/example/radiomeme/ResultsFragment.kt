package com.example.radiomeme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ResultsFragment : Fragment() {
    private var knownCount: Int = 0
    private var unknownCount: Int = 0

    fun setResults(known: Int, unknown: Int) {
        knownCount = known
        unknownCount = unknown
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_results, container, false)

        val knownTextView = view.findViewById<TextView>(R.id.text_known)
        val unknownTextView = view.findViewById<TextView>(R.id.text_unknown)

        knownTextView.text = getString(R.string.known_count, knownCount)
        unknownTextView.text = getString(R.string.unknown_count, unknownCount)

        return view
    }
}
