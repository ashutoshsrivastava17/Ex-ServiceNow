package com.servicenow.sample.activites.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.novid.app.activities.base.BaseNavigator;
import com.novid.app.activities.base.BaseViewModel;
import com.servicenow.sample.R;

/**
 * Created by Ashutosh Srivastava on 24-Mar-2021 12:15 AM.
 * Project : Ex-ServiceNow
 * Copyright (c) 2021  All rights reserved.
 */
public abstract class BaseActivity<VB extends ViewDataBinding, VM extends BaseViewModel>
        extends AppCompatActivity implements BaseNavigator {

    private VB mViewDataBinding;
    private VM mViewModel;
    protected ViewModelProvider.AndroidViewModelFactory mViewModelFactory;
    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModelFactory = new ViewModelProvider.AndroidViewModelFactory(getApplication());

        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        this.mViewModel.setNavigator(this);
        this.mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        this.mViewDataBinding.executePendingBindings();

        //~~~ Observing Loader Status
        mViewModel.observerLoader()
                .observe(this, (Observer<Boolean>) this::onLoaderStatusChanged);
    }

    protected void onLoaderStatusChanged(Boolean o) {
        if (o) {
            Toast.makeText(this, mViewModel.getLoaderMessage(), Toast.LENGTH_LONG).show();
        }
    }

    protected abstract int getBindingVariable();

    protected abstract VM getViewModel();

    protected abstract int getLayoutId();

    public VB getViewDataBinding() {
        return mViewDataBinding;
    }

    @Override
    public void onError(String message) {
        AlertDialog.Builder alertDialogBuilder = getAlertDialog(getString(R.string.error), message);
        alertDialogBuilder.setPositiveButton(getString(R.string.ok), (dialog, which) -> mAlertDialog.dismiss());
        mAlertDialog = alertDialogBuilder.show();
    }

    private AlertDialog.Builder getAlertDialog(String title, String message) {
        if (mAlertDialog != null) mAlertDialog.cancel();
        return new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message);
    }

}
