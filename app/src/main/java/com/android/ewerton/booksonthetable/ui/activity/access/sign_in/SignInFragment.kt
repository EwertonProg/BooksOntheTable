package com.android.ewerton.booksonthetable.ui.activity.access.sign_in

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.android.ewerton.booksonthetable.R
import com.android.ewerton.booksonthetable.databinding.FragmentSignInBinding
import com.android.ewerton.booksonthetable.ui.BaseFragment
import com.android.ewerton.booksonthetable.ui.activity.internal.InternalNavHostActivity
import com.android.ewerton.booksonthetable.ui.util.setColorizedText
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : BaseFragment<FragmentSignInBinding>(R.layout.fragment_sign_in) {

    private val viewModel: SignInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
        configureOnClickOnSingUpRequestTextView()
        setSignUpRequestTextViewColorized()
        setOnSignInResultCallback()
        return binding.root
    }

    private fun setOnSignInResultCallback() {
        viewModel.signInResult.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let {
                if (it) {
                    Toast.makeText(this.context, "Login realizado com sucesso!", Toast.LENGTH_LONG)
                        .show()
                    startActivity(Intent(requireContext(),InternalNavHostActivity::class.java))
                } else {
                    Toast.makeText(this.context, "Login falhou!", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun configureOnClickOnSingUpRequestTextView() {
        binding.signUpRequestTextView.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    private fun setSignUpRequestTextViewColorized() {
        val text = getString(R.string.sing_up_request)
        binding.signUpRequestTextView.setColorizedText(
            text,
            resources.getColor(R.color.main, null),
            Pair(15,text.length)
        )
    }
}