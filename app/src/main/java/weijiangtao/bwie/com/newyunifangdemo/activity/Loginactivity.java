package weijiangtao.bwie.com.newyunifangdemo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import weijiangtao.bwie.com.newyunifangdemo.R;
import weijiangtao.bwie.com.newyunifangdemo.bean.IdBean;
import weijiangtao.bwie.com.newyunifangdemo.bean.ZhuceBean;

/**
 * Created by asus on 2017/4/21.
 * <p>
 * 未江涛
 */

public class Loginactivity extends AppCompatActivity {


    private List<IdBean>list=new ArrayList<>();
    
    private TextView tv_register;
    private EditText log_pwd;
    private EditText log_login;
    private Button log_login1;
    private String shuju;
    private SharedPreferences.Editor edit;
    private SharedPreferences sha1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        
        initview();
        initdata();

        sha1 = getSharedPreferences("si", MODE_PRIVATE);
        edit = sha1.edit();
        

    }

    private void initdata() {
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Loginactivity.this,Registeractivity.class);
                startActivity(in);
            }
        });

        log_login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = log_login.getText().toString();
                String pwd = log_pwd.getText().toString();
                
                if(TextUtils.isEmpty(name)&&TextUtils.isEmpty(pwd)){
                    Toast.makeText(Loginactivity.this,"账号或密码不能为空",Toast.LENGTH_SHORT);
                }else {
                    initDatas(name,pwd);
                    
                }


                
            }
        });
        
        
        
        
    }

    private void initDatas(String name,String pwd) {


        OkHttpClient mOkhttpclit = new OkHttpClient();
        FormEncodingBuilder buider=new FormEncodingBuilder();
        buider.add("name",name);
        buider.add("pwd",pwd);

        Request request=new Request.Builder()
                .url("http://192.168.56.1:8080/bullking1/login")
                .post(buider.build())
                .build();

        mOkhttpclit.newCall(request).enqueue(new Callback() {

            private String name1;

            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

                shuju = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Gson gson=new Gson();
                        ZhuceBean zhuceBean = gson.fromJson(shuju, ZhuceBean.class);
                        String dataStr = zhuceBean.getDataStr();
                        int id = zhuceBean.getId();

                        edit.putInt("dengid",id);
                        edit.commit();



                        if(dataStr.equals("login succeed")&&id!=-1){

                            
                            Toast.makeText(Loginactivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(Loginactivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }



        });
        
        
    }

    private void initview() {
        tv_register = (TextView) findViewById(R.id.tv_register);

        log_login = (EditText) findViewById(R.id.log_user);
        log_pwd = (EditText) findViewById(R.id.log_pwd);
        log_login1 = (Button) findViewById(R.id.log_login);

    }
}
