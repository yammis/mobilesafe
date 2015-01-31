package cn.itcast.mobilesafe.utils;

import android.os.Handler;

public abstract class AsyncTask2 {
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			//后台任务执行后被调用.
			onPostExecute();
		};
	};
	
	/**
	 * 耗时操作执行之前调用的方法.
	 */
	public abstract void onPreExecute();
	
	/**
	 * 耗时任务执行完毕后调用的方法.
	 */
	public abstract void onPostExecute();
	/**
	 * 执行一个后台的耗时的任务
	 */
	public abstract void doInBackground();
	

	public void execute(){
		onPreExecute();
		new Thread(){
			public void run() {
				doInBackground();
				//通知耗时任务执行完毕了.
				handler.sendEmptyMessage(0);
			};
		}.start();
	}
}
