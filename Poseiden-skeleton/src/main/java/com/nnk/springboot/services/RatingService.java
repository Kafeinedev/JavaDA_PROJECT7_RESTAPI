package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.Rating;

public interface RatingService {

	public Rating createRating(Rating toCreate);

	public List<Rating> findAllRatings();

	public Rating findRatingById(int id);

	public Rating updateRating(int id, Rating toUpdate);

	public void deleteRatingById(int idToDelete);
}
