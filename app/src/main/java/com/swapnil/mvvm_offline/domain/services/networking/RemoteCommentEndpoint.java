package com.swapnil.mvvm_offline.domain.services.networking;

import com.swapnil.mvvm_offline.model.Comment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface RemoteCommentEndpoint {

    @POST("/posts")
    Call<Comment> addComment(@Body Comment comment);
}
