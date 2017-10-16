package example.weisente.top.tablayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Arrays;
import java.util.List;

import example.weisente.top.tablayout.widget.CommonNavigator.CommonNavigator;
import example.weisente.top.tablayout.widget.MagicIndicator;
import example.weisente.top.tablayout.widget.UIUtil;
import example.weisente.top.tablayout.widget.ViewPagerHelper;
import example.weisente.top.tablayout.widget.abs.CommonNavigatorAdapter;
import example.weisente.top.tablayout.widget.abs.IPagerIndicator;
import example.weisente.top.tablayout.widget.abs.IPagerTitleView;
import example.weisente.top.tablayout.widget.indicators.LinePagerIndicator;
import example.weisente.top.tablayout.widget.title.ClipPagerTitleView;

public class MainActivity extends AppCompatActivity {
    private static final String[] CHANNELS = new String[]{"全部", "男装", "男装","男装", "男装", "男装","男装", "男装", "男装"};
//    private static final String[] CHANNELS = new String[]{"KITKAT", "NOUGAT", "DONUT"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private ViewPager mViewPager;
    private ExamplePagerAdapter mExamplePagerAdapter = new ExamplePagerAdapter(mDataList);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mExamplePagerAdapter);
        initMagicIndicator();
    }

    private void initMagicIndicator() {
        MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator3);
//        magicIndicator.setBackgroundResource(R.drawable.round_indicator_bg);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText(mDataList.get(index));
                //设置每一个的padding
                clipPagerTitleView.setPadding( UIUtil.dip2px(context, 30),0, UIUtil.dip2px(context, 30),0);

                clipPagerTitleView.setTextColor(Color.parseColor("#222222"));
                clipPagerTitleView.setClipColor(Color.WHITE);
                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                float navigatorHeight = context.getResources().getDimension(R.dimen.common_navigator_height);
                float borderWidth = UIUtil.dip2px(context, 8);
                float lineHeight = navigatorHeight - 2 * borderWidth;
                indicator.setLineHeight(lineHeight);
                indicator.setRoundRadius(lineHeight / 2);
                indicator.setYOffset(borderWidth);
                indicator.setColors(Color.parseColor("#FF2C6B"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }
}
