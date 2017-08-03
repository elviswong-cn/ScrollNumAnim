package elvis.anim.scrollnum.ui.scrollnum;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import elvis.anim.scrollnum.R;

/**
 * Created by Elvis on 2017/2/14.
 */

public class AutoVerticalLinearLayout extends LinearLayout {
	private Context mContext;
	private String data;                                        //数值
	private List<String> datas = new ArrayList<>();        //数字按照个十百千
	private List<VerticalTextview> views = new ArrayList<>();
	// 描边默认白色
	private int strokeTextColor = getResources().getColor(android.R.color.white);
	// 字体默认黑色
	private int verticalTextColor = getResources().getColor(android.R.color.white);
	// 字体大小(dp)
	private int mTextSize = 20;
	// 字体style
	private int mTextStyle = Typeface.NORMAL;
	// 所有文本从滑入到滑出，以及停留的时间
	private long mAnimDurationTotal;

	public AutoVerticalLinearLayout(Context context) {
		this(context, null);
	}

	public AutoVerticalLinearLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public AutoVerticalLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoVerticalLinearLayout);
		verticalTextColor = a.getColor(R.styleable
				.AutoVerticalLinearLayout_autoVerticalTextColor, 0XFFFFFFFF);
		strokeTextColor = a.getColor(R.styleable
				.AutoVerticalLinearLayout_autoVerticalStrokeTextColor, 0XFFFFFFFF);
		mTextSize = a.getDimensionPixelSize(R.styleable
				.AutoVerticalLinearLayout_autoVerticalTextSize, mTextSize);
		mTextStyle = a.getInteger(R.styleable.AutoVerticalLinearLayout_autoVerticalTextStyle,
				mTextStyle);
		a.recycle();
	}

	public void addData(String data) {
		this.data = data;
		// 将数字转换为char数组
		char[] chars = String.valueOf(data).toCharArray();
		datas.clear();
		for (char ch : chars) {
			// 直接将char转换为int 拿到的数据为ASCII码值
			datas.add(String.valueOf(ch));
		}
		// 第二次的数据等于第一次的数据
		addView();
	}

	private void addView() {
		// 显示数字位数 > 当前view数
		if (datas.size() > views.size()) {
			// 显示数字位数 > view数，进行追加
			int count = datas.size() - views.size();
			for (int i = 0; i < count; i++) {
				VerticalTextview tv = getVerticalTextview();
				addView(tv, 0);
				views.add(0, tv);
			}
		} else if (datas.size() < views.size()) {
			views.clear();
			removeAllViews();
			for (String data : datas) {
				VerticalTextview tv = getVerticalTextview();
				tv.setNum(data);
				addView(tv);
				views.add(tv);
			}
			return;
		}
		for (int i = 0; i < views.size(); i++) {
			views.get(i).setNum(datas.get(i));
		}
	}

	@NonNull
	private VerticalTextview getVerticalTextview() {
		VerticalTextview tv = new VerticalTextview(mContext);
		tv.setVerticalTextColor(verticalTextColor);
		tv.setStrokeTextColor(strokeTextColor);
		tv.setTextSize(mTextSize);
		tv.setTextStyle(mTextStyle);
		LayoutParams lp = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
		lp.gravity = Gravity.CENTER;
		tv.setLayoutParams(lp);
		return tv;
	}

	/**
	 * 开始动画
	 */
	public void startAnim() {
		if (views.size() >= 3) {
			mAnimDurationTotal = 3000;
		} else {
			mAnimDurationTotal = 1000 * views.size();
		}
		for (int i = 0; i < views.size(); i++) {
			VerticalTextview v = views.get(i);
			v.setDurationTotal(mAnimDurationTotal);
			v.setTextStillTime();
			v.startAutoScroll();
		}
	}

	public void clear() {
		views.clear();
		removeAllViews();
	}

	public Animation getInAnim() {
		if (getChildAt(0) != null) {
			return ((VerticalTextview) getChildAt(0)).getInAnimation();
		}
		return null;
	}

	public Animation getOutAnim() {
		if (getChildAt(0) != null) {
			return ((VerticalTextview) getChildAt(0)).getOutAnimation();
		}
		return null;
	}

}
