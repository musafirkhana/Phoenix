package com.baf.musafir.phoenix.holder;

import com.baf.musafir.phoenix.model.MsnProfileModel;
import com.baf.musafir.phoenix.model.PhaseModel;

import java.util.Vector;


public class AllMsnProfileVector {
	public static Vector<MsnProfileModel> msnProfileModels = new Vector<MsnProfileModel>();

	public static Vector<MsnProfileModel> getAllMsnProfilelist() {
		return msnProfileModels;
	}

	public static void setAllMsnProfilelist(Vector<MsnProfileModel> msnProfileModels) {
		AllMsnProfileVector.msnProfileModels = msnProfileModels;
	}

	public static MsnProfileModel getAllMsnProfilelist(int pos) {
		return msnProfileModels.elementAt(pos);
	}

	public static void setAllMsnProfilelist(MsnProfileModel msnProfileModels) {
		AllMsnProfileVector.msnProfileModels.addElement(msnProfileModels);
	}

	public static void removeMsnProfilelist() {
		AllMsnProfileVector.msnProfileModels.removeAllElements();
	}
}
