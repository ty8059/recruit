package com.admn.recruit.utils;

import android.os.Environment;
import android.util.Log;

import com.admn.recruit.model.common.ResultEntity;

import java.io.File;

public class FileUtil {

    /**
     * 检查文件是否存在
     * @param fileName fileName
     * @return ResultEntity
     */
    public static ResultEntity isFileExist(String fileName) {
        String path = Environment.getExternalStorageDirectory().getPath() + "/blemonitor/" + fileName;
        File file = new File(path);
        Log.d("File_Util", "path为:" + path + "\nparent为" + file.getParentFile().getName());
        // 创建文件夹
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdir())
                return new ResultEntity(false, "文件夹" + file.getParentFile().getName() + "创建失败");
        }
        try {
            // 创建文件
            if (!file.exists()) {
                return new ResultEntity(false, "文件:" + file.getName() + "不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultEntity(true, path, "文件:" + file.getName() + "存在");
    }

}
