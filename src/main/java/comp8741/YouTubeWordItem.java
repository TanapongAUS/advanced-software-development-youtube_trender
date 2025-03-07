package comp8741;

import java.util.HashSet;
import java.util.Set;

public class YouTubeWordItem implements Comparable<YouTubeWordItem> {
    private int count; //use to store the number of occurrences that a word found.
    private Set<YouTubeVideo> videos; //use to store associated YouTube videos that have the same word.
    private String word; //use to store different word.

    /**
     * This is the constructor of YouTubeWordItem.
     *
     * @param word a string of found word.
     */
    public YouTubeWordItem(String word) {
        this.count = 0;
        this.videos = new HashSet<>();
        this.word = word;
    }

    /**
     * This method is used to get the different word.
     * @return a string of the found word.
     */
    public String getWord() {
        return word;
    }

    /**
     * This method is used to get the number of occurrences that a word found.
     * @return a positive integer of occurrences of the word.
     */
    public int getCount() {
        return count;
    }

    /**
     * This method is used to increase the number of occurrence in a word.
     */
    public void incrementCount() {
        this.count++;
    }

    /**
     * This method is used to get all the associated YouTube videos that have the same word.
     * @return a set of YouTube videos.
     */
    public Set<YouTubeVideo> getVideos() {
        return videos;
    }

    /**
     * This method is used to insert the new YouTube video that has the same word.
     * @param video a YouTube video object.
     */
    public void add(YouTubeVideo video) {
        // Add the video associated with the word
        videos.add(video);
    }

    /**
     * This method is used to compare the YouTubeWordItem with another one based on their occurrences.
     * This method reverses the natural ordering to sort items in descending order of found occurrences.
     * @param other the YouTube video object to be compared.
     * @return a negative integer, zero, or positive integer as the YouTubeWordItem object count is greater than, equal to,
     *          or less than the specified YouTubeWordItem object's count.
     */
    @Override
    public int compareTo(YouTubeWordItem other) {
        return Integer.compare(other.count, this.count);
    }

    /**
     * This method is used to format the displayed string of this class.
     * @return a formatted string.
     */
    @Override
    public String toString() {
        return word + " [" + count + "]";
    }
}
