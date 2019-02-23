package demo.orbitsys.com.fmgmdemo.farmerdb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import demo.orbitsys.com.fmgmdemo.farmerdb.dao.FarmerDao;
import demo.orbitsys.com.fmgmdemo.farmerdb.model.Records;
import demo.orbitsys.com.fmgmdemo.util.Constants;


@Database(entities = {Records.class},version = 3)
public abstract class FarmerDB extends RoomDatabase {
    public abstract FarmerDao getFarmerDao();
    public static FarmerDB farmerDB;
    public static FarmerDB getInstance(Context context){
        if(farmerDB==null){
            farmerDB=buildDatabaseInstance(context);
        }
        return farmerDB;
    }

    private static FarmerDB buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,FarmerDB.class,Constants.FARMER_DB).fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        farmerDB=null;
    }

}
