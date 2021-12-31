package com.nnk.springboot.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidService;

@Service
public class DefaultBidService implements BidService {

	@Autowired
	private BidListRepository bidListRepository;

	@Override
	public List<BidList> getAllBid() {
		return bidListRepository.findAll();
	}

}
