package com.example.andy.bugapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class InfoFragment : Fragment() {
    //Default onCreate method

    companion object {
        fun newInstance() : InfoFragment {
            val fragment = InfoFragment()
            val args = Bundle()
            args.putString("title", "InfoFragment")
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater!!.inflate(R.layout.fragment_info, container, false)
    }
}