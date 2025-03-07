package comp8741;

public class YouTubeVideoDescriptionComparator implements YouTubeVideoComparator {
    /**
     * This method is used to compare two YouTubeVideo objects based on the length of description's characters.
     *
     * @param v1 the first video object to be compared.
     * @param v2 the second video object to be compared.
     * @return a negative integer, zero, or a positive integer as the description length of the first video is
     * less than, equal to, or greater than the description length of the second video.
     */
    @Override
    public int compare(YouTubeVideo v1, YouTubeVideo v2) {
        return Integer.compare(v1.getDescription().length(), v2.getDescription().length());
    }
}
