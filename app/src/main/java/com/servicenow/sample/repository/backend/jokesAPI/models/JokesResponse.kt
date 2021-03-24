package com.servicenow.sample.repository.backend.jokesAPI.models

import com.servicenow.sample.repository.database.models.Joke

/**
 * Created by Ashutosh Srivastava on 24-Mar-2021 2:38 AM.
 * Project : Ex-ServiceNow
 * Copyright (c) 2021  All rights reserved.
 */
class JokesResponse {
    var type: String? = null
    var value: Joke? = null
}