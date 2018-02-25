package com.kulomady.strukturdata.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.kulomady.strukturdata.util.PermissionUtil


/**
 * @author kulomady on 2/25/18.
 */

open class BaseActivity: AppCompatActivity() {
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {

        PermissionUtil.onRequestPermissionResult(
                this, requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == 200) {
            PermissionUtil.onActivityResult(this, requestCode)
        }

    }
}