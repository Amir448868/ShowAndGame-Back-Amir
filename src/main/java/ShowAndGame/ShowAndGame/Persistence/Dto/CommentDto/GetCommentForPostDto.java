package ShowAndGame.ShowAndGame.Persistence.Dto.CommentDto;

import ShowAndGame.ShowAndGame.Persistence.Entities.Comment;
import ShowAndGame.ShowAndGame.Persistence.Entities.User;

public class GetCommentForPostDto {
    private  Long id;
    private String description;
    private String userName;
    private String userProfileImage;

    public GetCommentForPostDto(Comment comment, User user) {
        this.id = comment.getId();
        this.description = comment.getDescription();
        this.userName = user.getUsername();
        this.userProfileImage = user.getProfileImage();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(String userProfileImage) {
        this.userProfileImage = userProfileImage;
    }
}
