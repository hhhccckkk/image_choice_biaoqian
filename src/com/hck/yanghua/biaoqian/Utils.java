package com.hck.yanghua.biaoqian;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.DisplayMetrics;

public class Utils {
	private static DisplayMetrics displayMetrics = null;
	private static Context context;

	public static void init(Context context) {
		Utils.context = context;
	}

	public static float getScreenDensity() {
		if (displayMetrics == null) {
			setDisplayMetrics(context.getResources().getDisplayMetrics());
		}
		return displayMetrics.density;
	}

	public static int getScreenHeight() {
		if (displayMetrics == null) {
			setDisplayMetrics(context.getResources().getDisplayMetrics());
		}
		return displayMetrics.heightPixels;
	}

	public static int getScreenWidth() {
		if (displayMetrics == null) {
			setDisplayMetrics(context.getResources().getDisplayMetrics());
		}
		return displayMetrics.widthPixels;
	}

	public static void setDisplayMetrics(DisplayMetrics DisplayMetrics) {
		displayMetrics = DisplayMetrics;
	}

	public static int dp2px(float f) {
		return (int) (0.5F + f * getScreenDensity());
	}

	public static int px2dp(float pxValue) {
		return (int) (pxValue / getScreenDensity() + 0.5f);
	}

	// 获取应用的data/data/....File目录
	public static String getFilesDirPath() {
		return context.getFilesDir().getAbsolutePath();
	}

	// 获取应用的data/data/....Cache目录
	public static String getCacheDirPath() {
		return context.getCacheDir().getAbsolutePath();
	}

	public static Resources getResources() {

		return context.getResources();
	}

	public static Bitmap onCompositeImages(Bitmap bitmap, Bitmap bitmap2) {
		Bitmap bmp = null;

		bmp = Bitmap.createBitmap(bitmap2.getWidth(), bitmap2.getHeight(),
				bitmap2.getConfig());
		final Paint paint = new Paint();
		final Canvas canvas = new Canvas(bmp);
		canvas.drawBitmap(bitmap2, 0, 0, paint);

		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
		canvas.drawBitmap(bitmap, 0, 0, paint);

		return bmp;
	}

}
