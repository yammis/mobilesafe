package cn.itcast.mobilesafe.test;

import android.content.ContentValues;
import android.net.Uri;
import android.test.AndroidTestCase;

public class TestRestoreSms extends AndroidTestCase {

	public void testRestoreSms() throws Exception {
		Uri uri = Uri.parse("content://sms");
		// 解析xml文件 获取数据

		for (int i = 0; i < 2000; i++) {
			ContentValues values = new ContentValues();
			values.put("address", "110"+i);
			values.put("body", "搬起小板凳去报到");
			values.put("date", "1353381881136");
			values.put("type", "1");
			getContext().getContentResolver().insert(uri, values);
		}
	}
}
