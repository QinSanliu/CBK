package com.qgz.qinsa.cbk;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qgz.qinsa.cbk.adapters.MainViewPagerAdapter;
import com.qgz.qinsa.cbk.fragments.ContentFragment;

import java.util.ArrayList;
import java.util.List;

import static com.qgz.qinsa.cbk.R.id.linearLayout_drawer_contain;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView more;
    private DrawerLayout activity_main;
    private LinearLayout linearLayout_drawer;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<Fragment> fragmentList = new ArrayList<>();
    //private int lastSelected = 0;
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDrawerLayout();

        initViewPager();

        initTabLayout();

        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void drawerLayoutClick(View view){
        /*给drawerLayout设置一个空的点击事件，解决抽屉点击击穿问题*/
    }

    //抽屉里的各子控件点击事件
    public void drawerMenu(View view){
        switch (view.getId()){
            case R.id.back:     //灰色返回
                activity_main.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.righttopbutton://右上角的home按钮
                activity_main.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.gosearch: //  搜索按钮

                break;
            case R.id.myColection:  //我的收藏

                break;
            case R.id.copyright:    //版权信息

                break;
            case R.id.feedback:     //意见反馈

                break;
        }
    }
    //抽屉设置
    private void initDrawerLayout() {
        more = (ImageView) findViewById(R.id.more);
        activity_main = (DrawerLayout) findViewById(R.id.activity_main);
        linearLayout_drawer = (LinearLayout) findViewById(linearLayout_drawer_contain);

        DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) linearLayout_drawer.getLayoutParams();
        params.width = (int) (getResources().getDisplayMetrics().widthPixels*0.8);

        linearLayout_drawer.setLayoutParams(params);
//        activity_main.setAnimation(Animation);
        //点击more呼出抽屉菜单
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity_main.openDrawer(linearLayout_drawer);
            }
        });

    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager_container);
        String[] strings = getResources().getStringArray(R.array.tabTitles);

        for (int i = 0; i < strings.length; i++) {
            Fragment fragment = ContentFragment.newInstance(i);

            fragmentList.add(fragment);
        }

        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(),fragmentList,strings);
        mViewPager.setAdapter(mainViewPagerAdapter);

        //mViewPager的翻页动画效果setPageTransformer
        mViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                int pageWidth = view.getWidth();
                int pageHeight = view.getHeight();

                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    view.setAlpha(0);

                } else if (position <= 1) { // [-1,1]
                    // Modify the default slide transition to shrink the page as well
                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                    float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                    if (position < 0) {
                        view.setTranslationX(horzMargin - vertMargin / 2);
                    } else {
                        view.setTranslationX(-horzMargin + vertMargin / 2);
                    }

                    // Scale the page down (between MIN_SCALE and 1)
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);

                    // Fade the page relative to its size.
                    view.setAlpha(MIN_ALPHA +
                            (scaleFactor - MIN_SCALE) /
                                    (1 - MIN_SCALE) * (1 - MIN_ALPHA));

                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    view.setAlpha(0);
                }
            }
        });

    }

    private void initTabLayout() {
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);

//        String[] tabTitles =getResources().getStringArray(R.array.tabTitles);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }


    @Override
    public void onClick(View view) {
        int index = (int) view.getTag();
        mViewPager.setCurrentItem(index);
    }

}
