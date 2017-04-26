package weijiangtao.bwie.com.newyunifangdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import weijiangtao.bwie.com.newyunifangdemo.R;

/**
 * Created by asus on 2017/4/11.
 * <p>
 * 未江涛
 */

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);


        Timer tiamer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                Intent in=new Intent(WelcomeActivity.this,Homeactivity.class);
                startActivity(in);


            }
        };
        tiamer.schedule(task,3000);



    }

}
