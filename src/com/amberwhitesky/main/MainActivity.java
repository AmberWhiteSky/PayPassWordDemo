package com.amberwhitesky.main;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.amberwhitesky.dialog.CustomDialog;
import com.amberwhitesky.dialog.CustomDialog.InputDialogListener;
import com.amberwhitesky.paypassworddemo.R;

/**
 * 
 * @author AmberWhiteSky
 * @date  2015/6/3
 * 只是整合了一些资源而已
 * 用EditText 是为了形象而已，因为我对焦点和点击都做了监听所以没办法回退删除已经输入的
 * 可以根据自己的需求进行更改，感谢支持。
 *
 */
public class MainActivity extends Activity {

	private EditText pass_edit;
	private String  str_pass;
	
	private  CustomDialog customDialog;
	private InputDialogListener  inputDialogListener;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		init();
	}

	// 初始化
	private void init() {
		pass_edit = (EditText) findViewById(R.id.password_et);
		pass_edit.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
		pass_edit.setOnFocusChangeListener(FocusListener);
		pass_edit.setOnClickListener(ClickListener);

	}

	/**
	 * onFocusListener
	 */
	OnFocusChangeListener FocusListener = new OnFocusChangeListener() {

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			initDialog();
		}
	};
	
	/**
	 * onclicklistener
	 */
	OnClickListener ClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			initDialog();
		}
	};
	
	
	/**
	 * init Dialog
	 */
	private  void initDialog(){
		customDialog = new CustomDialog(MainActivity.this,R.style.mystyle,R.layout.customdialog) ;
		inputDialogListener  = new  InputDialogListener() {
			
			@Override
			public void onOK(String text) {
				//给密码文本框设置密码
				pass_edit.setText(text);
			}
			
		};
		customDialog.setListener(inputDialogListener);
		customDialog.show();
	}
	
}
