package gameFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class View extends Observable implements Observer, ActionListener {

    private String response;

    private JFrame game_frame;
    private JLabel label;
    private JButton responseButton1, responseButton2, responseButton3;

    // Default Constructor
    public View() {
        response = "";
        createFrame();
    }

    public void createFrame() {

        //Title area
        JLabel title = new JLabel("<html><h1>The Trails of Little Red</h1></html>");
        title.setHorizontalAlignment(JLabel.CENTER);


        //Conversation area
        JPanel userPanel = new JPanel(new BorderLayout());

        // TOP OF USER PANEL - Story Text
        JTextArea dialogTextArea = new JTextArea("This is the area where the dialog of the person" +
                "little red is talking to comes from");//, SwingConstants.EAST);
        dialogTextArea.setWrapStyleWord(true);
        dialogTextArea.setLineWrap(true);
        dialogTextArea.setEditable(false);

        //dialogTextArea is located in here to allow scrolling
        JScrollPane conversationScrollPane = new JScrollPane(dialogTextArea);
        dialogTextArea.setPreferredSize(new Dimension(350, 200));


        // BOTTOM OF USER PANEL - Responses
        JPanel redsResponsePanel = new JPanel(new GridBagLayout());
        JPanel responsePanel1 = new JPanel();
        JPanel responsePanel2 = new JPanel();
        JPanel responsePanel3 = new JPanel();

        responseButton1 = new JButton("A");
        responseButton1.addActionListener(this);
        responseButton2 = new JButton("B");
        responseButton2.addActionListener(this);
        responseButton3 = new JButton("C");
        responseButton3.addActionListener(this);

        JTextArea responseTextArea1 = new JTextArea("Response This is a test to see if all of the letters will fit in the text box on not go off of the screen");
        responseTextArea1.setSize(new Dimension(325, 500));
        responseTextArea1.setLineWrap(true);
        responseTextArea1.setWrapStyleWord(true);
        responseTextArea1.setEditable(false);
        JTextArea responseTextArea2 = new JTextArea("Response 2");
        responseTextArea2.setSize(new Dimension(325, 500));
        responseTextArea2.setLineWrap(true);
        responseTextArea2.setWrapStyleWord(true);
        responseTextArea2.setEditable(false);
        JTextArea responseTextArea3 = new JTextArea("Response 3");
        responseTextArea3.setSize(new Dimension(325, 500));
        responseTextArea3.setLineWrap(true);
        responseTextArea3.setWrapStyleWord(true);
        responseTextArea3.setEditable(false);

        responsePanel1.add(responseButton1, BorderLayout.WEST);
        responsePanel1.add(responseTextArea1, BorderLayout.EAST);
        responsePanel2.add(responseButton2, BorderLayout.WEST);
        responsePanel2.add(responseTextArea2, BorderLayout.EAST);
        responsePanel3.add(responseButton3, BorderLayout.WEST);
        responsePanel3.add(responseTextArea3, BorderLayout.EAST);


        //Add components to redsResponsePanel
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.PAGE_START;
        c.gridx = 1;
        c.gridy = 0;
        redsResponsePanel.add(responsePanel1, c);
        c.fill = GridBagConstraints.LINE_START;
        c.gridx = 1;
        c.gridy = 1;
        redsResponsePanel.add(responsePanel2, c);
        c.fill = GridBagConstraints.LAST_LINE_START;
        c.gridx = 1;
        c.gridy = 2;
        redsResponsePanel.add(responsePanel3, c);

        //Add components to userPanel
        userPanel.add(dialogTextArea, BorderLayout.NORTH);
        userPanel.add(redsResponsePanel, BorderLayout.WEST);


        //Image area
        ImageIcon image = createImageIcon("res/img/imgLabel/000.jpg");
        label = new JLabel("", image, JLabel.CENTER);


        //
        // Create frame
        game_frame = new JFrame("The Trails of Little Red");
        game_frame.setVisible(true);
        game_frame.getContentPane().add(title, BorderLayout.NORTH);
        game_frame.getContentPane().add(label, BorderLayout.WEST);
        game_frame.getContentPane().add(userPanel, BorderLayout.EAST);
        game_frame.setResizable(false);
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
        System.out.println("View: Received Scene " + s.getID() + " from Model.");

        // Update visuals
        label.setIcon(null);
        label.setIcon(s.getSceneImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == responseButton1) {
            response = "A";
        } else if (e.getSource() == responseButton2) {
            response = "B";
        } else if (e.getSource() == responseButton3) {
            response = "C";
        }
        System.out.println("View: User pressed button " + response + ".");

        // Notify Controller
        setChanged();
        notifyObservers(response);
    }
}