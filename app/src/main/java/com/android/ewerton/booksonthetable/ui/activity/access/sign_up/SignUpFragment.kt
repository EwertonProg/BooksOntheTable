package com.android.ewerton.booksonthetable.ui.activity.access.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.android.ewerton.booksonthetable.R
import com.android.ewerton.booksonthetable.databinding.FragmentSignUpBinding
import com.android.ewerton.booksonthetable.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : BaseFragment<FragmentSignUpBinding>(R.layout.fragment_sign_up) {

    private val viewModel: SignUpViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
        setupValidations()
        setOnSignUpResultCallback()
        return binding.root
    }

    private fun setupValidations() {
        setFullNameEditTextValidation()
        setEmailEditTextValidation()
        setPasswordEditTextValidation()
        setPasswordConfirmationEditTextValidation()
    }

    private fun setOnSignUpResultCallback() {
        viewModel.signUpResult.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let {
                if (it) {
                    Toast.makeText(this.context, "Usuario criado!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(this.context, "Erro ao criar o usuario!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun setFullNameEditTextValidation() {
        binding.fullNameEditText.validateOnLostFocus(binding.fullNameInputText,
            { if (it?.isValidAsName() == true) null else "Nome Invalido" })
    }

    private fun setEmailEditTextValidation() {
        binding.emailEditText.validateOnLostFocus(binding.emailTextInput,
            { if (it?.isValidAsEmail() == true) null else "Email Invalido" })
    }

    private fun setPasswordEditTextValidation() {
        binding.passwordEditText.validateOnLostFocus(binding.passwordInputText,
            { if (it?.isValidAsPassword() == true) null else "A senha deve conter ao menos 8 caracteres" })
    }

    private fun setPasswordConfirmationEditTextValidation() {
        binding.confirmPasswordEditText.validateOnLostFocus(binding.confirmPasswordInputText,
            { if (it == viewModel.user.value?.password) null else "As senhas devem ser iguais" })
    }
}