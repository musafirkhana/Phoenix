package com.baf.musafir.phoenix.databse;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.baf.musafir.phoenix.holder.CoordinateVector;
import com.baf.musafir.phoenix.holder.FlgHourVector;
import com.baf.musafir.phoenix.model.CoordinateModel;
import com.baf.musafir.phoenix.model.FlgHourModel;

import timber.log.Timber;


public class DataBaseUtility {
    private static String TAG = "DataBaseUtility";

    /******************************
     * Getting  Contact from DB
     * Getting All Mobile no where baseID=1 that means Air HQ
     ******************************/
    public void getCoordinateData(Context context) {
        AssetDatabaseOpenHelper databaseOpenHelper = new AssetDatabaseOpenHelper(context);
        SQLiteDatabase db = databaseOpenHelper.openDatabase();
        Log.w(TAG, "getAirHqLodgerData: " + db.getVersion());

        Cursor cursor = db.rawQuery(
                "SELECT * from cordinate;",
                null);
       // Log.i(TAG, "Database Query Are :" + cursor);
        CoordinateVector coordinateVector = new CoordinateVector();
        coordinateVector.removeAllCoordinatelist();
        if (cursor.moveToFirst()) {
            do {

                CoordinateModel coordinateModel = new CoordinateModel();
                coordinateModel.setLatitude(cursor.getString(0));
                coordinateModel.setLongitude(cursor.getString(1));
                coordinateModel.setPlaces(cursor.getString(2));
                coordinateVector.setAllCoordinatelist(coordinateModel);
                coordinateModel = null;
                Log.w(TAG, "Contact Data : " + cursor.getString(0));
                Timber.tag("asdsadsad").e(cursor.getString(2));

            } while (cursor.moveToNext());
        }
        db.close();
    }
    /******************************
     * Getting  Flg hhour from DB
     *
     ******************************/
    public void getFlghourData(Context context) {
        AssetDatabaseOpenHelper databaseOpenHelper = new AssetDatabaseOpenHelper(context);
        SQLiteDatabase db = databaseOpenHelper.openDatabase();
        Log.w(TAG, "getAirHqLodgerData: " + db.getVersion());

        Cursor cursor = db.rawQuery(
                "SELECT * from flg_hour;",
                null);
        Timber.tag("\"Database Query").e(""+cursor);
        FlgHourVector flgHourVector = new FlgHourVector();
        flgHourVector.removeFlhhourlist();
        if (cursor.moveToFirst()) {
            do {

                FlgHourModel flgHourModel = new FlgHourModel();
                flgHourModel.setYear(cursor.getString(0));
                flgHourModel.setMonth(cursor.getString(1));
                flgHourModel.setDate(cursor.getString(2));
                flgHourModel.setAc_type(cursor.getString(3));
                flgHourModel.setAc_serno(cursor.getString(4));
                flgHourModel.setFirst_pilot(cursor.getString(5));
                flgHourModel.setSecond_pilot(cursor.getString(6));
                flgHourModel.setDay_hour(cursor.getString(7));
                flgHourModel.setNight_hour(cursor.getString(8));
                flgHourModel.setInstr_actual(cursor.getString(9));
                flgHourModel.setInst_simulator(cursor.getString(10));
                flgHourModel.setD_n_flag(cursor.getString(12));

                flgHourVector.setFlhhourlist(flgHourModel);
                flgHourModel = null;
                Log.w(TAG, "Contact Data : " + cursor.getString(0));
                Timber.tag("Year").e(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        db.close();
    }

}
