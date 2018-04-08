package org.scci_cu.conferencetool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.scci_cu.conferencetool.Interfaces.UserInterface;
import org.scci_cu.conferencetool.Models.UserModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.usr_txt)
    EditText usr_txt;
    @BindView(R.id.pass_txt)
    EditText pass_txt;
    @BindView(R.id.login_btn)
    Button login_btn;
    MyPreferenceManager  Manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Manager = new MyPreferenceManager(LoginActivity.this);
        UserModel user = Manager.getUser();
        if( user != null)
        {
            Intent intent = null;
            if(user.getType().equals("AC"))
            {
                intent =   new Intent(LoginActivity.this,AcademicActivity.class);
            }
            else if(user.getType().equals("President"))
            {
                intent  = new Intent(LoginActivity.this,PresidentActivity.class);
            }
            else
            {
                intent  = new Intent(LoginActivity.this,HeadsActivity.class);
            }
            startActivity(intent);

        }


    }
    @OnClick(R.id.login_btn)
    void Login()
    {
        if(check(usr_txt) && check(pass_txt))
        {
            Login(new UserModel(usr_txt.getText().toString().trim(),pass_txt.getText().toString().trim(),null));
        }
    }

    boolean check(EditText edit_txt)
    {
        String txt = edit_txt.getText().toString().trim();
        if(txt.isEmpty() || txt == null)
        {
            edit_txt.setError("This field should has value...");
            Log.e("Tag","Error");
            return false;
        }
        else
        {
            edit_txt.setError(null);
            return  true;
        }
    }

    void Login(UserModel user)
    {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(getString(R.string.baseurl)).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        UserInterface loginService = retrofit.create(UserInterface.class);
        Call<UserModel> loginCall = loginService.Login(user);
        loginCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel userResult = response.body();
                if(!userResult.getType().equals("false"))
                {

                    Intent intent = null;
                    if(userResult.getType().equals("AC"))
                    {
                        intent =   new Intent(LoginActivity.this,AcademicActivity.class);
                    }
                    else if(userResult.getType().equals("President"))
                    {
                       intent  = new Intent(LoginActivity.this,PresidentActivity.class);
                    }
                    else
                    {
                       intent  = new Intent(LoginActivity.this,HeadsActivity.class);
                    }
                    Manager.storeUser(userResult);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Check your username and your password.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Check your Internet connection.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
