package comp8741;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class YouTubeVideoDateComparator implements YouTubeVideoComparator {
    //set date formatter for parsing the date string to date object for easy comparison.
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    /**
     * This method is used to compare two YouTubeVideo objects based on their published date and time.
     *
     * @param v1 the first video object to be compared.
     * @param v2 the second video object to be compared.
     * @return a negative integer, zero, or a positive integer as the published date and time of the first video is
     * less than, equal to, or greater than the date and time of the second video.
     */
    @Override
    public int compare(YouTubeVideo v1, YouTubeVideo v2) {
        LocalDateTime video1DateTime = LocalDateTime.parse(v1.getDate(), formatter);
        LocalDateTime video2DateTime = LocalDateTime.parse(v2.getDate(), formatter);

        return video1DateTime.compareTo(video2DateTime);
    }
}
