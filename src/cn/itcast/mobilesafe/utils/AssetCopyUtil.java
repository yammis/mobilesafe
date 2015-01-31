package cn.itcast.mobilesafe.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

public class AssetCopyUtil {

	/**
	 * 拷贝资产目录下的文件
	 * 
	 * @param context
	 *            上下文
	 * @param assetfilename
	 *            资产文件名称
	 * @param destPath
	 *            拷贝的路径
	 * @return 成功返回文件对象 失败返回Null
	 */
	public static File copy(Context context, String assetfilename, String destPath) {
		try {
			InputStream is = context.getAssets().open(assetfilename);
			File file = new File(destPath);
			FileOutputStream fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			fos.flush();
			fos.close();
			is.close();
			return file;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
