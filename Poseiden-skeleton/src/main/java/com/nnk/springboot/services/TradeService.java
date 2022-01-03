package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.Trade;

/**
 * Interface of TradeService handle CRUD for Trade domain.
 */
public interface TradeService {

	/**
	 * Creates a Trade in database.
	 *
	 * @param toCreate the Trade to create
	 * @return the created Trade
	 */
	public Trade createTrade(Trade toCreate);

	/**
	 * Find all Trade in database
	 * 
	 * @return a List containing all Trade
	 */
	public List<Trade> findAllTrades();

	/**
	 * Find one Trade in database
	 * 
	 * @param id of the Trade to find
	 * @return the Trade with that id
	 */
	public Trade findTradeById(int id);

	/**
	 * Update Trade.
	 *
	 * @param id       of the Trade to update
	 * @param toUpdate the updated Trade
	 * @return the updated Trade
	 */
	public Trade updateTrade(int id, Trade toUpdate);

	/**
	 * Delete Trade by id.
	 *
	 * @param id of the Trade to delete
	 */
	public void deleteTradeById(int idToDelete);
}
