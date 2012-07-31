package org.openintents.cloudsync.service;

import org.openintents.cloudsync.CloudSyncActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StartupIntentReceiver extends BroadcastReceiver {
	private static final boolean debug = true;
	private static final String TAG = "StartupIntentReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (debug) Log.d(TAG,"The broadcast reciever of boot complete recieved:-> ");
		
		Intent serviceIntent = new Intent();
		serviceIntent.setAction("org.openintents.cloudsync.service.StartUpService");
		context.startService(serviceIntent);
	}
}
