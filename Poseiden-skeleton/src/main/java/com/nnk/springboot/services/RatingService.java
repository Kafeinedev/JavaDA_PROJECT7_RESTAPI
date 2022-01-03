package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.Rating;

/**
 * Interface of RatingService handle CRUD for Rating domain.
 */
public interface RatingService {

	/**
	 * Creates a Rating in database.
	 *
	 * @param toCreate the Rating to create
	 * @return the created Rating
	 */
	public Rating createRating(Rating toCreate);

	/**
	 * Find all Rating in database
	 * 
	 * @return a List containing all Rating
	 */
	public List<Rating> findAllRatings();

	/**
	 * Find one Rating in database
	 * 
	 * @param id of the Rating to find
	 * @return the Rating with that id
	 */
	public Rating findRatingById(int id);

	/**
	 * Update Rating.
	 *
	 * @param id       of the Rating to update
	 * @param toUpdate the updated Rating
	 * @return the updated Rating
	 */
	public Rating updateRating(int id, Rating toUpdate);

	/**
	 * Delete Rating by id.
	 *
	 * @param id of the Rating to delete
	 */
	public void deleteRatingById(int idToDelete);
}
