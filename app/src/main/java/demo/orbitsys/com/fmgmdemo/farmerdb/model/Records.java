package demo.orbitsys.com.fmgmdemo.farmerdb.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import demo.orbitsys.com.fmgmdemo.util.Constants;
import demo.orbitsys.com.fmgmdemo.util.DateConverter;


@Entity(tableName = Constants.TABLE_RECORD)
public class Records implements Serializable {



    @PrimaryKey(autoGenerate = true)
    private int serial;


    private int farmerCode;

    private String name;
    private double weight;
    private double price;
    @TypeConverters(DateConverter.class)
    private Date date;



    public Records(int farmerCode, String name, double weight, double price, Date date) {
        this.farmerCode = farmerCode;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.date = date;
    }

    public int getFarmerCode() {
        return farmerCode;
    }

    public void setFarmerCode(int farmerCode) {
        this.farmerCode = farmerCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Records records = (Records) o;
        return farmerCode == records.farmerCode &&
                Double.compare(records.weight, weight) == 0 &&
                Double.compare(records.price, price) == 0 &&
                Objects.equals(name, records.name) &&
                Objects.equals(date, records.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(farmerCode, name, weight, price, date);
    }
    @Override
    public String toString() {
        return "Records{" +
                "farmerCode=" + farmerCode +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
