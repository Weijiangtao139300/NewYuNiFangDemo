package weijiangtao.bwie.com.newyunifangdemo.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import weijiangtao.bwie.com.newyunifangdemo.R;
import weijiangtao.bwie.com.newyunifangdemo.activity.Dingdanactivity;
import weijiangtao.bwie.com.newyunifangdemo.adapter.MyChaAdapter;
import weijiangtao.bwie.com.newyunifangdemo.bean.ChaBean;
import weijiangtao.bwie.com.newyunifangdemo.bean.Cheboxs;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by asus on 2017/4/11.
 * <p>
 * 未江涛
 */

public class Shoppingfragment extends Fragment implements Serializable{

    private View view;
    private int deid;
    private ListView cha_listv;
    private CheckBox quan;
    private TextView jiesuan;
    private List<ChaBean.CartItemListBean> cartItemList;
    private List<Cheboxs>list_c=new ArrayList<>();
    private MyChaAdapter cha;
    private Button but_jieshuan;
    int price=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shopping_fragment,null);


        SharedPreferences sha = getActivity().getSharedPreferences("si", MODE_PRIVATE);
        deid = sha.getInt("dengid", -1);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cha_listv = (ListView) view.findViewById(R.id.cha_listv);
        quan = (CheckBox) view.findViewById(R.id.quan);
        jiesuan = (TextView) view.findViewById(R.id.jiesuan);
        but_jieshuan = (Button) view.findViewById(R.id.but_jieshuan);

        initDatas();

        quan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quan.isChecked()) {
                    for (int i = 0; i <cartItemList.size() ; i++) {
                        cartItemList.get(i).setChekebox(true);
                    }
                } else {
                    for (int i = 0; i <cartItemList.size() ; i++) {
                        cartItemList.get(i).setChekebox(false);
                    }
                }
                setprice();
                cha.notifyDataSetChanged();
            }
        });

        but_jieshuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getActivity(),Dingdanactivity.class);
                ArrayList<ChaBean.CartItemListBean>list_j=new ArrayList<>();
                for (int i = 0; i <cartItemList.size() ; i++) {
                    if(cartItemList.get(i).isChekebox()){
                        list_j.add(cartItemList.get(i));
                    }
                }
                in.putExtra("list",list_j);
                in.putExtra("price",price);
                startActivity(in);
            }
        });
       
        
        
    }
    
    private int setType() {
        int type=0;
        for(int i=0;i<cartItemList.size();i++){
            if(cartItemList.get(i).isChekebox()) {
                type++;
            }
        }
        return type;
    }
    
    
    public void setprice() {

        
        for (int i = 0; i < cartItemList.size(); i++) {
            boolean checked = cartItemList.get(i).isChekebox();
            if(checked){
                price= price+(int) cartItemList.get(i).getPrice();
            }
        }
        jiesuan.setText(price+"$");
        
    }


    
    public void initDatas() {
       
       
        String path="http://169.254.217.5:8080/bullking1/cart?userID="+deid;
        //创建okHttpClient对象
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

                Log.i("yyyy", htmlStr);
               getActivity(). runOnUiThread(new Runnable() {

                   @Override
                    public void run() {
                        Gson gson = new Gson();
                        ChaBean chaBean = gson.fromJson(htmlStr, ChaBean.class);
                       cartItemList = chaBean.getCartItemList();
//                       for (int i = 0; i < cartItemList.size(); i++) {
//                           name1 = cartItemList.get(i).getName();
//                           price1 = (int) cartItemList.get(i).getPrice();
//
//                       }

                       cha = new MyChaAdapter(getActivity(),cartItemList);
                        cha_listv.setAdapter(cha);
                       
                       
                       
                       

                       cha.setOnClickListener(new MyChaAdapter.OnClickListener() {
                           @Override
                           public void onClickListener() {

                               int typedata=setType();
                               //设置金额的方法
                               setprice();
                               if(typedata==cartItemList.size()) {
                                   quan.setChecked(true);
                               }else{
                                   quan.setChecked(false);
                               }
                               cha.notifyDataSetChanged();
                           }


                           
                       });
                    }}
                );
            }
        });
        

    }
}
