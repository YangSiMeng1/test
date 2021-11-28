package com.bawei.registermvvm;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bawei.classify.ClassifyFragment;
import com.bawei.discover.DiscoverFragment;
import com.bawei.home.HomeFragment;
import com.bawei.mvvmcore.view.BaseActivity;
import com.bawei.registermvvm.adapter.FragmentAdapter;
import com.bawei.shopingtrolley.ShopFragment;
import com.bawei.usercenter.my.MainFragment;
import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends BaseActivity {


    private ViewPager vp;
    private RadioGroup rg;
    private RadioButton home;
    private RadioButton classify;
    private RadioButton discover;
    private RadioButton shop;
    private RadioButton main;
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        Transition fade = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_right);
//        getWindow().setEnterTransition(fade);
//
        setContentView(R.layout.activity_homepage);

        getWindow().setEnterTransition(new Slide(Gravity.RIGHT).setDuration(500));


        initView();

        initVP();

        initOn();



    }



    /**
     * 点击事件
     */
    private void initOn() {
        rg.check(R.id.home);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        rg.check(R.id.home);
                        break;
                    case 1:
                        rg.check(R.id.classify);
                        break;
                    case 2:
                        rg.check(R.id.discover);
                        break;
                    case 3:
                        rg.check(R.id.shop);
                        break;
                    case 4:
                        rg.check(R.id.main);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.home:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.classify:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.discover:
                        vp.setCurrentItem(2);
                        break;
                    case R.id.shop:
                        vp.setCurrentItem(3);
                        break;
                    case R.id.main:
                        vp.setCurrentItem(4);
                        break;
                }
            }
        });
    }



    /*
    设置vp
     */
    private void initVP() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ClassifyFragment());
        fragments.add(new DiscoverFragment());
        fragments.add(new ShopFragment());
        fragments.add(new MainFragment());

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(fragmentAdapter);
    }


    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        rg = (RadioGroup) findViewById(R.id.rg);
        home = (RadioButton) findViewById(R.id.home);
        classify = (RadioButton) findViewById(R.id.classify);
        discover = (RadioButton) findViewById(R.id.discover);
        shop = (RadioButton) findViewById(R.id.shop);
        main = (RadioButton) findViewById(R.id.main);
    }

    @Override
    protected void initEnv() {

    }
}