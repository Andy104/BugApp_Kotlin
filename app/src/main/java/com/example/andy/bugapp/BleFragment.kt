package com.example.andy.bugapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BleFragment : Fragment() {
    //Default onCreate method

    companion object {
        fun newInstance() : BleFragment {
            val fragment = BleFragment()
            val args = Bundle()
            args.putString("title", "BleFragment")
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_ble, container, false)
    }
}
