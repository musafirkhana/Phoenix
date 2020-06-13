package com.baf.musafir.phoenix.holder;

import com.baf.musafir.phoenix.model.CoordinateModel;
import com.baf.musafir.phoenix.model.NavAidModel;

import java.util.Vector;


public class NavaidVector {
    public static Vector<NavAidModel> navAidModels = new Vector<NavAidModel>();

    public static Vector<NavAidModel> getAllNavaidlist() {
        return navAidModels;
    }

    public static void setAllNavaidlist(Vector<NavAidModel> navAidModels) {
        NavaidVector.navAidModels = navAidModels;
    }

    public static NavAidModel getAllNavaidlist(int pos) {
        return navAidModels.elementAt(pos);
    }

    public static void setAllNavaidlist(NavAidModel navAidModels) {
        NavaidVector.navAidModels.addElement(navAidModels);
    }

    public static void removeAllNavaidlist() {
        NavaidVector.navAidModels.removeAllElements();
    }
}
