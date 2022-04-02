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

        presenter = Presenter()
        presenter?.onAttach(this)

        binding.enterBtn.setOnClickListener {
            presenter?.onLogin(
                binding.editLogin.text.toString(),
                binding.editPassword.text.toString()
            )
        }

    }


    override fun setSuccess() {
        binding.editLogin.isVisible = false
        binding.enterToSystem.isVisible = false
        binding.editPassword.isVisible = false
        binding.enterBtn.isVisible = false
        binding.registration.isVisible = false
        binding.remindPassword.isVisible = false
        binding.successEnter.isVisible = true
    }

    override fun setError() {
        Snackbar.make(binding.root, "Error", Snackbar.LENGTH_SHORT).show()
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