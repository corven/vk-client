package com.example.cos.vk_client.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cos.vk_client.ItemClickListener;
import com.example.cos.vk_client.R;
import com.squareup.picasso.Picasso;
import com.vk.sdk.api.model.VKApiModel;
import com.vk.sdk.api.model.VKList;

import org.json.JSONException;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder>{

    private Context context;
    private VKList vkList;

    public FriendsAdapter(Context context, VKList vkList) {
        this.context = context;
        this.vkList = vkList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friends_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VKApiModel model = vkList.get(position);

        try {
            holder.tvFirstName.setText((String) model.fields.get("first_name"));
            holder.tvLastName.setText((String) model.fields.get("last_name"));
            if (Integer.parseInt(String.valueOf(model.fields.get("online"))) == 1) {
                holder.tvOnline.setText("Online");
            }else {
                holder.tvOnline.setText("");
            }
            Picasso.with(context).load((String) model.fields.get("photo_50")).into(holder.img);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return vkList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemClickListener clickListener;
        TextView tvFirstName, tvLastName, tvOnline;
        ImageView img;

        public ViewHolder(View v) {
            super(v);
            tvFirstName = (TextView) v.findViewById(R.id.tvFriendsFirstName);
            tvLastName = (TextView) v.findViewById(R.id.tvFriendsLastName);
            tvOnline = (TextView) v.findViewById(R.id.tvFriendsOnline);
            img = (ImageView) v.findViewById(R.id.imgFriend);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition(), false);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }
    }
}
