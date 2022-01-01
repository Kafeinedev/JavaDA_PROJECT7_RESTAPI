package com.nnk.springboot.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.impl.DefaultRuleNameService;

@ExtendWith(MockitoExtension.class)
public class DefaultRuleNameServiceTests {

	@Mock
	private RuleNameRepository mockRepository;

	@InjectMocks
	private DefaultRuleNameService ruleNameService;

	private RuleName rule;

	@BeforeEach
	void setUp() {
		rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
	}

	@Test
	void createRuleName_whenCalled_returnCreatedRuleName() {
		when(mockRepository.save(rule)).thenReturn(rule);

		RuleName test = ruleNameService.createRuleName(rule);

		assertThat(test).isEqualTo(rule);
	}

	@Test
	void createRuleName_whenCalled_useRepository() {
		ruleNameService.createRuleName(rule);

		verify(mockRepository, times(1)).save(rule);
	}

	@Test
	void findAllRuleNames_whenNoRuleNamesAreFound_returnEmptyList() {
		when(mockRepository.findAll()).thenReturn(new ArrayList<RuleName>());

		List<RuleName> test = ruleNameService.findAllRuleNames();

		assertThat(test).isEmpty();
	}

	@Test
	void findAllRuleNames_whenRuleNamesAreFound_returnListOfRuleName() {
		when(mockRepository.findAll()).thenReturn(List.of(rule));

		List<RuleName> test = ruleNameService.findAllRuleNames();

		assertThat(test.size()).isEqualTo(1);
	}

	@Test
	void findAllRuleNames_whenCalled_useRepository() {
		ruleNameService.findAllRuleNames();

		verify(mockRepository, times(1)).findAll();
	}

	@Test
	void findById_whenRuleNameIsNotFound_throw() {
		when(mockRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> ruleNameService.findRuleNameById(1));
	}

	@Test
	void findById_whenRuleNameIsFound_returnRuleName() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(rule));

		RuleName test = ruleNameService.findRuleNameById(1);

		assertThat(test).isEqualTo(rule);
	}

	@Test
	void findById_whenCalled_useRepository() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(rule));

		ruleNameService.findRuleNameById(1);

		verify(mockRepository, times(1)).findById(1);
	}

	@Test
	void updateRuleName_whenCalled_returnUpdatedRuleName() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(rule));
		when(mockRepository.save(rule)).thenReturn(rule);

		RuleName test = ruleNameService.updateRuleName(1, rule);

		assertThat(test).isEqualTo(rule);
	}

	@Test
	void updateRuleName_whenRuleNameDoesntExist_throw() {
		when(mockRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> ruleNameService.updateRuleName(1, rule));
	}

	@Test
	void updateRuleName_whenCalled_useRepository() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(rule));

		ruleNameService.updateRuleName(1, rule);

		verify(mockRepository, times(1)).findById(1);
		verify(mockRepository, times(1)).save(rule);
	}

	@Test
	void deleteRuleNameById_whenCalled_useRepository() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(rule));

		ruleNameService.deleteRuleNameById(1);

		verify(mockRepository, times(1)).deleteById(1);
	}

	@Test
	void deleteRuleNameById_whenRuleNameNotFound_throw() {
		when(mockRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> ruleNameService.deleteRuleNameById(1));
	}
}
