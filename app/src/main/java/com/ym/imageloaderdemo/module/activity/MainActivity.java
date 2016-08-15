package com.ym.imageloaderdemo.module.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.ym.imageloaderdemo.R;
import com.ym.imageloaderdemo.module.ThreadManager;
import com.ym.imageloaderdemo.utils.ImageLoader;
/**
 * @className: MainActivity
 * @classDescription: UI
 * @author: leibing
 * @createTime: 2016/8/15
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public final static String IMAGE_URL = "http://g.hiphotos.baidu.com/imgad/pic/item/" +
            "023b5bb5c9ea15cec0e68e76b1003af33a87b241.jpg";
    private ImageView testIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_request).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        testIv = (ImageView) findViewById(R.id.iv_test);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_request:
                // 加载图片
                ImageLoader.getInstance().load(this, testIv, IMAGE_URL);
                break;
            case R.id.btn_clear:
                // 清除缓存
                ImageLoader.getInstance().clearViewCache(testIv);
                ImageLoader.getInstance().clearMemory(this);
                ThreadManager.getInstance().getNewCachedThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        ImageLoader.getInstance().clearDiskCache(MainActivity.this);
                    }
                });
                break;
            default:
                break;
        }
    }
}
