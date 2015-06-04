package com.amberwhitesky.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.amberwhitesky.paypassworddemo.R;
import com.amberwhitesky.pwd.GridPasswordView;
import com.amberwhitesky.pwd.GridPasswordView.OnPasswordChangedListener;

public class CustomDialog extends Dialog implements OnClickListener {

	private GridPasswordView gridpassword;
	public static PopupWindow pop;
	private int[] a;
	public static boolean falg = false;
	private String passwordStr = "";

	int layoutRes;
	Context context;
	private Button confirmBtn;
	private Button cancelBtn;

	private InputDialogListener mDialogListener;

	public interface InputDialogListener {
		void onOK(String text);
	}

	public void setListener(InputDialogListener inputDialogListener) {
		this.mDialogListener = inputDialogListener;
	}

	public CustomDialog(Context context) {
		super(context);
		this.context = context;
	}

	public CustomDialog(Context context, int resLayout) {
		super(context);
		this.context = context;
		this.layoutRes = resLayout;
	}

	public CustomDialog(Context context, int theme, int resLayout) {
		super(context, theme);
		this.context = context;
		this.layoutRes = resLayout;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
						| WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		this.setContentView(layoutRes);

		gridpassword = (GridPasswordView) findViewById(R.id.password);
		gridpassword.setOnPasswordChangedListener(passlistener);

		confirmBtn = (Button) findViewById(R.id.confirm_btn);
		cancelBtn = (Button) findViewById(R.id.cancel_btn);

		cancelBtn.setTextColor(0xff000000);
		// 判断密码长度是否满足6位， 如果不满足 确定按钮不能点击，文字颜色变灰色
		if (passwordStr.length() != 6) {
			confirmBtn.setEnabled(false);
			confirmBtn.setTextColor(Color.GRAY);
		}
		// 确定按钮点击事件
		confirmBtn.setOnClickListener(this);

		// 取消按钮点击事件
		cancelBtn.setOnClickListener(this);
		//
		gridpassword.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int view_id = v.getId();
		switch (view_id) {
		case R.id.confirm_btn:
			if (mDialogListener != null) {
				mDialogListener.onOK(passwordStr);
				dismiss();
			}
			break;
		case R.id.cancel_btn:
			dismiss();
			break;
		default:
			break;
		}
	}

	/**
	 * 监听输入的密码
	 */
	OnPasswordChangedListener passlistener = new OnPasswordChangedListener() {

		// 密码
		@Override
		public void onMaxLength(String psw) {
			// 获取密码
			passwordStr = psw;
		}

		// 密码长度
		@Override
		public void onChanged(String psw) {
			if (psw.length() != 6) {
				confirmBtn.setEnabled(false);
				confirmBtn.setTextColor(Color.GRAY);
			} else {
				confirmBtn.setEnabled(true);
				confirmBtn.setTextColor(0xffffffff);
			}
		}

	};
}