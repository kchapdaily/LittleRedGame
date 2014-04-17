package gameFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.net.URL;
import java.io.InputStream;

public class View extends Observable implements Observer, ActionListener {

    private JFrame game_frame;
    private JLabel storyLabel;
    private JButton responseButton1, responseButton2, responseButton3;
    private ImageIcon storyImage;
    private JTextArea dialogTextArea, responseTextArea1, responseTextArea2, responseTextArea3;
    private String fontName = "Minecraftia";
    private String fontFileName = "Minecraftia.ttf";

    public void loadFont(String fn, int fontFormat){
        URL url = getClass().getResource("res/fonts/" + fn);
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
    }

    public void createFrame() {


        // Story Text area
        dialogTextArea = new JTextArea("This is the area where the dialog of the person" +
                "little red is talking to comes from");
        dialogTextArea.setFont(new Font(fontName, Font.PLAIN, 14));
        dialogTextArea.setWrapStyleWord(true);
        dialogTextArea.setLineWrap(true);
        dialogTextArea.setEditable(false);
        dialogTextArea.setOpaque(false);


        // BOTTOM Panel, user responses and buttons
        // User Buttons
        responseButton1 = new JButton();
        responseButton1.setOpaque(false);
        responseButton1.setContentAreaFilled(false);
        responseButton1.setBorderPainted(false);
        responseButton1.addActionListener(this);

        responseButton2 = new JButton();
        responseButton2.setOpaque(false);
        responseButton2.setContentAreaFilled(false);
        responseButton2.setBorderPainted(false);
        responseButton2.addActionListener(this);

        responseButton3 = new JButton();
        responseButton3.setOpaque(false);
        responseButton3.setContentAreaFilled(false);
        responseButton3.setBorderPainted(false);
        responseButton3.addActionListener(this);

        // User response area
        responseTextArea1 = new JTextArea("Response This is " +
                "a test to see if all of the letters will fit in the text " +
                "box on not go off of the screen dssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        responseTextArea1.setFont(new Font(fontName, Font.PLAIN, 14));
        responseTextArea1.setOpaque(false);
        responseTextArea1.setLineWrap(true);
        responseTextArea1.setWrapStyleWord(true);
        responseTextArea1.setEditable(false);

        responseTextArea2 = new JTextArea("Response 2");
        responseTextArea2.setFont(new Font(fontName, Font.PLAIN, 14));
        responseTextArea2.setOpaque(false);
        responseTextArea2.setLineWrap(true);
        responseTextArea2.setWrapStyleWord(true);
        responseTextArea2.setEditable(false);

        responseTextArea3 = new JTextArea("Response 3");
        responseTextArea3.setFont(new Font(fontName, Font.PLAIN, 14));
        responseTextArea3.setOpaque(false);
        responseTextArea3.setLineWrap(true);
        responseTextArea3.setWrapStyleWord(true);
        responseTextArea3.setEditable(false);

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
        dialogTextArea.setSize(new Dimension(264, 300));
        dialogTextArea.setLocation(472, 38);
        responseTextArea1.setSize(new Dimension(674, 42));
        responseTextArea1.setLocation(62, 359);
        responseButton1.setSize(new Dimension(674, 42));
        responseTextArea1.add(responseButton1);

        responseTextArea2.setSize(new Dimension(674, 42));
        responseTextArea2.setLocation(62, 410);
        responseButton2.setSize(new Dimension(674, 42));
        responseTextArea2.add(responseButton2);

        responseTextArea3.setSize(new Dimension(674, 42));
        responseTextArea3.setLocation(62, 462);
        responseButton3.setSize(new Dimension(674, 42));
        responseTextArea3.add(responseButton3);


        // Place components on Frame
        gameLayeredPane.add(storyLabel, JLayeredPane.PALETTE_LAYER);
        gameLayeredPane.add(dialogTextArea, JLayeredPane.PALETTE_LAYER);
        gameLayeredPane.add(responseTextArea1, JLayeredPane.PALETTE_LAYER);
        gameLayeredPane.add(responseTextArea2, JLayeredPane.PALETTE_LAYER);
        gameLayeredPane.add(responseTextArea3, JLayeredPane.PALETTE_LAYER);
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
        String[] temp = s.getChoiceText();

        System.out.println("View: Received Scene " + s.getID() + " from Model.");

        // Update image
        storyLabel.setIcon(null);
        storyLabel.setIcon(s.getSceneImage());
        // Update text
        dialogTextArea.setText(s.getStoryText());

        if (!temp[0].isEmpty()){
            responseButton1.setEnabled(true);
            responseTextArea1.setText(temp[0]);
        }
        else{
            responseButton1.setEnabled(false);
            responseTextArea1.setText("");
        }
        if (!temp[1].isEmpty()){
            responseButton2.setEnabled(true);
            responseTextArea2.setText(temp[1]);
        }
        else{
            responseButton2.setEnabled(false);
            responseTextArea2.setText("");
        }
        if (!temp[2].isEmpty()){
            responseButton3.setEnabled(true);
            responseTextArea3.setText(temp[2]);
        }
        else{
            responseButton3.setEnabled(false);
            responseTextArea3.setText("");
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int response;
        if (e.getSource() == responseButton1) {
            response = 0;
        } else if (e.getSource() == responseButton2) {
            response = 1;
        } else if (e.getSource() == responseButton3) {
            response = 2;
        } else {
            response = -1;
        }

        // Notify Controller
        setChanged();
        notifyObservers(response);
    }
}