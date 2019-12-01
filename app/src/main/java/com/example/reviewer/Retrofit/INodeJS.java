package com.example.reviewer.Retrofit;

import io.reactivex.Observable;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface INodeJS {
    @POST("register")
    @FormUrlEncoded
    Observable<String> registerUser(@Field("email") String email,
                                    @Field("name") String name,
                                    @Field("password") String password);

    @POST("login")
    @FormUrlEncoded
    Observable<String> loginUser(@Field("email") String email,
                                 @Field("password") String password);

    @POST("postReview")
    @FormUrlEncoded
    Observable<String> postReview(@Field("game_id") int game_id,
                                  @Field("email") String email,
                                  @Field("password") String password,
                                  @Field("review_rating") int review_rating,
                                  @Field("written_review") String written_review,
                                  @Field("unique_id") String unique_id,
                                  @Field("name") String name);

    @POST("getGamesList")
    @FormUrlEncoded
    Observable<String> getGamesList(@Field("amount") int amount,
                                    @Field("page") int page);

    @POST("getProfileUser")
    @FormUrlEncoded
    Observable<String> getProfileUser(@Field("unique_id") String unique_id);

    @POST("getProfileReview")
    @FormUrlEncoded
    Observable<String> getProfileReview(@Field("unique_id") String unique_id);

    @POST("getGameInfo")
    @FormUrlEncoded
    Observable<String> getGameInfo(@Field("game_id") int game_id);

    @POST("getGameReviews")
    @FormUrlEncoded
    Observable<String> getGameReviews(@Field("amount") int amount,
                                      @Field("page") int page,
                                      @Field("game_id") int game_id);

}
