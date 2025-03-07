package comp8741;

public class YouTubeVideoChannelComparator implements YouTubeVideoComparator {

    /**
     * This method is used to compare two YouTubeVideo objects based on their channel names
     *
     * @param v1 the first video object to be compared.
     * @param v2 the second video object to be compared.
     * @return a negative integer, zero, or a positive integer as the channel of the first video is alphabetically
     * less than, equal to, or greater than the channel of the second video.
     */
    @Override
    public int compare(YouTubeVideo v1, YouTubeVideo v2) {
        return v1.getChannel().compareTo(v2.getChannel());
    }
}
