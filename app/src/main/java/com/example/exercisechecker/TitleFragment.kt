package com.example.exercisechecker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_title.*


/*
 タイトルのフラグメント
 */
class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_title, container, false)
    }

    /*
    フラグメントのタイトルを変更
     */
    fun setTitle(title: String) {
        titleText.text = title
    }

}
