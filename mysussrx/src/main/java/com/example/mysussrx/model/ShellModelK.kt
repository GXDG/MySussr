package com.example.mysussrx.model

import com.example.mysussrx.utils.ShellUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by hzg on 2018/2/7.
 */
data class ShellResult(var outputMessage: String, var errorMessage: String)

class ShellModelK {


    fun execShell(cmdArray: Array<String>, subscriber: ResultObserver<ShellResult>): Disposable {
        return Observable.create<Array<String>> {
            it.onNext(ShellUtil.execShell(cmdArray, true, true))
        }.map { t ->
            return@map ShellResult(t[0], t[1])
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(subscriber)

    }
}