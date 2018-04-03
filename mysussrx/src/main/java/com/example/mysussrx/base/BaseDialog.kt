package com.example.mysussrx.base

import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.os.Bundle
import android.view.*
import com.example.mysussrx.R


/**
 * Created by hzg on 2018/2/8.
 *
 */
abstract class BaseDialog : DialogFragment() {
    lateinit var mContext: Context
    var isFullScreen = false
    var mView: View? = null
    var mTheme = android.R.style.Theme_Holo_Light_Dialog_MinWidth
    var mGravity = Gravity.NO_GRAVITY
    var mWidth = WindowManager.LayoutParams.WRAP_CONTENT //宽比例(0-1]
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (getLayoutResId() == 0) {
            return super.onCreateView(inflater, container, savedInstanceState)
        } else {
            mView = inflater!!.inflate(getLayoutResId(), container, false)
            return mView!!
        }

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        if (!isFullScreen) {
            dialog.window.setLayout(mWidth, WindowManager.LayoutParams.WRAP_CONTENT)
        }
        init()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        initDialog()
        if (isFullScreen) {
            mTheme = R.style.dialog_full_screen;
        }
        val dialog = Dialog(mContext, mTheme)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.window.setGravity(mGravity);
        return dialog;

    }

    protected abstract fun init()
    protected fun initDialog() {}
    protected fun getLayoutResId(): Int {
        return 0
    }
}