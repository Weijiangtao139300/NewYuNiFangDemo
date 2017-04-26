package weijiangtao.bwie.com.newyunifangdemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import weijiangtao.bwie.com.newyunifangdemo.R;
import weijiangtao.bwie.com.newyunifangdemo.bean.ChaBean;
import weijiangtao.bwie.com.newyunifangdemo.bean.Cheboxs;
import weijiangtao.bwie.com.newyunifangdemo.fragment.Shoppingfragment;

/**
 * Created by asus on 2017/4/23.
 * <p>
 * 未江涛
 */

public class MyChaAdapter extends BaseAdapter {
    
    private Context context;
    private List<ChaBean.CartItemListBean>list;
    public OnClickListener listener;
    private List<String>list_2=new ArrayList<>();

    public interface OnClickListener{
        void onClickListener();
    }
    public void setOnClickListener(OnClickListener onClickListener) {
        this.listener = onClickListener;
    }

    public MyChaAdapter(Context context, List<ChaBean.CartItemListBean> list) {
        this.context = context;
        this.list = list;
       
    }

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Viewhouder vv;
        if(convertView==null){
            convertView =View.inflate(context, R.layout.chaxun,null);
            vv=new Viewhouder();
            vv.checbox= (CheckBox) convertView.findViewById(R.id.check_1);
            vv.chaimage= (ImageView) convertView.findViewById(R.id.chaimage);
            vv.chatext1= (TextView) convertView.findViewById(R.id.chaname);
            vv.chatext2= (TextView) convertView.findViewById(R.id.chajiage);
            convertView.setTag(vv);
        }else {
            vv= (Viewhouder) convertView.getTag();
        }

        ImageLoader.getInstance().displayImage(list.get(position).getPic(),vv.chaimage);
        vv.chatext1.setText(list.get(position).getName());
        vv.chatext2.setText(list.get(position).getPrice()+""+"￥");
        vv.checbox.setChecked(list.get(position).isChekebox());
        vv.checbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (vv.checbox.isChecked()){
                        list.get(position).setChekebox(true);
                        
                    }else {
                        list.get(position).setChekebox(false);
                    }

                    listener.onClickListener();
                  
                }
        });

        
        return convertView;
    }
    
    
    class Viewhouder{
        CheckBox checbox;
        ImageView chaimage;
        TextView chatext1,chatext2;
    }
}
