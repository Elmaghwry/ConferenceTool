package org.scci_cu.conferencetool.Interfaces;

import org.scci_cu.conferencetool.Models.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserInterface
{
    @POST("login.php")
    Call<UserModel> Login(@Body UserModel user);
}
