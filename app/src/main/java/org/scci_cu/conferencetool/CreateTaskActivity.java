package org.scci_cu.conferencetool;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        ButterKnife.bind(this);
    }

    @BindView(R.id.date_txt)
    TextView date_txt ;

    @OnClick(R.id.date_txt)
    void select_date() {

        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date_txt.setText(year + " - " + (month+1) + " - " + day);
            }
        };

        new DatePickerDialog(CreateTaskActivity.this,
                listener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
                ).show();

    }
    @BindView(R.id.time_txt)
    TextView time_txt ;
    @OnClick (R.id.time_txt)
    void selecttime(){
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int h, int m) {
                if(h>12) {
                    h = h - 12;
                }
                time_txt.setText(h + ":" + m );

            }
        };

        new TimePickerDialog(this , listener ,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false).show();;



    }


}
