package com.nnk.springboot.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;

@Service
@Transactional
public class DefaultTradeService implements TradeService {

	Logger log = LogManager.getLogger("DefaultTradeService");

	@Autowired
	private TradeRepository tradeRepository;

	@Override
	public Trade createTrade(Trade toCreate) {
		log.trace("creating a new Trade");
		return tradeRepository.save(toCreate);
	}

	@Override
	public List<Trade> findAllTrades() {
		log.trace("finding all Trades");
		return tradeRepository.findAll();
	}

	@Override
	public Trade findTradeById(int id) {
		log.trace("finding Trade by id: " + id);
		return tradeRepository.findById(id).orElseThrow(() -> {
			log.error("Could not find Trade with id: " + id);
			return new NoSuchElementException();
		});
	}

	@Override
	public Trade updateTrade(int id, Trade toUpdate) {
		log.trace("updating Trade id: " + id);
		tradeRepository.findById(id).orElseThrow(() -> {
			log.error("Could not update Trade with id: " + id);
			return new NoSuchElementException();
		});

		toUpdate.setTradeId(id);

		return tradeRepository.save(toUpdate);
	}

	@Override
	public void deleteTradeById(int idToDelete) {
		log.trace("deleting Trade id: " + idToDelete);
		tradeRepository.findById(idToDelete).orElseThrow(() -> {
			log.error("Could not delete Trade with id: " + idToDelete);
			return new NoSuchElementException();
		});

		tradeRepository.deleteById(idToDelete);
	}
}
