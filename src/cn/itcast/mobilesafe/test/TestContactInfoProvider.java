package cn.itcast.mobilesafe.test;

import java.util.List;

import android.test.AndroidTestCase;
import cn.itcast.mobilesafe.domain.ContactInfo;
import cn.itcast.mobilesafe.engine.ContactInfoProvider;

public class TestContactInfoProvider extends AndroidTestCase {

	public void testReadContacts() throws Exception{
		List<ContactInfo>  infos = ContactInfoProvider.getContactInfos(getContext());
		for(ContactInfo info: infos){
			System.out.println(info.toString());
		}
	}
}
