package com.ezen.koh10.store;

import java.util.ArrayList;

public interface Service {
	void insert(String place, String carname, int su, int price);
	
	ArrayList<StoreDTO> sort();

	ArrayList<StoreDTO> sort1();

	ArrayList<StoreDTO> sort2();

	ArrayList<StoreDTO> sort3();

	ArrayList<StoreDTO> sort4();
	
	/* ����¡ó�� 3�ܰ�*/
	public int total(); //
	
	public ArrayList<StoreDTO> page(PageDTO dto);
	/* ����¡ó�� 3�ܰ�*/

	int total4();

	ArrayList<StoreDTO> page4(PageDTO dto);

}
