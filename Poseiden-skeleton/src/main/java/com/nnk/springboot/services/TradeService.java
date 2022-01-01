package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.Trade;

public interface TradeService {

	public Trade createTrade(Trade toCreate);

	public List<Trade> findAllTrades();

	public Trade findTradeById(int id);

	public Trade updateTrade(int id, Trade toUpdate);

	public void deleteTradeById(int idToDelete);
}
