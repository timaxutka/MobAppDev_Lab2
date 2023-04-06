package com.example.lab_20

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

class BlankFragment3 : Fragment() {
    private lateinit var btn_fragment3: Button
    private lateinit var btn_fragment2: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_fragment3 = view.findViewById(R.id.btn_fragment3)
        btn_fragment2 = view.findViewById(R.id.btn_fragment2)
        btn_fragment3.setOnClickListener {
            EventBus.getDefault().post(FragmentChangeEvent(BlankFragment1::class.java))
        }
        btn_fragment2.setOnClickListener {
            EventBus.getDefault().post(FragmentChangeEvent(BlankFragment2::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}