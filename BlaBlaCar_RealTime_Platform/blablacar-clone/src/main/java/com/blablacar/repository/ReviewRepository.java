package com.blablacar.repository;

import com.blablacar.entity.Review;
import com.blablacar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByReviewee(User reviewee);
    List<Review> findByReviewer(User reviewer);
    List<Review> findByRevieweeOrderByCreatedAtDesc(User reviewee);
}
