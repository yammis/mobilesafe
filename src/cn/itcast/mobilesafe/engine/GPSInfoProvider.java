package cn.itcast.mobilesafe.engine;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
/**
 * 获取手机所在位置 
 * @author superboy
 *
 */
public class GPSInfoProvider {
	private GPSInfoProvider() {
	};

	private static GPSInfoProvider mGpsInfoProvider;
	private static LocationManager lm;
	private static MyLocationLinstener listener;
	private static SharedPreferences sp;
/**
 * 获取GPSInfoProvider对象
 * @param context
 * @return
 */
	public static synchronized GPSInfoProvider getInstance(Context context) {
		if (mGpsInfoProvider == null) {
			lm = (LocationManager) context
					.getSystemService(Context.LOCATION_SERVICE);
			Criteria criteria = new Criteria();
			// 获取位置精确度得要求
			criteria.setAccuracy(Criteria.ACCURACY_FINE);
			// 关心海拔
			criteria.setAltitudeRequired(true);
			// 是否可能产生开销  流量 消费
			criteria.setCostAllowed(true);
			criteria.setPowerRequirement(Criteria.POWER_HIGH);//费电高
			criteria.setSpeedRequired(true);//移动速度
			String provider = lm.getBestProvider(criteria, true);//网络 基站 gps定位
			System.out.println(provider);//定位以上哪种 方式最好
			sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
			mGpsInfoProvider = new GPSInfoProvider();
			
			listener = mGpsInfoProvider.new MyLocationLinstener();
			lm.requestLocationUpdates(provider, 0, 0, listener);
		}
		return mGpsInfoProvider;

	}

	public String getAddress(){
		return sp.getString("lastaddress", "");
	}
	
	private class MyLocationLinstener implements LocationListener {

		/**
		 * 当手机的位置发生改变的时候 调用的方法.
		 */
		public void onLocationChanged(Location location) {//location包装了当前用户的位置 
			// 美国卫星. 标准的经度和纬度. -> 火星坐标
			double longtitude = location.getLongitude();
			double latitude = location.getLatitude();
			double accuracy = location.getAccuracy();
			PointDouble s2c=null;
			//将标准  经纬  转换
//			try {
//				ModifyOffset instance = ModifyOffset.getInstance(this.getClass().getClassLoader().getResourceAsStream("axisoffset.dat"));
//				PointDouble pt = new PointDouble(longtitude, latitude);
//				 s2c = instance.s2c(pt);
//			} catch (Exception e) {
//			}
//			
//			String lastaddress = (s2c+ "\na:" + accuracy);
			String lastaddress = ("j:" + longtitude + "\nw:" + latitude//经度  、纬度
					+ "\na:" + accuracy);
			Editor editor = sp.edit();
			editor.putString("lastaddress", lastaddress);
			editor.commit();
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

		public void onProviderEnabled(String provider) {

		}

		public void onProviderDisabled(String provider) {

		}
	}
}
