package me.nereo.multi_image_selector.utils;

import me.nereo.multi_image_selector.MyTools;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class SaveImageUtil extends AsyncTask<Void, Void, String> {
	private OnSaveImageUtilListener listener;
	public interface OnSaveImageUtilListener{
		void saveImageUtilFinish(String path);
	}
	private static final String YANG_HUA = "yanghua";
	private Context context;
	private Bitmap bitmap;
	private String imageName;
	public static final String YANG_HUA_PATH = "/yanghua/yanghuaimg";

	public SaveImageUtil(Context context, Bitmap bitmap, String imageName,OnSaveImageUtilListener listener) {
		this.context = context;
		this.bitmap = bitmap;
		this.imageName = imageName;
		this.listener=listener;
	}

	@Override
	protected String doInBackground(Void... params) {
		String pathString=null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		if (!sdCardExist) {
			Toast.makeText(context, "sdcard不可用", Toast.LENGTH_SHORT).show();
		} else {
			pathString= MyTools.savePic(bitmap, YANG_HUA + imageName,
					YANG_HUA_PATH);
		}
		return pathString;
	}

	@Override
	protected void onPostExecute(String result) {
		Log.d("hck", "保存成功");
		Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
		listener.saveImageUtilFinish(result);
		if (bitmap != null) {
			bitmap.recycle();
			bitmap = null;
		}
		//context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri
		//		.parse("file://" + Environment.getExternalStorageDirectory())));
		System.gc();
	}

}
