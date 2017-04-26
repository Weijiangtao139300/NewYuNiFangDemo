package weijiangtao.bwie.com.newyunifangdemo.imagelouder;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.decode.ImageDecodingInfo;

import weijiangtao.bwie.com.newyunifangdemo.R;

/**
 * Created by asus on 2017/4/13.
 * <p>
 * 未江涛
 */

public class Inagelouder extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

      ImageLoaderConfiguration image=new ImageLoaderConfiguration.Builder(this)
               
              
                .build();

        ImageLoader.getInstance().init(image);
        
    }
}
