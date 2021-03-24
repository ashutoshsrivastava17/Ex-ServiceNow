package com.servicenow.sample.repository.backend.jokesAPI;

import com.servicenow.sample.repository.backend.jokesAPI.models.JokesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ashutosh Srivastava on 24-Mar-2021 1:48 AM.
 * Project : Ex-ServiceNow
 * Copyright (c) 2021  All rights reserved.
 */
public interface JokesAPI {

    @GET("jokes/random")
    Call<JokesResponse> getRandomJokes(@Query(value = "firstName") String firstName,
                                                     @Query(value = "lastName") String lastName,
                                                     @Query(value = "limitTo") List<String> nerdy
    );

}
