package com.nnk.springboot.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;

@Service
@Transactional
public class DefaultCurvePointService implements CurvePointService {

	Logger log = LogManager.getLogger("DefaultCurvePointService");

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Override
	public CurvePoint createCurvePoint(CurvePoint toCreate) {
		log.trace("creating a new CurvePoint");
		return curvePointRepository.save(toCreate);
	}

	@Override
	public List<CurvePoint> findAllCurvePoints() {
		log.trace("finding all CurvePoints");
		return curvePointRepository.findAll();
	}

	@Override
	public CurvePoint findCurvePointById(int id) {
		log.trace("finding CurvePoint by id: " + id);
		return curvePointRepository.findById(id).orElseThrow(() -> {
			log.error("Could not find CurvePoint with id: " + id);
			return new NoSuchElementException();
		});
	}

	@Override
	public CurvePoint updateCurvePoint(int id, CurvePoint toUpdate) {
		log.trace("updating CurvePoint id: " + id);
		curvePointRepository.findById(id).orElseThrow(() -> {
			log.error("Could not update CurvePoint with id: " + id);
			return new NoSuchElementException();
		});

		toUpdate.setId(id);

		return curvePointRepository.save(toUpdate);
	}

	@Override
	public void deleteCurvePointById(int idToDelete) {
		log.trace("deleting CurvePoint id: " + idToDelete);
		curvePointRepository.findById(idToDelete).orElseThrow(() -> {
			log.error("Could not delete CurvePoint with id: " + idToDelete);
			return new NoSuchElementException();
		});

		curvePointRepository.deleteById(idToDelete);
	}
}
