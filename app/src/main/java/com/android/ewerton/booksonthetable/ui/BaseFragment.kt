package com.android.ewerton.booksonthetable.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<DB: ViewDataBinding>(private val layoutId: Int) : Fragment(){

    lateinit var binding: DB

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflateViewAndReturnDataBinding(inflater,container)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    private fun inflateViewAndReturnDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): DB = DataBindingUtil.inflate(
        inflater,
        layoutId,
        container,
        false
    )

}