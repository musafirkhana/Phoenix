package com.baf.musafir.phoenix.holder;

import com.baf.musafir.phoenix.model.CoordinateModel;
import com.baf.musafir.phoenix.model.FlgHourModel;

import java.util.Vector;


public class CoordinateVector {
    public static Vector<CoordinateModel> coordinateModels = new Vector<CoordinateModel>();

    public static Vector<CoordinateModel> getAllCoordinatelist() {
        return coordinateModels;
    }

    public static void setAllCoordinatelist(Vector<CoordinateModel> coordinateModels) {
        CoordinateVector.coordinateModels = coordinateModels;
    }

    public static CoordinateModel getAllCoordinatelist(int pos) {
        return coordinateModels.elementAt(pos);
    }

    public static void setAllCoordinatelist(CoordinateModel coordinateModels) {
        CoordinateVector.coordinateModels.addElement(coordinateModels);
    }

    public static void removeAllCoordinatelist() {
        CoordinateVector.coordinateModels.removeAllElements();
    }
}
