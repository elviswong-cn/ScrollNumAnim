package elvis.anim.scrollnum.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import elvis.anim.scrollnum.R;


/* StrokeTextView的目标是给文字描边 实现方法是两个TextView叠加,只有描边的TextView为底,实体TextView叠加在上面
 * 看上去文字就有个不同颜色的边框了 */
public class StrokeTextView extends TextView {
	private TextView borderText = null;// /用于描边的TextView
	// 默认白色
	private int strokeTextColor = getResources().getColor(android.R.color.white);

	public StrokeTextView(Context context) {
		super(context);
		borderText = new TextView(context);
		init();
	}

	@Override
	public void setBackground(Drawable background) {
		super.setBackground(getResources().getDrawable(android.R.color.black));
	}

	public StrokeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		borderText = new TextView(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StrokeTextView);
		strokeTextColor = a.getColor(R.styleable.StrokeTextView_strokeTextColor, 0XFFFFFFFF);
		a.recycle();

		init();
	}

	public StrokeTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		borderText = new TextView(context, attrs, defStyle);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StrokeTextView);
		strokeTextColor = a.getColor(R.styleable.StrokeTextView_strokeTextColor, 0XFFFFFFFF);
		a.recycle();
		init();
	}

	@Override
	public void setTextSize(int unit, float size) {
		super.setTextSize(unit, size);
		borderText.setTextSize(unit, size);
	}

	@Override
	public void setTextSize(float size) {
		super.setTextSize(size);
		borderText.setTextSize(size);
	}


	public void init() {
		TextPaint tp1 = borderText.getPaint();
		tp1.setStrokeWidth(4); // 设置描边宽度
		tp1.setStyle(Style.STROKE); // 对文字只描边
		borderText.setTextColor(strokeTextColor); // 设置描边颜色
		borderText.setGravity(Gravity.CENTER);
	}

	@Override
	public void setLayoutParams(ViewGroup.LayoutParams params) {
		super.setLayoutParams(params);
		borderText.setLayoutParams(params);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		CharSequence tt = borderText.getText();
		// 两个TextView上的文字必须一致
		if (tt == null || !tt.equals(this.getText())) {
			borderText.setText(getText());
			this.postInvalidate();
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		borderText.measure(widthMeasureSpec, heightMeasureSpec);
	}

	public void setStrokeTextColor(int strokeTextColor) {
		this.strokeTextColor = strokeTextColor;
		borderText.setTextColor(strokeTextColor);
	}

	@Override
	public void setGravity(int gravity) {
		super.setGravity(gravity);
		borderText.setGravity(gravity);
	}

	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		borderText.layout(left, top, right, bottom);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		borderText.draw(canvas);
		super.onDraw(canvas);
	}
}