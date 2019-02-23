package demo.orbitsys.com.fmgmdemo.farmerdb.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import demo.orbitsys.com.fmgmdemo.farmerdb.model.Records;
import demo.orbitsys.com.fmgmdemo.util.Constants;

@Dao
public interface FarmerDao {

    @Query("SELECT *  FROM "+Constants.TABLE_RECORD)
    List<Records> getRecords();

    @Query("SELECT SUM(weight) FROM " +Constants.TABLE_RECORD)
    double getWeightSum();
    @Query("SELECT SUM(price) FROM " +Constants.TABLE_RECORD)
    double getPriceSum();

    @Delete
    void deleteRow(Records records);


    @Insert
    long insertRecord(Records records);

    @Update
    void updateRecord(Records records);
}
