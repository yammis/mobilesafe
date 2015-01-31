package cn.itcast.mobilesafe.test;

import java.util.List;

import android.test.AndroidTestCase;
import cn.itcast.mobilesafe.domain.AppInfo;
import cn.itcast.mobilesafe.engine.QueryBootupApplication;

public class TestGetAllHomeApp extends AndroidTestCase {
	public void testGet() throws Exception{
		List<AppInfo>  infos = QueryBootupApplication.getBootupApps(getContext());
		for(AppInfo info : infos){
			System.out.println(info.toString());
		}
	}
}
