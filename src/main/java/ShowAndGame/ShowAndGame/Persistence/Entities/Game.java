package ShowAndGame.ShowAndGame.Persistence.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id", nullable = false)
    private long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private float rating;
    @Column
    private int reviewAmount;
    @Column
    private float totalRating;
    @Column
    private String profileImage;
    @Column
    private String backgroundImage;
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedPost> feedPosts;
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<ReviewPost> reviews;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_id")
    private User owner;
    @Column
    private Integer followerAmount;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "game_tags",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();
    @OneToMany(mappedBy = "gameFollowed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Follow> follows;

    public Game() {
    }

    public Game(String name, String description, float rating, int reviewAmount, float totalRating, String profileImage, String backgroundImage, List<FeedPost> feedPosts, User owner, List<Tag> tags, Integer followerAmount, List<Follow> follows, List<ReviewPost> reviews) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.reviewAmount = reviewAmount;
        this.totalRating = totalRating;
        this.profileImage = profileImage;
        this.backgroundImage = backgroundImage;
        this.feedPosts = feedPosts;
        this.reviews = reviews;
        this.owner = owner;
        this.followerAmount = followerAmount;
        this.tags = tags;
        this.follows = follows;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getReviewAmount() {
        return reviewAmount;
    }

    public void setReviewAmount(int reviewAmount) {
        this.reviewAmount = reviewAmount;
    }

    public float getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(float totalReview) {
        this.totalRating = totalReview;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public List<FeedPost> getPosts() {
        return feedPosts;
    }

    public void setPosts(List<FeedPost> feedPosts) {
        this.feedPosts = feedPosts;
    }

    public List<ReviewPost> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewPost> reviews) {
        this.reviews = reviews;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Integer getFollowerAmount() {
        return followerAmount;
    }

    public void setFollowerAmount(Integer followerAmount) {
        this.followerAmount = followerAmount;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Follow> getFollows() {
        return follows;
    }

    public void setFollows(List<Follow> follows) {
        this.follows = follows;
    }
}
