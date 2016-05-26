package com.hck.yanghua.biaoqian;

import android.app.Application;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by sky on 2015/7/6.
 */
public class App extends Application {

//	protected static App mInstance;
//	private DisplayMetrics displayMetrics = null;
//
//
//	public static App getApp() {
//		if (mInstance != null && mInstance instanceof App) {
//			return (App) mInstance;
//		} else {
//			mInstance = new App();
//			mInstance.onCreate();
//			return (App) mInstance;
//		}
//	}
//
//	@Override
//	public void onCreate() {
//		
//		super.onCreate();
//		mInstance = this;
//		Log.d("hck", "onCreateonCreateonCreate: "+mInstance);
//
//	}
//
//	public float getScreenDensity() {
//		if (this.displayMetrics == null) {
//			setDisplayMetrics(mInstance.getResources().getDisplayMetrics());
//		}
//		return this.displayMetrics.density;
//	}
//
//	public int getScreenHeight() {
//		if (this.displayMetrics == null) {
//			setDisplayMetrics(mInstance.getResources().getDisplayMetrics());
//		}
//		return this.displayMetrics.heightPixels;
//	}
//
//	public int getScreenWidth() {
//		if (this.displayMetrics == null) {
//			setDisplayMetrics(mInstance.getResources().getDisplayMetrics());
//		}
//		return this.displayMetrics.widthPixels;
//	}
//
//	public void setDisplayMetrics(DisplayMetrics DisplayMetrics) {
//		this.displayMetrics = DisplayMetrics;
//	}
//
//	public int dp2px(float f) {
//		return (int) (0.5F + f * mInstance.getScreenDensity());
//	}
//
//	public int px2dp(float pxValue) {
//		return (int) (pxValue / mInstance.getScreenDensity() + 0.5f);
//	}
//
//	// 获取应用的data/data/....File目录
//	public String getFilesDirPath() {
//		return getFilesDir().getAbsolutePath();
//	}
//
//	// 获取应用的data/data/....Cache目录
//	public String getCacheDirPath() {
//		return getCacheDir().getAbsolutePath();
//	}

}
