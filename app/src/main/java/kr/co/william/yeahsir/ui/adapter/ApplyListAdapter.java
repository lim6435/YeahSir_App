package kr.co.william.yeahsir.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.william.yeahsir.R;
import kr.co.william.yeahsir.data.CompetitionVo;

/**
 * Created by sheo on 2018-03-13.
 */

public class ApplyListAdapter extends RecyclerView.Adapter<ApplyListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CompetitionVo> items;

    public ApplyListAdapter(Context context, ArrayList<CompetitionVo> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ApplyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_apply_list_view, parent, false);
        return new ApplyListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ApplyListAdapter.ViewHolder holder, int position) {
        holder.tv_competition.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_competition;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_competition = (TextView) itemView.findViewById(R.id.tv_attend);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}