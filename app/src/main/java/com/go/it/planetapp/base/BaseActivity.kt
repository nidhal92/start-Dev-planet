package com.go.it.planetapp.base


import android.os.Bundle


import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity<D : ViewDataBinding> : AppCompatActivity() {
    private  var dataBinding: D? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        setViewModel()
        init()


    }

    abstract fun setViewModel()

    abstract fun init()



    @LayoutRes
    abstract fun getLayoutId(): Int
fun getDataBinding():D?{
    return dataBinding
}





}