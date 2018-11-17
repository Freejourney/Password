package com.example.admin.password;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

/**
 * Created by mamadhaxor on 22/01/15.
 */
public class recycleViewAdapter extends RecyclerView.Adapter<recycleViewAdapter.ViewHolder>
{
    private ItemsModelList data;
    recycleViewAdapter(ItemsModelList data)
    {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        ItemsModel itemsModel = data.getItem(i);
        Bitmap bitmap = BitmapFactory.decodeResource(viewHolder.view.getResources(), itemsModel.getImage());
        viewHolder.imageView.setImageBitmap(bitmap);
        viewHolder.btn_getpasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", data);
                bundle.putInt("pos", i);
                Intent intent = new Intent(viewHolder.view.getContext(), DetailActivity.class);
                intent.putExtras(bundle);
                viewHolder.view.getContext().startActivity(intent);
                */

                Log.i("Clicked on item", "just clicked on item "+ i);
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
            MorphingButton.Params circle = MorphingButton.Params.create()
                    .duration(500)
                    .cornerRadius(R.dimen.mb_height_56) // 56 dp
                    .width(R.dimen.mb_height_56) // 56 dp
                    .height(R.dimen.mb_height_56) // 56 dp
                    .color(R.color.mb_green) // normal state color
                    .colorPressed(R.color.mb_green_dark) // pressed state color
                    .icon(R.drawable.ic_done); // icon
            btn_getpasswd.morph(circle);
            imageView = (ImageView)view.findViewById(R.id.image);
        }
    }
}
