package comp8741;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test YouTubeVideoIndexer and YouTubeWordItem class")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class YouTubeVideoIndexerTest {
    private static YouTubeDataParser dataParser;

    private List<YouTubeVideo> originalVideos1;
    private List<YouTubeVideo> originalVideos2;
    private List<YouTubeVideo> originalVideos3;
    private List<YouTubeVideo> originalVideos4;
    private List<YouTubeVideo> originalVideos5;

    private String fileName1 = "data/youtubedata.json";
    private String fileName2 = "data/youtubedata_1_50.json";
    private String fileName3 = "data/youtubedata_15_50.json";
    private String fileName4 = "data/youtubedata_indextest.json";
    private String fileName5 = "data/youtubedata_loremipsum.json";

    private YouTubeVideoIndexer trending;
    private List<YouTubeWordItem> sortedWords;
    private YouTubeWordItem wordItem;

    @BeforeEach
    void setUp() throws YouTubeDataParserException {
        dataParser = new YouTubeDataParser();
        originalVideos1 = dataParser.parse(fileName1);
        originalVideos2 = dataParser.parse(fileName2);
        originalVideos3 = dataParser.parse(fileName3);
        originalVideos4 = dataParser.parse(fileName4);
        originalVideos5 = dataParser.parse(fileName5);
    }

    @Test
    @Order(1)
    @DisplayName("Test getSortedWords")
    void testGetSortedWords() {
        YouTubeWordItem firstItem;
        YouTubeWordItem secondItem;

        //Test with Dataset_1: youtubedata.json
        trending = new YouTubeVideoIndexer(originalVideos1);
        sortedWords = trending.getSortedWords();

        firstItem = sortedWords.get(0);
        assertEquals("a", firstItem.getWord());
        assertEquals(2, firstItem.getCount());
        secondItem = sortedWords.get(1);
        assertEquals("really", secondItem.getWord());
        assertEquals(2, secondItem.getCount());

        //Test with Dataset_2: youtubedata_1_50.json
        trending = new YouTubeVideoIndexer(originalVideos2);
        sortedWords = trending.getSortedWords();

        firstItem = sortedWords.get(0);
        assertEquals("the", firstItem.getWord());
        assertEquals(66, firstItem.getCount());
        secondItem = sortedWords.get(1);
        assertEquals("a", secondItem.getWord());
        assertEquals(33, secondItem.getCount());

        //Test with Dataset_3: youtubedata_15_50.json
        trending = new YouTubeVideoIndexer(originalVideos3);
        sortedWords = trending.getSortedWords();

        firstItem = sortedWords.get(0);
        assertEquals("the", firstItem.getWord());
        assertEquals(97, firstItem.getCount());
        secondItem = sortedWords.get(1);
        assertEquals("-", secondItem.getWord());
        assertEquals(93, secondItem.getCount());

        //Test with Dataset_4: youtubedata_indextest.json
        trending = new YouTubeVideoIndexer(originalVideos4);
        sortedWords = trending.getSortedWords();

        firstItem = sortedWords.get(0);
        assertEquals("FIVE", firstItem.getWord());
        assertEquals(5, firstItem.getCount());
        secondItem = sortedWords.get(1);
        assertEquals("FOUR", secondItem.getWord());
        assertEquals(4, secondItem.getCount());

        //Test with Dataset_5: youtubedata_loremipsum.json
        trending = new YouTubeVideoIndexer(originalVideos5);
        sortedWords = trending.getSortedWords();

        firstItem = sortedWords.get(0);
        assertEquals("sit", firstItem.getWord());
        assertEquals(17, firstItem.getCount());
        secondItem = sortedWords.get(1);
        assertEquals("ipsum", secondItem.getWord());
        assertEquals(12, secondItem.getCount());
    }

    @Test
    @Order(2)
    @DisplayName("Test getWordCount")
    void testGetWordCount() {
        //Test with Dataset_1: youtubedata.json
        trending = new YouTubeVideoIndexer(originalVideos1);
        assertEquals(1, trending.getWordCount("youtubers"));
        assertEquals(1, trending.getWordCount("promotional"));

        //Test with Dataset_2: youtubedata_1_50.json
        trending = new YouTubeVideoIndexer(originalVideos2);
        assertEquals(32, trending.getWordCount("Stranger"));
        assertEquals(30, trending.getWordCount("Things"));

        //Test with Dataset_3: youtubedata_15_50.json
        trending = new YouTubeVideoIndexer(originalVideos3);
        assertEquals(22, trending.getWordCount("Toys"));
        assertEquals(22, trending.getWordCount("Surprise"));

        //Test with Dataset_4: youtubedata_indextest.json
        trending = new YouTubeVideoIndexer(originalVideos4);
        assertEquals(3, trending.getWordCount("THREE"));
        assertEquals(2, trending.getWordCount("TWO"));

        //Test with Dataset_5: youtubedata_loremipsum.json
        trending = new YouTubeVideoIndexer(originalVideos5);
        assertEquals(12, trending.getWordCount("consectetur"));
        assertEquals(9, trending.getWordCount("Aliquam"));
    }

    @Test
    @Order(3)
    @DisplayName("Test getWordVideos, verify channel ID and number of associated video with the specified word")
    void testGetWordVideos() {
        List<YouTubeVideo> videoList;
        String idText;
        String[] idChecker;
        List<String> idResult;

        //Test with Dataset_1: youtubedata.json
        trending = new YouTubeVideoIndexer(originalVideos1);
        videoList = new ArrayList<>(trending.getWordVideos("a"));
        //verify the channel id whether it matches the expected id.
        assertEquals("UCehf4850q1L3ng7s7L54ATA", videoList.get(0).getId());

        // Check number of the associated videos
        assertEquals(1, videoList.size());

        //Test with Dataset_2: youtubedata_1_50.json
        trending = new YouTubeVideoIndexer(originalVideos2);
        videoList = new ArrayList<>(trending.getWordVideos("and"));

        //list all expected ids to be compared with the actual ids.
        idText = "UCxEIsWMaDpY34Y6Eg91HpHQ UC5lwAZVi50kX_qQxlcn1iNA UCa6vGFO9ty8v5KZJXQxdhaw UCtjitZkKk537mAP0v6z9Hng UC-fIOAuozqNXLKvfhfTytqw UCsuekIjdgj7aWmD4UufRJSQ UCEzfu-v75N1WWYf9MftkTsw";
        idChecker = idText.split("\\s+");

        idResult = new ArrayList<>();
        for (YouTubeVideo video : videoList) {
            idResult.add(video.getId());
        }

        //verify the channel id whether it matches the expected id.
        for (int i = 0; i < idChecker.length; i++) {
            assertTrue(idResult.contains(idChecker[i]));
        }
        // Check number of the associated videos
        assertEquals(7, videoList.size());

        //Test with Dataset_3: youtubedata_15_50.json
        trending = new YouTubeVideoIndexer(originalVideos3);
        videoList = new ArrayList<>(trending.getWordVideos("-"));

        //list all expected ids to be compared with the actual ids.
        idText = "UCc-ISYJT3eeeXjoOvHV9w8Q UCr3cBLTYmIK9kY0F_OdFWFQ UCPTgHz-ZYiYvmArYkmC-5tQ UCDArKsXbbF1IXXlOVyXebJA UCPckaTvXvCmfCSZhFIIhi0g UCelMeixAOTs2OQAAi9wU8-g UC3FzLarMpYyt2Hm1rk56Xyw UC1DeG8ZTab89_XNNqWxlDqA UC5lMzpZvCLpwyvu348B8zYw UCPRlGA2w7C_DVw-1ynolJYw UC6E2mP01ZLH_kbAyeazCNdg UCilwZiBBfI9X6yiZRzWty8Q UCsooa4yRKGN_zEE8iknghZA UCWK-4MQPIP8ODMpwCgeHuRg UC8HQ5RQsiUTvgDkSmkVzfvg UCxaVOVnhmT0-HCUv72jtOTA UCFuh236pCvwmXWH3JipTwSg UCxRQtyA1fTsz92QErYYPSag UCYFo16eFDNhD3W_POj9gKFw UCP-iaFrmWcOG0o461wMicdg";
        idChecker = idText.split("\\s+");

        idResult = new ArrayList<>();
        for (YouTubeVideo video : videoList) {
            idResult.add(video.getId());
        }

        //verify the channel id whether it matches the expected id.
        for (int i = 0; i < idChecker.length; i++) {
            assertTrue(idResult.contains(idChecker[i]));
        }
        // Check number of the associated videos
        assertEquals(20, videoList.size());

        //Test with Dataset_4: youtubedata_indextest.json
        trending = new YouTubeVideoIndexer(originalVideos4);
        videoList = new ArrayList<>(trending.getWordVideos("FOUR"));

        //list all expected ids to be compared with the actual ids.
        idText = "UCehf4850q1L3ng7s7L54ATA";
        idChecker = idText.split("\\s+");

        idResult = new ArrayList<>();
        for (YouTubeVideo video : videoList) {
            idResult.add(video.getId());
        }

        //verify the channel id whether it matches the expected id.
        for (int i = 0; i < idChecker.length; i++) {
            assertTrue(idResult.contains(idChecker[i]));
        }
        // Check number of the associated videos
        assertEquals(1, videoList.size());

        //Test with Dataset_5: youtubedata_loremipsum.json
        trending = new YouTubeVideoIndexer(originalVideos5);
        videoList = new ArrayList<>(trending.getWordVideos("vitae"));

        //list all expected ids to be compared with the actual ids.
        idText = "UC7lxhDFfw0jbfr0K8W3Ydmw UCdxi8d8qRsRyUi2ERYjYb-w UCPDis9pjXuqyI7RYLJ-TTSA UCQzdMyuz0Lf4zo4uGcEujFw UCUK0HBIBWgM2c4vsPhkYY4w UCXoDvkmiyy57LOU7E6saINw UClqoU3DHuKFsYCCLXUNUE1g";
        idChecker = idText.split("\\s+");

        idResult = new ArrayList<>();
        for (YouTubeVideo video : videoList) {
            idResult.add(video.getId());
        }

        //verify the channel id whether it matches the expected id.
        for (int i = 0; i < idChecker.length; i++) {
            assertTrue(idResult.contains(idChecker[i]));
        }
        // Check number of the associated videos
        assertEquals(7, videoList.size());
    }

    @Test
    @Order(4)
    @DisplayName("Test getWordItem by printing out toString from YouTubeWordItem")
    void testGetWordItem() {
        //Test with Dataset_1: youtubedata.json
        trending = new YouTubeVideoIndexer(originalVideos1);
        //Test an existing word
        wordItem = trending.getWordItem("a");
        assertEquals("a [2]", wordItem.toString());

        //Test an existing word
        wordItem = trending.getWordItem("lots");
        assertEquals("lots [1]", wordItem.toString());

        //Test a non-existing word
        wordItem = trending.getWordItem("ABABABABABA");
        assertNull(wordItem);

        //Test with Dataset_2: youtubedata_1_50.json
        trending = new YouTubeVideoIndexer(originalVideos2);
        //Test an existing word
        wordItem = trending.getWordItem("and");
        assertEquals("and [32]", wordItem.toString());

        //Test an existing word
        wordItem = trending.getWordItem("Things");
        assertEquals("Things [30]", wordItem.toString());

        //Test a non-existing word
        wordItem = trending.getWordItem("zzzzzzzzzzzzzzzzzz");
        assertNull(wordItem);

        //Test with Dataset_3: youtubedata_15_50.json
        trending = new YouTubeVideoIndexer(originalVideos3);
        //Test an existing word
        wordItem = trending.getWordItem("The");
        assertEquals("The [26]", wordItem.toString());

        //Test an existing word
        wordItem = trending.getWordItem("you");
        assertEquals("you [22]", wordItem.toString());

        //Test a non-existing word
        wordItem = trending.getWordItem("xxxxxxxxxxxxxxx");
        assertNull(wordItem);

        //Test with Dataset_4: youtubedata_indextest.json
        trending = new YouTubeVideoIndexer(originalVideos4);
        //Test an existing word
        wordItem = trending.getWordItem("THREE");
        assertEquals("THREE [3]", wordItem.toString());

        //Test an existing word
        wordItem = trending.getWordItem("TWO");
        assertEquals("TWO [2]", wordItem.toString());

        //Test a non-existing word
        wordItem = trending.getWordItem("yyyyyyyyyyyyyyyyy");
        assertNull(wordItem);

        //Test with Dataset_5: youtubedata_loremipsum.json
        trending = new YouTubeVideoIndexer(originalVideos5);
        //Test an existing word
        wordItem = trending.getWordItem("elit.");
        assertEquals("elit. [10]", wordItem.toString());

        //Test an existing word
        wordItem = trending.getWordItem("sed");
        assertEquals("sed [9]", wordItem.toString());

        //Test a non-existing word
        wordItem = trending.getWordItem("GGGGGGGGGGGGGGG");
        assertNull(wordItem);
    }

}
