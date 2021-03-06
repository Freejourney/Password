package com.example.admin.password.adapter;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.dd.morphingbutton.MorphingButton;
import com.example.admin.password.Bean.ItemsModel;
import com.example.admin.password.activities.MainActivity;
import com.example.admin.password.utils.ItemsModelList;
import com.example.admin.password.R;
import com.example.admin.password.utils.MyToast;

import java.util.HashMap;
import java.util.Map;

/*
 * 文件名：recycleViewAdapter
 * 作者：created by admin on 2018 十一月
 * 描述：
 *      从数据库中读取出数据并显示于屏幕上
 */
public class recycleViewAdapter extends RecyclerView.Adapter<recycleViewAdapter.ViewHolder>
{
    private ItemsModelList data;
    private Context context;

    private Map<Integer, Integer> mStatus = new HashMap<>();
    private String password = "";


    public String getPassword() {
        return password;
    }


    public recycleViewAdapter(ItemsModelList data, Context context)
    {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_row, viewGroup, false);
        return new ViewHolder(view);
    }

    public int dimen(@DimenRes int resId) {
        return (int) this.context.getResources().getDimension(resId);
    }

    public int color(@ColorRes int resId) {
        return this.context.getResources().getColor(resId);
    }

    public int integer(@IntegerRes int resId) {
        return this.context.getResources().getInteger(resId);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final ItemsModel itemsModel = data.getItem(i);
        Bitmap bitmap = BitmapFactory.decodeResource(viewHolder.view.getResources(), itemsModel.getImage());
        viewHolder.imageView.setImageBitmap(bitmap);
        viewHolder.btn_getpasswd.setBackgroundColor(itemsModel.getBtn_color());
        viewHolder.btn_getpasswd.setText(String.valueOf(i+1));
        viewHolder.btn_getpasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = itemsModel.getPwd().getPassword();
                ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(password);

                MyToast.showOnUIThreadx(context, "password extract success", Toast.LENGTH_LONG);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.getItemCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
//        public TextView firstText;
        public ImageView imageView;
        public MorphingButton btn_getpasswd;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            btn_getpasswd = (MorphingButton)view.findViewById(R.id.btn_getpasswd);
            imageView = (ImageView)view.findViewById(R.id.image);
        }
    }

    private void onMorphButton1Clicked(final MorphingButton btnMorph, int index) {

        if (!this.mStatus.containsKey(index)) {
            mStatus.put(index, 1);
            morphToSuccess(btnMorph);
        } else {
            if (this.mStatus.get(index) == 1) {
                morphToSquare(btnMorph, 1);
                mStatus.put(index, 0);
            } else {
                morphToSuccess(btnMorph);
                mStatus.put(index, 1);
            }
        }
    }

    private void morphToSquare(final MorphingButton btnMorph, int duration) {
        MorphingButton.Params square = MorphingButton.Params.create()
                .duration(duration)
                .cornerRadius(dimen(R.dimen.mb_corner_radius_2))
                .width(ViewGroup.LayoutParams.WRAP_CONTENT)
                .height(ViewGroup.LayoutParams.MATCH_PARENT)
                .color(color(R.color.mb_blue))
                .colorPressed(color(R.color.mb_blue_dark));
        btnMorph.morph(square);
    }

    private void morphToSuccess(final MorphingButton btnMorph) {
        MorphingButton.Params circle = MorphingButton.Params.create()
                .duration(1)
                .cornerRadius(dimen(R.dimen.mb_height_56))
                .width(ViewGroup.LayoutParams.WRAP_CONTENT)
                .height(ViewGroup.LayoutParams.MATCH_PARENT)
                .color(color(R.color.mb_green))
                .colorPressed(color(R.color.mb_green_dark))
                .icon(R.drawable.ic_done);
        btnMorph.morph(circle);
    }
}
