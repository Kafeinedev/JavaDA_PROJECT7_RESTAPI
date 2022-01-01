package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.BidList;

public interface BidService {

	public BidList createBid(BidList toCreate);

	public List<BidList> findAllBids();

	public BidList findBidById(int id);

	public BidList updateBid(int id, BidList toUpdate);

	public void deleteBidById(int idToDelete);
}
