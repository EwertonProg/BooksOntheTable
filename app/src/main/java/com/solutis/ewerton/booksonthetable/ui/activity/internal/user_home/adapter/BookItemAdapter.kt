package com.solutis.ewerton.booksonthetable.ui.activity.internal.user_home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solutis.ewerton.booksonthetable.databinding.BookItemBinding
import com.solutis.ewerton.booksonthetable.model.Book
import com.solutis.ewerton.booksonthetable.ui.activity.internal.user_home.UserHomeFragmentDirections
import com.solutis.ewerton.booksonthetable.ui.activity.internal.view_book.ViewBookFragmentDirections

class BookItemAdapter :
    ListAdapter<Book, BookItemAdapter.BookItemViewHolder>(BookItemDiffCallback()) {

    inner class BookItemViewHolder(val binding: BookItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BookItemViewHolder(
        BookItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        val book = getItem(position)
        holder.binding.book = book
        holder.binding.bookCard.setOnClickListener {
            holder.binding.root.findNavController()
                .navigate(UserHomeFragmentDirections.actionUserHomeFragmentToViewBookFragment(book))
        }
    }

}

class BookItemDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book) = newItem.id == oldItem.id

    override fun areContentsTheSame(oldItem: Book, newItem: Book) = newItem == oldItem
}