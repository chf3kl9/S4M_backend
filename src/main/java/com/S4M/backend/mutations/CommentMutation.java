package com.S4M.backend.mutations;

import com.S4M.backend.services.CommentService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@Component
@CrossOrigin(origins = {"https://s4m-frontend.herokuapp.com", "http://s4m-frontend.herokuapp.com", "s4m-frontend.herokuapp.com", "http://localhost:3000"})
public class CommentMutation implements GraphQLMutationResolver {

    private final CommentService commentService;

    public CommentMutation(CommentService commentService) {
        this.commentService = commentService;
    }

    public String placeComment(String email, Integer movieId, String text){
        return commentService.placeComment(email, movieId, text);
    }

    public String deleteCommentById(Integer id, String email){
        return commentService.deleteCommentById(id, email);
    }
}
