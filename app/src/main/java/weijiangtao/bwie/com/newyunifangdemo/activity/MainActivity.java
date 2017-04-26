package weijiangtao.bwie.com.newyunifangdemo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import weijiangtao.bwie.com.newyunifangdemo.R;
import weijiangtao.bwie.com.newyunifangdemo.adapter.Myviewpage;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewpage = (ViewPager) findViewById(R.id.viewpage);
        View page1 = View.inflate(this, R.layout.page_1, null);
        View page2 = View.inflate(this, R.layout.page_2, null);
        View page3 = View.inflate(this, R.layout.page_3, null);
        View page4 = View.inflate(this, R.layout.page_4, null);
        View page5 = View.inflate(this, R.layout.page_5, null);




        List<View> list = new ArrayList<>();
        list.add(page1);
        list.add(page2);
        list.add(page3);
        list.add(page4);
        list.add(page5);

        viewpage.setAdapter(new Myviewpage(list));


        SharedPreferences sha = getSharedPreferences("name", MODE_PRIVATE);
        boolean bloo = sha.getBoolean("name", false);

        if (bloo) {
            Intent in = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(in);
        } else {
            page5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    SharedPreferences sha = getSharedPreferences("name", MODE_PRIVATE);
                    SharedPreferences.Editor edit = sha.edit();
                    edit.putBoolean("name", true);


                    Intent in = new Intent(MainActivity.this, WelcomeActivity.class);
                    startActivity(in);
                    edit.commit();

                }
            });
        }
    }
        
    }

