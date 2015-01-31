package cn.itcast.mobilesafe.utils;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
/**
 * 判断 服务是否开启
 * @author superboy
 *
 */
public class ServiceStatusUtil {
	/**
	 * 判断服务是否处于运行状态
	 * @param context
	 * @param serviceName
	 * @return
	 */
	public static boolean isServiceRunning(Context context,String serviceName){
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> infos = am.getRunningServices(100);
		for(RunningServiceInfo info : infos){
			if(serviceName.equals(info.service.getClassName())){//service全包名匹配
				return true;
			}
		}
		return false;
	}
}
