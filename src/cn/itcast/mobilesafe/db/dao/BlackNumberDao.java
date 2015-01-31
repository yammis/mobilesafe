package cn.itcast.mobilesafe.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.itcast.mobilesafe.db.BlackNumberDBOpenHelper;
import cn.itcast.mobilesafe.domain.BlackNumberBean;
/**
 * 黑名单 拦截dao,blacknumber.db单表的增删改查
 * @author superboy
 *
 */
public class BlackNumberDao {

	private BlackNumberDBOpenHelper helper;

	public BlackNumberDao(Context context) {
		helper = new BlackNumberDBOpenHelper(context);
	}

	/**
	 * 添加黑名单号码
	 * 
	 * @param number
	 *            黑名单号码
	 * @param mode
	 *            拦截模式
	 */
	public void add(String number, String mode) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("insert into blacknumber (number,mode) values (?,?)",
				new Object[] { number, mode });
		db.close();
	}

	/**
	 * 查询黑名单号码
	 * 
	 * @param number
	 *            黑名单号码
	 * @return
	 */
	public boolean find(String number) {
		boolean result = false;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor curosr = db.rawQuery("select * from blacknumber where number=?",
				new String[] { number });
		if (curosr.moveToNext()) {
			result = true;
		}
		curosr.close();
		db.close();
		return result;
	}
	/**
	 * 查询黑名单号码的拦截模式
	 * 
	 * @param number
	 *            黑名单号码
	 * @return 0 全部拦截
	 *         1 电话拦截
	 *         2 短信拦截
	 *         -1 不拦截 数据库没有记录
	 */
	public String findMode(String number) {
		String mode  = "-1";
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor curosr = db.rawQuery("select mode from blacknumber where number=?",
				new String[] { number });
		if (curosr.moveToNext()) {
			mode = curosr.getString(0);
		}
		curosr.close();
		db.close();
		return mode;
	}
	/**
	 * 数据库的修改 修改拦截模式.
	 * 
	 * @param newmode
	 *            新的拦截模式
	 * @param number
	 *            要修改的黑名单号码
	 */
	public void update(String newmode, String number) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("update blacknumber set mode=? where number=?",
				new Object[] { newmode, number });
		db.close();
	}

	/**
	 * 删除一条黑名单记录
	 * 
	 * @param number
	 */
	public void delete(String number) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from blacknumber where number=?",
				new Object[] { number });
		db.close();
	}

	/**
	 * 查询全部黑名单号码.
	 */
	public List<BlackNumberBean> findAll() {
		ArrayList<BlackNumberBean> blacknumbers = new ArrayList<BlackNumberBean>();
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor curosr = db.rawQuery("select number,mode from blacknumber ",
				new String[] {});
		while (curosr.moveToNext()) {
			String number = curosr.getString(0);
			String mode = curosr.getString(1);
			BlackNumberBean bean = new BlackNumberBean();
			bean.setMode(mode);
			bean.setNumber(number);
			blacknumbers.add(bean);
			bean = null;
		}
		curosr.close();
		db.close();
		return blacknumbers;
	}

	/**
	 * 查询部分黑名单号码.
	 * 
	 * @param startindex
	 *            从第几条数据开始查询
	 * @param maxnum
	 *            最多返回多少个信息
	 * @return
	 */
	public List<BlackNumberBean> findPart(int startindex, int maxnum) {
		ArrayList<BlackNumberBean> blacknumbers = new ArrayList<BlackNumberBean>();
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor curosr = db.rawQuery(
				"select number,mode from blacknumber order by id desc limit ? offset ? ",
				new String[] { String.valueOf(maxnum),
						String.valueOf(startindex) });
		while (curosr.moveToNext()) {
			String number = curosr.getString(0);
			String mode = curosr.getString(1);
			BlackNumberBean bean = new BlackNumberBean();
			bean.setMode(mode);
			bean.setNumber(number);
			blacknumbers.add(bean);
			bean = null;
		}
		curosr.close();
		db.close();
		return blacknumbers;
	}
	/**
	 * 反回一共有多少页的内容
	 * @param maxnumber
	 * @return
	 */
	public int getTotalPage(int maxnumber){
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from blacknumber", null);
		int totalnumber = cursor.getCount();
		cursor.close();
		db.close();
		if(totalnumber%maxnumber==0){
			return totalnumber/maxnumber;
		}else{
			return totalnumber/maxnumber + 1;
		}
		
	}
}
