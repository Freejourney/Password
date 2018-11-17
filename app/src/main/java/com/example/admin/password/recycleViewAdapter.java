package com.example.admin.password;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.morphingbutton.MorphingButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by mamadhaxor on 22/01/15.
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


    recycleViewAdapter(ItemsModelList data, Context context)
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
        ItemsModel itemsModel = data.getItem(i);
        Bitmap bitmap = BitmapFactory.decodeResource(viewHolder.view.getResources(), itemsModel.getImage());
        viewHolder.imageView.setImageBitmap(bitmap);
        morphToSquare(viewHolder.btn_getpasswd, 0);
        viewHolder.btn_getpasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890~!@#$%^&*()_+{}|:><?";
                int length = str.length();
                //由Random生成随机数
                Random random=new Random();
                StringBuffer sb=new StringBuffer();
                //长度为几就循环几次
                for(int i=0; i<11; ++i) {
                    //产生0-61的数字
                    int number = random.nextInt(length);
                    //将产生的数字通过length次承载到sb中
                    sb.append(str.charAt(number));
                }
                password = sb.toString();
                ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(password);
                onMorphButton1Clicked(viewHolder.btn_getpasswd, i);

                Toast.makeText(context, "password extract success", Toast.LENGTH_LONG);
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
