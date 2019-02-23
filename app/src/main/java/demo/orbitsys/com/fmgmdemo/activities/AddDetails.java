package demo.orbitsys.com.fmgmdemo.activities;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;

import demo.orbitsys.com.fmgmdemo.R;
import demo.orbitsys.com.fmgmdemo.farmerdb.FarmerDB;
import demo.orbitsys.com.fmgmdemo.farmerdb.model.Records;

public class AddDetails extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public  final String TAG=getClass().getName();
 private    EditText edtCode,edtName,edtWeight,edtPrice,edtDate;
 private    Button btnAddDetails;
 private    FarmerDB farmerDB;
   private Records records;
    private Date date;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;
    private boolean update;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_details);
        edtCode=findViewById(R.id.edtCode);
        edtName=findViewById(R.id.edtName);
        edtWeight=findViewById(R.id.edtWeight);
        edtPrice=findViewById(R.id.edtPrice);
        edtDate=findViewById(R.id.edtDate);
        btnAddDetails=findViewById(R.id.btnAddDetails);
        farmerDB=FarmerDB.getInstance(AddDetails.this);
        calendar = Calendar.getInstance();
        if((records=(Records)getIntent().getSerializableExtra("records"))!=null){
            getSupportActionBar().setTitle("Update Record");
            btnAddDetails.setText("Update");
            update=true;
            edtCode.setText(String.valueOf(records.getFarmerCode()));
            edtName.setText(records.getName());
            edtPrice.setText(String.valueOf(records.getPrice()));
            edtWeight.setText(String.valueOf(records.getWeight()));
            edtDate.setText(String.valueOf(records.getDate().toLocaleString().substring(0,12)));

        }

        datePickerDialog = new DatePickerDialog(this, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

edtDate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        datePickerDialog.show();

    }
});
        btnAddDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* records.setFarmerCode(Integer.parseInt(edtCode.getText().toString()));
                records.setName(edtName.getText().toString());
                records.setWeight(Double.parseDouble(edtWeight.getText().toString()));
                records.setWeight(Double.parseDouble(edtPrice.getText().toString()));
                records.setDate(date);*/
if(checkValidation()){
    if(update){
        records.setFarmerCode(Integer.parseInt(edtCode.getText().toString()));
        records.setName(edtName.getText().toString());
        records.setWeight(Double.parseDouble(edtWeight.getText().toString()));
        records.setWeight(Double.parseDouble(edtPrice.getText().toString()));
        if(date!=null){
            records.setDate(date);

        }
        else{
            records.setDate(records.getDate());
        }
        farmerDB.getFarmerDao().updateRecord(records);
        finish();
    }else {
        records = new Records(Integer.parseInt(edtCode.getText().toString()), edtName.getText().toString()
                , Double.parseDouble(edtWeight.getText().toString()), Double.parseDouble(edtPrice.getText().toString())
                , date);

        new InsertTask(AddDetails.this, records).execute();
    }
}





            }
        });

    }

    private boolean checkValidation() {
        if(edtCode.getText().toString().trim().isEmpty()){
            edtCode.setError("Enter Code");
        }else if(edtName.getText().toString().trim().isEmpty()){
            edtName.setError("Enter Name");
        }else if(edtPrice.getText().toString().trim().isEmpty()){
            edtPrice.setError("Enter Price");
        }else if(edtWeight.getText().toString().trim().isEmpty()){
            edtWeight.setError("Enter Weight");
        }else if(edtDate.getText().toString().trim().isEmpty()){
            edtDate.setError("Enter Date");
        }else {
            return true;
        }
        return false;
    }

    private void invalidate() {

        edtDate.setText(null);
        edtName.setText(null);
        edtWeight.setText(null);
        edtPrice.setText(null);
        edtCode.setText(null);

        Toast.makeText(this, "SAVED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = calendar.getTime();
        edtDate.setText(date.toLocaleString().substring(0, 12));


    }

    private class InsertTask extends AsyncTask<Void,Void,Boolean> {
        private WeakReference<AddDetails> activityReference;
        Records records;


        public InsertTask(AddDetails context, Records records) {
            activityReference=new WeakReference<>(context);
            this.records=records;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            activityReference.get().farmerDB.getFarmerDao().insertRecord(records);
            Log.d("ID ","doINBackground: ");
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if(aBoolean){
                invalidate();
            }
        }
    }
}
