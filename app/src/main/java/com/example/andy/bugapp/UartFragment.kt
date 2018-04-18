package com.example.andy.bugapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class UartFragment : Fragment() {
    //Default onCreate method

    companion object {
        fun newInstance() : UartFragment {
            val fragment = UartFragment()
            val args = Bundle()
            args.putString("title", "UartFragment")
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater!!.inflate(R.layout.fragment_uart, container, false)
    }
}