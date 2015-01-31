package cn.itcast.mobilesafe.utils;

import cn.itcast.mobilesafe.R;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyToast {

	public static void show(Context context ,int icon, String msg) {
		Toast toast = new Toast(context);
		View view = View.inflate(context, R.layout.my_toast, null);
		ImageView iv = (ImageView) view.findViewById(R.id.iv_toast);
		TextView tv = (TextView) view.findViewById(R.id.tv_toast);
		iv.setImageResource(icon);
		tv.setText(msg);
		toast.setView(view);
		toast.setDuration(1);
		//toast.set
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
}
