package gameFiles;

import javax.swing.*;
import java.awt.*;

public class SelectableTextPane extends JTextPane {

    private boolean isSelected, isSelectable;
    private ImageIcon image;

    public SelectableTextPane(String text) {
        super();
        this.setText(text);
        selectIcon("res/img/other/StaticWindow.jpg");
        setOpaque(true);
        setHighlighter(null);
        isSelected = false;
        isSelectable = true;
        this.setBackground(new Color(0, 0, 0, 0));
    }

    public void selectIcon(String imagePath) {
        java.net.URL imageURL = getClass().getResource(imagePath);
        if (imageURL != null) {
            image = new ImageIcon(imageURL);
        } else {
            System.err.println("When selecting response, couldn't find file: " + imagePath);
            image = null;
        }
    }

    public void selectResponse() {
        isSelected = true;
    }

    public void deselectResponse() {
        isSelected = false;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public void setSelectable(boolean isSelectable) {
        this.isSelectable = isSelectable;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        g.drawImage(image.getImage(), 0, 0, this);
        super.paintComponent(g);
    }
}
