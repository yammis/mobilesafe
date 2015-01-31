package cn.itcast.mobilesafe.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.ProgressDialog;

public class DownLoadUtil {

	/**
	 * 下载文件
	 * 
	 * @param serverpath
	 *            文件服务器的路径
	 * @param savedfilepath
	 *            在本地保存的路径
	 * @param pd
	 *            进度条对话框
	 * @return 下载成功 返回文件对象,下载失败返回null
	 */
	public static File downLoad(String serverpath, String savedfilepath,
			ProgressDialog pd) {
		try {
			URL url = new URL(serverpath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			if (conn.getResponseCode() == 200) {
				int max = conn.getContentLength();
				pd.setMax(max);//设置进度条最大值
				InputStream is = conn.getInputStream();
				File file = new File(savedfilepath);
				FileOutputStream fos = new FileOutputStream(file);//采用文件字节流操作
				int len = 0;
				byte[] buffer = new byte[1024];
				int total=0;//计数器思想 
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
					total+=len;
					pd.setProgress(total);//下载 时进度条更新
					Thread.sleep(30);//延迟提高，提高cpu效率
				}
				fos.flush();
				fos.close();
				is.close();
				return file;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * 获取文件名称
	 * @param serverurl url地址 
	 * @return  文件名称
	 */
	public static String getFileName(String serverurl){
		return serverurl.substring(serverurl.lastIndexOf("/")+1);
	}
}
