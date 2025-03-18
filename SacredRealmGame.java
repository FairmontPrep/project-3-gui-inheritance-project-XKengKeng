import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class HolyLandBackground extends JPanel {
    protected BufferedImage background;
    protected String description = "Sacred Realm";
    protected final Dimension CANVAS_SIZE = new Dimension(1152, 702);

    public HolyLandBackground() {
        try {
            background = ImageIO.read(new File("background.png"));
            if (background == null) System.err.println("Background image missing!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, CANVAS_SIZE.width, CANVAS_SIZE.height, null);
    }

    @Override
    public Dimension getPreferredSize() {
        return CANVAS_SIZE;
    }
}

class HolyGround extends HolyLandBackground {
    private BufferedImage ground;

    public HolyGround() {
        super();
        try {
            ground = ImageIO.read(new File("ground.png"));
            description += " with blessed grass";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ground, 0, 0, CANVAS_SIZE.width, CANVAS_SIZE.height, null);
    }
}

class HolyTrees extends HolyGround {
    private BufferedImage trees;

    public HolyTrees() {
        super();
        try {
            trees = ImageIO.read(new File("trees.png"));
            description += " beneath ancient trees";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(trees, 0, 0, CANVAS_SIZE.width, CANVAS_SIZE.height, null);
    }
}

class CharacterLayer extends HolyTrees {
    private BufferedImage character;
    private String characterName;

    public CharacterLayer() {
        super();
        try {
            if (Math.random() < 0.5) {
                character = ImageIO.read(new File("Miku.png"));
                characterName = "Hatsune Miku";
            } else {
                character = ImageIO.read(new File("character.png"));
                characterName = "Divine Guardian";
            }
            description += " protected by " + characterName;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(character, 0, 0, CANVAS_SIZE.width, CANVAS_SIZE.height, null);
        
        // Draw description text
        g.setColor(new Color(255, 215, 0));
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString(description, 20, 680);
    }
}

public class SacredRealmGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Holy Land Sanctuary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1165, 735);
        
        CharacterLayer scene = new CharacterLayer();
        frame.add(scene);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}