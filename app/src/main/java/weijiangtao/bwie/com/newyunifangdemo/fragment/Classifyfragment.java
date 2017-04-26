package weijiangtao.bwie.com.newyunifangdemo.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import weijiangtao.bwie.com.newyunifangdemo.R;
import weijiangtao.bwie.com.newyunifangdemo.adapter.MyClassadapter;
import weijiangtao.bwie.com.newyunifangdemo.bean.Bean;
import weijiangtao.bwie.com.newyunifangdemo.view.MyGridview;

/**
 * Created by asus on 2017/4/11.
 * <p>
 * 未江涛
 */

public class Classifyfragment extends Fragment {

    private View view;
   
    
    
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            
            String s= (String) msg.obj;

            Log.i("sss",s);
         
            
            
            
        }
    };
    private OkHttpClient mOkHttpClient;
    private MyGridview class_gridview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.classify_fragment,null);
       
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        class_gridview = (MyGridview) view.findViewById(R.id.class_gridview);


        initdata();
        
        
    }

    private void initdata() {

        mOkHttpClient = new OkHttpClient();
        Request.Builder requestBuilder = new Request
                .Builder()
                .url("http://m.yunifang.com/yunifang/mobile/category/list?random=96333&encode=bf3386e14fe5bb0bcef234baebca2414");
        //可以省略，默认是GET请求
        requestBuilder.method("GET",null);
        Request request = requestBuilder.build();
        Call mcall= mOkHttpClient.newCall(request);
        mcall.enqueue(new Callback() {

                          private String s;

                          @Override
                          public void onFailure(Call call, IOException e) {

                              Toast.makeText(getActivity(),"失败",Toast.LENGTH_SHORT).show();
                          }

                          @Override
                          public void onResponse(Call call, Response response) throws IOException {

                              s = response.body().string();
                              Log.i("ddd", s);

                              getActivity().runOnUiThread(new Runnable() {
                                  @Override
                                  public void run() {
                                      Gson gson=new Gson();
                                      Bean bean = gson.fromJson(s, Bean.class);
                                      Bean.DataBean data = bean.getData();
                                      List<Bean.DataBean.GoodsBriefBean> goodsBrief = data.getGoodsBrief();
                                      MyClassadapter myclass=new MyClassadapter(goodsBrief,getActivity());
                                      class_gridview.setAdapter(myclass); 
                                      
                                  }
                              });
                              
                              
//                              Message mge=new Message();
//                              mge.obj=s;
//                              handler.handleMessage(mge);
                              
                              
                          }
                      }
        );
        
        
    }
}
