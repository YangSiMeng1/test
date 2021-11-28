package com.bawei.registermvvm;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import com.bawei.mvvmcore.view.BaseActivity;
import com.bawei.registermvvm.view.TimeView;

public  class MainActivity extends BaseActivity {

    private TimeView tim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        tim = (TimeView) findViewById(R.id.tim);
        tim.setTimeView(timeText);
    }


    private  TimeView.TimeText timeText = new TimeView.TimeText() {
        @Override
        public void finish() {
            startActivity(new Intent(MainActivity.this, HomePageActivity.class), ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            MainActivity.this.finish();
        }
    };


    @Override
    protected void initEnv() {

    }
}