package com.example.mysussrx.features

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.example.mysussrx.R
import com.example.mysussrx.model.ResultObserver
import com.example.mysussrx.model.ShellModelK
import com.example.mysussrx.model.ShellResult
import com.example.mysussrx.utils.Logger
import io.reactivex.disposables.CompositeDisposable
import java.util.Arrays.asList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mDisposable = CompositeDisposable()

        findViewById<Button>(R.id.btn_su).setOnClickListener({
            mDisposable.add(ShellModelK().execShell(asList("su", "/data/sussr/start.sh").toTypedArray(), object : ResultObserver<ShellResult>() {
                override fun onSuccess(t: ShellResult) {
                   Logger.d(t)
                }

                override fun onFailure(e: Throwable) {
                e.stackTrace

                }

            }))

        })
    }
}
