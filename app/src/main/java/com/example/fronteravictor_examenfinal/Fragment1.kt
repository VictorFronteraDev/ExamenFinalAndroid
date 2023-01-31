package com.example.fronteravictor_examenfinal

import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment

class Fragment1: Fragment() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.layout_fragment1, container, false)

        val btn1: Button = view.findViewById(R.id.btnStyle1)

        val btn2: Button = view.findViewById(R.id.btnStyle2)

        val txtView: TextView = view.findViewById(R.id.txtViewExercise1)

        btn1.setOnClickListener {
            txtView.isVisible = true
            txtView.setTextColor(resources.getColor(android.R.color.holo_green_light))
        }

        btn2.setOnClickListener {
            txtView.isVisible = true
            txtView.setText(getString(R.string.bye))
            txtView.setTextSize(15F)
        }

        return view
    }
}