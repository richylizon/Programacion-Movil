package com.loopwiki.loginregisterwithsqlite.ecografias;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.loopwiki.loginregisterwithsqlite.R;

public class ImageAdapter extends PagerAdapter {
    private Context mContext;
    private int[] mImageIds = new int[] {R.drawable.ecoprimermes647_483,R.drawable.ecosegundomes684_483,
                                         R.drawable.ecotercermes644_483,R.drawable.ecocuartomes609_483, R.drawable.ecoquintomes632_483,
                                         R.drawable.ecosextomes700_465,R.drawable.ecoseptimomes618_483,R.drawable.ecooctavomes700_476,
                                         R.drawable.econovenomes689_483};

    ImageAdapter(Context context){
        mContext = context;
    }



    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mImageIds[position]);
        container.addView(imageView,0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
