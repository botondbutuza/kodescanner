package uk.co.botondbutuza.kodescanner.common.ui

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import uk.co.botondbutuza.kodescanner.KodeScannerApp
import uk.co.botondbutuza.kodescanner.R

abstract class BaseActivity(@LayoutRes private val layoutResId: Int) : AppCompatActivity(), BaseContract.View {

    abstract fun initViews()
    abstract fun teardown()
    abstract fun injectDagger()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDagger()
        setContentView(layoutResId)

        setSupportActionBar(findViewById(R.id.toolbar))
        initViews()
    }

    override fun onDestroy() {
        teardown()
        super.onDestroy()
    }


    // BaseContract.View implementation.

    override fun onError(e: Throwable) {
        e.printStackTrace()

        Snackbar
            .make(findViewById(android.R.id.content), "An error has occurred", Snackbar.LENGTH_INDEFINITE)
            .show()
    }


    // Internal.

    internal fun app(): KodeScannerApp = application as KodeScannerApp
}