package com.hck.yanghua.biaoqian;

import it.sephiroth.android.library.widget.HListView;

import java.util.ArrayList;
import java.util.List;

import jp.co.cyberagent.android.gpuimage.GPUImageView;
import me.nereo.multi_image_selector.MyTools;
import me.nereo.multi_image_selector.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.imagezoom.ImageViewTouch.OnImageViewTouchSingleTapListener;

public class ShowImageActivity extends Activity {
	GPUImageView mGPUImageView;
	private MyImageViewDrawableOverlay mImageView;
	ViewGroup drawArea;
	HListView bottomToolBar;
	private LabelSelector labelSelector;
	// 小白点标签
	private LabelView emptyLabelView;
	private View commonLabelArea;
	ViewGroup toolArea;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_img);
		initView();
		mGPUImageView = (GPUImageView) findViewById(R.id.img);
		String path = getIntent().getStringExtra("img");
		Bitmap bitmap = MyTools.getSmallBitmap(path);
		mGPUImageView.setImage(bitmap);
		initStickerToolBar();
	}

	private void initView() {
		// 初始化空白标签
		toolArea = (ViewGroup) findViewById(R.id.toolbar_area);
		emptyLabelView = new LabelView(this);
		emptyLabelView.setEmpty();
		// 添加贴纸水印的画布
		bottomToolBar = (HListView) findViewById(R.id.list_tools);
		drawArea = (ViewGroup) findViewById(R.id.drawing_view_container);
		View overlay = LayoutInflater.from(this).inflate(
				R.layout.view_drawable_overlay, null);
		
		
		
		
		mImageView = (MyImageViewDrawableOverlay) overlay
				.findViewById(R.id.drawable_overlay);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
				Utils.getScreenWidth(), Utils.getScreenHeight());
		mImageView.setLayoutParams(params);
		overlay.setLayoutParams(params);
		drawArea.addView(overlay);
		bottomToolBar.setVisibility(View.VISIBLE);

		// 添加标签选择器
		RelativeLayout.LayoutParams rparams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);

		labelSelector = new LabelSelector(this);
		labelSelector.setLayoutParams(rparams);
		drawArea.addView(labelSelector);
		labelSelector.setVisibility(View.GONE);

		commonLabelArea = LayoutInflater.from(this).inflate(
				R.layout.view_label_bottom, null);
		commonLabelArea.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		toolArea.addView(commonLabelArea);
		commonLabelArea.setVisibility(View.VISIBLE);
	}

	public void tagClick(View v) {
		TextView textView = (TextView) v;
		TagItem tagItem = new TagItem(AppConstants.POST_TYPE_TAG, textView
				.getText().toString());
		addLabel(tagItem);
	}

	// 初始化贴图
	private void initStickerToolBar() {

		bottomToolBar.setAdapter(new StickerToolAdapter(this,
				EffectUtil.addonList));
		bottomToolBar
				.setOnItemClickListener(new it.sephiroth.android.library.widget.AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(
							it.sephiroth.android.library.widget.AdapterView<?> arg0,
							View arg1, int arg2, long arg3) {
						// labelSelector.hide();
						Addon sticker = EffectUtil.addonList.get(arg2);
						EffectUtil.addStickerImage(mImageView,
								ShowImageActivity.this, sticker,
								new EffectUtil.StickerCallback() {
									@Override
									public void onRemoveSticker(Addon sticker) {
										// labelSelector.hide();
									}
								});
					}
				});

		labelSelector.setTxtClicked(new OnClickListener() {

			@Override
			public void onClick(View v) {

				EditTextActivity.openTextEdit(ShowImageActivity.this, "", 8,
						AppConstants.ACTION_EDIT_LABEL);

			}
		});
		labelSelector.setAddrClicked(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditTextActivity.openTextEdit(ShowImageActivity.this, "", 8,
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

			Log.d("hck", "addLabel: " + left + ": " + top);
			LabelView label = new LabelView(this);
			label.init(tagItem);
			EffectUtil.addLabelEditable(mImageView, drawArea, label, left, top);
			labels.add(label);
		}
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

}
