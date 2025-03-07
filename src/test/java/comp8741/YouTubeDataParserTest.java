package comp8741;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test YouTubeDataParser class")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class YouTubeDataParserTest {

    private static YouTubeDataParser parser;

    @BeforeAll
    public static void setUpClass() {
        parser = new YouTubeDataParser();
    }

    @ParameterizedTest
    @Order(1)
    @DisplayName("Test empty file path")
    @ValueSource(strings = {"", " "})
    void testInvalidFilePath(String filePath) {
        YouTubeDataParserException exception = assertThrows(YouTubeDataParserException.class, () -> {
            parser.parse(filePath);
        });
        assertEquals("YouTube data file cannot be empty. Please choose a JSON file.", exception.getMessage());
    }

    @Test
    @Order(2)
    @DisplayName("Test null file path")
    void testNullFilePath() {
        YouTubeDataParserException exception = assertThrows(YouTubeDataParserException.class, () -> {
            parser.parse(null);
        });
        assertEquals("YouTube data file cannot be empty. Please choose a JSON file.", exception.getMessage());
    }

    @ParameterizedTest
    @Order(3)
    @DisplayName("Test unsupported file format")
    @ValueSource(strings = {"data/youtubedata_15_50_info.txt", "data/youtubedata_1_50_info.txt", "data/youtubedata_loremipsum_info.txt"})
    void testUnsupportedFileFormat(String filePath) {
        YouTubeDataParserException exception = assertThrows(YouTubeDataParserException.class, () -> {
            parser.parse(filePath);
        });
        assertEquals("Unsupported file format. Please choose a JSON file.", exception.getMessage());
    }

    @Test
    @Order(4)
    @DisplayName("Test JSON parsing error with incorrect file path")
    void testJsonParsingError() {
        YouTubeDataParserException exception = assertThrows(YouTubeDataParserException.class, () -> {
            parser.parse("incorrect_file.json");
        });
        assertEquals("Error while parsing YouTubeVideo data file", exception.getMessage());
    }

    @Test
    @Order(5)
    @DisplayName("Test parsing json file: youtubedata.json ")
    public void testParseFile1() throws YouTubeDataParserException {
        String fileName = "data/youtubedata.json";
        List<YouTubeVideo> videos = parser.parse(fileName);
        YouTubeVideo firstVideo = videos.get(0);

        assertAll(
                "Grouped Assertion of 1st json file.",
                () -> assertEquals("UCehf4850q1L3ng7s7L54ATA", firstVideo.getId()),
                () -> assertEquals("Joe Bloggs", firstVideo.getChannel()),
                () -> assertEquals("20-04-2016 23:15:17", firstVideo.getDate()),
                () -> assertEquals("This should have a really useful title", firstVideo.getTitle()),
                () -> assertEquals("This should have a really useful description.  However lots of youtubers put links and other promotional material.", firstVideo.getDescription()),
                () -> assertEquals(14180950, firstVideo.getViewCount()),
                () -> assertEquals(28740, firstVideo.getLikeCount()),
                () -> assertEquals(4499, firstVideo.getDislikeCount()),
                () -> assertEquals(0, firstVideo.getFavoriteCount()),
                () -> assertEquals(11754, firstVideo.getCommentCount())
        );

    }

    @Test
    @Order(6)
    @DisplayName("Test parsing json file: youtubedata_1_50.json")
    public void testParseFile2() throws YouTubeDataParserException {
        String fileName = "data/youtubedata_1_50.json";
        List<YouTubeVideo> videos = parser.parse(fileName);
        YouTubeVideo secondVideo = videos.get(0);

        assertAll(
                "Grouped assertion of 2nd file.",
                () -> assertEquals("UCozJ0aIe6xasoYC4p8XOvcw", secondVideo.getId()),
                () -> assertEquals("My Movie", secondVideo.getChannel()),
                () -> assertEquals("06-09-2024 09:00:20", secondVideo.getDate()),
                () -> assertEquals("He asked for a super soldier, but they created an uncontrollable monster 😱 | Far Cry | #movie", secondVideo.getTitle()),
                () -> assertEquals("Far Cry", secondVideo.getDescription()),
                () -> assertEquals(10292900, secondVideo.getViewCount()),
                () -> assertEquals(508662, secondVideo.getLikeCount()),
                () -> assertEquals(0, secondVideo.getDislikeCount()),
                () -> assertEquals(0, secondVideo.getFavoriteCount()),
                () -> assertEquals(4875, secondVideo.getCommentCount())
        );
    }

    @Test
    @Order(7)
    @DisplayName("Test parsing json file: youtubedata_15_50.json")
    public void testParseFile3() throws YouTubeDataParserException {
        String fileName = "data/youtubedata_15_50.json";
        List<YouTubeVideo> videos = parser.parse(fileName);
        YouTubeVideo thirdVideo = videos.get(0);

        assertAll(
                "Grouped assertion of 3rd file.",
                () -> assertEquals("UCehf4850q1L3ng7s7L54ATA", thirdVideo.getId()),
                () -> assertEquals("Sean Naber", thirdVideo.getChannel()),
                () -> assertEquals("20-04-2016 23:15:17", thirdVideo.getDate()),
                () -> assertEquals("Guy cuts down tree, but there's a surprise inside", thirdVideo.getTitle()),
                () -> assertEquals("For licensing/usage please contact licensing@viralhog.com\n\nvideo taken by Ryan Saunders", thirdVideo.getDescription()),
                () -> assertEquals(14187775, thirdVideo.getViewCount()),
                () -> assertEquals(28745, thirdVideo.getLikeCount()),
                () -> assertEquals(4499, thirdVideo.getDislikeCount()),
                () -> assertEquals(0, thirdVideo.getFavoriteCount()),
                () -> assertEquals(11756, thirdVideo.getCommentCount())
        );
    }

    @Test
    @Order(8)
    @DisplayName("Test parsing json file: youtubedata_indextest.json")
    public void testParseFile4() throws YouTubeDataParserException {
        String fileName = "data/youtubedata_indextest.json";
        List<YouTubeVideo> videos = parser.parse(fileName);
        YouTubeVideo fourthVideo = videos.get(0);

        assertAll(
                "Grouped assertion of 4th file.",
                () -> assertEquals("UCehf4850q1L3ng7s7L54ATA", fourthVideo.getId()),
                () -> assertEquals("Joe Bloggs", fourthVideo.getChannel()),
                () -> assertEquals("20-04-2016 23:15:17", fourthVideo.getDate()),
                () -> assertEquals("ONE TWO TWO THREE THREE THREE", fourthVideo.getTitle()),
                () -> assertEquals("FOUR FOUR FOUR FOUR FIVE FIVE FIVE FIVE FIVE", fourthVideo.getDescription()),
                () -> assertEquals(14180950, fourthVideo.getViewCount()),
                () -> assertEquals(28740, fourthVideo.getLikeCount()),
                () -> assertEquals(4499, fourthVideo.getDislikeCount()),
                () -> assertEquals(0, fourthVideo.getFavoriteCount()),
                () -> assertEquals(11754, fourthVideo.getCommentCount())
        );
    }

    @Test
    @Order(9)
    @DisplayName("Test parsing json file: youtubedata_loremipsum.json")
    public void testParseFile5() throws YouTubeDataParserException {
        String fileName = "data/youtubedata_loremipsum.json";
        List<YouTubeVideo> videos = parser.parse(fileName);
        YouTubeVideo fifthVideo = videos.get(0);

        assertAll(
                "Grouped assertion of 5th file.",
                () -> assertEquals("UCXoDvkmiyy57LOU7E6saINw", fifthVideo.getId()),
                () -> assertEquals("hando238", fifthVideo.getChannel()),
                () -> assertEquals("03-05-2016 07:33:43", fifthVideo.getDate()),
                () -> assertEquals("Cras sollicitudin vel libero vitae tempor", fifthVideo.getTitle()),
                () -> assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sollicitudin vel libero vitae tempor. Maecenas velit risus, tincidunt non sagittis a, volutpat vitae leo. Praesent egestas dolor porttitor nulla sollicitudin, sit amet sodales velit convallis. Nullam hendrerit metus a nisi facilisis, ut pulvinar arcu mattis. Aliquam erat volutpat.", fifthVideo.getDescription()),
                () -> assertEquals(183208, fifthVideo.getViewCount()),
                () -> assertEquals(481, fifthVideo.getLikeCount()),
                () -> assertEquals(18, fifthVideo.getDislikeCount()),
                () -> assertEquals(0, fifthVideo.getFavoriteCount()),
                () -> assertEquals(153, fifthVideo.getCommentCount())
        );
    }

    @Test
    @Order(10)
    @DisplayName("Test parsing json file: youtubedata_singleitem.json")
    public void testParseFile6() throws YouTubeDataParserException {
        String fileName = "data/youtubedata_singleitem.json";
        List<YouTubeVideo> videos = parser.parse(fileName);
        YouTubeVideo sixthVideo = videos.get(0);

        assertAll(
                "Grouped assertion of 6th file.",
                () -> assertEquals("UCehf4850q1L3ng7s7L54ATA", sixthVideo.getId()),
                () -> assertEquals("Joe Bloggs", sixthVideo.getChannel()),
                () -> assertEquals("20-04-2016 23:15:17", sixthVideo.getDate()),
                () -> assertEquals("This should have a really useful title", sixthVideo.getTitle()),
                () -> assertEquals("This should have a really useful description.  However lots of youtubers put links and other promotional material.", sixthVideo.getDescription()),
                () -> assertEquals(14180950, sixthVideo.getViewCount()),
                () -> assertEquals(28740, sixthVideo.getLikeCount()),
                () -> assertEquals(4499, sixthVideo.getDislikeCount()),
                () -> assertEquals(0, sixthVideo.getFavoriteCount()),
                () -> assertEquals(11754, sixthVideo.getCommentCount())
        );
    }

    @Test
    @Order(11)
    @DisplayName("Test parsing json file without snippet field.")
    public void testParseNoSnippetDetails() throws YouTubeDataParserException {
        YouTubeDataParserException exception = assertThrows(YouTubeDataParserException.class, () -> {
            parser.parse("data/youtubedata_nosnippet.json");
        });
        assertEquals("Error while parsing YouTubeVideo data file", exception.getMessage());
    }

    @Test
    @Order(11)
    @DisplayName("Test parsing json file without statistics field.")
    public void testParseNoStatisticsDetails() throws YouTubeDataParserException {
        YouTubeDataParserException exception = assertThrows(YouTubeDataParserException.class, () -> {
            parser.parse("data/youtubedata_nostatistics.json");
        });
        assertEquals("Error while parsing YouTubeVideo data file", exception.getMessage());
    }

}
