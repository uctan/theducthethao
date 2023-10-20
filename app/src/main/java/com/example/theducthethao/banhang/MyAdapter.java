package com.example.theducthethao.banhang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.theducthethao.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class  MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private Context context;
    private List<Dataclass> dataList;
    private List<Boolean> isFavoriteList; // Danh sách trạng thái yêu thích của các sản phẩm
    private DatabaseReference databaseReference;

    public MyAdapter(Context context, List<Dataclass> dataList, List<Boolean> isFavoriteList) {
        this.context = context;
        this.dataList = dataList;
        this.isFavoriteList = isFavoriteList;
        for (Dataclass data : dataList) {
            isFavoriteList.add(data.isFavorite());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);
        holder.recTitle.setText(dataList.get(position).getDataTitle());
        holder.recLang.setText(dataList.get(position).getDataLang());



        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Title", dataList.get(holder.getAdapterPosition()).getDataTitle());
                intent.putExtra("Description", dataList.get(holder.getAdapterPosition()).getDataDesc());
                intent.putExtra("Giaca", dataList.get(holder.getAdapterPosition()).getDataLang());

                context.startActivity(intent); // Điều chỉnh dòng này từ startActivities(intent) thành startActivity(intent)
            }
        });
        boolean isFavorite = isFavoriteList.get(position);
        if (isFavorite) {
            holder.favoriteIcon.setImageResource(R.drawable.redstart);
        } else {
            holder.favoriteIcon.setImageResource(R.drawable.start);
        }

        holder.favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean currentFavorite = isFavoriteList.get(position); // Lấy trạng thái yêu thích hiện tại
                isFavoriteList.set(position, !currentFavorite); // Cập nhật trạng thái yêu thích mới
                try {
                    updateFavoriteStatus(dataList.get(position).getDataTitle(), !currentFavorite);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<Dataclass> searchList) {
        dataList = searchList;
        notifyDataSetChanged();
    }

    public void setFavoriteItem(int position, boolean isFavorite) {
        isFavoriteList.set(position, isFavorite);
        notifyItemChanged(position);
    }

    public void updateFavoriteStatus(String dataTitle, boolean Favorite) throws UnsupportedEncodingException {
        if (dataTitle != null) {
            DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Android Tutorials");
            // Sử dụng dataTitle làm định danh cho sản phẩm
            databaseRef.child(dataTitle).child("favorite").setValue(Favorite);
        }
    }

}

class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView recImage;
    TextView recTitle, recLang;
    CardView recCard;
    ImageButton favoriteIcon;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recImage = itemView.findViewById(R.id.recImage);
        recTitle = itemView.findViewById(R.id.recTitle);
        recLang = itemView.findViewById(R.id.recLang);
        recCard = itemView.findViewById(R.id.recCard);
        favoriteIcon = itemView.findViewById(R.id.favoriteIcon);
    }
}


