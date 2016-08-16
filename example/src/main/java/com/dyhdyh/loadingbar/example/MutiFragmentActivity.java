package com.dyhdyh.loadingbar.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.dyhdyh.loadingbar.example.fragment.ContentFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author  dengyuhan
 * created 2016/8/16 9:41
 */
public class MutiFragmentActivity extends FragmentActivity{
    private String[] title= new String[]{"新闻", "图片", "视频"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muti);

        List<Fragment> fragments=new ArrayList<>();
        for (int i = 0; i <title.length ; i++) {
            Fragment fragment=new ContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title",title[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment1,fragments.get(0));
        transaction.replace(R.id.fragment2,fragments.get(1));
        transaction.replace(R.id.fragment3,fragments.get(2));
        transaction.commit();
    }
}
