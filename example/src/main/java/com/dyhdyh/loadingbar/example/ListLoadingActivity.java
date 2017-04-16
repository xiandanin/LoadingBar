package com.dyhdyh.loadingbar.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.dyhdyh.loadingbar.example.adapter.TextAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 多loading的操作
 * author  dengyuhan
 * created 2016/7/27 18:29
 */
public class ListLoadingActivity extends AppCompatActivity {
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_loading);
        rv = (RecyclerView) this.findViewById(R.id.rv);

        rv.setLayoutManager(new GridLayoutManager(this,3));
        rv.setAdapter(new TextAdapter(getTestData()));
        rv.addItemDecoration(new DividerGridItemDecoration(this));
    }

    public void clickLinearLayoutManager(MenuItem item) {
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    public void clickGridLayoutManager(MenuItem item) {
        rv.setLayoutManager(new GridLayoutManager(this,3));
    }

    private List<String> getTestData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            data.add("Item " + i);
        }
        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_loading,menu);
        return super.onCreateOptionsMenu(menu);
    }


}
