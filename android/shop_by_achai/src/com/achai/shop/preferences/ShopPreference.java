package com.achai.shop.preferences;

import com.achai.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ShopPreference extends PreferenceActivity {

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.shop_preference);
		}
}
