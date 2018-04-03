package com.example.mysussrx.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog


/**
 * Created by hzg on 2017/8/25.
 * 权限检测
 */
object PermissionUtis {
    val permissionNeeds = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_WIFI_STATE);
    val REQUEST_PERMISSION_CODE = 1
    fun checkPermission(context: Context): Boolean {
        var isGrant = false
        if (Build.VERSION.SDK_INT < 23) {
            isGrant = true
        } else {
            if (!checkPermissionAllGranted(permissionNeeds)) {
                ActivityCompat.requestPermissions(context as Activity, permissionNeeds, REQUEST_PERMISSION_CODE)
                isGrant = false
            } else {
                isGrant = true
            }
        }
        return isGrant
    }

    private fun checkPermissionAllGranted(permissions: Array<String>): Boolean {
        permissions.forEach {
            if (ContextCompat.checkSelfPermission(Utils.getApp(), it) != PackageManager.PERMISSION_GRANTED)
                return false
        }
        return true
    }

    /**
     * 打开 APP 的详情设置
     */
    fun openAppDetails(context: Activity, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setPositiveButton("去手动授权", DialogInterface.OnClickListener { dialog, which ->
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.data = Uri.parse("package:" + context.packageName)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            context.startActivity(intent)
        })
        builder.setNegativeButton("取消", { dialog, which -> checkPermission(context) })
        builder.setNeutralButton("退出软件", { dialog, which ->

            context.finish()
        })
        builder.show()
    }
}