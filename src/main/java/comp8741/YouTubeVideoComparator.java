package comp8741;

import java.util.Comparator;

/**
 * This is a Interface created as an abstract for all comparators used in comparing YouTubeVideo
 */
public interface YouTubeVideoComparator extends Comparator<YouTubeVideo> {
    /**
     * This method is used to compare two YouTube videos.
     *
     * @param v1 the first video object to be compared.
     * @param v2 the second video object to be compared.
     * @return a negative integer, zero, and positive integer as the value of the first video is
     * less than, equal to, or greater than the value of the second video.
     */
    int compare(YouTubeVideo v1, YouTubeVideo v2);
}
