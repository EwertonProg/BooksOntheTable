package com.android.ewerton.booksonthetable.ui.activity.internal.view_book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.ewerton.booksonthetable.R
import com.android.ewerton.booksonthetable.databinding.FragmentViewBookBinding
import com.android.ewerton.booksonthetable.model.Book
import com.android.ewerton.booksonthetable.model.BookStatus
import com.android.ewerton.booksonthetable.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewBookFragment : BaseFragment<FragmentViewBookBinding>(R.layout.fragment_view_book) {

    private val viewModel: ViewBookViewModel by viewModel()
    private val args: ViewBookFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
        args.book.UUID?.let { viewModel.getBook(it) }
        setBookLiveDataObserver()
        setOnEditBookClickListener()
        setOnDeleteBookObserver()
        return binding.root
    }

    private fun setBookLiveDataObserver() {
        viewModel.book.observe(viewLifecycleOwner, {
            it?.let {
                setupChangeStatusButtonVisibility(it)
                setTitle(it.name)
            }
        })
    }

    private fun setOnEditBookClickListener() {
        binding.editBookButton.setOnClickListener {
            findNavController().navigate(
                ViewBookFragmentDirections.actionViewBookFragmentToMaintainBookFragment(
                    viewModel.book.value
                )
            )
        }
    }

    private fun setOnDeleteBookObserver() {
        viewModel.deleteReturn.observe(viewLifecycleOwner, {
            findNavController().popBackStack()
        })
    }

    private fun setupChangeStatusButtonVisibility(book: Book) {
        binding.changeStatusButton.visibility =
            if (book.status == BookStatus.READ) View.GONE else View.VISIBLE
    }

    private fun setTitle(title: String?) {
        binding.title.titleTextView.text = title
    }
}