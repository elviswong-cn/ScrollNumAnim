package elvis.anim.scrollnum.kotlin.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.animation.Animation
import android.widget.Toast
import elvis.anim.scrollnum.R
import kotlinx.android.synthetic.main.activity_kotlin_main.*

class KotlinMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)
        setEventLinstener()
    }

    private fun setEventLinstener() {
        this.main_btn_start.setOnClickListener {
            val content = this.main_et_num.text.toString()
            if (!TextUtils.isEmpty(content)) {
                this.main_auto_layout.addData(content)
                //                mAutoLayout.getAnimation().setAnimationListener(autoAnimListener);
                val outAnim = this.main_auto_layout.getOutAnim()
                // 设置动画结束操作
                if (outAnim != null) {
                    outAnim!!.setAnimationListener(autoAnimListener)
                }
                this.main_auto_layout.startAnim()
            } else {
                Toast.makeText(this@KotlinMainActivity, "请输入一个有效整数", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val autoAnimListener = object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {

        }

        override fun onAnimationEnd(animation: Animation) {
            // 动画结束清除数据
            this@KotlinMainActivity.main_auto_layout.clear()
            Log.d("KotlinMainActivity", "animation end")
        }

        override fun onAnimationRepeat(animation: Animation) {

        }
    }
}
