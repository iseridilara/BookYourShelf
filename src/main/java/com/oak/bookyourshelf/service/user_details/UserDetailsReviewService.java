package com.oak.bookyourshelf.service.user_details;

import com.oak.bookyourshelf.model.Review;
import com.oak.bookyourshelf.repository.user_details.UserDetailsReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserDetailsReviewService {
    final UserDetailsReviewRepository userDetailsReviewRepository;

    public UserDetailsReviewService(UserDetailsReviewRepository userDetailsReviewRepository) {
        this.userDetailsReviewRepository = userDetailsReviewRepository;
    }

    public Iterable<Review> listAll() {
        return userDetailsReviewRepository.findAll();
    }

    public Review findReviewById(int id) {
        return userDetailsReviewRepository.findReviewById(id);
    }

    public void delete(int id) {
        userDetailsReviewRepository.removeProductReviewByReviewId(id);
        userDetailsReviewRepository.removeUserReviewByReviewId(id);
        userDetailsReviewRepository.deleteById(id);
    }

    public void save(Review review) {
        userDetailsReviewRepository.save(review);
    }

    public List<Review> sortReviewsOfUser(String sortType, int userId) {
        switch (sortType) {
            case "date-asc":
                return userDetailsReviewRepository.findByUserIdOrderByUploadDateAsc(userId);

            case "title-desc":
                return userDetailsReviewRepository.findByUserIdOrderByReviewTitleDesc(userId);

            case "title-asc":
                return userDetailsReviewRepository.findByUserIdOrderByReviewTitleAsc(userId);

            case "content-desc":
                return userDetailsReviewRepository.findByUserIdOrderByReviewContentDesc(userId);

            case "content-asc":
                return userDetailsReviewRepository.findByUserIdOrderByReviewContentAsc(userId);

            case "rate-desc":
                return userDetailsReviewRepository.findByUserIdOrderByScoreOutOf5Desc(userId);

            case "rate-asc":
                return userDetailsReviewRepository.findByUserIdOrderByScoreOutOf5Asc(userId);

            default:        // date-desc
                return userDetailsReviewRepository.findByUserIdOrderByUploadDateDesc(userId);
        }
    }

    public List<Review> sortReviewsOfProduct(String sortType, int productId) {
        switch (sortType) {
            case "date-asc":
                return userDetailsReviewRepository.findByProductIdOrderByUploadDateAsc(productId);

            case "title-desc":
                return userDetailsReviewRepository.findByProductIdOrderByReviewTitleDesc(productId);

            case "title-asc":
                return userDetailsReviewRepository.findByProductIdOrderByReviewTitleAsc(productId);

            case "content-desc":
                return userDetailsReviewRepository.findByProductIdOrderByReviewContentDesc(productId);

            case "content-asc":
                return userDetailsReviewRepository.findByProductIdOrderByReviewContentAsc(productId);

            case "rate-desc":
                return userDetailsReviewRepository.findByProductIdOrderByScoreOutOf5Desc(productId);

            case "rate-asc":
                return userDetailsReviewRepository.findByProductIdOrderByScoreOutOf5Asc(productId);

            default:        // date-desc
                return userDetailsReviewRepository.findByProductIdOrderByUploadDateDesc(productId);
        }
    }
}
