package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.BidList;

/**
 * Interface of BidService handle CRUD for BidList domain.
 */
public interface BidService {

	/**
	 * Creates a BidList in database.
	 *
	 * @param toCreate the bidList to create
	 * @return the created BidList
	 */
	public BidList createBid(BidList toCreate);

	/**
	 * Find all BidList in database
	 * 
	 * @return a List containing all BidList
	 */
	public List<BidList> findAllBids();

	/**
	 * Find one BidList in database
	 * 
	 * @param id of the BidList to find
	 * @return the BidList with that id
	 */
	public BidList findBidById(int id);

	/**
	 * Update BidList.
	 *
	 * @param id       of the BidList to update
	 * @param toUpdate the updated BidList
	 * @return the updated BidList
	 */
	public BidList updateBid(int id, BidList toUpdate);

	/**
	 * Delete BidList by id.
	 *
	 * @param id of the BidList to delete
	 */
	public void deleteBidById(int idToDelete);
}
