package com.test.homego.common

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.test.homego.R

object ConnectionCheck {
    fun checkPermissions(activity : AppCompatActivity) : Boolean {
        var hasPermissions = true
        val permissions = activity.packageManager.getPackageInfo(activity.packageName, PackageManager.GET_PERMISSIONS)
            .requestedPermissions

        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                hasPermissions = false
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                    InfoFragmentDialog(R.string.need_permissions)
                        .show(activity.supportFragmentManager, activity.getString(R.string.warning))
                } else {
                    ActivityCompat.requestPermissions(activity, permissions, Constants.REQUEST_PERMISSION)
                }
            }
        }

        if (hasPermissions) {
            return checkInternet(activity)
        }
        return false

    }

    fun checkInternet(activity : AppCompatActivity) : Boolean {
        val cm = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if (isConnected) {
            return true
        }

        InfoFragmentDialog(R.string.need_internet)
            .show(activity.supportFragmentManager, activity.getString(R.string.warning))
        return false
    }
}