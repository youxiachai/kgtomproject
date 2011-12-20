package cn.meitong.tab.query;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.meitong.R;
import cn.meitong.handleparse.ResultHandle;
import cn.meitong.tab.listener.ContentMananger;
import cn.meitong.values.ResultValues;
import cn.meitong.values.TipsValues;

public class PhoneInputActivity extends Activity {

	private boolean isStartSet = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phoneinput);
		mPhoneInput = (EditText) findViewById(R.id.phone_input);
		mDateStart = (TextView) findViewById(R.id.fp_date_start);
		mDateEnd = (TextView) findViewById(R.id.fp_date_end);
		mDateStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("soap", "set date start");
				isStartSet = true;
				showDialog(DATE_DLALOG_ID);
			}
		});

		mDateEnd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("soap", "set date end");
				isStartSet = false;
				showDialog(DATE_DLALOG_ID);
			}
		});

		// 获得当前时间
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		updateDiaplay();
	}

	private int startDay, startMonth, startYear;

	private boolean checkEndDate(int year, int month, int day) {

		String startDate = mDateStart.getText().toString();

		String endDate = year + "-" + (month + 1) + "-" + day;
		Log.d("soap", startDate + "---" + endDate);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// long day=(startC.getTime()-endC.getTime())/(24*60*60*1000);
		try {

			Date start = df.parse(startDate);
			Date end = df.parse(endDate);
			Long d = (end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000);
			Log.d("soap", "相隔天数" + d);
			if (d > 60) {

				return false;
			} else {
				return true;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private void updateDiaplay() {
		if (isStartSet) {
			startDay = mYear;
			startMonth = mMonth;
			startYear = mYear;
			Log.d("soap", "" + startDay + "---" + startMonth + "---"
					+ startYear);
			mDateStart.setText(new StringBuffer().append(mYear).append("-")
					.append(mMonth + 1).append("-").append(mDay));
		} else {
			if (checkEndDate(mYear, mMonth, mDay)) {
				mDateEnd.setText(new StringBuffer().append(mYear).append("-")
						.append(mMonth + 1).append("-").append(mDay));
			} else {
				ContentMananger.haveTips(this, TipsValues.dateError);

			}
		}
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDiaplay();
		}
	};

	private int mYear;
	private int mMonth;
	private int mDay;
	private EditText mPhoneInput;
	private ProgressDialog dialog;
	private TextView mDateStart;
	private TextView mDateEnd;
	private static final int DATE_DLALOG_ID = 1;

	public void sendToQuery() {
		String phoneno = mPhoneInput.getText().toString();
		String kprqq = mDateStart.getText().toString();
		String kprqz = mDateEnd.getText().toString();
		Log.d("soap", "开票日期--->" + kprqz);
		Log.d("soap", "phoneno" + phoneno.length());

		if (phoneno.length() == 11) {
			// 开票日期止
			if (kprqz.equals("")) {
				ContentMananger.haveTips(this, TipsValues.kprqzError);

			} else {
				dialog = ContentMananger.haveProgressTips(this,
						TipsValues.phoneProgress);
				String queryValues = phoneno + "," + kprqq + "," + kprqz;
				String message = ContentMananger.queryValues(
						ResultValues.QueryType.phoneNumber, queryValues, this);
				Log.d("soap", "" + message);
				ResultHandle rh = new ResultHandle(this, message, true, dialog);
			}
		} else {
			ContentMananger.haveTips(this, TipsValues.phoneNoError);
		}

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case DATE_DLALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		default:
			break;
		}
		return super.onCreateDialog(id);
	}
}
