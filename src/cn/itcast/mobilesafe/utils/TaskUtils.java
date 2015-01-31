package cn.itcast.mobilesafe.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.widget.Button;

public class TaskUtils {
	/**
	 * 获取当前手机里面正在运行的进程数目
	 * @param context
	 * @return
	 */
	public static int getRunningProcessCount(Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> infos = am.getRunningAppProcesses();
		return infos.size();
	}
	/**
	 * 获取手机剩余可以用内存
	 * @param context
	 * @return long byte
	 */
	public static long getAvailMem(Context context){
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo outinfo = new MemoryInfo();
		am.getMemoryInfo(outinfo);
		return outinfo.availMem;
	}
	/**
	 * 获取手机的总内存大小.
	 * @return
	 */
	public static long getTotalMem(){
		try {
			File file = new File("/proc/meminfo");
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = br.readLine();
			StringBuffer sb = new StringBuffer();
			for(char c : line.toCharArray()){
				if(c>='0'&&c<='9'){
					sb.append(c);
				}
			}
			return Long.parseLong(sb.toString())*1024;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
}
