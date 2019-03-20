package com.dyhdyh.loadingbar.example.adapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dyhdyh.loadingbar.example.R;
import com.dyhdyh.widget.loadingbar2.LoadingBar;

import java.util.List;


/**
 * author  dengyuhan
 * created 2017/4/16 02:18
 */
public class TextAdapter extends RecyclerView.Adapter<TextAdapter.Holder> {
    private List<ExampleModel> mData;
    private Handler mHandler = new Handler();

    public TextAdapter(List<ExampleModel> data) {
        this.mData = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        final ExampleModel model = mData.get(position);
        holder.tv.setText(model.getText());
        if (model.isShow()) {
            LoadingBar.view(holder.itemView).show();

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    model.setShow(false);
                    LoadingBar.view(holder.itemView).cancel();
                }
            }, model.getDuration());
        } else {
            LoadingBar.view(holder.itemView).cancel();
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView tv;

        public Holder(View itemView) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

}
