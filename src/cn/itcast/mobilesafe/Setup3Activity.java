package cn.itcast.mobilesafe;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
/**
 * �ֻ�����������3
 * @author superboy
 *
 */
public class Setup3Activity extends BaseSetupActivity {
	private EditText et_setup3_number;

	@Override
	public void findView() {
		setContentView(R.layout.activity_setup3);
		et_setup3_number = (EditText) findViewById(R.id.et_setup3_number);
	}

	@Override
	public void setupView() {
		String safenubmer = sp.getString("safenumber", "");
		et_setup3_number.setText(safenubmer);
	}

	@Override
	public void showNext() {
		String safenumber = et_setup3_number.getText().toString().trim();
		
		if(TextUtils.isEmpty(safenumber)){
			Toast.makeText(this, "��ȫ���벻��Ϊ��", 0).show();
			return;
		}
		
		Editor editor = sp.edit();
		editor.putString("safenumber", safenumber);
		editor.commit();
		
		openActivity(Setup4Activity.class);
		overridePendingTransition(R.anim.tran_shownext_in,
				R.anim.tran_shownext_out);
	}

	@Override
	public void showPre() {
		openActivity(Setup2Activity.class);
		overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
	}

	// ѡ����ϵ��
	public void selectContact(View view) {
		Intent intent = new Intent(this, SelectContactActivity.class);
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data != null) {//��ȡǰһ��activity���ص�ֵ
			String number = data.getStringExtra("number");
			et_setup3_number.setText(number);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}