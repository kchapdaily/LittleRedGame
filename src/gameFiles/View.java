package gameFiles;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

public class View extends Observable implements Observer {

    JLabel backgroundLabel;
    private JLabel storyLabel;
    private JTextArea dialogTextArea;
    private SelectableTextPane responseTextPane1, responseTextPane2, responseTextPane3, nextTextPane, restartTextPane;
    private String fontName = "Minecraftia";
    private Font smallFont, regularFont;
    private int bBoringFeature06status;
    private int boringFeature06Index;
    private MouseListener paneML = new MouseListener() {

        private void repaintAll() {
            responseTextPane1.repaint();
            responseTextPane2.repaint();
            responseTextPane3.repaint();
            nextTextPane.repaint();
            restartTextPane.repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int response;
            if (e.getSource() == nextTextPane && nextTextPane.isSelectable()) {
                nextTextPane.selectIcon("res/img/imgButton/DisabledButton.jpg");
                nextTextPane.setSelectable(false);

                if (responseTextPane1.isSelected()) {
                    response = 0;
                } else if (responseTextPane2.isSelected()) {
                    response = 1;
                } else if (responseTextPane3.isSelected()) {
                    if (bBoringFeature06status == 2) {
                        response = 3;
                    } else {
                        response = 2;
                    }
                } else {
                    response = -1;
                }

                responseTextPane1.deselectResponse();
                responseTextPane2.deselectResponse();
                responseTextPane3.deselectResponse();

                setChanged();
                notifyObservers(response);

            } else if (e.getSource() == restartTextPane && restartTextPane.isSelectable()) {
                nextTextPane.selectIcon("res/img/imgButton/DisabledButton.jpg");
                nextTextPane.setSelectable(false);

                responseTextPane1.deselectResponse();
                responseTextPane2.deselectResponse();
                responseTextPane3.deselectResponse();

                response = 4;
                bBoringFeature06status = 0;
                boringFeature06Index = 0;
                setChanged();
                notifyObservers(response);

            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

            if (e.getSource() == responseTextPane1 && responseTextPane1.isSelectable()) {
                responseTextPane1.selectIcon("res/img/other/PressedWindow.jpg");
                responseTextPane1.selectResponse();
                responseTextPane2.deselectResponse();
                responseTextPane3.deselectResponse();

                if (responseTextPane2.isSelectable()) {
                    responseTextPane2.selectIcon("res/img/other/StaticWindow.jpg");
                }
                if (responseTextPane3.isSelectable()) {
                    responseTextPane3.selectIcon("res/img/other/StaticWindow.jpg");
                }

                nextTextPane.selectIcon("res/img/imgButton/StaticButton.jpg");
                nextTextPane.setSelectable(true);
            } else if (e.getSource() == responseTextPane2 && responseTextPane2.isSelectable()) {
                responseTextPane2.selectIcon("res/img/other/PressedWindow.jpg");
                responseTextPane2.selectResponse();
                responseTextPane1.deselectResponse();
                responseTextPane3.deselectResponse();

                if (responseTextPane1.isSelectable()) {
                    responseTextPane1.selectIcon("res/img/other/StaticWindow.jpg");
                }
                if (responseTextPane3.isSelectable()) {
                    responseTextPane3.selectIcon("res/img/other/StaticWindow.jpg");
                }

                nextTextPane.selectIcon("res/img/imgButton/StaticButton.jpg");
                nextTextPane.setSelectable(true);
            } else if (e.getSource() == responseTextPane3 && responseTextPane3.isSelectable()) {
                responseTextPane3.selectIcon("res/img/other/PressedWindow.jpg");
                responseTextPane3.selectResponse();
                responseTextPane1.deselectResponse();
                responseTextPane2.deselectResponse();

                if (responseTextPane1.isSelectable()) {
                    responseTextPane1.selectIcon("res/img/other/StaticWindow.jpg");
                }
                if (responseTextPane2.isSelectable()) {
                    responseTextPane2.selectIcon("res/img/other/StaticWindow.jpg");
                }

                nextTextPane.selectIcon("res/img/imgButton/StaticButton.jpg");
                nextTextPane.setSelectable(true);
            } else if (e.getSource() == nextTextPane && nextTextPane.isSelectable()) {
                nextTextPane.selectIcon("res/img/imgButton/PressedButton.jpg");
            } else if (e.getSource() == restartTextPane && restartTextPane.isSelectable()) {
                restartTextPane.selectIcon("res/img/imgButton/PressedButton.jpg");
            }
            repaintAll();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getSource() == nextTextPane && nextTextPane.isSelectable()) {
                nextTextPane.selectIcon("res/img/imgButton/StaticButton.jpg");
            } else if (e.getSource() == restartTextPane && restartTextPane.isSelectable()) {
                restartTextPane.selectIcon("res/img/imgButton/StaticButton.jpg");
            }
            repaintAll();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == responseTextPane1 && responseTextPane1.isSelectable() && !responseTextPane1.isSelected()) {
                responseTextPane1.selectIcon("res/img/other/SelectedWindow.jpg");
            } else if (e.getSource() == responseTextPane2 && responseTextPane2.isSelectable() && !responseTextPane2.isSelected()) {
                responseTextPane2.selectIcon("res/img/other/SelectedWindow.jpg");
            } else if (e.getSource() == responseTextPane3 && responseTextPane3.isSelectable() && !responseTextPane3.isSelected()) {
                responseTextPane3.selectIcon("res/img/other/SelectedWindow.jpg");
            } else if (e.getSource() == nextTextPane && nextTextPane.isSelectable()) {
                nextTextPane.selectIcon("res/img/imgButton/SelectedButton.jpg");
            } else if (e.getSource() == restartTextPane && restartTextPane.isSelectable()) {
                restartTextPane.selectIcon("res/img/imgButton/SelectedButton.jpg");
            }
            repaintAll();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == responseTextPane1 && responseTextPane1.isSelectable() && !responseTextPane1.isSelected()) {
                responseTextPane1.selectIcon("res/img/other/StaticWindow.jpg");
            } else if (e.getSource() == responseTextPane2 && responseTextPane2.isSelectable() && !responseTextPane2.isSelected()) {
                responseTextPane2.selectIcon("res/img/other/StaticWindow.jpg");
            } else if (e.getSource() == responseTextPane3 && responseTextPane3.isSelectable() && !responseTextPane3.isSelected()) {
                responseTextPane3.selectIcon("res/img/other/StaticWindow.jpg");
            } else if (e.getSource() == nextTextPane && nextTextPane.isSelectable()) {
                nextTextPane.selectIcon("res/img/imgButton/StaticButton.jpg");
            } else if (e.getSource() == restartTextPane && restartTextPane.isSelectable()) {
                restartTextPane.selectIcon("res/img/imgButton/StaticButton.jpg");
            }
            repaintAll();
        }
    };
    private int boringFeature06ref[];

    // Default Constructor
    public View() {
        String fontFileName = "Minecraftia.ttf";
        loadFont(fontFileName, Font.TRUETYPE_FONT);
        this.regularFont = new Font(fontName, Font.PLAIN, 13);
        this.smallFont = new Font(fontName, Font.PLAIN, 9);
        bBoringFeature06status = 0;
        boringFeature06Index = 0;
        boringFeature06ref = new int[10];
        boringFeature06ref[0] = 38;
        boringFeature06ref[1] = 38;
        boringFeature06ref[2] = 40;
        boringFeature06ref[3] = 40;
        boringFeature06ref[4] = 37;
        boringFeature06ref[5] = 39;
        boringFeature06ref[6] = 37;
        boringFeature06ref[7] = 39;
        boringFeature06ref[8] = 66;
        boringFeature06ref[9] = 65;

        createFrame();
    }

    private void loadFont(String ffn, int fontFormat) {
        URL url = getClass().getResource("res/fonts/" + ffn);
        InputStream fontStream = null;
        Font font = null;

        try {
            fontStream = url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            font = Font.createFont(fontFormat, fontStream);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
    }

    public void createFrame() {
        // Story Text area
        dialogTextArea = new JTextArea();
        dialogTextArea.setFont(regularFont);
        dialogTextArea.setWrapStyleWord(true);
        dialogTextArea.setLineWrap(true);
        dialogTextArea.setEditable(false);
        dialogTextArea.setOpaque(false);

        dialogTextArea.setHighlighter(null);
        dialogTextArea.setSize(new Dimension(260, 300));
        dialogTextArea.setLocation(474, 38);

        // User response area
        responseTextPane1 = new SelectableTextPane("");
        responseTextPane1.setFont(regularFont);
        responseTextPane1.setEditable(false);
        responseTextPane1.addMouseListener(paneML);
        responseTextPane1.setSize(new Dimension(680, 48));
        responseTextPane1.setLocation(59, 356);

        responseTextPane2 = new SelectableTextPane("");
        responseTextPane2.setFont(regularFont);
        responseTextPane2.setEditable(false);
        responseTextPane2.addMouseListener(paneML);
        responseTextPane2.setSize(new Dimension(680, 48));
        responseTextPane2.setLocation(59, 407);

        responseTextPane3 = new SelectableTextPane("");
        responseTextPane3.setFont(regularFont);
        responseTextPane3.setEditable(false);
        responseTextPane3.addMouseListener(paneML);
        responseTextPane3.setSize(new Dimension(680, 48));
        responseTextPane3.setLocation(59, 459);

        nextTextPane = new SelectableTextPane("    Next");
        nextTextPane.setFont(new Font(fontName, Font.PLAIN, 19));
        nextTextPane.setEditable(false);
        nextTextPane.addMouseListener(paneML);
        nextTextPane.selectIcon("res/img/imgButton/DisabledButton.jpg");
        nextTextPane.setSelectable(false);
        nextTextPane.setSize(new Dimension(147, 37));
        nextTextPane.setLocation(492, 525);

        restartTextPane = new SelectableTextPane("  Restart");
        restartTextPane.setFont(new Font(fontName, Font.PLAIN, 19));
        restartTextPane.setEditable(false);
        restartTextPane.addMouseListener(paneML);
        restartTextPane.selectIcon("res/img/imgButton/StaticButton.jpg");
        restartTextPane.setSize(new Dimension(147, 37));
        restartTextPane.setLocation(159, 525);

        //Image area
        ImageIcon storyImage = createImageIcon("res/img/imgLabel/000.jpg");
        storyLabel = new JLabel("", storyImage, JLabel.CENTER);
        storyLabel.setSize(new Dimension(400, 300));
        storyLabel.setLocation(63, 38);

        // Create background
        ImageIcon backgroundImage = createImageIcon("res/img/other/Background.jpg");
        JPanel backgroundPanel = new JPanel();
        backgroundLabel = new JLabel("", backgroundImage, JLabel.CENTER);
        backgroundPanel.add(backgroundLabel, BorderLayout.CENTER);

        // Create frame and resize and set location of components
        JFrame game_frame = new JFrame("The Trails of Little Red");
        game_frame.setIconImage(createImageIcon("res/img/other/GAMEICON.ico").getImage());
        JLayeredPane gameLayeredPane = new JLayeredPane();

        backgroundLabel.setFocusable(true);
        backgroundLabel.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

                int keyCode = e.getKeyCode();

                if (keyCode == boringFeature06ref[boringFeature06Index]) {
                    boringFeature06Index++;
                    if (boringFeature06Index == 9) {
                        bBoringFeature06status = 1;
                        boringFeature06Index = 0;
                    }
                } else {
                    boringFeature06Index = 0;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        gameLayeredPane.setPreferredSize(new Dimension(800, 600));
        backgroundLabel.setSize(gameLayeredPane.getPreferredSize());
        backgroundLabel.setLocation(0, 0);
        gameLayeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        // Get icon image
        BufferedImage iconImg;
        try {
            String iconImgPath = "res/img/other/Background.jpg";    // Change Icon Path here
            InputStream imgStream = getClass().getResourceAsStream(iconImgPath);
            iconImg = ImageIO.read(imgStream);
        } catch (IOException e) {
            e.printStackTrace();
            iconImg = null;
        }

        // Place components on Frame
        game_frame.add(gameLayeredPane, BorderLayout.CENTER);
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
        game_frame.setIconImage(iconImg);
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
        backgroundLabel.requestFocusInWindow();

        System.out.println("View: Received Scene " + s.getID() + " from Model.");

        //boring feature 06
        if ((s.getID().equals("001")) && (bBoringFeature06status == 1)) {
            System.out.print("*Boring Feature 06 activated*");
        }

        if (((s.getID().equals("006")) || (s.getID().equals("007"))) && (bBoringFeature06status == 1)) {
            //set story label to drunk red image
            sceneChoice[2] = "Screw the paths, that sounds like a whole lot of work. I'd rather get super drunk with Granny's wine.";
            bBoringFeature06status = 2;
        }

        // Update image
        storyLabel.setIcon(null);
        storyLabel.setIcon(s.getSceneImage());

        // Update text
        if (s.getStoryText().length() > 346) {
            dialogTextArea.setFont(smallFont);
        } else {
            dialogTextArea.setFont(regularFont);
        }

        dialogTextArea.setText(s.getStoryText());

        if (!sceneChoice[0].isEmpty()) {
            responseTextPane1.setText(sceneChoice[0]);
            responseTextPane1.setSelectable(true);
            responseTextPane1.selectIcon("res/img/other/StaticWindow.jpg");
        } else {
            responseTextPane1.setText("");
            responseTextPane1.setSelectable(false);
            responseTextPane1.selectIcon("res/img/other/DisabledWindow.jpg");
        }

        if (!sceneChoice[1].isEmpty()) {
            responseTextPane2.setText(sceneChoice[1]);
            responseTextPane2.setSelectable(true);
            responseTextPane2.selectIcon("res/img/other/StaticWindow.jpg");
        } else {
            responseTextPane2.setText("");
            responseTextPane2.setSelectable(false);
            responseTextPane2.selectIcon("res/img/other/DisabledWindow.jpg");
        }

        if (!sceneChoice[2].isEmpty()) {
            responseTextPane3.setText(sceneChoice[2]);
            responseTextPane3.setSelectable(true);
            responseTextPane3.selectIcon("res/img/other/StaticWindow.jpg");
        } else {
            responseTextPane3.setText("");
            responseTextPane3.setSelectable(false);
            responseTextPane3.selectIcon("res/img/other/DisabledWindow.jpg");
        }

    }
}