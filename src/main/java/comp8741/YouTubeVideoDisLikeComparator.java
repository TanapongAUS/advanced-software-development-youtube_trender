package comp8741;

public class YouTubeVideoDisLikeComparator implements YouTubeVideoComparator {
    /**
     * This method is used to compare two YouTubeVideo objects based on their dislikes count.
     *
     * @param v1 the first video object to be compared.
     * @param v2 the second video object to be compared.
     * @return a negative integer, zero, or a positive integer as the number of dislikes of the first video is
     * less than, equal to, or greater than the number of dislikes of the second video.
     */
    @Override
    public int compare(YouTubeVideo v1, YouTubeVideo v2) {
        return Long.compare(v1.getDislikeCount(), v2.getDislikeCount());
    }
}
