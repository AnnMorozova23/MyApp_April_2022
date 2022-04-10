package com.example.myapp_april_2022.ui.login

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import com.example.myapp_april_2022.app
import com.example.myapp_april_2022.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var viewModel: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = restoreViewModel()


        binding.enterBtn.setOnClickListener {
            viewModel?.onLogin(
                binding.editLoginTextView.text.toString(),
                binding.editPasswordTextView.text.toString()
            )
        }

        binding.registrationLinkTextView.setOnClickListener {
            viewModel?.onRegistration(
                binding.editLoginTextView.text.toString(),
                binding.editPasswordTextView.text.toString()
            )
        }

        binding.backBtn.setOnClickListener {
            backLogin()
        }

        binding.remindPasswordLinkTextView.setOnClickListener {
            viewModel?.onForgotPassword(login = "")
        }


        viewModel?.shouldShowProgress?.subscribe { shouldShow ->
            if (shouldShow == true) {
                showProgress()
            } else
                hideProgress()
        }

        viewModel?.isSuccess?.subscribe { success ->
            if (success == true) {
                setSuccess()
            } else
                setError()
        }

        viewModel?.isRegistration?.subscribe { registration ->
            if (registration == true) {
                setRegistrationSuccess()
            } else
                setError()
        }

        viewModel?.isSendPassword?.subscribe {
           sendPassword()
        }

        viewModel?.errorText?.subscribe {
            setError()
        }


    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel?.isSuccess?.unSubscribeAll()
        viewModel?.isRegistration?.unSubscribeAll()
        viewModel?.shouldShowProgress?.unSubscribeAll()
        viewModel?.errorText?.unSubscribeAll()
        viewModel?.isSendPassword?.unSubscribeAll()

    }


    private fun restoreViewModel(): LoginViewModel {
        val viewModel = onRetainCustomNonConfigurationInstance() as? LoginViewModel
        return viewModel ?: LoginViewModel(app.api, app.useCase)
    }

    override fun getLastNonConfigurationInstance(): Any? {
        return viewModel
    }


    private fun setSuccess() {
        binding.editLoginTextView.isVisible = false
        binding.enterToSystemTextView.isVisible = false
        binding.editPasswordTextView.isVisible = false
        binding.enterBtn.isVisible = false
        binding.registrationLinkTextView.isVisible = false
        binding.remindPasswordLinkTextView.isVisible = false
        binding.successLoginTextView.isVisible = true
        binding.backBtn.isVisible = true
    }

    private fun setError() {
        Snackbar.make(
            binding.root,
            "Неверный логин или пароль, попробуйете еще раз",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun sendPassword() {

        Snackbar.make(
            binding.root,
            "Новый пароль выслан на вашу электронную почту",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun showProgress() {
        binding.enterBtn.isEnabled = false
        binding.progressBar.isVisible = true
        hideKeyboard(this)
    }

    private fun hideProgress() {
        binding.enterBtn.isEnabled = true
        binding.progressBar.isVisible = false
    }

    private fun setRegistrationSuccess() {
        binding.editLoginTextView.isVisible = false
        binding.enterToSystemTextView.isVisible = false
        binding.editPasswordTextView.isVisible = false
        binding.enterBtn.isVisible = false
        binding.registrationLinkTextView.isVisible = false
        binding.remindPasswordLinkTextView.isVisible = false
        binding.successLoginTextView.text = "Вы успешно прошли регистрацию!"
        binding.successLoginTextView.isVisible = true
        binding.backBtn.isVisible = true

    }

    private fun chekLogin() {
        Snackbar.make(binding.root, "Учетная запись уже создана", Snackbar.LENGTH_SHORT).show()
    }

    private fun backLogin() {
        viewModel?.onLogOut()
        binding.editLoginTextView.isVisible = true
        binding.enterToSystemTextView.isVisible = true
        binding.editPasswordTextView.isVisible = true
        binding.enterBtn.isVisible = true
        binding.registrationLinkTextView.isVisible = true
        binding.remindPasswordLinkTextView.isVisible = true
        binding.successLoginTextView.isVisible = false
        binding.backBtn.isVisible = false
    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}