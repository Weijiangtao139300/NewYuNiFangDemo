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

public class MyChunJiadapter extends RecyclerView.Adapter<MyChunJiadapter.Viewhouder> {
    
    private List<Homebean.DataBean.BestSellersBean.GoodsListBeanX>lists;
    private Context context;
    public OnItemClickListener itemClickListener;
    public OnItemLongClickListener itemLongClickListener;

    


    public interface OnItemLongClickListener{
        void onitemLongClick(View view,int position);
    }
    //定义条目点击事件
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    public MyChunJiadapter(List<Homebean.DataBean.BestSellersBean.GoodsListBeanX> list, Context context) {
        this.lists = list;
        this.context = context;
    }

    
    
    
    
    
    
    @Override
    public Viewhouder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= View.inflate(context, R.layout.chunji_item_item,null);

        final Viewhouder holder=new Viewhouder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if(itemClickListener!=null){
                    itemClickListener.onItemClick(v,position);
                }

            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = holder.getAdapterPosition();
                if(itemLongClickListener!=null){
                    itemLongClickListener.onitemLongClick(v,position);
                }
                
                
                return true;
            }
        });
        
        
        
        
        
        return holder;
    }

    @Override
    public void onBindViewHolder(Viewhouder holder, int position) {

        ImageLoader.getInstance().displayImage(lists.get(position).getGoods_img(),holder.image1);
        holder.text1.setText(lists.get(position).getEfficacy());
        holder.text2.setText(lists.get(position).getGoods_name());
        holder.text3.setText(lists.get(position).getShop_price()+"");



    }

   

    @Override
    public int getItemCount() {
        return lists.size();
    }



    public class Viewhouder extends RecyclerView.ViewHolder{
        private final TextView text1,text2,text3;
        private final ImageView image1;

        public Viewhouder(View itemView) {
            super(itemView);


            image1 = (ImageView) itemView.findViewById(R.id.chunji_item_item_image);
            
            text1 = (TextView) itemView.findViewById(R.id.chunji_item_item_text1);
            text2 = (TextView) itemView.findViewById(R.id.chunji_item_item_text2);
            text3 = (TextView) itemView.findViewById(R.id.chunji_item_item_text3);

            
            
            
        }
    }
}
