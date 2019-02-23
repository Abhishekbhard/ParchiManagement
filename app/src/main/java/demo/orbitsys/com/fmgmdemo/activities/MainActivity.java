package demo.orbitsys.com.fmgmdemo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import demo.orbitsys.com.fmgmdemo.R;
import demo.orbitsys.com.fmgmdemo.farmerdb.FarmerDB;
import demo.orbitsys.com.fmgmdemo.farmerdb.model.Records;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button btnAdd,btShow;
FarmerDB farmerDB;
Records records;
double sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd=findViewById(R.id.btnAdd);
        btShow=findViewById(R.id.btShow);
        btnAdd.setOnClickListener(this);
        btShow.setOnClickListener(this);
       /* farmerDB=FarmerDB.getInstance(MainActivity.this);
        new RetriveTask(MainActivity.this,records).execute();*/


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAdd:
                Intent intent=new Intent(this,AddDetails.class);
                startActivity(intent);
                break;
            case R.id.btShow:
                Intent intentShow=new Intent(this,ShowDetails.class);
                startActivity(intentShow);
                break;

        }

    }

/*
    private class RetriveTask extends AsyncTask<Void,Void,Boolean> {
        WeakReference<MainActivity> weakReference;
        Records records;
        public RetriveTask(MainActivity mainActivity, Records records) {
            weakReference=new WeakReference<>(mainActivity);
            this.records=records;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
           sum=  weakReference.get().farmerDB.getFarmerDao().getWeightSum();
          return true;

        }
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            showToast();
        }
    }
*/

    private void showToast() {
        Toast.makeText(this, String.valueOf(sum), Toast.LENGTH_SHORT).show();

    }
}
