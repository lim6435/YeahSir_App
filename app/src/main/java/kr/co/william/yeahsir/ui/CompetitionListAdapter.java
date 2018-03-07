package kr.co.william.yeahsir.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.william.yeahsir.R;

/**
 * Created by sheo on 2018-03-06.
 */

public class CompetitionListAdapter extends RecyclerView.Adapter<CompetitionListAdapter.ViewHolder> {

    private ArrayList<String> items;

    public CompetitionListAdapter(ArrayList<String> items) {
        this.items = items;
    }

    @Override
    public CompetitionListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_competition_list_view, parent, false);
        return new CompetitionListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_competition.setText(items.get(position));
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
                    System.out.println("[sheotest] 대회리스트 클릭 " + getAdapterPosition() + " 번째 " + items.get(getAdapterPosition()));
                    // sheotest TODO 참가신청 화면으로 이동
                }
            });
        }
    }

}