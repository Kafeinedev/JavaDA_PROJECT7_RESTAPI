package com.nnk.springboot.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;

@Service
public class DefaultRuleNameService implements RuleNameService {

	Logger log = LogManager.getLogger("DefaultRuleNameService");

	@Autowired
	private RuleNameRepository ruleNameRepository;

	@Override
	public RuleName createRuleName(RuleName toCreate) {
		log.trace("creating a new RuleName");
		return ruleNameRepository.save(toCreate);
	}

	@Override
	public List<RuleName> findAllRuleNames() {
		log.trace("finding all RuleNames");
		return ruleNameRepository.findAll();
	}

	@Override
	public RuleName findRuleNameById(int id) {
		log.trace("finding RuleName by id: " + id);
		return ruleNameRepository.findById(id).orElseThrow(() -> {
			log.error("Could not find RuleName with id: " + id);
			return new NoSuchElementException();
		});
	}

	@Override
	public RuleName updateRuleName(int id, RuleName toUpdate) {
		log.trace("updating RuleName id: " + id);
		ruleNameRepository.findById(id).orElseThrow(() -> {
			log.error("Could not update RuleName with id: " + id);
			return new NoSuchElementException();
		});

		toUpdate.setId(id);

		return ruleNameRepository.save(toUpdate);
	}

	@Override
	public void deleteRuleNameById(int idToDelete) {
		log.trace("deleting RuleName id: " + idToDelete);
		ruleNameRepository.findById(idToDelete).orElseThrow(() -> {
			log.error("Could not delete RuleName with id: " + idToDelete);
			return new NoSuchElementException();
		});

		ruleNameRepository.deleteById(idToDelete);
	}
}
