package cn.itcast.mobilesafe.receiver;

import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import cn.itcast.mobilesafe.service.UpdateWidgetService;

public class MyWidget extends AppWidgetProvider {

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		Intent intent = new Intent(context,UpdateWidgetService.class);
		context.startService(intent);
	}

	@Override
	public void onDisabled(Context context) {
		Intent intent = new Intent(context,UpdateWidgetService.class);
		context.stopService(intent);
		super.onDisabled(context);
	}

}
