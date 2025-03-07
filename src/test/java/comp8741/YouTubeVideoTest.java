package comp8741;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test YouTubeVideo class")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class YouTubeVideoTest {

    private static YouTubeVideo video;

    @BeforeAll
    public static void setUpClass() {
        video = new YouTubeVideo("UgdfSDFSDF234234", "KingMaster", "2024-10-01T12:34:56Z",
                "What are DevOps metrics?", "DevOps metrics have four key metrics.", 1000000, 200000, 30, 40000, 500);
    }

    @Test
    @Order(1)
    @DisplayName("Test Getters")
    void testConstructorAndGetters() {
        assertEquals("UgdfSDFSDF234234", video.getId());
        assertEquals("KingMaster", video.getChannel());
        assertEquals("01-10-2024 12:34:56", video.getDate());
        assertEquals("What are DevOps metrics?", video.getTitle());
        assertEquals("DevOps metrics have four key metrics.", video.getDescription());
        assertEquals(1000000, video.getViewCount());
        assertEquals(200000, video.getLikeCount());
        assertEquals(30, video.getDislikeCount());
        assertEquals(40000, video.getFavoriteCount());
        assertEquals(500, video.getCommentCount());
    }

    @ParameterizedTest
    @Order(2)
    @DisplayName("Test setting Channel ID")
    @ValueSource(strings = {"vid001", "vid002"})
    void testSetId(String newId) {
        video.setId(newId);
        assertEquals(newId, video.getId());
    }

    @ParameterizedTest
    @Order(3)
    @DisplayName("Test setting Channel")
    @ValueSource(strings = {"MrBean", "TomorrowSunshine"})
    void testSetChannel(String newChannel) {
        video.setChannel(newChannel);
        assertEquals(newChannel, video.getChannel());
    }

    @ParameterizedTest
    @Order(4)
    @DisplayName("Test setting published date with valid dates")
    @ValueSource(strings = {"02-10-2024 10:00:00", "03-10-2024 15:45:30"})
    void testSetDate(String dateInput) {
        video.setDate(dateInput);
        assertEquals(dateInput, video.getDate());
    }

    @Test
    @Order(5)
    @DisplayName("Test setting channel title")
    void testSetTitle() {
        video.setTitle("You are the best");
        assertEquals("You are the best", video.getTitle());
    }

    @Test
    @Order(6)
    @DisplayName("Test setting Description")
    void testSetDescription() {
        video.setDescription("Modern web technologies.");
        assertEquals("Modern web technologies.", video.getDescription());
    }

    @ParameterizedTest
    @Order(7)
    @DisplayName("Test setting view count")
    @ValueSource(longs = {0, 1, 1500000})
    void testSetViewCount(long newViewCount) {
        video.setViewCount(newViewCount);
        assertEquals(newViewCount, video.getViewCount());
    }

    @ParameterizedTest
    @Order(8)
    @DisplayName("Test setting Like Count")
    @ValueSource(longs = {0, 1, 999999})
    void testSetLikeCount(long newLikeCount) {
        video.setLikeCount(newLikeCount);
        assertEquals(newLikeCount, video.getLikeCount());
    }

    @ParameterizedTest
    @Order(9)
    @DisplayName("Test setting Dislike Count")
    @ValueSource(longs = {0, 1, 34})
    void testSetDislikeCount(long newDislikeCount) {
        video.setDislikeCount(newDislikeCount);
        assertEquals(newDislikeCount, video.getDislikeCount());
    }

    @ParameterizedTest
    @Order(10)
    @DisplayName("Test setting Favorite Count")
    @ValueSource(longs = {0, 1, 8232})
    void testSetFavoriteCount(long newFavoriteCount) {
        video.setFavoriteCount(newFavoriteCount);
        assertEquals(newFavoriteCount, video.getFavoriteCount());
    }

    @ParameterizedTest
    @Order(11)
    @DisplayName("Test setting Comment Count")
    @ValueSource(longs = {0, 1, 5431})
    void testSetCommentCount(long newCommentCount) {
        video.setCommentCount(newCommentCount);
        assertEquals(newCommentCount, video.getCommentCount());
    }

    @Test
    @Order(12)
    @DisplayName("Test toString()")
    void testToString() {

        video = new YouTubeVideo("vid999", "TestChannel", "2024-10-01T12:34:56Z",
                "Test Video Title", "Test description", 1000000, 200000, 30, 40000, 500);

        String expectedString = "Video Id: vid999\nChannel: TestChannel\nDate: 01-10-2024 12:34:56\n" +
                "Title: Test Video Title\nDescription: Test description\n" +
                "ViewCount: 1000000\nLikeCount: 200000\nDislikeCount: 30\nFavoriteCount: 40000\nCommentCount: 500";
        assertEquals(expectedString, video.toString());
    }

    @Test
    @Order(13)
    @DisplayName("Test date parsing exception")
    void testDateParsingException() {
        String incorrectDate = "12/12/2024-15:00:00";
        YouTubeVideo video = new YouTubeVideo("SDFSDpsdfk02324", "MonsterKing", incorrectDate,
                "Godzilla", "A monster movie.", 1000000, 200000, 30, 40000, 500);

        assertEquals(incorrectDate, video.getDate());
    }
}
