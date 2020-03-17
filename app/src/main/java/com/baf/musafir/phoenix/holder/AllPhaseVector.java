package com.baf.musafir.phoenix.holder;

import com.baf.musafir.phoenix.model.PhaseModel;

import java.util.Vector;


public class AllPhaseVector {
	public static Vector<PhaseModel> phaseModels = new Vector<PhaseModel>();

	public static Vector<PhaseModel> getAllPhaselist() {
		return phaseModels;
	}

	public static void setAllPhaselist(Vector<PhaseModel> phaseModels) {
		AllPhaseVector.phaseModels = phaseModels;
	}

	public static PhaseModel getAllPhaselist(int pos) {
		return phaseModels.elementAt(pos);
	}

	public static void setAllPhaselist(PhaseModel phaseModels) {
		AllPhaseVector.phaseModels.addElement(phaseModels);
	}

	public static void removePhaselist() {
		AllPhaseVector.phaseModels.removeAllElements();
	}
}
