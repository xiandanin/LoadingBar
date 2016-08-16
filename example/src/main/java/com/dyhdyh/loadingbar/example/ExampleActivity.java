package com.dyhdyh.loadingbar.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dyhdyh.widget.loadingbar.LoadingBar;

import java.util.ArrayList;
import java.util.List;

/**
 * author  dengyuhan
 * created 2016/7/27 18:29
 */
public class ExampleActivity extends AppCompatActivity{
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        lv= (ListView) this.findViewById(R.id.lv);

        final LoadingBar loadingBar = LoadingBar.show(getWindow().getDecorView());

        lv.postDelayed(new Runnable() {
            @Override
            public void run() {
                lv.setAdapter(new ArrayAdapter<String>(ExampleActivity.this,android.R.layout.simple_list_item_1,testData()));
                loadingBar.cancel();
            }
        },2000);
    }

    private List<String> testData() {
        List<String> data=new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            data.add("Item "+i);
        }
        return data;
    }
}
