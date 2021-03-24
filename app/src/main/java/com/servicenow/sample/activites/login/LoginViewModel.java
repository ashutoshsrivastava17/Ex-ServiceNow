package com.servicenow.sample.activites.login;

import android.app.Application;

import androidx.databinding.Observable;
import androidx.databinding.ObservableField;

import com.novid.app.activities.base.BaseViewModel;
import com.servicenow.sample.repository.preferences.ISharedPref;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Ashutosh Srivastava on 24-Mar-2021 12:31 AM.
 * Project : Ex-ServiceNow
 * Copyright (c) 2021  All rights reserved.
 */
public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    public ObservableField<String> mObservaleFirstName = new ObservableField<>();
    public ObservableField<String> mObservaleLastName = new ObservableField<>();
    public ObservableField<Boolean> mObservaleSubmitVisibility = new ObservableField<>(false);

    public LoginViewModel(@NotNull Application application) {
        super(application);

        mObservaleFirstName.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                getNavigator().enableSubmit(mObservaleFirstName.get(), mObservaleLastName.get());
            }
        });

        mObservaleLastName.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                getNavigator().enableSubmit(mObservaleFirstName.get(), mObservaleLastName.get());
            }
        });
    }

    public void onActionSubmit() {
        String firstName = mObservaleFirstName.get();
        String lastName = mObservaleLastName.get();
        getSharedPref().putString(ISharedPref.KEY_FIRST_NAME, firstName);
        getSharedPref().putString(ISharedPref.KEY_LAST_NAME, lastName);
        getNavigator().onActionSubmit(firstName, lastName);
    }


}
