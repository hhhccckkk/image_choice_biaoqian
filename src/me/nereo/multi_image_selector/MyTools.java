package me.nereo.multi_image_selector;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Environment;

public class MyTools {
	public static Bitmap getSmallBitmap(String filePath) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		options.inSampleSize = calculateInSampleSize(options, 480, 800);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filePath, options);
	}

	public static Bitmap getBitmap(String filePath) {
		return BitmapFactory.decodeFile(filePath);
	}

	public static Bitmap mergeBitmap(Bitmap firstBitmap, Bitmap secondBitmap) {
		Bitmap bitmap = Bitmap.createBitmap(firstBitmap.getWidth(),
				firstBitmap.getHeight(), firstBitmap.getConfig());
		Canvas canvas = new Canvas(bitmap);
		canvas.drawBitmap(firstBitmap, new Matrix(), null);
		canvas.drawBitmap(secondBitmap, 0, 0, null);
		return bitmap;
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	public static String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
		} else {
			return null;
		}
		return sdDir.toString();

	}

	/**
	 * 将传进的图片存储在手机上,并返回存储路径
	 * 
	 * @param photo
	 *            Bitmap 类型,传进的图片
	 * @return String 返回存储路径
	 */
	public static String savePic(Bitmap photo, String name, String path) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); // 创建一个
																	// outputstream
																	// 来读取文件流
		photo.compress(Bitmap.CompressFormat.PNG, 100, baos); // 把 bitmap 的图片转换成
																// jpge
																// 的格式放入输出流中
		byte[] byteArray = baos.toByteArray();
		String saveDir = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		Environment.getRootDirectory();
		File dir = new File(saveDir + "/" + path); // 定义一个路径
		if (!dir.exists()) { // 如果路径不存在,创建路径
			dir.mkdirs();
		}
		File file = new File(dir, name); // 定义一个文件
		if (file.exists())
			file.delete(); // 删除原来有此名字文件,删除
		try {
			file.createNewFile();
			FileOutputStream fos;
			fos = new FileOutputStream(file); // 通过 FileOutputStream 关联文件
			BufferedOutputStream bos = new BufferedOutputStream(fos); // 通过 bos
																		// 往文件里写东西
			bos.write(byteArray);
			bos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file.getPath();
	}
}
