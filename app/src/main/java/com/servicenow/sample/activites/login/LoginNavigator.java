package com.servicenow.sample.activites.login;

import com.novid.app.activities.base.BaseNavigator;

/**
 * Created by Ashutosh Srivastava on 24-Mar-2021 12:31 AM.
 * Project : Ex-ServiceNow
 * Copyright (c) 2021  All rights reserved.
 */
public interface LoginNavigator extends BaseNavigator {


    void enableSubmit(String firstName, String lastName);

    void onActionSubmit(String firstName, String lastName);
}
