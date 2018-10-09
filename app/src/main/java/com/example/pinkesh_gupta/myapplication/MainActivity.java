package com.example.pinkesh_gupta.myapplication;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    UserAdapter userAdapter;


    @BindView(R.id.toolbar_actionbar)
    Toolbar mActionBarToolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Setup butterknife
        ButterKnife.bind(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);

        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        userViewModel.getUsers().observe(this, new Observer<List<UserContent>>() {
            @Override
            public void onChanged(@Nullable List<UserContent> userContentList) {
                userAdapter = new UserAdapter(MainActivity.this,userContentList);
                recyclerView.setAdapter(userAdapter);
            }
        });

        userViewModel.gettitle().observe(this, new Observer<String>(){
            @Override
            public void onChanged(@Nullable String title) {
                mActionBarToolbar.setTitle(title);
                setSupportActionBar(mActionBarToolbar);
            }


    });
    }
}
