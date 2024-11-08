package ShowAndGame.ShowAndGame.Persistence.Dto.CommentDto;

import ShowAndGame.ShowAndGame.Persistence.Entities.Comment;
import ShowAndGame.ShowAndGame.Persistence.Entities.User;

public class CommentForCreationAndUpdateDto {
    private String description;

    public CommentForCreationAndUpdateDto() {
    }

    public CommentForCreationAndUpdateDto(Comment comment, User user) {
        this.description = comment.getDescription();

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
