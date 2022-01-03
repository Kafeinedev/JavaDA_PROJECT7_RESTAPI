package com.nnk.springboot.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidService;

@Service
@Transactional
public class DefaultBidService implements BidService {

	Logger log = LogManager.getLogger("DefaultBidService");

	@Autowired
	private BidListRepository bidListRepository;

	@Override
	public BidList createBid(BidList toCreate) {
		log.trace("creating a new bid");
		return bidListRepository.save(toCreate);
	}

	@Override
	public List<BidList> findAllBids() {
		log.trace("finding all bids");
		return bidListRepository.findAll();
	}

	@Override
	public BidList findBidById(int id) {
		log.trace("finding bid by id: " + id);
		return bidListRepository.findById(id).orElseThrow(() -> {
			log.error("Could not find Bid with id: " + id);
			return new NoSuchElementException();
		});
	}

	@Override
	public BidList updateBid(int id, BidList toUpdate) {
		log.trace("updating bid id: " + id);
		bidListRepository.findById(id).orElseThrow(() -> {
			log.error("Could not update Bid with id: " + id);
			return new NoSuchElementException();
		});

		toUpdate.setBidListId(id);

		return bidListRepository.save(toUpdate);
	}

	@Override
	public void deleteBidById(int idToDelete) {
		log.trace("deleting bid id: " + idToDelete);
		bidListRepository.findById(idToDelete).orElseThrow(() -> {
			log.error("Could not delete Bid with id: " + idToDelete);
			return new NoSuchElementException();
		});

		bidListRepository.deleteById(idToDelete);
	}
}
