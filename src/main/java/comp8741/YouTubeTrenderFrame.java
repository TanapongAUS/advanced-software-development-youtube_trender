package comp8741;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class YouTubeTrenderFrame extends JFrame {
    Dimension frmWDim = new Dimension(10, 0);
    Dimension frmHDim = new Dimension(0, 10);
    Dimension smlVerticalGap = new Dimension(0, 5);
    Dimension smlHorizontalGap = new Dimension(5, 0);

    List<YouTubeVideo> list;
    Set<YouTubeVideo> indexedVideos;
    private JTextField jTextFieldDataFile;
    private JTextField jTextFieldChannel;
    private JTextField jTextFieldDate;
    private JTextField jTextFieldTitle;
    private JTextArea jTextAreaVideoDescription;
    private JTextField jTextFieldViewCount;
    private JTextField jTextFieldLikeCount;
    private JTextField jTextFieldDislikeCount;
    private JTextField jTextFieldFavoriteCount;
    private JTextField jTextFieldCommentCount;
    private JList<YouTubeVideo> jListVideo;
    private DefaultListModel<YouTubeVideo> videoModel;
    private ButtonGroup sortGroup; //a group of sorting options
    private SortingListener sortingListener; //sorting listener class for individual sort criteria
    private JTextField searchField; //a search box
    //declare variables for indexer
    private DefaultListModel<String> trendingModel;
    private JList<String> jListTrendingWords;
    private YouTubeVideoIndexer indexer;
    private JButton jButtonIndex;
    //Number formatter to be used to format the number string of statistics.
    private DecimalFormat df = new DecimalFormat("###,###,###");

    /**
     * Creates new form YouTubeTrenderFrame
     */
    public YouTubeTrenderFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //close the program when the window of this frame is closed.
        setSize(new Dimension(1000, 300)); //set the size of the frame
        setResizable(true); //make the frame resizable
        setLocationRelativeTo(null); //set the frame's initial position to the center of the screen
        initComponents();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) { // feel free to change this as you see fit.
                    // Available: Nimbus, CDE, Metal, Windows...
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                //If the working OS is macOS, then use the LookAndFeel model compatible to mac.
                if (info.getName().contains("Mac")) { // set look and feel UI for Mac Operating System
                    UIManager.setLookAndFeel("com.apple.laf.AquaLookAndFeel");
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            Logger.getLogger(YouTubeTrenderFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        EventQueue.invokeLater(() -> new YouTubeTrenderFrame().setVisible(true));
    }

    /**
     * This method is called to initially create all the UI elements.
     */
    private void initComponents() {
        JPanel jPanelContainer = new JPanel();
        jPanelContainer.setLayout(new BoxLayout(jPanelContainer, BoxLayout.Y_AXIS));
        jPanelContainer.setBorder(new MatteBorder(10, 10, 10, 10, new Color(99, 102, 241)));
        jPanelContainer.setBackground(new Color(99, 102, 241));
        jPanelContainer.add(Box.createRigidArea(frmHDim));
        jPanelContainer.add(createTopPanel());
        jPanelContainer.add(Box.createRigidArea(frmHDim));
        //add the panel of sort criteria and search box
        jPanelContainer.add(createGroupedSearchAndSortPanel());

        //create a panel to hold the Trending Panel and Video Panel side by side
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        centerPanel.add(createTrendingPanel());
        centerPanel.add(Box.createRigidArea(new Dimension(10, 0)));  // Spacer
        centerPanel.add(createVideoPanel());
        jPanelContainer.add(centerPanel);

        jPanelContainer.add(Box.createRigidArea(frmHDim));
        jPanelContainer.add(Box.createRigidArea(frmHDim));
        jPanelContainer.add(createVideoDetailsPanel());

        //create a scrollable pane for jPanelContainer
        JScrollPane jScrollPaneVideo = new JScrollPane(jPanelContainer);
        jScrollPaneVideo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPaneVideo.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jScrollPaneVideo);

        pack();
    }

    /**
     * This method is used to create the top panel that contains file input and load button
     */
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setMaximumSize(new Dimension(800, 25));

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBackground(new Color(99, 102, 241));

        JLabel jLabel = new JLabel("File Name: "); //add a label for data file text field.
        jLabel.setForeground(Color.WHITE); //change the label colour to white.
        jTextFieldDataFile = new JTextField();
        jTextFieldDataFile.setPreferredSize(new Dimension(200, 25));
        jTextFieldDataFile.setText("data/youtubedata_15_50.json");
        JButton jButtonParse = new JButton("Choose File");
        //change the styles of the 'Choose File' button.
        jButtonParse.setFocusPainted(false);
        jButtonParse.setContentAreaFilled(false);
        jButtonParse.setBorderPainted(false);
        jButtonParse.setOpaque(true);
        jButtonParse.setBackground(new Color(52, 211, 153));
        jButtonParse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtonParse.addActionListener(this::jButtonParseActionPerformed);

        topPanel.add(Box.createRigidArea(frmWDim));
        topPanel.add(jLabel);
        topPanel.add(jTextFieldDataFile);
        topPanel.add(Box.createRigidArea(frmWDim));
        topPanel.add(jButtonParse);
        topPanel.add(Box.createRigidArea(frmWDim));

        return topPanel;
    }

    /**
     * This method is used to create the video panel that will be used to display the list
     * of videos from the loaded file.
     *
     * @return the component of Video Panel
     */
    private JPanel createVideoPanel() {
        JPanel videoPanel = new JPanel();
        videoPanel.setPreferredSize(new Dimension(800, 240));

        videoPanel.setBorder(BorderFactory.createTitledBorder("Videos"));
        JScrollPane jScrollPaneListVideo = new JScrollPane();
        jScrollPaneListVideo.setPreferredSize(new Dimension(775, 200));

        videoModel = new DefaultListModel<>();
        jListVideo = new JList<YouTubeVideo>(videoModel);
        //Set a customised label depicting individual video in the list by its video title.
        jListVideo.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof YouTubeVideo) {
                    setText(((YouTubeVideo) value).getTitle());
                }
                return this;
            }
        });

        //set the video list can be selected one by one at each time.
        jListVideo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //set an event listener for selection action on individual element in the video list.
        jListVideo.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectVideoActionPerformed();
            }
        });
        jScrollPaneListVideo.setViewportView(jListVideo);
        videoPanel.add(jScrollPaneListVideo);
        return videoPanel;
    }

    /**
     * This method is used to create Video Details Panel which contains all essential
     * labels and text fields to be used for displaying the details of selected item from the Video Panel.
     *
     * @return
     */
    private JPanel createVideoDetailsPanel() {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setPreferredSize(new Dimension(800, 420));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Video Details"));
        JLabel jLabelChannel = new JLabel("Channel:");
        jTextFieldChannel = new JTextField();
        jTextFieldChannel.setEditable(false);
        JLabel jLabelDate = new JLabel("Date Posted:");
        jTextFieldDate = new JTextField();
        jTextFieldDate.setEditable(false);
        JLabel jLabelTitle = new JLabel("Title:");
        jTextFieldTitle = new JTextField();
        jTextFieldTitle.setEditable(false);
        JLabel jLabelViewCount = new JLabel("View Count:");
        jTextFieldViewCount = new JTextField();
        jTextFieldViewCount.setEditable(false);
        JLabel jLabelLikeCount = new JLabel("Like Count:");
        jTextFieldLikeCount = new JTextField();
        jTextFieldLikeCount.setEditable(false);
        JLabel jLabelDislikeCount = new JLabel("Dislike Count:");
        jTextFieldDislikeCount = new JTextField();
        jTextFieldDislikeCount.setEditable(false);
        JLabel jLabelFavoriteCount = new JLabel("Favorite Count:");
        jTextFieldFavoriteCount = new JTextField();
        jTextFieldFavoriteCount.setEditable(false);
        JLabel jLabelCommentCount = new JLabel("Comment Count:");
        jTextFieldCommentCount = new JTextField();
        jTextFieldCommentCount.setEditable(false);
        JLabel jLabelDescription = new JLabel("Description:");
        JScrollPane jScrollPaneVideoDescription = new JScrollPane();
        jTextAreaVideoDescription = new JTextArea();
        jTextAreaVideoDescription.setEditable(false);
        jTextAreaVideoDescription.setColumns(20);
        jTextAreaVideoDescription.setLineWrap(true);
        jTextAreaVideoDescription.setRows(5);
        jTextAreaVideoDescription.setWrapStyleWord(true);
        jScrollPaneVideoDescription.setViewportView(jTextAreaVideoDescription);
        JTextField jTextField = new JTextField(); // dummy to fill the gap
        jTextField.setBorder(new EmptyBorder(0, 0, 0, 0));
        jTextField.setEditable(false);
        jTextField.setVisible(false);

        detailsPanel.add(createHorizontalBox(jLabelChannel, jTextFieldChannel));
        detailsPanel.add(createHorizontalBox(jLabelDate, jTextFieldDate));
        detailsPanel.add(createHorizontalBox(jLabelTitle, jTextFieldTitle));
        detailsPanel.add(createHorizontalBox(jLabelViewCount, jTextFieldViewCount));
        detailsPanel.add(createHorizontalBox(jLabelLikeCount, jTextFieldLikeCount));
        detailsPanel.add(createHorizontalBox(jLabelDislikeCount, jTextFieldDislikeCount));
        detailsPanel.add(createHorizontalBox(jLabelFavoriteCount, jTextFieldFavoriteCount));
        detailsPanel.add(createHorizontalBox(jLabelCommentCount, jTextFieldCommentCount));
        detailsPanel.add(createHorizontalBox(jLabelDescription, jTextField));

        Box descriptionTextBox = Box.createHorizontalBox();
        descriptionTextBox.setPreferredSize(new Dimension(775, 109));
        descriptionTextBox.add(jScrollPaneVideoDescription);

        detailsPanel.add(Box.createHorizontalGlue());
        detailsPanel.add(descriptionTextBox);

        return detailsPanel;
    }


    /**
     * Convenience method to create a Horizontal Box
     *
     * @param jLabel     the label on the left of the box
     * @param jTextField the text field on the right of the box
     * @return the horizontal box
     */
    private Box createHorizontalBox(JLabel jLabel, JTextField jTextField) {
        Box b = Box.createHorizontalBox();
        b.setPreferredSize(new Dimension(775, 25));
        jLabel.setPreferredSize(new Dimension(120, 25));
        jLabel.setHorizontalAlignment(SwingConstants.LEFT);
        jTextField.setPreferredSize(new Dimension(655, 40));
        jTextField.setHorizontalAlignment(SwingConstants.LEFT);
        b.add(jLabel);
        b.add(Box.createRigidArea(smlHorizontalGap));
        b.add(jTextField);

        return b;
    }

    /**
     * Method to handle the event of pushing the "Load" button
     *
     * @param evt the Action Event associated with this button
     */
    private void jButtonParseActionPerformed(ActionEvent evt) {
        //Clear all the elements of videoModel list first before adding the recently loaded elements.
        videoModel.clear();
        //Reset the trending topics when loading new data file.
        trendingModel.clear();
        indexer = null;

        String dataFile = "";
        //create a file chooser dialog
        JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int rValue = jFileChooser.showOpenDialog(this);
        if (rValue == JFileChooser.APPROVE_OPTION) { //set action for selected file
            dataFile = jFileChooser.getSelectedFile().getAbsolutePath();
            jTextFieldDataFile.setText(dataFile);
        } else { // no selection, then use default json file
            dataFile = jTextFieldDataFile.getText();
        }

        YouTubeDataParser parser = new YouTubeDataParser();
        list = null;
        try {
            list = parser.parse(dataFile);
        } catch (YouTubeDataParserException ex) {
            /* TODO: maybe notify the user something has happened! */
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        if (list != null) {
            // YouTube data has been parsed successfully!
            /* TODO: connect the list to the GUI */
            //Set a customised label depicting individual video in the list by its video title as a default view.
            jListVideo.setCellRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof YouTubeVideo) {
                        setText(((YouTubeVideo) value).getTitle());
                    }
                    return this;
                }
            });
            for (YouTubeVideo video : list) {
                videoModel.addElement(video);
            }
            /* Highlight the first element in the jListVideo list
                and fill its values to the video details panel. */
            jListVideo.setSelectedIndex(0);
            selectVideoActionPerformed();
        }
    }

    /**
     * This method is used to get the selected video and
     * fill in the details of the selected item into each text fields in the video details panel.
     */
    private void selectVideoActionPerformed() {
        YouTubeVideo selectedVideo = jListVideo.getSelectedValue();
        if (selectedVideo != null) {
            jTextFieldChannel.setText(selectedVideo.getChannel());
            jTextFieldDate.setText(selectedVideo.getDate());
            jTextFieldTitle.setText(selectedVideo.getTitle());
            jTextFieldViewCount.setText(df.format(selectedVideo.getViewCount()));
            jTextFieldLikeCount.setText(df.format(selectedVideo.getLikeCount()));
            jTextFieldDislikeCount.setText(df.format(selectedVideo.getDislikeCount()));
            jTextFieldFavoriteCount.setText(df.format(selectedVideo.getFavoriteCount()));
            jTextFieldCommentCount.setText(df.format(selectedVideo.getCommentCount()));
            jTextAreaVideoDescription.setText(selectedVideo.getDescription());
        }
    }

    /**
     * This method is used to create the sort criteria panel.
     *
     * @return The component of sort criteria panel.
     */
    private JPanel createSortPanel() {
        //Create a panel for sort criteria
        JPanel sortPanel = new JPanel() {
            @Override
            public void setForeground(Color fg) {
                super.setForeground(fg);
                for (Component c : getComponents()) {
                    c.setForeground(fg);
                }
            }
        };
        sortPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Sort Criteria", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        sortPanel.setLayout(new BoxLayout(sortPanel, BoxLayout.X_AXIS));
        sortPanel.setBackground(new Color(99, 102, 241));
        sortPanel.setOpaque(true);

        // Create Radio button for each sorting option.
        sortGroup = new ButtonGroup();
        JRadioButton rbChannel = new JRadioButton("Channel");
        rbChannel.setForeground(Color.WHITE);
        rbChannel.setBackground(new Color(99, 102, 241));
        JRadioButton rbDate = new JRadioButton("Date");
        rbDate.setForeground(Color.WHITE);
        rbDate.setBackground(new Color(99, 102, 241));
        JRadioButton rbViews = new JRadioButton("Views");
        rbViews.setForeground(Color.WHITE);
        rbViews.setBackground(new Color(99, 102, 241));
        JRadioButton rbLikes = new JRadioButton("Likes");
        rbLikes.setForeground(Color.WHITE);
        rbLikes.setBackground(new Color(99, 102, 241));
        JRadioButton rbDislikes = new JRadioButton("Dislikes");
        rbDislikes.setForeground(Color.WHITE);
        rbDislikes.setBackground(new Color(99, 102, 241));
        JRadioButton rbFavorites = new JRadioButton("Favorites");
        rbFavorites.setForeground(Color.WHITE);
        rbFavorites.setBackground(new Color(99, 102, 241));
        JRadioButton rbComments = new JRadioButton("Comments");
        rbComments.setForeground(Color.WHITE);
        rbComments.setBackground(new Color(99, 102, 241));
        JRadioButton rbDescription = new JRadioButton("Description");
        rbDescription.setForeground(Color.WHITE);
        rbDescription.setBackground(new Color(99, 102, 241));

        //Add each radio button as a group "sortGroup".
        sortGroup.add(rbChannel);
        sortGroup.add(rbDate);
        sortGroup.add(rbViews);
        sortGroup.add(rbLikes);
        sortGroup.add(rbDislikes);
        sortGroup.add(rbFavorites);
        sortGroup.add(rbComments);
        sortGroup.add(rbDescription);

        // Add sorting listener for each radio button with its sorting method.
        sortingListener = new SortingListener();
        rbChannel.addActionListener(sortingListener);
        rbDate.addActionListener(sortingListener);
        rbViews.addActionListener(sortingListener);
        rbLikes.addActionListener(sortingListener);
        rbDislikes.addActionListener(sortingListener);
        rbFavorites.addActionListener(sortingListener);
        rbComments.addActionListener(sortingListener);
        rbDescription.addActionListener(sortingListener);

        // Add radio buttons to the panel
        sortPanel.add(rbChannel);
        sortPanel.add(rbDate);
        sortPanel.add(rbViews);
        sortPanel.add(rbLikes);
        sortPanel.add(rbDislikes);
        sortPanel.add(rbFavorites);
        sortPanel.add(rbComments);
        sortPanel.add(rbDescription);

        return sortPanel;
    }

    /**
     * This method is called to create the trending panel.
     *
     * @return the trending panel.
     */
    private JPanel createTrendingPanel() {
        JPanel trendingPanel = new JPanel();
        trendingPanel.setBorder(BorderFactory.createTitledBorder("Trending"));
        trendingPanel.setLayout(new BorderLayout());
        trendingPanel.setPreferredSize(new Dimension(200, 300));

        // Create the Index button
        jButtonIndex = new JButton("Click to index");
        jButtonIndex.setBackground(new Color(129, 140, 248));
        jButtonIndex.setFocusPainted(false);
        jButtonIndex.setContentAreaFilled(false);
        jButtonIndex.setBorderPainted(false);
        jButtonIndex.setOpaque(true); //setBackground(new Color(99, 102, 241));
        jButtonIndex.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //Add the listener for indexing the videos when clicking the 'Click to index' button.
        jButtonIndex.addActionListener(e -> indexVideos());

        // Create the list model and JList for trending words
        trendingModel = new DefaultListModel<>();
        jListTrendingWords = new JList<>(trendingModel);
        jListTrendingWords.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListTrendingWords.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedValue = jListTrendingWords.getSelectedValue();
                if (selectedValue != null) {
                    String word = selectedValue.split(" ")[0];  // Extract word from "word (count)"
                    displayIndexedYouTubeVideoList(word);  // Display associated videos for the selected word
                    //if a sort criteria is selected, then sort the indexed videos based on the selected sort option.
                    if (sortGroup.getSelection() != null) {
                        String selectedSortingOptionText = this.getSelectedCriteriaOptionText(sortGroup);
                        if (selectedSortingOptionText != null) {
                            new SortingListener().sortVideos(selectedSortingOptionText);
                        }
                    }
                }
            }
        });

        JScrollPane jScrollPaneTrending = new JScrollPane(jListTrendingWords);
        trendingPanel.add(jButtonIndex, BorderLayout.NORTH);
        trendingPanel.add(jScrollPaneTrending, BorderLayout.CENTER);

        return trendingPanel;
    }

    /**
     * This method is called to index the loaded YouTube video list.
     */
    private void indexVideos() {
        if (list == null || list.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please load your videos first.");
            return;
        }

        if (!jButtonIndex.getText().equals("Click to index")) {
            //change to index button text back to normal index state.
            jButtonIndex.setText("Click to index");
            //Reset the trending topics when loading new data file.
            trendingModel.clear();
            indexer = null;

            //set the default view of individual element of the video list by its title only.
            jListVideo.setCellRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof YouTubeVideo) {
                        setText(((YouTubeVideo) value).getTitle());
                    }
                    return this;
                }
            });
            //in case it is clicked to reset all, show all the elements of initially loaded videos.
            searchVideoActionPerformed();
            return;
        }
        //Change the index button text to reflect the reset indexing action.
        jButtonIndex.setText("Click to reset all");

        //Start to index the videos using YouTubeVideoIndexer
        indexer = new YouTubeVideoIndexer(list);
        updateTrendingList();  // Update the trending panel with words
    }

    /**
     * This method is called to update the YouTubeVideo list into the video list panel.
     */
    private void updateTrendingList() {
        //clear the elements of trending model first
        trendingModel.clear();
        List<YouTubeWordItem> sortedWords = indexer.getSortedWords();
        for (YouTubeWordItem wordItem : sortedWords) {
            trendingModel.addElement(wordItem.toString());
        }
    }

    /**
     * This method is called to display all the indexed YouTube videos into the video list panel.
     *
     * @param word the specific word to look up in the index.
     */
    private void displayIndexedYouTubeVideoList(String word) {
        //Change to display individual element by its channel name and title
        jListVideo.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof YouTubeVideo) {
                    setText(((YouTubeVideo) value).getChannel() + ": " + ((YouTubeVideo) value).getTitle());
                }
                return this;
            }
        });

        if (indexer != null) {
            indexedVideos = indexer.getWordVideos(word);

            // Clear the video list model
            videoModel.clear();

            // Add the filtered videos to the video list model
            for (YouTubeVideo video : indexedVideos) {
                videoModel.addElement(video);
            }

            // Highlight the first element in the video list (optional)
            if (!videoModel.isEmpty()) {
                jListVideo.setSelectedIndex(0);
                selectVideoActionPerformed();  // Update the video details panel with the first video
            }
        }
    }

    /**
     * This method is used to group the search panel and sorting panel in the same horizontal position.
     *
     * @return a grouped panel of search panel and sort panel.
     */
    private JPanel createGroupedSearchAndSortPanel() {
        JPanel groupedPanel = new JPanel(new BorderLayout());
        JPanel sortingPanel = createSortPanel();
        groupedPanel.add(sortingPanel, BorderLayout.WEST);
        JPanel searchBoxPanel = createSearchPanel();
        groupedPanel.add(searchBoxPanel, BorderLayout.CENTER);
        return groupedPanel;
    }

    /**
     * This method is used to create the Search Panel to add all the search UIs.
     *
     * @return a panel of searching UI elements.
     */
    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel() {
            @Override
            public void setForeground(Color fg) {
                super.setForeground(fg);
                for (Component c : getComponents()) {
                    c.setForeground(fg);
                }
            }
        };
        searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Search", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setBackground(new Color(99, 102, 241));

        searchField = new JTextField(10);
        searchField.setForeground(Color.GRAY);
        searchField.setText("Type keyword here");
        //This adds a listener for focus action of the search field when it changes.
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Type keyword here")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        //This listener triggers a search action whenever the search field's text is modified.
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            //Call when the text is inserted into the document
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchVideoActionPerformed();
            }

            //Call when the text is removed from the document.
            @Override
            public void removeUpdate(DocumentEvent e) {
                searchVideoActionPerformed();
            }

            //Call when the text is changed.
            @Override
            public void changedUpdate(DocumentEvent e) {
                searchVideoActionPerformed();
            }
        });
        searchPanel.add(searchField);
        return searchPanel;
    }

    /**
     * This method is used to handle the action of search box while the search keyword is changed.
     */
    private void searchVideoActionPerformed() {
        if (list == null || list.isEmpty()) { //Prevent error when initially loading the UI and type in the search box.
            return;
        }

        String searchWord = searchField.getText().toLowerCase().trim();
        if (searchWord.isEmpty() || searchWord.equalsIgnoreCase("type keyword here")) { //if the user removes all the keyword, then return the initially loaded YouTubeVideo data.
            updateVideoList(list);
            return;
        }
        //Declare a list to store the filtered YouTubeVideos.
        List<YouTubeVideo> filteredVideoList = new ArrayList<>();

        //Declare a YouTubeVideo list to store which videos should be searched for between indexed videos or originally loaded videos
        List<YouTubeVideo> videosToSearch = (indexer == null || indexedVideos == null) ? list : new ArrayList<>(indexedVideos);
        for (YouTubeVideo v : videosToSearch) {
            //Check if the title or description of each YouTube video in original list contains the search term.
            if (v.getTitle().toLowerCase().contains(searchWord) || v.getChannel().toLowerCase().contains(searchWord)) {
                filteredVideoList.add(v);
            }
        }

        //update the filtered YouTube video list
        updateVideoList(filteredVideoList);
    }

    /**
     * This method is used to update the list of YouTube videos in the video list panel.
     *
     * @param videos a list of YouTube videos to be inserted to the video list panel.
     */
    private void updateVideoList(List<YouTubeVideo> videos) {
        //clear the elements of video model before adding new ones.
        videoModel.clear();
        for (YouTubeVideo video : videos) {
            videoModel.addElement(video);
        }
        if (!videoModel.isEmpty()) { //select the first element if the video model is not empty.
            jListVideo.setSelectedIndex(0);
            selectVideoActionPerformed();
        } else {
            //clear the video details if no videos match the search words.
            clearVideoDetails();
        }
    }

    /**
     * This method is used to clear all the values of video details in the video details panel.
     */
    private void clearVideoDetails() {
        jTextFieldChannel.setText("");
        jTextFieldDate.setText("");
        jTextFieldTitle.setText("");
        jTextFieldViewCount.setText("");
        jTextFieldLikeCount.setText("");
        jTextFieldDislikeCount.setText("");
        jTextFieldFavoriteCount.setText("");
        jTextFieldCommentCount.setText("");
        jTextAreaVideoDescription.setText("");
    }

    /**
     * @param buttonGroup the buttongroup for all radio buttons of the sort criteria.
     * @return a string of selected radio button or null if no radio button is selected.
     */
    private String getSelectedCriteriaOptionText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    /**
     * This method is used to sort the elements in the video list by selected criteria.
     */
    private class SortingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            this.sortVideos(e.getActionCommand());
        }

        public void sortVideos(String criteria) {
            if (list == null || list.isEmpty()) return;
            if (criteria == null || criteria.isEmpty()) return;

            YouTubeVideoComparator comparator;
            switch (criteria) {
                case "Channel":
                    comparator = new YouTubeVideoChannelComparator();
                    break;
                case "Date":
                    comparator = new YouTubeVideoDateComparator();
                    break;
                case "Views":
                    comparator = new YouTubeVideoViewComparator();
                    break;
                case "Likes":
                    comparator = new YouTubeVideoLikeComparator();
                    break;
                case "Dislikes":
                    comparator = new YouTubeVideoDisLikeComparator();
                    break;
                case "Favorites":
                    comparator = new YouTubeVideoFavoriteComparator();
                    break;
                case "Comments":
                    comparator = new YouTubeVideoCommentComparator();
                    break;
                case "Description":
                    comparator = new YouTubeVideoDescriptionComparator();
                    break;
                default:
                    return;
            }
            

            if (trendingModel.isEmpty()) { // check whether the video list is indexed or not.
                //sort the videos in the list based on the selected comparator before adding the video panel
                list.sort(comparator);

                // Update the list model with the sorted videos
                videoModel.clear();
                for (YouTubeVideo video : list) {
                    videoModel.addElement(video);
                }

            } else {
                //if the video list is indexed, then sorting should sort the indexed videos instead of all initially loaded data.
                List<YouTubeVideo> indexedVideoList = new ArrayList<>(indexedVideos);
                indexedVideoList.sort(comparator);
                // Update the list model with the sorted videos
                videoModel.clear();
                for (YouTubeVideo video : indexedVideoList) {
                    videoModel.addElement(video);
                }
            }
            //set the first element selected
            jListVideo.setSelectedIndex(0);
            selectVideoActionPerformed();
        }
    }

}
