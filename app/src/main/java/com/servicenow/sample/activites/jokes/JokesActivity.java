package com.servicenow.sample.activites.jokes;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.novid.app.dataRepository.preferences.SharedPref;
import com.servicenow.sample.BR;
import com.servicenow.sample.R;
import com.servicenow.sample.activites.base.BaseActivity;
import com.servicenow.sample.databinding.ActivityJokesBinding;
import com.servicenow.sample.repository.database.models.Joke;
import com.servicenow.sample.views.layoutmanager.OnItemSwiped;
import com.servicenow.sample.views.layoutmanager.SwipeableLayoutManager;
import com.servicenow.sample.views.layoutmanager.SwipeableTouchHelperCallback;
import com.servicenow.sample.views.layoutmanager.touchelper.ItemTouchHelper;

import java.util.Stack;

/**
 * Created by Ashutosh Srivastava on 24-Mar-2021 1:32 AM.
 * Project : Ex-ServiceNow
 * Copyright (c) 2021  All rights reserved.
 */
public class JokesActivity extends BaseActivity<ActivityJokesBinding, JokesViewModel> implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "JokesActivity";
    private JokesAdapter jokesAdapter;
    private Stack<Joke> removedJokes = new Stack<>();
    private String firstName, lastName;

    public static Intent getStartIntent(AppCompatActivity activity) {
        Intent intent = new Intent(activity, JokesActivity.class);
        return intent;
    }

    private void getIntentExtras() {
        firstName = getIntent().getStringExtra(SharedPref.KEY_FIRST_NAME);
        lastName = getIntent().getStringExtra(SharedPref.KEY_LAST_NAME);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getIntentExtras();

        //~~~ Setting Refresh listener on Swipe Layout
        getViewDataBinding().swipeLayout.setOnRefreshListener(this);

        //~~~ Setting up Joke Recycler View
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeableTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(getViewDataBinding().rvJokes);
        getViewDataBinding().rvJokes.setLayoutManager(new SwipeableLayoutManager().setAngle(10)
                .setAnimationDuratuion(450)
                .setMaxShowCount(3)
                .setScaleGap(0.1f)
                .setTransYGap(0));

        //~~~ Setting up adapter on RecyclerView
        jokesAdapter = new JokesAdapter(this);
        getViewDataBinding().rvJokes.setAdapter(jokesAdapter);

        //~~~ Fetching Random Jokes from Database
        getViewModel().getRandomJokeFromDB().observe(this, jokes -> {
            jokesAdapter.addItems(jokes);
        });

        onRefresh();
    }

    @Override
    protected void onLoaderStatusChanged(Boolean status) {
        getViewDataBinding().swipeLayout.setRefreshing(status);
    }

    @Override
    public void onRefresh() {
        getViewModel().getRandomJokeFromAPI(firstName, lastName);
    }

    @Override
    protected int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    protected JokesViewModel getViewModel() {
        return new ViewModelProvider(this, mViewModelFactory).get(JokesViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jokes;
    }

    SwipeableTouchHelperCallback swipeableTouchHelperCallback = new SwipeableTouchHelperCallback(new OnItemSwiped() {

        @Override
        public void onItemSwipedLeft() {
            removedJokes.push(jokesAdapter.removeTopItem());
            if (jokesAdapter.getItemCount() == 0)
                onRefresh();
        }

        @Override
        public void onItemSwipedRight() {
            if (!removedJokes.isEmpty()) {
                jokesAdapter.addItemAtPosition(removedJokes.pop(), 0);
            } else {
                onRefresh();
            }
        }
    });


}
