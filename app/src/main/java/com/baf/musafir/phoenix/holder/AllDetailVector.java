package com.baf.musafir.phoenix.holder;

import com.baf.musafir.phoenix.model.DetailModel;

import java.util.Vector;


public class AllDetailVector {
	public static Vector<DetailModel> detailModels = new Vector<DetailModel>();

	public static Vector<DetailModel> getAllDetail() {
		return detailModels;
	}

	public static void setAllDetail(Vector<DetailModel> detailModels) {
		AllDetailVector.detailModels = detailModels;
	}

	public static DetailModel getAllDetail(int pos) {
		return detailModels.elementAt(pos);
	}

	public static void setAllDetail(DetailModel detailModels) {
		AllDetailVector.detailModels.addElement(detailModels);
	}

	public static void removeDetail() {
		AllDetailVector.detailModels.removeAllElements();
	}
}
