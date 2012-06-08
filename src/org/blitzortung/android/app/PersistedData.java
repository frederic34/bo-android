package org.blitzortung.android.app;

import org.blitzortung.android.alarm.AlarmManager;
import org.blitzortung.android.data.DataRetriever;
import org.blitzortung.android.map.overlay.StrokesOverlay;
import org.blitzortung.android.map.overlay.color.StrokeColorHandler;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.location.LocationManager;


public class PersistedData {

	private TimerTask timerTask;
	
	private DataRetriever provider;
	
	private AlarmManager alarmManager;
	
	private StrokesOverlay strokesOverlay;
	
	public PersistedData(Resources resources, LocationManager locationManager, SharedPreferences sharedPreferences) {
		provider = new DataRetriever(sharedPreferences);
		timerTask = new TimerTask(resources, sharedPreferences, provider);
		alarmManager = new AlarmManager(locationManager, sharedPreferences, timerTask);
		strokesOverlay = new StrokesOverlay(new StrokeColorHandler(sharedPreferences));
	}

	public StrokesOverlay getStrokesOverlay() {
		return strokesOverlay;
	}

	public TimerTask getTimerTask() {
		return timerTask;
	}

	public DataRetriever getProvider() {
		return provider;
	}

	public AlarmManager getAlarmManager() {
		return alarmManager;
	}
	
}
