package elvis.anim.scrollnum.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import elvis.anim.scrollnum.R;
import elvis.anim.scrollnum.ui.scrollnum.AutoVerticalLinearLayout;

public class MainActivity extends AppCompatActivity {
    private EditText mEtNum;
    private Button mBtnStart;
    private AutoVerticalLinearLayout mAutoLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        setListener();
    }

    /**
     * widget初始化
     */
    private void initWidget() {
        mEtNum = (EditText) findViewById(R.id.main_et_num);
        mBtnStart = (Button) findViewById(R.id.main_btn_start);
        mAutoLayout = (AutoVerticalLinearLayout) findViewById(R.id.main_auto_layout);
    }

    private void setListener() {
        mBtnStart.setOnClickListener(startOnClickListener);
    }

    private View.OnClickListener startOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String numTxt = mEtNum.getText().toString();
            if (!TextUtils.isEmpty(numTxt)) {
                mAutoLayout.addData(numTxt);
                //                mAutoLayout.getAnimation().setAnimationListener(autoAnimListener);
                Animation outAnim = mAutoLayout.getOutAnim();
                // 设置动画结束操作
                if (outAnim != null) {
                    outAnim.setAnimationListener(autoAnimListener);
                }
                mAutoLayout.startAnim();
            } else {
                Toast.makeText(MainActivity.this, "请输入一个有效数字", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private Animation.AnimationListener autoAnimListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            // 动画结束清除数据
            mAutoLayout.clear();
            Log.d("MainActivity", "animation end");
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
}
