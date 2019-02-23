package demo.orbitsys.com.fmgmdemo.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Switch;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import demo.orbitsys.com.fmgmdemo.R;
import demo.orbitsys.com.fmgmdemo.adapter.RecordsAdapter;
import demo.orbitsys.com.fmgmdemo.farmerdb.FarmerDB;
import demo.orbitsys.com.fmgmdemo.farmerdb.model.Records;

public class ShowDetails extends AppCompatActivity implements RecordsAdapter.OnEditDeleteClick {
    RecyclerView recycler_view;
    TextView tvTotalWeight,tvTotalPrice;
    FarmerDB farmerDB;
    List<Records> records;
    RecordsAdapter recordsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        recycler_view=findViewById(R.id.recycler_view);
        tvTotalWeight=findViewById(R.id.tvTotalWeight);
        tvTotalPrice=findViewById(R.id.tvTotalPrice);
        displayList();
        initializeVies();

    }



    private void displayList() {
        farmerDB = FarmerDB.getInstance(ShowDetails.this);
        new RetrieveTask(this).execute();
        tvTotalWeight.setText(String.valueOf(farmerDB.getFarmerDao().getWeightSum()));
        tvTotalPrice.setText(String.valueOf(farmerDB.getFarmerDao().getPriceSum()));



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        displayList();
    }

    private static class RetrieveTask extends AsyncTask<Void,Void,List<Records>>{
        WeakReference<ShowDetails> activityReference;
        private RetrieveTask(ShowDetails context){
            activityReference=new WeakReference<>(context);

        }

        @Override
        protected List<Records> doInBackground(Void... voids) {
            if(activityReference.get()!=null){
                return activityReference.get().farmerDB.getFarmerDao().getRecords();
            }else
            return null;
        }

        @Override
        protected void onPostExecute(List<Records> records) {
            if(records!=null&&records.size()>0){
                activityReference.get().records.clear();
                activityReference.get().records.addAll(records);
                activityReference.get().recordsAdapter.notifyDataSetChanged();


            }
        }
    }
    private void initializeVies() {
        recycler_view.setLayoutManager(new LinearLayoutManager(ShowDetails.this));
        records=new ArrayList<>();
        recordsAdapter=new RecordsAdapter(records,ShowDetails.this);
        recycler_view.setAdapter(recordsAdapter);

    }
    @Override
    public void onRecordEditDelete(final int position) {
        new AlertDialog.Builder(ShowDetails.this).setTitle("Are you want to?")
                .setItems(new String[]{"Delete", "Update"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch(i){
                            case 0:
                                farmerDB.getFarmerDao().deleteRow(records.get(position));
                                records.remove(position);
                                tvTotalWeight.setText(String.valueOf(farmerDB.getFarmerDao().getWeightSum()));
                                tvTotalPrice.setText(String.valueOf(farmerDB.getFarmerDao().getPriceSum()));
                                recordsAdapter.notifyDataSetChanged();
                                break;
                            case 1:
                                startActivityForResult(new Intent(ShowDetails.this,AddDetails.class).putExtra(
                                        "records",records.get(position)),100);
                                records.set(position,records.get(position));
                                break;

                        }
                    }
                }).show();

    }
}
