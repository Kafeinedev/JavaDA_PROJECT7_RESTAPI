package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.CurvePoint;

public interface CurvePointService {

	public CurvePoint createCurvePoint(CurvePoint toCreate);

	public List<CurvePoint> findAllCurvePoints();

	public CurvePoint findCurvePointById(int id);

	public CurvePoint updateCurvePoint(int id, CurvePoint toUpdate);

	public void deleteCurvePointById(int idToDelete);
}
