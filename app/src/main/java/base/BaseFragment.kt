package base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.bookingagent.Routes
import com.example.bookingagent.di.routes.IRoutesFactory
import com.example.bookingagent.di.viewmodel.IViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel, R : Routes> : DaggerFragment() {

	protected open var TAG: String = "BaseFragment"

	@Inject lateinit var viewModelFactory: IViewModelFactory
	@Inject lateinit var routesFactory: IRoutesFactory

	val navigation: R by lazy {
		@Suppress("UNCHECKED_CAST")
		routesFactory.get(this::class.java) as R
	}

	protected var actionBar: ActionBar? = null

	protected val viewModel: VM by lazy {
		val viewModelClassType = viewModelFactory.getViewModelClassByFragment(this::class)!!

		if (getActivityAsVMOwner())
			ViewModelProviders.of(activity!!, viewModelFactory).get(viewModelClassType) as VM
		else ViewModelProviders.of(this, viewModelFactory).get(viewModelClassType) as VM

	}

	protected fun getActivityAsVMOwner(): Boolean {
		return false
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setObservers()
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val inflated = inflater.inflate(getLayoutId(), container, false)
		setHasOptionsMenu(true)
		return inflated
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initView()

	}

	abstract fun setObservers()

	abstract fun initView()

	@LayoutRes
	abstract fun getLayoutId(): Int

	protected fun setActionBar(toolbar: Toolbar, up: Boolean = false) {
		(activity as AppCompatActivity).setSupportActionBar(toolbar)
		val supportActionBar = (activity as AppCompatActivity).supportActionBar
		actionBar = supportActionBar
		Navigation.findNavController(activity!!, com.example.bookingagent.R.id.nav_host_fragment).run {
			NavigationUI.setupActionBarWithNavController(activity!! as AppCompatActivity,this)
		}
	}

	protected fun setActionBarTitle(title: String) {
		actionBar?.title = title
	}

}