package kr.co.william.yeahsir.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.william.yeahsir.R;

/**
 * Created by sheo on 2018-03-01.
 */

public class AttendListAdapter extends RecyclerView.Adapter<AttendListAdapter.ViewHolder> {

    private ArrayList<String> items;

    public AttendListAdapter(ArrayList<String> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attend_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_no.setText(String.valueOf(position + 1));
        holder.tv_attend.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_no;
        private TextView tv_attend;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_no = (TextView) itemView.findViewById(R.id.tv_no);
            tv_attend = (TextView) itemView.findViewById(R.id.et_id);
        }
    }

}
