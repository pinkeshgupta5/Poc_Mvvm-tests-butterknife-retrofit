package com.example.pinkesh_gupta.myapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.pinkesh_gupta.myapplication.model.UserContent;
import com.example.pinkesh_gupta.myapplication.R;
import com.example.pinkesh_gupta.myapplication.adapter.UserAdapter;
import com.example.pinkesh_gupta.myapplication.utilities.Network;
import com.example.pinkesh_gupta.myapplication.viewModel.UserViewModel;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    UserAdapter userAdapter;
    @BindView(R.id.button)
    Button loadData;
    @BindView(R.id.toolbar_actionbar)
    Toolbar mActionBarToolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup butterknife
        ButterKnife.bind(this);
        loadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.button)
                {
                    getView();
                }
            }
        });
        if (Network.checkNetworkAvailability(this)) {
            getView();
        } else {
            mSwipeRefreshLayout.setVisibility(View.GONE);
            loadData.setVisibility(View.VISIBLE);
            Toast.makeText(this, "No Network Connectivity, please enable internet and click on Load button", Toast.LENGTH_SHORT).show();
        }
    }



    public void getView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        userViewModel.getUsers().observe(this, new Observer<List<UserContent>>() {
            @Override
            public void onChanged(@Nullable List<UserContent> userContentList) {
                userAdapter = new UserAdapter(MainActivity.this, userContentList);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(userAdapter);
            }
        });

        userViewModel.gettitle().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String title) {
                mActionBarToolbar.setTitle(title);
                setSupportActionBar(mActionBarToolbar);
            }


        });
        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            RefreshData();
            mSwipeRefreshLayout.setRefreshing(false);
        }
    });
}

    public void RefreshData() {
        if (Network.checkNetworkAvailability(this)) {
                userViewModel.getUsers().observe(this, new Observer<List<UserContent>>() {
                    @Override
                    public void onChanged(@Nullable List<UserContent> userContentList) {
                        userAdapter = new UserAdapter(MainActivity.this, userContentList);
                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(userAdapter);
                    }
                });

        }
     else{
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(this, "No Network Connectivity, please check and try again", Toast.LENGTH_SHORT).show();
        }
    }
}
