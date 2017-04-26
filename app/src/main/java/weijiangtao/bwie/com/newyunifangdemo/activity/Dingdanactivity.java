package weijiangtao.bwie.com.newyunifangdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import weijiangtao.bwie.com.newyunifangdemo.R;
import weijiangtao.bwie.com.newyunifangdemo.bean.ChaBean;
import weijiangtao.bwie.com.newyunifangdemo.bean.OrderBean;

/**
 * Created by asus on 2017/4/25.
 * <p>
 * 未江涛
 */

public class Dingdanactivity extends AppCompatActivity implements Serializable {

    private List<ChaBean.CartItemListBean> list;
    private ListView list_ding;
    private Button but_tijiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan);

        list_ding = (ListView) findViewById(R.id.list_ding);

        but_tijiao = (Button) findViewById(R.id.but_tijiao);
        TextView text_tijiao = (TextView) findViewById(R.id.text_tijiao);

        Intent intent = getIntent();
        list = (List<ChaBean.CartItemListBean>) intent.getSerializableExtra("list");
        int price = intent.getIntExtra("price", 0);
        text_tijiao.setText(price+""+"￥");
        Log.i("yuyu",list.toString());
        DingAdapter ding=new DingAdapter();
        list_ding.setAdapter(ding);
        
        
        but_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHttp();
            }
        });
    }

    private void setHttp() {

        AsyncHttpClient client = new AsyncHttpClient();
        String path="http://lexue365.51dangao.cn/api/order/add_order";
        client.addHeader("userid",465+"");
        client.addHeader("cltid", "1");
        client.addHeader("token", "bbb6e99c4cd22930ea4c945d9932f98a");
        client.addHeader("mobile", "15718812708");
        RequestParams params = new RequestParams();
        params.put("activity_id",4);
        params.put("time_id",2927);
        params.put("child_num",1);
        params.put("contact","噶");
        params.put("mobile","15718812708");
        params.put("remark",1);
        client.post(Dingdanactivity.this, path, params, new TextHttpResponseHandler() {


            private OrderBean orderBean;

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "获取订单失败", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Toast.makeText(getApplicationContext(), "获取订单成功", Toast.LENGTH_SHORT).show();
               Gson gson=new Gson();
                
                Log.i("oooo",responseString);
                OrderBean orde= gson.fromJson(responseString, OrderBean.class);
                Intent intent=new Intent(Dingdanactivity.this,UpOrderActivity.class);
                intent.putExtra("orderBean", orde);
                startActivity(intent);

            }
        });
        
    }


    class DingAdapter extends BaseAdapter implements Serializable{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view =View.inflate(Dingdanactivity.this,R.layout.ding_item,null);
            TextView text1= (TextView) view.findViewById(R.id.ding_text1);
            TextView text2= (TextView) view.findViewById(R.id.ding_text2);
            ImageView image1= (ImageView) view.findViewById(R.id.ding_image);

            text1.setText(list.get(position).getName());
            text2.setText(list.get(position).getPrice()+"");
            
            ImageLoader.getInstance().displayImage(list.get(position).getPic(),image1);
            return view;
        }
    }
    
    
    
    }
