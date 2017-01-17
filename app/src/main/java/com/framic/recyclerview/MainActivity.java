package com.framic.recyclerview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> datas;
    private SimpleAdapter adapter;
    private StaggeredAdapter staggeredAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();
        initView();
        adapter = new SimpleAdapter(this, datas);
        adapter.setOnItemClickListener(new SimpleAdapter.OnItemClicklistener() {
            @Override
            public void OnItemClick(View view, int pos) {
                //    view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                Toast.makeText(MainActivity.this, "" + pos, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemLongClick(View view, int pos) {
                //     view.setBackgroundColor(Color.GRAY);
                Toast.makeText(MainActivity.this, "" + pos, Toast.LENGTH_SHORT).show();

            }
        });
        staggeredAdapter = new StaggeredAdapter(this, datas);
        staggeredAdapter.setOnItemClickListener(new SimpleAdapter.OnItemClicklistener() {
            @Override
            public void OnItemClick(View view, int pos) {

            }

            @Override
            public void OnItemLongClick(View view, int pos) {

            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //设置recyclerView布局管理
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        //设置recyclerView分割线
        // recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    private void initDatas() {
        datas = new ArrayList<String>();
        for (int i = 'A'; i < 'Z'; i++) {
            datas.add("" + (char) i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = new MenuInflater(this);
//        inflater.inflate(R.menu.menu, menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_listView:
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_gridView:
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                break;
            case R.id.action_hor_listView:
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                break;
            case R.id.action_hor_gridView:
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.action_starggered:
                recyclerView.setAdapter(staggeredAdapter);
                //设置recyclerView布局管理
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                break;
            case R.id.action_add:
                adapter.addData(1);
                staggeredAdapter.addData(1);
                break;
            case R.id.action_delete:
                adapter.delData(1);
                staggeredAdapter.delData(1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
