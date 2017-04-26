package weijiangtao.bwie.com.newyunifangdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import weijiangtao.bwie.com.newyunifangdemo.R;
import weijiangtao.bwie.com.newyunifangdemo.bean.ZhuceBean;

/**
 * Created by asus on 2017/4/21.
 * <p>
 * 未江涛
 */

public class Registeractivity extends AppCompatActivity {

    private EditText reg_user;
    private EditText reg_pwd;
    private Button bt_regst;
    private String s;
   


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);


        initview();
        initData();
        
        
        


    }

    private void initData() {
        bt_regst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
               String name = reg_user.getText().toString();
                String   pwd = reg_pwd.getText().toString();
                
                if(TextUtils.isEmpty(reg_user.getText().toString())&&TextUtils.isEmpty(reg_pwd.getText().toString())){
                    Toast.makeText(Registeractivity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
                  return;
                }else {
                    //Toast.makeText(Registeractivity.this,"eee",Toast.LENGTH_SHORT).show();
                    initDatas(name,pwd);
                    finish();
                }
                
            }
        });
    }

    public void initDatas(String name,String pwd) {
        Log.d("zzz", name + "   " + pwd);
        String path="http://192.168.56.1:8080/bullking1/register?name="+name+"&pwd="+pwd+"";
        //创建okHttpClient对象192.168.56.1
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url(path)
                .get()
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Request request, IOException e)
            {
            }
            @Override
            public void onResponse(final Response response) throws IOException
            {
                final   String htmlStr =  response.body().string();
              
                Log.i("zzz", htmlStr);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        ZhuceBean zhuceBean = gson.fromJson(htmlStr, ZhuceBean.class);
                        String dataStr = zhuceBean.getDataStr();
                        int id = zhuceBean.getId();
                        if (dataStr.equals("register succeed")) {
                            Toast.makeText(Registeractivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Registeractivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }


                    }}
                );
            }
        });
      

             

            }

            private void initview() {
                reg_user = (EditText) findViewById(R.id.reg_user);
                reg_pwd = (EditText) findViewById(R.id.reg_pwd);
                bt_regst = (Button) findViewById(R.id.bt_regst);


            }
        }
    
   
