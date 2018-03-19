package kr.co.william.yeahsir.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.william.yeahsir.R;
import kr.co.william.yeahsir.data.MemberVo;

/**
 * Created by sheo on 2018-03-13.
 */

public class ApplyMemberListAdapter extends RecyclerView.Adapter<ApplyMemberListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<MemberVo> items;

    public ApplyMemberListAdapter(Context context, ArrayList<MemberVo> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ApplyMemberListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_apply_list_view, parent, false);
        return new ApplyMemberListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ApplyMemberListAdapter.ViewHolder holder, final int position) {
//        holder.tv_competition.setText(items.get(position).getName());

        holder.cb_id.setChecked(false);
        holder.cb_id.setTag(items.get(position));

        holder.cb_id.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                MemberVo memberVo = (MemberVo) cb.getTag();
//                memberVo.setSelected(cb.isChecked());
//                items.get(position).setSelected(cb.isChecked());

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox cb_id;

        public ViewHolder(View itemView) {
            super(itemView);
            cb_id = (CheckBox) itemView.findViewById(R.id.cb_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

}