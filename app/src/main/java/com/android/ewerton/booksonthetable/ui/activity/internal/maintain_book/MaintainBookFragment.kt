package com.android.ewerton.booksonthetable.ui.activity.internal.maintain_book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.ewerton.booksonthetable.R
import com.android.ewerton.booksonthetable.databinding.FragmentMaintainBookBinding
import com.android.ewerton.booksonthetable.model.BookStatus
import com.android.ewerton.booksonthetable.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MaintainBookFragment :
    BaseFragment<FragmentMaintainBookBinding>(R.layout.fragment_maintain_book) {

    private val viewModel: MaintainBookViewModel by viewModel()
    private val args: MaintainBookFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
        updateScreenToSave()
        getBookIfExistsAndUpdateScreen()
        setOnPersistReturnNavigate()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setupGenderAdapter()
        setupStatusAdapter()
    }

    private fun setupGenderAdapter() {
        binding.genderEditText.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.dropdown_list_item,
                viewModel.getBookGenders()
            )
        )
    }

    private fun setupStatusAdapter() {
        binding.statusEditText.apply {
                setAdapter(
                ArrayAdapter(
                    requireContext(),
                    R.layout.dropdown_list_item,
                    BookStatus.values().map { bookStatus -> bookStatus.statusName }
                )
            )
        }
    }

    private fun getBookIfExistsAndUpdateScreen() {
            args.book?.let {
                viewModel.book.value = it
                updateScreenToUpdate()
            }
    }

    private fun setOnPersistReturnNavigate() {
        viewModel.persistReturn.observe(viewLifecycleOwner, {
            findNavController().navigate(MaintainBookFragmentDirections.actionMaintainBookFragmentToUserHomeFragment())
        })
    }

    private fun updateScreenToUpdate() {
        setButtonText(getString(R.string.edit))
        setPageTitle(getString(R.string.edit_book))
    }

    private fun updateScreenToSave() {
        setPageTitle(getString(R.string.register_book))
        setButtonText(getString(R.string.save))
    }

    private fun setButtonText(text: String) {
        binding.persistButton.text = text
    }

    private fun setPageTitle(title: String) {
        binding.title.titleTextView.text = title
    }

}