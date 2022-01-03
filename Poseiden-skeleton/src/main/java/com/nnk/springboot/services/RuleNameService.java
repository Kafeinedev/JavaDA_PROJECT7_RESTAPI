package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.RuleName;

/**
 * Interface of RuleNameService handle CRUD for RuleName domain.
 */
public interface RuleNameService {

	/**
	 * Creates a RuleName in database.
	 *
	 * @param toCreate the RuleName to create
	 * @return the created RuleName
	 */
	public RuleName createRuleName(RuleName toCreate);

	/**
	 * Find all RuleName in database
	 * 
	 * @return a List containing all RuleName
	 */
	public List<RuleName> findAllRuleNames();

	/**
	 * Find one RuleName in database
	 * 
	 * @param id of the RuleName to find
	 * @return the RuleName with that id
	 */
	public RuleName findRuleNameById(int id);

	/**
	 * Update RuleName.
	 *
	 * @param id       of the RuleName to update
	 * @param toUpdate the updated RuleName
	 * @return the updated RuleName
	 */
	public RuleName updateRuleName(int id, RuleName toUpdate);

	/**
	 * Delete RuleName by id.
	 *
	 * @param id of the RuleName to delete
	 */
	public void deleteRuleNameById(int idToDelete);
}
