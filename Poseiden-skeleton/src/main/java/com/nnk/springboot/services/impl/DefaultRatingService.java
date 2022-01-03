package com.nnk.springboot.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;

@Service
@Transactional
public class DefaultRatingService implements RatingService {

	Logger log = LogManager.getLogger("DefaultRatingService");

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating createRating(Rating toCreate) {
		log.trace("creating a new Rating");
		return ratingRepository.save(toCreate);
	}

	@Override
	public List<Rating> findAllRatings() {
		log.trace("finding all Ratings");
		return ratingRepository.findAll();
	}

	@Override
	public Rating findRatingById(int id) {
		log.trace("finding Rating by id: " + id);
		return ratingRepository.findById(id).orElseThrow(() -> {
			log.error("Could not find Rating with id: " + id);
			return new NoSuchElementException();
		});
	}

	@Override
	public Rating updateRating(int id, Rating toUpdate) {
		log.trace("updating Rating id: " + id);
		ratingRepository.findById(id).orElseThrow(() -> {
			log.error("Could not update Rating with id: " + id);
			return new NoSuchElementException();
		});

		toUpdate.setId(id);

		return ratingRepository.save(toUpdate);
	}

	@Override
	public void deleteRatingById(int idToDelete) {
		log.trace("deleting Rating id: " + idToDelete);
		ratingRepository.findById(idToDelete).orElseThrow(() -> {
			log.error("Could not delete Rating with id: " + idToDelete);
			return new NoSuchElementException();
		});

		ratingRepository.deleteById(idToDelete);
	}
}
