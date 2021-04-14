package com.solutis.ewerton.booksonthetable.ui.activity.internal.view_book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.solutis.ewerton.booksonthetable.R
import com.solutis.ewerton.booksonthetable.databinding.FragmentViewBookBinding
import com.solutis.ewerton.booksonthetable.model.Book
import com.solutis.ewerton.booksonthetable.model.BookStatus
import com.solutis.ewerton.booksonthetable.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewBookFragment : BaseFragment<FragmentViewBookBinding>(R.layout.fragment_view_book) {

    private val viewModel: ViewBookViewModel by viewModel()
    private val args: ViewBookFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val book = args.book
        binding.viewModel = viewModel
        viewModel.book.value = book
        setupChangeStatusButtonVisibility(book)
        setTitle(book.name)
        return binding.root
    }

    private fun setupChangeStatusButtonVisibility(book: Book) {
        binding.changeStatusButton.visibility =
            if (book.status == BookStatus.READ) View.GONE else View.VISIBLE
    }

    private fun setTitle(title:String?){
        binding.title.titleTextView.text = title
    }
}