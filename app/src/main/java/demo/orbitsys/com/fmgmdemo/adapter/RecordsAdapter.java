package demo.orbitsys.com.fmgmdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextClassification;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;

import demo.orbitsys.com.fmgmdemo.R;
import demo.orbitsys.com.fmgmdemo.farmerdb.model.Records;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.MYViewHolder> {
List<Records> records;
Context context;
OnEditDeleteClick onEditDeleteClick;


    public RecordsAdapter(List<Records> records, Context context){
        this.records=records;
        this.context=context;
        this.onEditDeleteClick= (OnEditDeleteClick) context;

    }


    @NonNull
    @Override
    public MYViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_records_adapter,parent,false);
        return new MYViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MYViewHolder holder, final int position) {

        if(position%2==1){
            holder.llContainer.setBackgroundColor(Color.parseColor("#EEEEEE"));



        }

        else {
            holder.llContainer.setBackgroundColor(Color.parseColor("#E0E0E0"));

        }
        holder.tvCode.setText(String.valueOf(records.get(position).getFarmerCode()));
        holder.tvName.setText(records.get(position).getName());
        holder.tvWeight.setText(String.valueOf(records.get(position).getWeight()));
        holder.tvPrice.setText(String.valueOf(records.get(position).getPrice()));
        holder.tvDate.setText(DateFormat.getDateInstance().format(records.get(position).getDate()));
        Log.d("Date--",records.get(position).getDate().toString());
        holder.image_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEditDeleteClick.onRecordEditDelete(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public class MYViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvCode,tvName,tvWeight,tvPrice,tvDate;
        ImageView image_edit;
        LinearLayout llContainer;

        public MYViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCode=itemView.findViewById(R.id.tvCode);
            tvName=itemView.findViewById(R.id.tvName);
            tvWeight=itemView.findViewById(R.id.tvWeight);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            image_edit=itemView.findViewById(R.id.image_edit);
            tvDate=itemView.findViewById(R.id.tvDate);
            llContainer=itemView.findViewById(R.id.llContainer);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.image_edit:
                    onEditDeleteClick.onRecordEditDelete(getAdapterPosition());
                    break;

                    default:{
                        break;
                    }
            }
        }
    }
    public interface OnEditDeleteClick{
        void onRecordEditDelete(int position);
    }
}
