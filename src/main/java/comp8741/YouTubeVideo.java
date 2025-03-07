package comp8741;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class YouTubeVideo {
    private String id; //video channel id
    private String channel; //youtube video channel
    private String date; //the posted date of the video
    private String title; //video title
    private String description; //video description
    private long viewCount; //number of viewers
    private long likeCount; //number of people who like the video
    private long dislikeCount; // number of people who like the video
    private long favoriteCount; // number of people who favor the video
    private long commentCount; //number of comments on the video

    /**
     * This is a constructor of this class.
     *
     * @param id           The Channel ID of the YouTube Video
     * @param channel      The Channel of the YouTube Video
     * @param date         The published date of the YouTube Video
     * @param title        The title of the YouTube video
     * @param description  The description of the YouTube video
     * @param viewCount    The number of viewer on the video
     * @param likeCount    The number of people who like the video
     * @param dislikeCount The number of people who dislike the video
     * @param favoriteCount The number of people who favor the video
     * @param commentCount The number of comments on the video
     */
    public YouTubeVideo(String id, String channel, String date, String title, String description, long viewCount, long likeCount, long dislikeCount, long favoriteCount, long commentCount) {
        this.id = id;
        this.channel = channel;
        try {
            this.date = ZonedDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        } catch (Exception e) {
            this.date = date;
        }
        this.title = title;
        this.description = description;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.favoriteCount = favoriteCount;
        this.commentCount = commentCount;
    }

    /**
     * This method is used to get the value of the YouTube Video's ID.
     * @return the value of the YouTube video's ID as a String.
     */
    public String getId() {
        return id;
    }

    /**
     * This method is used to set the value of the YouTube video's ID.
     * @param id a string value of YouTube video's id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method is used to get the channel of the called YouTube video.
     * @return a string value of the called YouTube video's ID
     */
    public String getChannel() {
        return channel;
    }

    /**
     * This method is used to set the channel value of the YouTube video.
     * @param channel a string value of the channel.
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * This method is used to get the date string of the YouTube video.
     * @return a date string of the YouTube video.
     */
    public String getDate() {
        return date;
    }

    /**
     * This method is used to set the date string of the YouTube video.
     * @param date a date string of the YouTube video.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * This method is used to get the description of the YouTube video.
     * @return a string of the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method is used to set the description of the YouTube video.
     * @param description a string of the description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method is used to get the title of the YouTube video.
     * @return a string of the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method is used to set the title of the YouTube video.
     * @param title a string value of the tile.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method is used to get the number of viewers on the YouTube video.
     * @return a long integer number of the viewers.
     */
    public long getViewCount() {
        return viewCount;
    }

    /**
     * This method is used to set the number of viewers on a YouTube video.
     * @param viewCount a long integer number of viewers.
     */
    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * This method is used to get the number of people who like a YouTube video.
     * @return a long integer number of people who like the video.
     */
    public long getLikeCount() {
        return likeCount;
    }

    /**
     * This method is used to set the number of people who like a YouTube video.
     * @param likeCount a long integer number of people who like the video.
     */
    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method is used to get the number of people who dislike a YouTube video.
     *
     * @return a long integer number of people who dislike a video.
     */
    public long getDislikeCount() {
        return dislikeCount;
    }

    /**
     * This method is used to set the number of people who dislike a YouTube video.
     *
     * @param dislikeCount a long integer number of people who dislike the video.
     */
    public void setDislikeCount(long dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    /**
     * This method is used to get the number of people who favor a YouTube video.
     *
     * @return a long integer number of people who favour the video.
     */
    public long getFavoriteCount() {
        return favoriteCount;
    }

    /**
     * This method is used to set the number of people who favor a YouTube video.
     *
     * @param favoriteCount a long integer number of people who favour the video.
     */
    public void setFavoriteCount(long favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    /**
     * This method is used to get the number of comments on the YouTube video.
     * @return a long integer number of comments on a video.
     */
    public long getCommentCount() {
        return commentCount;
    }

    /**
     * This method is used to set the number of comments on a YouTube video.
     * @param commentCount a long integer number of comments on a video.
     */
    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * This method is used to format a string showing the attributes of each instance of YouTubeVideo class.
     * @return Formatted string of all attributes of each instance of YouTubeVideo class.
     */
    @Override
    public String toString() {
        return "Video Id: " + id + "\nChannel: " + channel + "\nDate: " + date
                + "\nTitle: " + title + "\nDescription: " + description
                + "\nViewCount: " + viewCount + "\nLikeCount: " + likeCount
                + "\nDislikeCount: " + dislikeCount + "\nFavoriteCount: " + favoriteCount
                + "\nCommentCount: " + commentCount;
    }
}
