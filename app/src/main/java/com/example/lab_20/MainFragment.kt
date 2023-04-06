package com.example.lab_20

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import com.example.lab_20.FragmentChangeEvent

interface OnButtonClickListener {
    fun onButtonClicked()
}

class MainFragment : Fragment() {
    private lateinit var button: Button
    private var onButtonClickListener: OnButtonClickListener? = null
    lateinit var btn_fragment1: Button
    lateinit var btn_fragment2: Button
    lateinit var btn_fragment3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    fun onFragmentChange(event: FragmentChangeEvent) {
        val fragmentClass = event.fragmentClass
        val fragment = fragmentClass.newInstance()
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onButtonClickListener = context as? OnButtonClickListener
    }

    override fun onDetach() {
        super.onDetach()
        onButtonClickListener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button = view.findViewById(R.id.button)
        button.setOnClickListener {
            onButtonClickListener?.onButtonClicked()
        }
        btn_fragment1 = view.findViewById(R.id.btn_fragment1)
        btn_fragment2 = view.findViewById(R.id.btn_fragment2)
        btn_fragment3 = view.findViewById(R.id.btn_fragment3)
        btn_fragment1.setOnClickListener {
            EventBus.getDefault().post(FragmentChangeEvent(BlankFragment1::class.java))
        }
        btn_fragment2.setOnClickListener {
            EventBus.getDefault().post(FragmentChangeEvent(BlankFragment2::class.java))
        }
        btn_fragment3.setOnClickListener {
            EventBus.getDefault().post(FragmentChangeEvent(BlankFragment3::class.java))
        }


    }
}