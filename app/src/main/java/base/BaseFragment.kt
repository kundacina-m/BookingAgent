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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel> : DaggerFragment() {

	protected open var TAG: String = "BaseFragment"

	@Inject lateinit var viewModelFactory: ViewModelProvider.Factory

	protected var actionBar: ActionBar? = null

	protected val viewModel: VM by lazy {
		ViewModelProviders.of(this, viewModelFactory).get(getClassTypeVM())
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

	abstract fun initView()

	@LayoutRes
	abstract fun getLayoutId(): Int

	abstract fun getClassTypeVM(): Class<VM>

	protected fun setActionBar(toolbar: Toolbar, up: Boolean = false) {
		(activity as AppCompatActivity).setSupportActionBar(toolbar)
		val supportActionBar = (activity as AppCompatActivity).supportActionBar
		supportActionBar?.setDisplayHomeAsUpEnabled(up)
		actionBar = supportActionBar
	}

	protected fun setActionBarTitle(title: String) {
		actionBar?.title = title
	}

}