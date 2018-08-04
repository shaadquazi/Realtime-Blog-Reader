package com.shaad.admin.ureka360.Helper;

import com.shaad.admin.ureka360.Model.Category;
import com.shaad.admin.ureka360.Model.Post;
import com.shaad.admin.ureka360.Model.User;
import com.shaad.admin.ureka360.Model.WPMedia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestInterface {
    @GET("posts")
    Call<List<Post>> getBlogPost(@Query("per_page") int per_page);

    @GET("posts")
    Call<List<Post>> getBlogPostByCategory(@Query("categories") List<Integer> id);

    @GET("media")
    Call<List<WPMedia>> getBlogMediaById(@Query("include") List<Integer> id);

    @GET("users/{id}")
    Call<User> getUserById(@Path("id") int id);

    @GET("media/{id}")
    Call<WPMedia> getMediaById(@Path("id") int id);

    @GET("users")
    Call<List<User>> getUsers();

    @GET("categories")
    Call<List<Category>> getCategories();





}
