package com.solutis.ewerton.booksonthetable.ui.activity.internal.maintain_book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.solutis.ewerton.booksonthetable.R
import com.solutis.ewerton.booksonthetable.databinding.FragmentMaintainBookBinding
import com.solutis.ewerton.booksonthetable.databinding.FragmentSignUpBinding
import com.solutis.ewerton.booksonthetable.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MaintainBookFragment : BaseFragment<FragmentMaintainBookBinding>(R.layout.fragment_maintain_book) {

    private val viewModel: MaintainBookViewModel by viewModel()
    private val args: MaintainBookFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        setPageTitle()
        binding.viewModel = viewModel
        args.book?.let {
            viewModel.book.value = it
        }
        return binding.root
    }

    private fun setPageTitle() {
        binding.title.titleTextView.text = getString(R.string.register_book)
    }

}