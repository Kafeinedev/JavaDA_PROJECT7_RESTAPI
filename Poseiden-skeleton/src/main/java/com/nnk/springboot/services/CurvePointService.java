package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.CurvePoint;

/**
 * Interface of CurvePointService handle CRUD for CurvePoint domain.
 */
public interface CurvePointService {

	/**
	 * Creates a CurvePoint in database.
	 *
	 * @param toCreate the CurvePoint to create
	 * @return the created CurvePoint
	 */
	public CurvePoint createCurvePoint(CurvePoint toCreate);

	/**
	 * Find all CurvePoint in database
	 * 
	 * @return a List containing all CurvePoint
	 */
	public List<CurvePoint> findAllCurvePoints();

	/**
	 * Find one CurvePoint in database
	 * 
	 * @param id of the CurvePoint to find
	 * @return the CurvePoint with that id
	 */
	public CurvePoint findCurvePointById(int id);

	/**
	 * Update CurvePoint.
	 *
	 * @param id       of the CurvePoint to update
	 * @param toUpdate the updated CurvePoint
	 * @return the updated CurvePoint
	 */
	public CurvePoint updateCurvePoint(int id, CurvePoint toUpdate);

	/**
	 * Delete CurvePoint by id.
	 *
	 * @param id of the CurvePoint to delete
	 */
	public void deleteCurvePointById(int idToDelete);
}
