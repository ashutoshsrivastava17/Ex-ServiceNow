package com.servicenow.sample.activites.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.novid.app.dataRepository.preferences.SharedPref;
import com.servicenow.sample.BR;
import com.servicenow.sample.R;
import com.servicenow.sample.activites.base.BaseActivity;
import com.servicenow.sample.activites.jokes.JokesActivity;
import com.servicenow.sample.databinding.ActivityLoginBinding;

/**
 * Created by Ashutosh Srivastava on 24-Mar-2021 12:24 AM.
 * Project : Ex-ServiceNow
 * Copyright (c) 2021  All rights reserved.
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigator {

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    protected LoginViewModel getViewModel() {
        return new ViewModelProvider(this, mViewModelFactory).get(LoginViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void enableSubmit(String firstName, String lastName) {
        firstName = firstName == null ? "" : firstName;
        lastName = lastName == null ? "" : lastName;
        getViewDataBinding().btnSubmit.setEnabled(!firstName.isEmpty() || !lastName.isEmpty());
    }

    @Override
    public void onActionSubmit(String firstName, String lastName) {
        Intent intent = JokesActivity.getStartIntent(this);
        intent.putExtra(SharedPref.KEY_FIRST_NAME, firstName);
        intent.putExtra(SharedPref.KEY_LAST_NAME, lastName);
        startActivity(intent);
    }

}
