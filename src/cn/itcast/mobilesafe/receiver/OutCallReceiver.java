package cn.itcast.mobilesafe.receiver;

import cn.itcast.mobilesafe.LostFindActivity;
import cn.itcast.mobilesafe.db.dao.AddressDao;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class OutCallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String number = getResultData();
		if("20182018".equals(number)){
			Intent lostfindIntent = new Intent(context,LostFindActivity.class);
			lostfindIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//必须增加这个标识
			context.startActivity(lostfindIntent);
			//abortBroadcast();
			setResultData(null);//拨打电话 为有序广播
			return;
		}
		//需求 是让广播接收者 和 服务关联起来,如果归属地服务开启,执行广播接受者的代码
		//Toast.makeText(context, AddressDao.getAddress(number), 1).show();
	}
}
