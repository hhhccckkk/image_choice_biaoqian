package com.hck.yanghua.biaoqian;

import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import me.nereo.multi_image_selector.MyTools;
import me.nereo.multi_image_selector.R;
import me.nereo.multi_image_selector.utils.SaveImageUtil;
import me.nereo.multi_image_selector.utils.SaveImageUtil.OnSaveImageUtilListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.imagezoom.ImageViewTouch.OnImageViewTouchSingleTapListener;

public class HandleImageActivity extends Activity {
////	private MyImageViewDrawableOverlay mImageView;
//	private ViewGroup drawArea;
//	private LabelSelector labelSelector;
//	// 小白点标签
//	private CommonTitleBar mTitleBar;
//	private LabelView emptyLabelView;
//	private Bitmap oldBitmap, biaoqianBitmap, newBitmap;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_show_img2);
//		initView();
//		String path = getIntent().getStringExtra("img");
//		oldBitmap = MyTools.getBitmap(path);
//		mImageView.setDrawingCacheEnabled(true);
//		mImageView.setImageBitmap(oldBitmap);
//
//		mImageView.setScaleType(ScaleType.FIT_XY);
//		mImageView.setScaleWithContent(true);
//		drawArea.setDrawingCacheEnabled(true);
//		setListener();
//		initStickerToolBar();
//	}
//
//	private void initView() {
//		mTitleBar = (CommonTitleBar) findViewById(R.id.handleImgTitle);
//		// 初始化空白标签
//		emptyLabelView = new LabelView(this);
//		emptyLabelView.setEmpty();
//		// 添加贴纸水印的画布
//		drawArea = (ViewGroup) findViewById(R.id.drawing_view_container);
//
//		mImageView = (MyImageViewDrawableOverlay) findViewById(R.id.drawable_overlay);
//
//		// 添加标签选择器
//		RelativeLayout.LayoutParams rparams = new RelativeLayout.LayoutParams(
//				RelativeLayout.LayoutParams.MATCH_PARENT,
//				RelativeLayout.LayoutParams.MATCH_PARENT);
//
//		labelSelector = new LabelSelector(this);
//		labelSelector.setLayoutParams(rparams);
//		drawArea.addView(labelSelector);
//		labelSelector.setVisibility(View.GONE);
//
//	}
//
//	public void tagClick(View v) {
//		TextView textView = (TextView) v;
//		TagItem tagItem = new TagItem(AppConstants.POST_TYPE_TAG, textView
//				.getText().toString());
//		addLabel(tagItem);
//	}
//
//	private void setListener() {
//		mTitleBar.setRightBtnOnclickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				savePicter();
//			}
//		});
//	}
//
//	// 初始化贴图
//	private void initStickerToolBar() {
//
//		labelSelector.setTxtClicked(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//
//				EditTextActivity.openTextEdit(HandleImageActivity.this, "", 8,
//						AppConstants.ACTION_EDIT_LABEL);
//
//			}
//		});
//		labelSelector.setAddrClicked(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				EditTextActivity.openTextEdit(HandleImageActivity.this, "", 8,
//						AppConstants.ACTION_EDIT_LABEL_POI);
//			}
//		});
//		labelSelector.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				labelSelector.hide();
//				emptyLabelView.updateLocation(
//						(int) labelSelector.getmLastTouchX(),
//						(int) labelSelector.getmLastTouchY());
//				emptyLabelView.setVisibility(View.VISIBLE);
//
//			}
//		});
//		mImageView.setOnDrawableEventListener(wpEditListener);
//		mImageView
//				.setSingleTapListener(new OnImageViewTouchSingleTapListener() {
//					public void onSingleTapConfirmed() {
//
//						emptyLabelView.updateLocation(
//								(int) mImageView.getmLastMotionScrollX(),
//								(int) mImageView.getmLastMotionScrollY());
//						emptyLabelView.setVisibility(View.VISIBLE);
//
//						labelSelector.showToTop();
//						drawArea.postInvalidate();
//
//					}
//				});
//
//	}
//
//	private MyImageViewDrawableOverlay.OnDrawableEventListener wpEditListener = new MyImageViewDrawableOverlay.OnDrawableEventListener() {
//		@Override
//		public void onMove(MyHighlightView view) {
//		}
//
//		@Override
//		public void onFocusChange(MyHighlightView newFocus,
//				MyHighlightView oldFocus) {
//		}
//
//		@Override
//		public void onDown(MyHighlightView view) {
//
//		}
//
//		@Override
//		public void onClick(MyHighlightView view) {
//			labelSelector.hide();
//		}
//
//		@Override
//		public void onClick(final LabelView label) {
//			if (label.equals(emptyLabelView)) {
//				return;
//			}
//			// alert("温馨提示", "是否需要删除该标签！", "确定",
//			// new DialogInterface.OnClickListener() {
//			// @Override
//			// public void onClick(DialogInterface dialog, int which) {
//			// EffectUtil.removeLabelEditable(mImageView,
//			// drawArea, label);
//			// labels.remove(label);
//			// }
//			// }, "取消", null);
//		}
//	};
//	private List<LabelView> labels = new ArrayList<LabelView>();
//
//	// 添加标签
//	private void addLabel(TagItem tagItem) {
//		labelSelector.hide();
//		emptyLabelView.setVisibility(View.INVISIBLE);
//		if (labels.size() >= 5) {
//			// alert("温馨提示", "您只能添加5个标签！", "确定", null, null, null, true);
//		} else {
//			int left = emptyLabelView.getLeft();
//			int top = emptyLabelView.getTop();
//
//			left = mImageView.getWidth() / 2 - 10;
//			top = mImageView.getWidth() / 2;
//
//			LabelView label = new LabelView(this);
//			label.setDrawingCacheEnabled(true);
//			label.init(tagItem);
//			EffectUtil.addLabelEditable(mImageView, drawArea, label, left, top);
//
//		}
//	}
//
//	public void savePicter() {
//		biaoqianBitmap = mImageView.getDrawingCache();
//		newBitmap = MyTools.mergeBitmap(oldBitmap, newBitmap);
//		new SaveImageUtil(this, newBitmap, System.currentTimeMillis() + ".png",
//				new OnSaveImageUtilListener() {
//
//					@Override
//					public void saveImageUtilFinish(String path) {
//						Log.d("hck", "path: " + path);
//						ImageData.imagePath = path;
//						MultiImageSelectorActivity.activity.sendImge();
//						finish();
//					}
//
//				}).execute();
//
//	}
//
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		labelSelector.hide();
//		super.onActivityResult(requestCode, resultCode, data);
//		if (AppConstants.ACTION_EDIT_LABEL == requestCode && data != null) {
//			String text = data.getStringExtra(AppConstants.PARAM_EDIT_TEXT);
//			if (!TextUtils.isEmpty(text)) {
//				TagItem tagItem = new TagItem(AppConstants.POST_TYPE_TAG, text);
//				addLabel(tagItem);
//			}
//		} else if (AppConstants.ACTION_EDIT_LABEL_POI == requestCode
//				&& data != null) {
//			String text = data.getStringExtra(AppConstants.PARAM_EDIT_TEXT);
//			if (!TextUtils.isEmpty(text)) {
//				TagItem tagItem = new TagItem(AppConstants.POST_TYPE_POI, text);
//				addLabel(tagItem);
//			}
//		}
//	}
//
//	@Override
//	protected void onDestroy() {
//		super.onDestroy();
//		if (oldBitmap != null) {
//			oldBitmap.recycle();
//			oldBitmap = null;
//		}
//		if (biaoqianBitmap != null) {
//			biaoqianBitmap.recycle();
//			biaoqianBitmap = null;
//		}
//		if (newBitmap != null) {
//			newBitmap.recycle();
//			newBitmap = null;
//		}
//		System.gc();
//	}
	private MyImageViewDrawableOverlay mImageView;
	private ViewGroup drawArea;
	private LabelSelector labelSelector;
	// 小白点标签
	private CommonTitleBar mTitleBar;
	private LabelView emptyLabelView;
	private Bitmap oldBitmap, biaoqianBitmap, newBitmap;
    private ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_img2);
		initView();
		String path = getIntent().getStringExtra("img");
		oldBitmap = MyTools.getBitmap(path);
		mImageView.setDrawingCacheEnabled(true);
		imageView.setDrawingCacheEnabled(true);
		imageView.setImageBitmap(oldBitmap);
		mImageView.setScaleWithContent(true);
		drawArea.setDrawingCacheEnabled(true);
		setListener();
		initStickerToolBar();
	}

	private void initView() {
		imageView=(ImageView) findViewById(R.id.image);
		mTitleBar = (CommonTitleBar) findViewById(R.id.handleImgTitle);
		// 初始化空白标签
		emptyLabelView = new LabelView(this);
		emptyLabelView.setEmpty();
		// 添加贴纸水印的画布
		drawArea = (ViewGroup) findViewById(R.id.drawing_view_container);

		mImageView = (MyImageViewDrawableOverlay) findViewById(R.id.drawable_overlay);

		// 添加标签选择器
		RelativeLayout.LayoutParams rparams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);

		labelSelector = new LabelSelector(this);
		labelSelector.setLayoutParams(rparams);
		drawArea.addView(labelSelector);
		labelSelector.setVisibility(View.GONE);

	}

	public void tagClick(View v) {
		TextView textView = (TextView) v;
		TagItem tagItem = new TagItem(AppConstants.POST_TYPE_TAG, textView
				.getText().toString());
		addLabel(tagItem);
	}

	private void setListener() {
		mTitleBar.setRightBtnOnclickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				savePicter();
			}
		});
	}

	// 初始化贴图
	private void initStickerToolBar() {

		labelSelector.setTxtClicked(new OnClickListener() {

			@Override
			public void onClick(View v) {

				EditTextActivity.openTextEdit(HandleImageActivity.this, "", 8,
						AppConstants.ACTION_EDIT_LABEL);

			}
		});
		labelSelector.setAddrClicked(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditTextActivity.openTextEdit(HandleImageActivity.this, "", 8,
						AppConstants.ACTION_EDIT_LABEL_POI);
			}
		});
		labelSelector.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				labelSelector.hide();
				emptyLabelView.updateLocation(
						(int) labelSelector.getmLastTouchX(),
						(int) labelSelector.getmLastTouchY());
				emptyLabelView.setVisibility(View.VISIBLE);

			}
		});
		mImageView.setOnDrawableEventListener(wpEditListener);
		mImageView
				.setSingleTapListener(new OnImageViewTouchSingleTapListener() {
					public void onSingleTapConfirmed() {

						emptyLabelView.updateLocation(
								(int) mImageView.getmLastMotionScrollX(),
								(int) mImageView.getmLastMotionScrollY());
						emptyLabelView.setVisibility(View.VISIBLE);

						labelSelector.showToTop();
						drawArea.postInvalidate();

					}
				});

	}

	private MyImageViewDrawableOverlay.OnDrawableEventListener wpEditListener = new MyImageViewDrawableOverlay.OnDrawableEventListener() {
		@Override
		public void onMove(MyHighlightView view) {
		}

		@Override
		public void onFocusChange(MyHighlightView newFocus,
				MyHighlightView oldFocus) {
		}

		@Override
		public void onDown(MyHighlightView view) {

		}

		@Override
		public void onClick(MyHighlightView view) {
			labelSelector.hide();
		}

		@Override
		public void onClick(final LabelView label) {
			if (label.equals(emptyLabelView)) {
				return;
			}
			// alert("温馨提示", "是否需要删除该标签！", "确定",
			// new DialogInterface.OnClickListener() {
			// @Override
			// public void onClick(DialogInterface dialog, int which) {
			// EffectUtil.removeLabelEditable(mImageView,
			// drawArea, label);
			// labels.remove(label);
			// }
			// }, "取消", null);
		}
	};
	private List<LabelView> labels = new ArrayList<LabelView>();

	// 添加标签
	private void addLabel(TagItem tagItem) {
		labelSelector.hide();
		emptyLabelView.setVisibility(View.INVISIBLE);
		if (labels.size() >= 5) {
			// alert("温馨提示", "您只能添加5个标签！", "确定", null, null, null, true);
		} else {
			int left = emptyLabelView.getLeft();
			int top = emptyLabelView.getTop();

			left = mImageView.getWidth() / 2 - 10;
			top = mImageView.getWidth() / 2;

			LabelView label = new LabelView(this);
			label.setDrawingCacheEnabled(true);
			label.init(tagItem);
			EffectUtil.addLabelEditable(mImageView, drawArea, label, left, top);

		}
	}

	public void savePicter() {
		//biaoqianBitmap = mImageView.getDrawingCache();
		//newBitmap = MyTools.mergeBitmap(oldBitmap, newBitmap);
		new SaveImageUtil(this, drawArea.getDrawingCache(), System.currentTimeMillis() + ".png",
				new OnSaveImageUtilListener() {

					@Override
					public void saveImageUtilFinish(String path) {
						Log.d("hck", "path: " + path);
						ImageData.imagePath = path;
						MultiImageSelectorActivity.activity.sendImge();
						finish();
					}

				}).execute();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		
		
		labelSelector.hide();
		super.onActivityResult(requestCode, resultCode, data);
		if (AppConstants.ACTION_EDIT_LABEL == requestCode && data != null) {
			String text = data.getStringExtra(AppConstants.PARAM_EDIT_TEXT);
			if (!TextUtils.isEmpty(text)) {
				TagItem tagItem = new TagItem(AppConstants.POST_TYPE_TAG, text);
				addLabel(tagItem);
			}
		} else if (AppConstants.ACTION_EDIT_LABEL_POI == requestCode
				&& data != null) {
			String text = data.getStringExtra(AppConstants.PARAM_EDIT_TEXT);
			if (!TextUtils.isEmpty(text)) {
				TagItem tagItem = new TagItem(AppConstants.POST_TYPE_POI, text);
				addLabel(tagItem);
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (oldBitmap != null) {
			oldBitmap.recycle();
			oldBitmap = null;
		}
		if (biaoqianBitmap != null) {
			biaoqianBitmap.recycle();
			biaoqianBitmap = null;
		}
		if (newBitmap != null) {
			newBitmap.recycle();
			newBitmap = null;
		}
		System.gc();
	}

}
