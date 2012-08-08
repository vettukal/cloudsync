package org.openintents.cloudsync.service;

import org.openintents.cloudsync.Util;
import org.openintents.cloudsync.util.Ulg;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class StartUpService extends Service {

	private static final boolean debug = true;
	private static final String TAG = "StartUpService";

	@Override
	public IBinder onBind(Intent intent) {

		return null;

	}

	@Override
	public void onCreate() {
		super.onCreate();


		Toast.makeText(this, "Service Created Vincent", Toast.LENGTH_LONG).show();
		if (debug) Log.d(TAG,"startup service onCreate:-> ");

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		Toast.makeText(this, "Service Destroyed Vincent", Toast.LENGTH_LONG).show();
		

	}

	@Override
	public void onStart(Intent intent, int startId) {

		super.onStart(intent, startId);
		Toast.makeText(this, "Service Started Vincent", Toast.LENGTH_LONG).show();
		if (debug) Log.d(TAG,"The startup service onStart():-> ");
		
		startServiceSync();

	}

	private void startServiceSync() {
		if(Util.isNetworkAvailable(this)) {
			SharedPreferences prefs = Util.getSharedPreferences(this);
			SharedPreferences.Editor editor = prefs.edit();
			boolean inSync = prefs.getBoolean(Util.IN_SYNC, false);
			long nowTime = System.currentTimeMillis();
			long lastTime = prefs.getLong(Util.LAST_TIME, 0);
			if ((nowTime - lastTime) > 120000) {
				editor.putLong(Util.LAST_TIME, nowTime);
				editor.putBoolean(Util.IN_SYNC, true);
				Log.d("vincent", "Do the sync baccha!!");
				editor.commit();
				AsyncDetectChange adc = new AsyncDetectChange(this);
				adc.execute();
			}
			
		} else {
			if (debug) Log.d(TAG,"The network is not presently available:-> ");
		}
	}
}