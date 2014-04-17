package gameFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

public class View extends Observable implements Observer {

    private JLabel storyLabel;
    private JTextArea dialogTextArea;
    private SelectableTextPane responseTextPane1, responseTextPane2, responseTextPane3, nextTextPane, restartTextPane;
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
                    response = 2;
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
    private String fontName = "Minecraftia";
    private Font smallFont, regularFont;

    // Default Constructor
    public View() {
        String fontFileName = "Minecraftia.ttf";
        loadFont(fontFileName, Font.TRUETYPE_FONT);
        this.regularFont = new Font(fontName, Font.PLAIN, 13);
        this.smallFont = new Font(fontName, Font.PLAIN, 9);
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
        JLabel backgroundLabel = new JLabel("", backgroundImage, JLabel.CENTER);
        backgroundPanel.add(backgroundLabel, BorderLayout.CENTER);

        // Create frame and resize and set location of components
        JFrame game_frame = new JFrame("The Trails of Little Red");
        JLayeredPane gameLayeredPane = new JLayeredPane();
        gameLayeredPane.setPreferredSize(new Dimension(800, 600));
        backgroundLabel.setSize(gameLayeredPane.getPreferredSize());
        backgroundLabel.setLocation(0, 0);
        gameLayeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

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