package cn.itcast.mobilesafe.test;

import java.util.List;

import android.test.AndroidTestCase;
import cn.itcast.mobilesafe.domain.AppInfo;
import cn.itcast.mobilesafe.engine.AppInfoProvider;

public class TestAppInfoProvider extends AndroidTestCase {
	//测试代码把所有异常 抛给测试框架
	public void testGetApp() throws Exception {
		List<AppInfo> appinfos = AppInfoProvider.getAppInfos(getContext());
		for(AppInfo info : appinfos){
			System.out.println(info.toString());
		}
	}
}
