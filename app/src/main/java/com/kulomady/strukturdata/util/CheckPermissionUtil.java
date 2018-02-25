package com.kulomady.strukturdata.util;

import android.Manifest;
import android.app.Activity;


/**
 * @author kulomady on 2/25/18.
 */

public class CheckPermissionUtil {

    private static final int WRITE_SD_REQ_CODE = 201;
    public static void checkWriteSdCard(Activity activity,
                                        PermissionUtil.PermissionCallback callback) {
        PermissionUtil.checkPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                WRITE_SD_REQ_CODE,
                "Aplikasi ini membutuhkan akses ke folder Download untuk menyimpan data .csv",
                "Maaf anda tidak dapat menggunakan aplikasi ini tanpa mengizinkan akses ke folder download",
                callback);
    }
}
