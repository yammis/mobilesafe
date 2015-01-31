package cn.itcast.mobilesafe.test;

import java.util.List;
import java.util.Random;

import android.test.AndroidTestCase;
import cn.itcast.mobilesafe.db.BlackNumberDBOpenHelper;
import cn.itcast.mobilesafe.db.dao.BlackNumberDao;
import cn.itcast.mobilesafe.domain.BlackNumberBean;

public class TestBlackNumberDB extends AndroidTestCase {
	public void testCreateDB() throws Exception {
		BlackNumberDBOpenHelper helper = new BlackNumberDBOpenHelper(
				getContext());

		helper.getReadableDatabase();
	}

	public void testAdd() throws Exception {
		BlackNumberDao dao = new BlackNumberDao(getContext());
		Random random = new Random();
		for (int i = 0; i < 200; i++) {
			long number = 13500000000l;
			dao.add(String.valueOf(number+i), random.nextInt(3)+"");
		}
	}

	public void testDelete() throws Exception {
		BlackNumberDao dao = new BlackNumberDao(getContext());
		dao.delete("12345667");
	}

	public void testUpdate() throws Exception {
		BlackNumberDao dao = new BlackNumberDao(getContext());
		dao.update("1", "12345667");
	}

	public void testFind() throws Exception {
		BlackNumberDao dao = new BlackNumberDao(getContext());
		boolean result = dao.find("12345667");
		assertEquals(true, result);
	}

	public void testFindAll() throws Exception {
		BlackNumberDao dao = new BlackNumberDao(getContext());
		List<BlackNumberBean> infos = dao.findAll();
		for (BlackNumberBean bean : infos) {
			System.out.println(bean.toString());
		}
	}
}
