package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.RuleName;

public interface RuleNameService {

	public RuleName createRuleName(RuleName toCreate);

	public List<RuleName> findAllRuleNames();

	public RuleName findRuleNameById(int id);

	public RuleName updateRuleName(int id, RuleName toUpdate);

	public void deleteRuleNameById(int idToDelete);
}
