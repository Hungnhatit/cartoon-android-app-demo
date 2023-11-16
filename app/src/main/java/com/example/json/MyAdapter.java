package com.example.json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<MyItem> itemList;

    public MyAdapter(Context context, int layout, List<MyItem> itemList)  {
        this.context = context;
        this.layout = layout;
        this.itemList = itemList;
    }


    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView myTitle, myYear;
        ImageView myImage;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);

            holder = new ViewHolder();
            //ánh xạ
            holder.myTitle = view.findViewById(R.id.name_txt);
            holder.myYear = view.findViewById(R.id.year_txt);
            holder.myImage = view.findViewById(R.id.imageView);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }

        //gasn gia tri

        MyItem Item=itemList.get(i);
        holder.myTitle.setText(Item.getName());
        holder.myYear.setText(Item.getYear());
        Picasso.get().load(Item.getImage()).into(holder.myImage);

        return view;
    }
}
