package org.scci_cu.conferencetool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.usr_txt)
    EditText usr_txt;
    @BindView(R.id.pass_txt)
    EditText pass_txt;
    @BindView(R.id.login_btn)
    Button login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.login_btn)
    void Login()
    {
        if(check(usr_txt) && check(pass_txt))
        {
            Intent intent = new Intent(this,CreateTaskActivity.class);
            startActivity(intent);
        }
    }

    boolean check(EditText edit_txt)
    {
        String txt = edit_txt.getText().toString().trim();
        if(txt.isEmpty() || txt == null)
        {
            edit_txt.setError("Error");
            Log.e("Tag","Error");
            return false;
        }
        else
        {
            edit_txt.setError(null);
            return  true;
        }
    }
}
