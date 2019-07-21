package fr.outadoc.quickhass.feature.onboarding.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import fr.outadoc.quickhass.R
import fr.outadoc.quickhass.feature.onboarding.model.NavigationFlow
import fr.outadoc.quickhass.feature.onboarding.vm.WelcomeViewModel

class WelcomeFragment : Fragment() {

    private lateinit var viewModel: WelcomeViewModel
    private lateinit var viewHolder: ViewHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(WelcomeViewModel::class.java).apply {
            navigateTo.observe(this@WelcomeFragment, Observer {
                when (it.pop()) {
                    NavigationFlow.Next -> viewHolder.navController.navigate(R.id.action_welcomeFragment_to_setupHostFragment)
                    NavigationFlow.Back -> viewHolder.navController.navigateUp()
                    else -> {
                    }
                }
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

        viewHolder = ViewHolder(view).apply {
            continueButton.setOnClickListener {
                viewModel.onContinueClicked()
            }
        }

        return view
    }

    private class ViewHolder(private val view: View) {
        val continueButton: Button = view.findViewById(R.id.btn_continue)

        val navController: NavController
            get() = view.findNavController()
    }

    companion object {
        fun newInstance() = WelcomeFragment()
    }
}
