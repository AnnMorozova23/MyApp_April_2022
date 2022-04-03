package com.example.myapp_april_2022

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import com.example.myapp_april_2022.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var binding: ActivityMainBinding
    private var presenter: Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = restorePresenter()
        presenter?.onAttach(this)

        binding.enterBtn.setOnClickListener {
            presenter?.onLogin(
                binding.editLogin.text.toString(),
                binding.editPassword.text.toString()
            )
        }

        binding.registration.setOnClickListener {
            presenter?.onRegistration(
                binding.editLogin.text.toString(),
                binding.editPassword.text.toString()
            )
        }

        binding.backBtn.setOnClickListener {
            backLogin()
        }

        binding.remindPassword.setOnClickListener {
            presenter?.onRemindPassword()
        }

    }

    private fun restorePresenter(): Presenter {
        val presenter = onRetainCustomNonConfigurationInstance() as? Presenter
        return presenter ?: Presenter()
    }

    override fun getLastNonConfigurationInstance(): Any? {
        return presenter
    }


    override fun setSuccess() {
        binding.editLogin.isVisible = false
        binding.enterToSystem.isVisible = false
        binding.editPassword.isVisible = false
        binding.enterBtn.isVisible = false
        binding.registration.isVisible = false
        binding.remindPassword.isVisible = false
        binding.successEnter.isVisible = true
        binding.backBtn.isVisible = true
    }

    override fun setError() {
        Snackbar.make(
            binding.root,
            "Неверный логин или пароль, попробуйете еще раз",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun sendPassword() {

        Snackbar.make(
            binding.root,
            "Новый пароль выслан на вашу электронную почту",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun showProgress() {
        binding.enterBtn.isEnabled = false
        binding.progressBar.isVisible = true
        hideKeyboard(this)
    }

    override fun hideProgress() {
        binding.enterBtn.isEnabled = true
        binding.progressBar.isVisible = false
    }

    override fun setRegistrationSuccess() {
        binding.editLogin.isVisible = false
        binding.enterToSystem.isVisible = false
        binding.editPassword.isVisible = false
        binding.enterBtn.isVisible = false
        binding.registration.isVisible = false
        binding.remindPassword.isVisible = false
        binding.successEnter.text = "Вы успешно прошли регистрацию!"
        binding.successEnter.isVisible = true
        binding.backBtn.isVisible = true

    }

    override fun chekLogin() {
        Snackbar.make(binding.root, "Учетная запись уже создана", Snackbar.LENGTH_SHORT).show()
    }

    override fun backLogin() {
        binding.editLogin.isVisible = true
        binding.enterToSystem.isVisible = true
        binding.editPassword.isVisible = true
        binding.enterBtn.isVisible = true
        binding.registration.isVisible = true
        binding.remindPassword.isVisible = true
        binding.successEnter.isVisible = false
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