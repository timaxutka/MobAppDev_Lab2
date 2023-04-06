package com.example.lab_20

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

interface OnButtonClickListener {
    fun onButtonClicked()
}
class MainFragment : Fragment() {
    private lateinit var button: Button
    private var onButtonClickListener: OnButtonClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onButtonClickListener = context as? OnButtonClickListener
    }

    override fun onDetach() {
        super.onDetach()
        onButtonClickListener = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button = view.findViewById(R.id.button)
        button.setOnClickListener {
            onButtonClickListener?.onButtonClicked()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

}