package com.insta360.bvaandroid.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AlertDialog;

import com.insta360.bvaandroid.BvaDemo.Detector;
import com.insta360.bvaandroid.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class BvaUtils {
    // Bva可用的设备信息，与底层C中的Device类一一对应
    public enum Device{
        MNN_CPU(0),
        /*Android / Common Device GPU API*/
        MNN_OPENCL(1),
        MNN_OPENGL(2),
        MNN_VULKAN(3);
        private int value;
        Device(int value){
            this.value = value;
        }
        public int getValue(){
            return this.value;
        }
    }

    public static final int USE_PHOTO = 1001;    // 从相册中选图的request_code
    public static final int TAKE_PICTURE = 1002; // 拍照并返回图像的request_code
    public static File storageDir;               // 存储模型文件等的手机路径，已在MainActivity中初始化

    /**
     * 此方法用于将assets目录下的文件存储到手机中，且保证只存储一次，不冲突
     * @param activity 调用者
     * @param fileName 要保存的文件
     */
    public static void copyAssetsFile(Activity activity, String fileName) {
        if(storageDir != null){
            // 如果文件已经被存储过一次，那么不再存储此文件，直接返回
            File[] files = storageDir.listFiles();
            if(files != null){
                for (File file : files) {
                    String name = file.getName();
                    if (name.equals(fileName)) return;
                }
            }

            try {
                File storeFile = new File(storageDir,fileName);
                InputStream is = activity.getAssets().open(fileName);
                //getAssets().
                FileOutputStream fos = new FileOutputStream(storeFile);
                byte[] buffer = new byte[1024];
                int byteCount;
                while((byteCount = is.read(buffer)) != -1){
                    fos.write(buffer,0,byteCount);
                }
                fos.flush();
                is.close();
                fos.close();
            } catch (IOException e) {
                System.err.println(fileName+" is not in the assets!");
                e.printStackTrace();
            }
        }
    }
}
