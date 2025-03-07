package comp8741;

import java.util.*;

public class YouTubeVideoIndexer {
    private Map<String, YouTubeWordItem> wordMap;  // Use to store the different word and their corresponding YouTubeWordItems
    private List<YouTubeWordItem> sortedYouTubeWordItems;  // Use to store the list of sorted YouTubeWordItems

    /**
     * This is the constructor of this class.
     *
     * @param videos a list of YouTube video objects to be indexed.
     */
    public YouTubeVideoIndexer(List<YouTubeVideo> videos) {
        wordMap = new HashMap<>();
        sortedYouTubeWordItems = new ArrayList<>();
        index(videos);
    }

    /**
     * This method is used to index individual words from the given title and description,
     * and sort the resulting word items.
     * @param videos
     */
    public void index(List<YouTubeVideo> videos) {
        for (YouTubeVideo video : videos) {
            /* Combine the title and decription into a single string for easily finding the occurrences
                of individual word in both title and description
             */
            String combinedText = video.getTitle() + " " + video.getDescription();
            String[] words = combinedText.split("\\s+");  // Split individual word by spaces

            //For each word in the title and description, process it
            for (String word : words) {
                //Use the separated 'indexWord' function to process each word
                indexWord(word, video);
            }
        }

        // After indexing, store the sorted the YouTubeWordItems into the declared list.
        sortedYouTubeWordItems = new ArrayList<>(wordMap.values());
        //Sort the list of word items
        Collections.sort(sortedYouTubeWordItems);
    }

    /**
     * This method is used to index a single word from a YouTube video object, updating the word count and associated videos.
     * @param word a string of the specific word, to be indexed.
     * @param video a YouTubeVideo object associated with the specific word.
     */
    private void indexWord(String word, YouTubeVideo video) {
        if (wordMap.containsKey(word)) {
            // If the word already exists, increment its count
            YouTubeWordItem wordItem = wordMap.get(word);
            wordItem.incrementCount();
        } else {
            // If the word is new, add it to the map with a count of 1
            YouTubeWordItem wordItem = new YouTubeWordItem(word);
            wordItem.incrementCount();  // Set initial count to 1
            wordMap.put(word, wordItem);
        }

        // Associate the word with the given video
        wordMap.get(word).add(video);
    }

    /**
     * This method is used to retrieve the count of occurrences for a specific word in the index.
     * @param word a string of a specific word to look up in the index.
     * @return a positive integer of times the word appears in the indexed videos.
     */
    public int getWordCount(String word) {
        YouTubeWordItem wordItem = wordMap.get(word);
        return wordItem.getCount();
    }

    /**
     * This method is used to retrieve the set of YouTube videos associated with a specific word in the index.
     * @param word the word to look up in the index.
     * @return a set of YouTube video objects containing all videos where the word appears.
     */
    public Set<YouTubeVideo> getWordVideos(String word) {
        YouTubeWordItem wordItem = wordMap.get(word);
        return wordItem.getVideos();
    }

    /**
     * This method is used to get the set of YouTube videos associated with a specific word in the index.
     * @return a set of sorted YouTube video objects containing all videos where the word appears.
     */
    public List<YouTubeWordItem> getSortedWords() {
        return sortedYouTubeWordItems;
    }

    /**
     *  This method is used to get YouTubeWordItem associated with a specific word from the index.
     * @param word the word to look up in the index.
     * @return the YouTubeWordItem associated with the given word.
     */
    public YouTubeWordItem getWordItem(String word) {
        return wordMap.get(word);
    }
}
