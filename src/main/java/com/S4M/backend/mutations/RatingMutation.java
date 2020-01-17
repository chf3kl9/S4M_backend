package com.S4M.backend.mutations;

import com.S4M.backend.services.RatingService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@Component
@CrossOrigin(origins = {"https://s4m-frontend.herokuapp.com", "http://s4m-frontend.herokuapp.com", "s4m-frontend.herokuapp.com", "http://localhost:3000"})
public class RatingMutation implements GraphQLMutationResolver {

    private final RatingService ratingService;

    public RatingMutation(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    public String rateMovie(String email, Integer movieId, Integer rating){
        return ratingService.rateMovie(email, movieId, rating);
    }
}
