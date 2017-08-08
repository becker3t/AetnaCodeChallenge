package exercise.tbecker.aetnacodingexercise.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import exercise.tbecker.aetnacodingexercise.R;
import exercise.tbecker.aetnacodingexercise.models.MyData;
import exercise.tbecker.aetnacodingexercise.models.ResponseItem;

/**
 * Created by Android on 8/7/2017.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<MyData> myResponseDataList;
    private Context context;
    private ListItemClickListener listItemClickListener;

    public MyRecyclerAdapter(List<MyData> myResponseDataList, Context context) {
        this.myResponseDataList = myResponseDataList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyData dataResponse = myResponseDataList.get(position);
        Glide.with(context).load(dataResponse.getImgUrl()).into(holder.imageThumbnail);
        holder.textTitle.setText(dataResponse.getTitle());
    }

    @Override
    public int getItemCount() {
        return myResponseDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.title_textview)
        TextView textTitle;

        @BindView(R.id.flickr_imgvw)
        ImageView imageThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(listItemClickListener != null) {
                listItemClickListener.OnItemClick(view, getAdapterPosition());
            }
        }
    }

    public void setListItemClickListener (ListItemClickListener listener) {
        this.listItemClickListener = listener;
    }

    public interface ListItemClickListener {
        void OnItemClick(View v, int position);
    }
}
