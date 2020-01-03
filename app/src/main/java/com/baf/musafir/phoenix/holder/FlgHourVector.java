package com.baf.musafir.phoenix.holder;

import com.baf.musafir.phoenix.model.FlgHourModel;

import java.util.Vector;


public class FlgHourVector {
    public static Vector<FlgHourModel> flgHourModels = new Vector<FlgHourModel>();

    public static Vector<FlgHourModel> getAllFlhhourlist() {
        return flgHourModels;
    }

    public static void setFlhhourlist(Vector<FlgHourModel> flgHourModels) {
        FlgHourVector.flgHourModels = flgHourModels;
    }

    public static FlgHourModel getFlhhourlist(int pos) {
        return flgHourModels.elementAt(pos);
    }

    public static void setFlhhourlist(FlgHourModel flgHourModels) {
        FlgHourVector.flgHourModels.addElement(flgHourModels);
    }

    public static void removeFlhhourlist() {
        FlgHourVector.flgHourModels.removeAllElements();
    }
}
