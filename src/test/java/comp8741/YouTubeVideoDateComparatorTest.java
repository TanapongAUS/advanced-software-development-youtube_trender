package comp8741;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test YouTubeVideoDateComparator class")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class YouTubeVideoDateComparatorTest {
    private static YouTubeDataParser dataParser;
    private YouTubeVideoDateComparator comparator;
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
        comparator = new YouTubeVideoDateComparator();
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
    @DisplayName("Test 1st File, Comparing Videos by Date (Ascending order)")
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
    @DisplayName("Test 2nd File, Comparing Videos by Date (Ascending order)")
    void compareSecondFileData() {
        originalVideos2.sort(comparator);
        List<YouTubeVideo> sortedVideos = originalVideos2;
        video2 = sortedVideos.get(0);

        assertAll(
                "Grouped Assertions of the Sorted Video of 2nd File:",
                () -> assertEquals(video2.getId(), "UCaxpAIn0XYokA8vLRjw8GHg"),
                () -> assertEquals(video2.getChannel(), "YouTube International Spotlight"),
                () -> assertEquals(video2.getTitle(), "Celebrate the return of Beetlejuice with the new Beetlejuice Beetlejuice trend #BeetlejuiceOnShorts"),
                () -> assertEquals(video2.getDate(), "28-08-2024 19:47:25"),
                () -> assertEquals(video2.getViewCount(), 138739),
                () -> assertEquals(video2.getLikeCount(), 2371),
                () -> assertEquals(video2.getDislikeCount(), 0),
                () -> assertEquals(video2.getFavoriteCount(), 0),
                () -> assertEquals(video2.getCommentCount(), 0)
        );
    }

    @Test
    @Order(3)
    @DisplayName("Test 3rd File, Comparing Videos by Date (Ascending order)")
    void compareThirdFileData() {
        originalVideos3.sort(comparator);
        List<YouTubeVideo> sortedVideos = originalVideos3;
        video3 = sortedVideos.get(0);

        assertAll(
                "Grouped Assertions of the Sorted Video of 3rd File:",
                () -> assertEquals(video3.getId(), "UC6eCfDyUc4TwvwxwTDxMfMg"),
                () -> assertEquals(video3.getChannel(), "luckyhoof"),
                () -> assertEquals(video3.getTitle(), "Reunited after a long holiday"),
                () -> assertEquals(video3.getDate(), "11-02-2008 22:59:52"),
                () -> assertEquals(video3.getViewCount(), 1885637),
                () -> assertEquals(video3.getLikeCount(), 388),
                () -> assertEquals(video3.getDislikeCount(), 57),
                () -> assertEquals(video3.getFavoriteCount(), 0),
                () -> assertEquals(video3.getCommentCount(), 79)
        );
    }

    @Test
    @Order(4)
    @DisplayName("Test 4th File, Comparing Videos by Date (Ascending order)")
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
    @DisplayName("Test 5th File, Comparing Videos by Date (Ascending order)")
    void compareFifthFileData() {
        originalVideos5.sort(comparator);
        List<YouTubeVideo> sortedVideos = originalVideos5;
        video5 = sortedVideos.get(0);

        assertAll(
                "Grouped Assertions of the Sorted Video of 5th File:",
                () -> assertEquals(video5.getId(), "UCPDis9pjXuqyI7RYLJ-TTSA"),
                () -> assertEquals(video5.getChannel(), "FailArmy"),
                () -> assertEquals(video5.getTitle(), "Phasellus at turpis aliquet odio posuere accumsan"),
                () -> assertEquals(video5.getDate(), "29-04-2016 16:30:00"),
                () -> assertEquals(video5.getViewCount(), 4426368),
                () -> assertEquals(video5.getLikeCount(), 64561),
                () -> assertEquals(video5.getDislikeCount(), 1986),
                () -> assertEquals(video5.getFavoriteCount(), 0),
                () -> assertEquals(video5.getCommentCount(), 2581)
        );
    }

    @Test
    @Order(6)
    @DisplayName("Test 6th File, Comparing Videos by Date (Ascending order)")
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