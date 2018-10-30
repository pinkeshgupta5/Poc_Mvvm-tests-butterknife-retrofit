package com.example.pinkesh_gupta.myapplication.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.pinkesh_gupta.myapplication.model.UserContent;
import com.example.pinkesh_gupta.myapplication.model.UserResponses;
import com.example.pinkesh_gupta.myapplication.restApiClient.Api;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<UserContent>> userContentList;
    private MutableLiveData<String> title;

    //we will call this method to get the data
    public LiveData<List<UserContent>> getUsers() {
        //if the list is null
        if (userContentList == null) {
            userContentList = new MutableLiveData<List<UserContent>>();
            //we will load it asynchronously from server in this method
            loadUsers();
        }

        //finally we will return the list
        return userContentList;
    }
    public LiveData<String> gettitle() {
        if(title==null) {
            title = new  MutableLiveData<String> ();
            loadUsers();
        }
        return title;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadUsers() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<UserResponses> call = api.getUsers();


        call.enqueue(new Callback<UserResponses>() {
            @Override
            public void onResponse(Call<UserResponses> call, Response<UserResponses> response) {

                //finally we are setting the list to our MutableLiveData
                //ArrayList<UserContent> rows = response.body().getRows();
                title.setValue(response.body().getTitle());
                userContentList.setValue(response.body().getRows());
            }

            @Override
            public void onFailure(Call<UserResponses> call, Throwable t) {

            }
        });
    }


}
