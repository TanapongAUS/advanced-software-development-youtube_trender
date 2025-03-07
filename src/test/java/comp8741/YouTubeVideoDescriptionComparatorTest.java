package comp8741;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test YouTubeVideoDescriptionComparator class")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class YouTubeVideoDescriptionComparatorTest {
    private static YouTubeDataParser dataParser;
    private YouTubeVideoDescriptionComparator comparator;
    private YouTubeVideo video1;
    private YouTubeVideo video2;
    private YouTubeVideo video3;
    private YouTubeVideo video4;
    private YouTubeVideo video5;
    private YouTubeVideo video6;
    private List<YouTubeVideo> originalVideos1;
    private List<YouTubeVideo> originalVideos2;
    private List<YouTubeVideo> originalVideos3;
    private List<YouTubeVideo> originalVideos4;
    private List<YouTubeVideo> originalVideos5;
    private List<YouTubeVideo> originalVideos6;
    private String fileName1 = "data/youtubedata.json";
    private String fileName2 = "data/youtubedata_1_50.json";
    private String fileName3 = "data/youtubedata_15_50.json";
    private String fileName4 = "data/youtubedata_indextest.json";
    private String fileName5 = "data/youtubedata_loremipsum.json";
    private String fileName6 = "data/youtubedata_singleitem.json";

    @BeforeEach
    void setUp() throws YouTubeDataParserException {
        comparator = new YouTubeVideoDescriptionComparator();
        dataParser = new YouTubeDataParser();
        originalVideos1 = dataParser.parse(fileName1);
        originalVideos2 = dataParser.parse(fileName2);
        originalVideos3 = dataParser.parse(fileName3);
        originalVideos4 = dataParser.parse(fileName4);
        originalVideos5 = dataParser.parse(fileName5);
        originalVideos6 = dataParser.parse(fileName6);
    }

    @Test
    @Order(1)
    @DisplayName("Test 1st File, Comparing Videos by Description Length (Ascending order)")
    void compareFirstFileData() {
        originalVideos1.sort(comparator);
        List<YouTubeVideo> sortedVideos = originalVideos1;
        video1 = sortedVideos.get(0);

        assertAll(
                "Grouped Assertions of the Sorted Video of 1st File:",
                () -> assertEquals(video1.getId(), "UCehf4850q1L3ng7s7L54ATA"),
                () -> assertEquals(video1.getChannel(), "Joe Bloggs"),
                () -> assertEquals(video1.getTitle(), "This should have a really useful title"),
                () -> assertEquals(video1.getDate(), "20-04-2016 23:15:17"),
                () -> assertEquals(video1.getViewCount(), 14180950),
                () -> assertEquals(video1.getLikeCount(), 28740),
                () -> assertEquals(video1.getDislikeCount(), 4499),
                () -> assertEquals(video1.getFavoriteCount(), 0),
                () -> assertEquals(video1.getCommentCount(), 11754)
        );
    }

    @Test
    @Order(2)
    @DisplayName("Test 2nd File, Comparing Videos by Description Length (Ascending order)")
    void compareSecondFileData() {
        originalVideos2.sort(comparator);
        List<YouTubeVideo> sortedVideos = originalVideos2;
        video2 = sortedVideos.get(0);

        assertAll(
                "Grouped Assertions of the Sorted Video of 2nd File:",
                () -> assertEquals(video2.getId(), "UCgRHYxFhV_SjP1wBO6_OTuw"),
                () -> assertEquals(video2.getChannel(), "Binge Heroes"),
                () -> assertEquals(video2.getTitle(), "It Makes You Dangerous || Captain America: Civil War #captainamerica"),
                () -> assertEquals(video2.getDate(), "09-09-2024 03:14:08"),
                () -> assertEquals(video2.getViewCount(), 7681276),
                () -> assertEquals(video2.getLikeCount(), 619195),
                () -> assertEquals(video2.getDislikeCount(), 0),
                () -> assertEquals(video2.getFavoriteCount(), 0),
                () -> assertEquals(video2.getCommentCount(), 3090)
        );
    }

    @Test
    @Order(3)
    @DisplayName("Test 3rd File, Comparing Videos by Description Length (Ascending order)")
    void compareThirdFileData() {
        originalVideos3.sort(comparator);
        List<YouTubeVideo> sortedVideos = originalVideos3;
        video3 = sortedVideos.get(0);

        assertAll(
                "Grouped Assertions of the Sorted Video of 3rd File:",
                () -> assertEquals(video3.getId(), "UC1DeG8ZTab89_XNNqWxlDqA"),
                () -> assertEquals(video3.getChannel(), "AllHamishandAndyVids"),
                () -> assertEquals(video3.getTitle(), "Hamish & Andy - Kangaroo Lady"),
                () -> assertEquals(video3.getDate(), "01-05-2016 06:00:01"),
                () -> assertEquals(video3.getViewCount(), 5313),
                () -> assertEquals(video3.getLikeCount(), 116),
                () -> assertEquals(video3.getDislikeCount(), 0),
                () -> assertEquals(video3.getFavoriteCount(), 0),
                () -> assertEquals(video3.getCommentCount(), 12)
        );
    }

    @Test
    @Order(4)
    @DisplayName("Test 4th File, Comparing Videos by Description Length (Ascending order)")
    void compareFourthFileData() {
        originalVideos4.sort(comparator);
        List<YouTubeVideo> sortedVideos = originalVideos4;
        video4 = sortedVideos.get(0);

        assertAll(
                "Grouped Assertions of the Sorted Video of 4th File:",
                () -> assertEquals(video4.getId(), "UCehf4850q1L3ng7s7L54ATA"),
                () -> assertEquals(video4.getChannel(), "Joe Bloggs"),
                () -> assertEquals(video4.getTitle(), "ONE TWO TWO THREE THREE THREE"),
                () -> assertEquals(video4.getDate(), "20-04-2016 23:15:17"),
                () -> assertEquals(video4.getViewCount(), 14180950),
                () -> assertEquals(video4.getLikeCount(), 28740),
                () -> assertEquals(video4.getDislikeCount(), 4499),
                () -> assertEquals(video4.getFavoriteCount(), 0),
                () -> assertEquals(video4.getCommentCount(), 11754)
        );
    }

    @Test
    @Order(5)
    @DisplayName("Test 5th File, Comparing Videos by Description Length (Ascending order)")
    void compareFifthFileData() {
        originalVideos5.sort(comparator);
        List<YouTubeVideo> sortedVideos = originalVideos5;
        video5 = sortedVideos.get(0);

        assertAll(
                "Grouped Assertions of the Sorted Video of 5th File:",
                () -> assertEquals(video5.getId(), "UCq19-LqvG35A-30oyAiPiqA"),
                () -> assertEquals(video5.getChannel(), "Radiohead"),
                () -> assertEquals(video5.getTitle(), "Phasellus lacinia fringilla tristique"),
                () -> assertEquals(video5.getDate(), "03-05-2016 14:59:21"),
                () -> assertEquals(video5.getViewCount(), 5533768),
                () -> assertEquals(video5.getLikeCount(), 93941),
                () -> assertEquals(video5.getDislikeCount(), 3996),
                () -> assertEquals(video5.getFavoriteCount(), 0),
                () -> assertEquals(video5.getCommentCount(), 11351)
        );
    }

    @Test
    @Order(6)
    @DisplayName("Test 6th File, Comparing Videos by Description Length (Ascending order)")
    void compareSixthFileData() {
        originalVideos6.sort(comparator);
        List<YouTubeVideo> sortedVideos = originalVideos6;
        video6 = sortedVideos.get(0);

        assertAll(
                "Grouped Assertions of the Sorted Video of 6th File:",
                () -> assertEquals(video6.getId(), "UCehf4850q1L3ng7s7L54ATA"),
                () -> assertEquals(video6.getChannel(), "Joe Bloggs"),
                () -> assertEquals(video6.getTitle(), "This should have a really useful title"),
                () -> assertEquals(video6.getDate(), "20-04-2016 23:15:17"),
                () -> assertEquals(video6.getViewCount(), 14180950),
                () -> assertEquals(video6.getLikeCount(), 28740),
                () -> assertEquals(video6.getDislikeCount(), 4499),
                () -> assertEquals(video6.getFavoriteCount(), 0),
                () -> assertEquals(video6.getCommentCount(), 11754)
        );
    }
}