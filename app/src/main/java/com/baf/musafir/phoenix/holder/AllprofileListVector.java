package com.baf.musafir.phoenix.holder;

import com.baf.musafir.phoenix.model.AbbrlListModel;
import com.baf.musafir.phoenix.model.ProfileModel;

import java.util.Vector;


public class AllprofileListVector {
    public static Vector<ProfileModel> profileModels = new Vector<ProfileModel>();

    public static Vector<ProfileModel> getAllProfilelist() {
        return profileModels;
    }

    public static void setAllProfilelist(Vector<ProfileModel> profileModels) {
        AllprofileListVector.profileModels = profileModels;
    }

    public static ProfileModel getAllProfilelist(int pos) {
        return profileModels.elementAt(pos);
    }

    public static void setAllProfilelist(ProfileModel profileModels) {
        AllprofileListVector.profileModels.addElement(profileModels);
    }

    public static void removeProfilelist() {
        AllprofileListVector.profileModels.removeAllElements();
    }
}
