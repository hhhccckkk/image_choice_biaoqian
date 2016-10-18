package me.nereo.multi_image_selector;

import java.io.File;
import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hck.yanghua.biaoqian.HandleImageActivity;
import com.hck.yanghua.biaoqian.ImageData;
import com.hck.yanghua.biaoqian.Utils;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * 多图选择 Created by Nereo on 2015/4/7.
 */
public class MultiImageSelectorActivity extends FragmentActivity implements
		MultiImageSelectorFragment.Callback {

	/** 最大图片选择次数，int类型，默认9 */
	public static final String EXTRA_SELECT_COUNT = "max_select_count";
	/** 图片选择模式，默认多选 */
	public static final String EXTRA_SELECT_MODE = "select_count_mode";
	/** 是否显示相机，默认显示 */
	public static final String EXTRA_SHOW_CAMERA = "show_camera";
	/** 选择结果，返回为 ArrayList&lt;String&gt; 图片路径集合 */
	public static final String EXTRA_RESULT = "select_result";
	/** 默认选择集 */
	public static final String EXTRA_DEFAULT_SELECTED_LIST = "default_list";

	/** 单选 */
	public static final int MODE_SINGLE = 0;
	/** 多选 */
	public static final int MODE_MULTI = 1;

	private ArrayList<String> resultList = new ArrayList<String>();
	private Button mSubmitButton;
	private int mDefaultCount;
	public static MultiImageSelectorActivity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = this;
		setContentView(R.layout.activity_default);
		Utils.init(this);
		initImageLoader();
		Intent intent = getIntent();
		mDefaultCount = intent.getIntExtra(EXTRA_SELECT_COUNT, 9);
		int mode = intent.getIntExtra(EXTRA_SELECT_MODE, MODE_MULTI);
		boolean isShow = intent.getBooleanExtra(EXTRA_SHOW_CAMERA, true);
		if (mode == MODE_MULTI && intent.hasExtra(EXTRA_DEFAULT_SELECTED_LIST)) {
			resultList = intent
					.getStringArrayListExtra(EXTRA_DEFAULT_SELECTED_LIST);
		}

		Bundle bundle = new Bundle();
		bundle.putInt(MultiImageSelectorFragment.EXTRA_SELECT_COUNT,
				mDefaultCount);
		bundle.putInt(MultiImageSelectorFragment.EXTRA_SELECT_MODE, mode);
		bundle.putBoolean(MultiImageSelectorFragment.EXTRA_SHOW_CAMERA, isShow);
		bundle.putStringArrayList(
				MultiImageSelectorFragment.EXTRA_DEFAULT_SELECTED_LIST,
				resultList);

		getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.image_grid,
						Fragment.instantiate(this,
								MultiImageSelectorFragment.class.getName(),
								bundle)).commit();

		// 返回按钮
		findViewById(R.id.btn_back).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						setResult(RESULT_CANCELED);
						finish();
					}
				});

		// 完成按钮
		mSubmitButton = (Button) findViewById(R.id.commit);
		if (resultList == null || resultList.size() <= 0) {
			mSubmitButton.setText("完成");
			mSubmitButton.setEnabled(false);
		} else {
			mSubmitButton.setText("完成(" + resultList.size() + "/"
					+ mDefaultCount + ")");
			mSubmitButton.setEnabled(true);
		}
		mSubmitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (resultList != null && resultList.size() > 0) {
					// 返回已选择的图片数据
					Intent data = new Intent();
					data.putStringArrayListExtra(EXTRA_RESULT, resultList);
					setResult(RESULT_OK, data);
					finish();
				}
			}
		});
	}

	public void sendImge() {
		if (ImageData.imagePath != null) {
			Log.d("hck", "onDestroy>>>>>>>>>>>>>>>>>>>>>>>>onDestroy"
					+ ImageData.imagePath);
			Intent data = new Intent();
			data.putExtra("img", ImageData.imagePath);
			setResult(2, data);
			ImageData.imagePath = null;
			finish();
		}
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();

	}

	@Override
	public void onSingleImageSelected(String path) {
		Intent intent = new Intent();
		intent.putExtra("img", path);
		intent.setClass(this, HandleImageActivity.class);
		startActivity(intent);
	}

	@Override
	public void onImageSelected(String path) {
		if (!resultList.contains(path)) {
			resultList.add(path);
		}
		// 有图片之后，改变按钮状态
		if (resultList.size() > 0) {
			mSubmitButton.setText("完成(" + resultList.size() + "/"
					+ mDefaultCount + ")");
			if (!mSubmitButton.isEnabled()) {
				mSubmitButton.setEnabled(true);
			}
		}
	}

	@Override
	public void onImageUnselected(String path) {
		if (resultList.contains(path)) {
			resultList.remove(path);
			mSubmitButton.setText("完成(" + resultList.size() + "/"
					+ mDefaultCount + ")");
		} else {
			mSubmitButton.setText("完成(" + resultList.size() + "/"
					+ mDefaultCount + ")");
		}
		// 当为选择图片时候的状态
		if (resultList.size() == 0) {
			mSubmitButton.setText("完成");
			mSubmitButton.setEnabled(false);
		}
	}

	@Override
	public void onCameraShot(File imageFile) {
		if (imageFile != null) {
			Intent data = new Intent();
			resultList.add(imageFile.getAbsolutePath());
			data.putStringArrayListExtra(EXTRA_RESULT, resultList);
			setResult(RESULT_OK, data);
			sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
					Uri.fromFile(imageFile)));// 刷新系统相册
			finish();
		}
	}

	private void initImageLoader() {
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheInMemory(false).imageScaleType(ImageScaleType.EXACTLY)
				.cacheOnDisc(true).build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this).threadPriority(Thread.NORM_PRIORITY - 2)
				.defaultDisplayImageOptions(defaultOptions)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new LruMemoryCache(2 * 1024 * 1024))
				.memoryCacheSize(2 * 1024 * 1024).threadPoolSize(3).build();
		ImageLoader.getInstance().init(config);
	}

}
