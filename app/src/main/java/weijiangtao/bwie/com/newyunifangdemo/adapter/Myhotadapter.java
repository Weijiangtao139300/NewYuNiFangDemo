package weijiangtao.bwie.com.newyunifangdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import weijiangtao.bwie.com.newyunifangdemo.R;
import weijiangtao.bwie.com.newyunifangdemo.bean.Homebean;

/**
 * Created by asus on 2017/4/14.
 * <p>
 * 未江涛
 */

public class Myhotadapter extends RecyclerView.Adapter<Myhotadapter.Viewhouder> {
    
    private List<Homebean.DataBean.SubjectsBean.GoodsListBean>list;
    private Context context;
   

    public Myhotadapter(List<Homebean.DataBean.SubjectsBean.GoodsListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Viewhouder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= View.inflate(context, R.layout.hot_item_item,null);

        Viewhouder holder=new Viewhouder(view);
        
        
        return holder;
    }

    @Override
    public void onBindViewHolder(Viewhouder holder, int position) {


     
            holder.text1.setText(list.get(position).getEfficacy());
            holder.text2.setText(list.get(position).getShop_price()+"");
            ImageLoader.getInstance().displayImage(list.get(position).getGoods_img(),holder.image2);
       
        
        
        
       



    }

   

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class Viewhouder extends RecyclerView.ViewHolder{
        private final TextView text1,text2;
        private final ImageView image2;

        public Viewhouder(View itemView) {
            super(itemView);


           
            image2 = (ImageView) itemView.findViewById(R.id.hot_item_item_image);
            text1 = (TextView) itemView.findViewById(R.id.hot_item_item_text1);
            text2 = (TextView) itemView.findViewById(R.id.hot_item_item_text2);
           

            
            
            
        }
    }
}
