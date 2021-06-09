import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.applet.AudioClip;

/* <applet code = "Animation.class" width = "750" height = "750">
</applet> */

public class Animation extends Applet implements ActionListener, Runnable {
    Button play, stop;
    Image dogeIcon;
    Image ElonBG;
    int X = 0, Y = 0, Xinc = 2, Yinc = 1;
    int ball_width = 100, ball_height = 100, aw, ah;
    Thread t;
    AudioClip audioClip;

    public void init() {
        play = new Button("  Make It Funny  ");
        add(play);
        play.addActionListener(this);
        stop = new Button("  Only Anime  ");
        add(stop);
        stop.addActionListener(this);

        t = new Thread(this);
        dogeIcon = getImage(getDocumentBase(), "doge_logo2.png");
        ElonBG = getImage(getDocumentBase(), "elon.jpeg");
        audioClip = getAudioClip(getCodeBase(), "shooting_stars_effect.wav");
        aw = getSize().width;
        ah = getSize().height;
    }

    public void start() {
        t.start();
    }

    public void run() {
        while (true) {
            X = X + Xinc;
            Y = Y + Yinc;
            repaint();
            try {
                Thread.sleep(10);
            } catch (Exception ex) {
                System.out.println(ex);
            }
            if ((X + ball_width) >= aw || X <= 0) {
                Xinc = Xinc * (-1);
            }
            if ((Y + ball_height) >= ah || Y <= 0) {
                Yinc = Yinc * (-1);
            }
        }

    }

    public void actionPerformed(ActionEvent ae) {
        Button source = (Button) ae.getSource();
        if (source.getLabel() == "  Make It Funny  ") {
            audioClip.play();
        } else if (source.getLabel() == "  Only Anime  ") {
            audioClip.stop();
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.pink);
        // g.drawImage(ElonBG, 0, 0, this);
        g.drawImage(ElonBG, 0, 0, getWidth(), getHeight(), this);
        super.paint(g);

        g.drawImage(dogeIcon, X, Y, 200, 200, this);
    }

}
