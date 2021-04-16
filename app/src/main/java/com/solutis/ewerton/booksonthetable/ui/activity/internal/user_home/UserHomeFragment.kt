package com.solutis.ewerton.booksonthetable.ui.activity.internal.user_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.solutis.ewerton.booksonthetable.R
import com.solutis.ewerton.booksonthetable.databinding.FragmentUserHomeBinding
import com.solutis.ewerton.booksonthetable.model.Book
import com.solutis.ewerton.booksonthetable.ui.BaseFragment
import com.solutis.ewerton.booksonthetable.ui.activity.internal.user_home.adapter.BookItemAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserHomeFragment : BaseFragment<FragmentUserHomeBinding>(R.layout.fragment_user_home) {

    private val viewModel: UserHomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        setupReadingBooksSection()
        setupToReadBooksSection()
        setupReadBooksSection()
        setNavigationOnClickOnAddBook()
        return binding.root
    }

    private fun setNavigationOnClickOnAddBook() {
        binding.addBookButton.setOnClickListener {
            findNavController().navigate(
                UserHomeFragmentDirections.actionUserHomeFragmentToMaintainBookFragment(
                    null
                )
            )
        }
    }

    private fun setupReadingBooksSection() {
        val adapter = BookItemAdapter()
        binding.readingSection.apply {
            this.statusBookTitleLabel.text = getString(R.string.reading)
            this.bookStatusRecyclerView.adapter = adapter
        }
        viewModel.readingBooks.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    private fun setupToReadBooksSection() {
        val adapter = BookItemAdapter()
        binding.toReadSection.apply {
            this.statusBookTitleLabel.text = getString(R.string.to_read)
            this.bookStatusRecyclerView.adapter = adapter
        }
        viewModel.booksToRead.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    private fun setupReadBooksSection() {
        val adapter = BookItemAdapter()
        binding.readSection.apply {
            this.statusBookTitleLabel.text = getString(R.string.read)
            this.bookStatusRecyclerView.adapter = adapter
        }
        viewModel.readBooks.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }
}