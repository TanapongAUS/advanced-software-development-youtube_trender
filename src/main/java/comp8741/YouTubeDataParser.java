package comp8741;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class YouTubeDataParser {

    /**
     * This method is used to parse a JSON file containing details of YouTube videos into a list of YouTube video collections.
     *
     * @param filePath The given file path of YouTube Video data.
     * @return This method returns a list of YouTube video collections.
     */
    public List<YouTubeVideo> parse(String filePath) throws YouTubeDataParserException {
        List<YouTubeVideo> videos = new ArrayList<>();
        if (filePath == null || filePath.trim().equals("")) {
            throw new YouTubeDataParserException("YouTube data file cannot be empty. Please choose a JSON file.");
        }
        if (!filePath.toLowerCase().endsWith(".json")) {
            throw new YouTubeDataParserException("Unsupported file format. Please choose a JSON file.");
        }
        try {
            //Read JSON data from the given file path
            JsonReader jsonReader = Json.createReader(new FileInputStream(filePath));
            JsonObject jsonObj = jsonReader.readObject();

            //Read the values of the items field
            JsonArray items = jsonObj.getJsonArray("items");
            if (items == null) {
                JsonObject snipDetails = jsonObj.getJsonObject("snippet");
                if (snipDetails == null) {
                    throw new YouTubeDataParserException("YouTube data file does not contain a snippet details.");
                }
                JsonObject statDetails = jsonObj.getJsonObject("statistics");
                if (statDetails == null) {
                    throw new YouTubeDataParserException("YouTube data file does not contain a statistics details.");
                }
                String channelId = snipDetails.getString("channelId", "");
                String titleText = snipDetails.getString("title", "");
                String des = snipDetails.getString("description", "");
                String publishedDate = snipDetails.getString("publishedAt", "");
                String channelText = snipDetails.getString("channelTitle", "");
                long viewNum = Long.parseLong(statDetails.getString("viewCount", "0"));
                long likeNum = Long.parseLong(statDetails.getString("likeCount", "0"));
                long disLikeNum = Long.parseLong(statDetails.getString("dislikeCount", "0"));
                long favoriteNum = Long.parseLong(statDetails.getString("favoriteCount", "0"));
                long commentNum = Long.parseLong(statDetails.getString("commentCount", "0"));

                //Add details of YouTubeVideo to the videos variable
                videos.add(new YouTubeVideo(channelId, channelText, publishedDate, titleText, des, viewNum, likeNum, disLikeNum, favoriteNum, commentNum));
            } else {
                for (int i = 0; i < items.size(); i++) {
                    //Read each object of nth element of the items.
                    JsonObject item = items.getJsonObject(i);
                    //Read the values of the snippet field
                    JsonObject snippet = item.getJsonObject("snippet");
                    //Read the values of the statistics field
                    JsonObject statistics = item.getJsonObject("statistics");

                    //Set all relevant attributes to add a new element to the YoutubeVideo List.
                    String id = snippet.getString("channelId", "");
                    String title = snippet.getString("title", "");
                    String description = snippet.getString("description", "");
                    String publishedAt = snippet.getString("publishedAt", "");
                    String channel = snippet.getString("channelTitle", "");
                    long viewCount = Long.parseLong(statistics.getString("viewCount", "0"));
                    long likeCount = Long.parseLong(statistics.getString("likeCount", "0"));
                    long disLikeCount = Long.parseLong(statistics.getString("dislikeCount", "0"));
                    long favoriteCount = Long.parseLong(statistics.getString("favoriteCount", "0"));
                    long commentCount = Long.parseLong(statistics.getString("commentCount", "0"));

                    //Add each instance of YouTubeVideo to the videos variable
                    videos.add(new YouTubeVideo(id, channel, publishedAt, title, description, viewCount, likeCount, disLikeCount, favoriteCount, commentCount));
                }
            }
        } catch (Exception exception) {
            //Log the exception
            Logger.getLogger(YouTubeDataParser.class.getName()).log(Level.SEVERE, null, exception);
            throw new YouTubeDataParserException("Error while parsing YouTubeVideo data file");
        }

        return videos;
    }
}
