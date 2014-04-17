package gameFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.net.URL;
import java.io.InputStream;

public class View extends Observable implements Observer {

    private JFrame game_frame;
    private JLabel storyLabel;
    private ImageIcon storyImage;
    private JTextArea dialogTextArea;
    private SelectableTextPane responseTextPane1, responseTextPane2, responseTextPane3, nextTextPane, restartTextPane;
    private String fontName = "Minecraftia";
    private String fontFileName = "Minecraftia.ttf";
    private Font smallFont;
    private Font regularFont;

    public void loadFont(String ffn, int fontFormat){
        URL url = getClass().getResource("res/fonts/" + ffn);
        InputStream fontStream = null;

        try {
            fontStream = url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Font font = null;

        try {
            font = Font.createFont(fontFormat, fontStream);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);


        //for testing
        /*String fontFamilies[] = ge.getAvailableFontFamilyNames();
        for (int i = 0; i < (fontFamilies.length); i++){
            System.out.print(fontFamilies[i] + '\n');
        }*/
    }

    // Default Constructor
    public View() {
        createFrame();
        loadFont(fontFileName, Font.TRUETYPE_FONT);
        this.regularFont = new Font(fontName, Font.PLAIN, 13);
        this.smallFont = new Font(fontName, Font.PLAIN, 9);
    }

    public void createFrame() {


        // Story Text area
        dialogTextArea = new JTextArea("This is the area where the dialog of the person" +
                "little red is talking to comes from");
        dialogTextArea.setFont(regularFont);
        dialogTextArea.setWrapStyleWord(true);
        dialogTextArea.setLineWrap(true);
        dialogTextArea.setEditable(false);
        dialogTextArea.setOpaque(false);
        dialogTextArea.setHighlighter(null);

        // User response area
        responseTextPane1 = new SelectableTextPane("Response 1");
        responseTextPane1.setFont(new Font(fontName, Font.PLAIN, 13));
        responseTextPane1.setEditable(false);
        responseTextPane1.addMouseListener(paneML);

        responseTextPane2 = new SelectableTextPane("Response 2");
        responseTextPane2.setFont(new Font(fontName, Font.PLAIN, 14));
        responseTextPane2.setEditable(false);
        responseTextPane2.addMouseListener(paneML);

        responseTextPane3 = new SelectableTextPane("Response 3");
        responseTextPane3.setFont(new Font(fontName, Font.PLAIN, 12));
        responseTextPane3.setEditable(false);
        responseTextPane3.addMouseListener(paneML);

        nextTextPane = new SelectableTextPane("    Next");
        nextTextPane.setFont(new Font(fontName, Font.PLAIN, 19));
        nextTextPane.setEditable(false);
        nextTextPane.addMouseListener(paneML);
        nextTextPane.selectIcon("res/img/imgButton/StaticButton.jpg");
        nextTextPane.setSelectable(false);

        restartTextPane = new SelectableTextPane("  Restart");
        restartTextPane.setFont(new Font(fontName, Font.PLAIN, 19));
        restartTextPane.setEditable(false);
        restartTextPane.addMouseListener(paneML);
        restartTextPane.selectIcon("res/img/imgButton/StaticButton.jpg");

        //Image area
        storyImage = createImageIcon("res/img/imgLabel/000.jpg");
        storyLabel = new JLabel("", storyImage, JLabel.CENTER);


        // Create background
        ImageIcon backgroundImage = createImageIcon("res/img/other/Background.jpg");
        JPanel backgroundPanel = new JPanel();
        JLabel backgroundLabel = new JLabel("", backgroundImage, JLabel.CENTER);
        backgroundPanel.add(backgroundLabel, BorderLayout.CENTER);


        // Create frame and resize and set location of components
        game_frame = new JFrame("The Trails of Little Red");
        JLayeredPane gameLayeredPane = new JLayeredPane();
        gameLayeredPane.setPreferredSize(new Dimension(800, 600));
        game_frame.add(gameLayeredPane, BorderLayout.CENTER);
        backgroundLabel.setSize(gameLayeredPane.getPreferredSize());
        backgroundLabel.setLocation(0, 0);
        gameLayeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        storyLabel.setSize(new Dimension(400, 300));
        storyLabel.setLocation(63, 38);

        dialogTextArea.setSize(new Dimension(260, 300));
        dialogTextArea.setLocation(474, 38);

        responseTextPane1.setSize(new Dimension(680, 48));
        responseTextPane1.setLocation(59, 356);

        responseTextPane2.setSize(new Dimension(680, 48));
        responseTextPane2.setLocation(59, 407);

        responseTextPane3.setSize(new Dimension(680, 48));
        responseTextPane3.setLocation(59, 459);

        nextTextPane.setSize(new Dimension(147, 37));
        nextTextPane.setLocation(492, 525);

        restartTextPane.setSize(new Dimension(147, 37));
        restartTextPane.setLocation(159, 525);


        // Place components on Frame
        gameLayeredPane.add(storyLabel, JLayeredPane.PALETTE_LAYER);
        gameLayeredPane.add(dialogTextArea, JLayeredPane.PALETTE_LAYER);
        gameLayeredPane.add(responseTextPane1, JLayeredPane.PALETTE_LAYER);
        gameLayeredPane.add(responseTextPane2, JLayeredPane.PALETTE_LAYER);
        gameLayeredPane.add(responseTextPane3, JLayeredPane.PALETTE_LAYER);
        gameLayeredPane.add(nextTextPane, JLayeredPane.PALETTE_LAYER);
        gameLayeredPane.add(restartTextPane, JLayeredPane.PALETTE_LAYER);
        game_frame.setResizable(false);
        game_frame.setVisible(true);
        game_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_frame.pack();
    }

    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        // Scene whose data members need to be loaded
        Scene s = (Scene) arg;
        String[] sceneChoice = s.getChoiceText();

        System.out.println("View: Received Scene " + s.getID() + " from Model.");

        // Update image
        storyLabel.setIcon(null);
        storyLabel.setIcon(s.getSceneImage());
        // Update text
        if (s.getStoryText().length() > 346){
            dialogTextArea.setFont(smallFont);
        }
        else{
            dialogTextArea.setFont(regularFont);
        }

        dialogTextArea.setText(s.getStoryText());

        if (!sceneChoice[0].isEmpty()){
            responseTextPane1.setText(sceneChoice[0]);
            responseTextPane1.setSelectable(true);
            responseTextPane1.selectIcon("res/img/other/StaticWindow.jpg");
        } else {
            responseTextPane1.setText("");
            responseTextPane1.setSelectable(false);
            responseTextPane1.selectIcon("res/img/other/PressedWindow.jpg");    // Disabled
        if (!temp[0].isEmpty()){
            responseButton1.setEnabled(true);
            if ( temp[0].length() > 139){
                responseTextArea1.setFont(smallFont);
            }
            else{
                responseTextArea1.setFont(regularFont);
            }
            responseTextArea1.setText(temp[0]);
        }

        if (!sceneChoice[1].isEmpty()){
            responseTextPane2.setText(sceneChoice[1]);
            responseTextPane2.setSelectable(true);
            responseTextPane2.selectIcon("res/img/other/StaticWindow.jpg");
        } else {
            responseTextPane2.setText("");
            responseTextPane2.setSelectable(false);
            responseTextPane2.selectIcon("res/img/other/PressedWindow.jpg");    // Disabled
        }

        if (!sceneChoice[2].isEmpty()){
            responseTextPane2.setText(sceneChoice[2]);
            responseTextPane2.setSelectable(true);
            responseTextPane2.selectIcon("res/img/other/StaticWindow.jpg");
        } else {
            responseTextPane2.setText("");
            responseTextPane2.setSelectable(false);
            responseTextPane2.selectIcon("res/img/other/PressedWindow.jpg");    // Disabled
        if (!temp[1].isEmpty()){
            responseButton2.setEnabled(true);
            if ( temp[1].length() > 139){

                responseTextArea2.setFont(smallFont);
            }
            else{
                responseTextArea2.setFont(regularFont);
            }
            responseTextArea2.setText(temp[1]);
        }
    }

    private MouseListener paneML = new MouseListener() {

        private boolean isPressed = false;

        private void repaintAll(){
            responseTextPane1.repaint();
            responseTextPane2.repaint();
            responseTextPane3.repaint();
            nextTextPane.repaint();
            restartTextPane.repaint();
        }
        if (!temp[2].isEmpty()){
            responseButton3.setEnabled(true);
            if ( temp[2].length() > 139){
                responseTextArea3.setFont(smallFont);
            }
            else{
                responseTextArea3.setFont(regularFont);
            }
            responseTextArea3.setText(temp[2]);

        @Override
        public void mouseClicked(MouseEvent e) {
            int response;
            if (e.getSource()==nextTextPane && nextTextPane.isSelectable()){
                nextTextPane.selectIcon("res/img/imgButton/PressedButton.jpg");     // Need disabled button image
                nextTextPane.setSelectable(false);

                if (responseTextPane1.isSelected()) {
                    response = 0;
                } else if (responseTextPane2.isSelected()) {
                    response = 1;
                } else if (responseTextPane3.isSelected()) {
                    response = 2;
                } else {
                    response = -1;
                }
                notifyObservers(response);

            } else if (e.getSource()==restartTextPane && restartTextPane.isSelectable()){
                nextTextPane.setSelectable(false);
                response = 4;
                notifyObservers(response);

            } else {
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            isPressed = true;

            if(e.getSource()==responseTextPane1 && responseTextPane1.isSelectable()) {
                responseTextPane1.selectIcon("res/img/other/PressedWindow.jpg");
                responseTextPane2.selectIcon("res/img/other/StaticWindow.jpg");
                responseTextPane3.selectIcon("res/img/other/StaticWindow.jpg");

                responseTextPane1.selectResponse();
                responseTextPane2.deselectResponse();
                responseTextPane3.deselectResponse();

                nextTextPane.setSelectable(true);
            } else if(e.getSource()==responseTextPane2 && responseTextPane2.isSelectable()) {
                responseTextPane1.selectIcon("res/img/other/StaticWindow.jpg");
                responseTextPane2.selectIcon("res/img/other/PressedWindow.jpg");
                responseTextPane3.selectIcon("res/img/other/StaticWindow.jpg");

                responseTextPane1.deselectResponse();
                responseTextPane2.selectResponse();
                responseTextPane3.deselectResponse();

                nextTextPane.setSelectable(true);
            } else if(e.getSource()==responseTextPane3 && responseTextPane3.isSelectable()) {
                responseTextPane1.selectIcon("res/img/other/StaticWindow.jpg");
                responseTextPane2.selectIcon("res/img/other/StaticWindow.jpg");
                responseTextPane3.selectIcon("res/img/other/PressedWindow.jpg");

                responseTextPane1.deselectResponse();
                responseTextPane2.deselectResponse();
                responseTextPane3.selectResponse();

                nextTextPane.setSelectable(true);
            } else if (e.getSource()==nextTextPane && nextTextPane.isSelectable()){
                nextTextPane.selectIcon("res/img/imgButton/PressedButton.jpg");
            } else if (e.getSource()==restartTextPane && restartTextPane.isSelectable()){
                restartTextPane.selectIcon("res/img/imgButton/PressedButton.jpg");
            } else {
            }
            repaintAll();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            isPressed = false;
            if (e.getSource()==nextTextPane && nextTextPane.isSelectable()){
                nextTextPane.selectIcon("res/img/imgButton/StaticButton.jpg");
            } else if (e.getSource()==restartTextPane && restartTextPane.isSelectable()){
                restartTextPane.selectIcon("res/img/imgButton/StaticButton.jpg");
            } else {
            }
            repaintAll();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getSource()==responseTextPane1 && responseTextPane1.isSelectable() && !responseTextPane1.isSelected()) {
                if (isPressed) {
                    responseTextPane1.selectIcon("res/img/other/PressedWindow.jpg");
                } else {
                    responseTextPane1.selectIcon("res/img/other/SelectedWindow.jpg");
                }
            } else if(e.getSource()==responseTextPane2 && responseTextPane2.isSelectable() && !responseTextPane2.isSelected()) {
                if (isPressed) {
                    responseTextPane2.selectIcon("res/img/other/PressedWindow.jpg");
                } else {
                    responseTextPane2.selectIcon("res/img/other/SelectedWindow.jpg");
                }
            } else if(e.getSource()==responseTextPane3 && responseTextPane3.isSelectable() && !responseTextPane3.isSelected()) {
                if (isPressed) {
                    responseTextPane3.selectIcon("res/img/other/PressedWindow.jpg");
                } else {
                    responseTextPane3.selectIcon("res/img/other/SelectedWindow.jpg");
                }
            } else if(e.getSource()==nextTextPane && nextTextPane.isSelectable()) {
                if (isPressed) {
                    nextTextPane.selectIcon("res/img/other/PressedButton.jpg");
                } else {
                    nextTextPane.selectIcon("res/img/other/SelectedButton.jpg");
                }
            } else if(e.getSource()==restartTextPane && restartTextPane.isSelectable()) {
                if (isPressed) {
                    restartTextPane.selectIcon("res/img/other/PressedButton.jpg");
                } else {
                    restartTextPane.selectIcon("res/img/other/SelectedButton.jpg");
                }
            } else {
            }
            repaintAll();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource()==responseTextPane1 && responseTextPane1.isSelectable() && !responseTextPane1.isSelected()) {
                responseTextPane1.selectIcon("res/img/other/StaticWindow.jpg");
            } else if(e.getSource()==responseTextPane2 && responseTextPane2.isSelectable() && !responseTextPane2.isSelected()) {
                responseTextPane2.selectIcon("res/img/other/StaticWindow.jpg");
            } else if(e.getSource()==responseTextPane3 && responseTextPane3.isSelectable() && !responseTextPane3.isSelected()) {
                responseTextPane3.selectIcon("res/img/other/StaticWindow.jpg");
            } else if(e.getSource()==nextTextPane && nextTextPane.isSelectable()) {
                nextTextPane.selectIcon("res/img/imgButton/StaticButton.jpg");
            } else if(e.getSource()==restartTextPane && restartTextPane.isSelectable()) {
                restartTextPane.selectIcon("res/img/imgButton/StaticButton.jpg");
            } else {
            }
            repaintAll();
        }
    };
}