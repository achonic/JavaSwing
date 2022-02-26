package af.swing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import  javax.swing.JPanel;

public class PhotoView extends JPanel {

    private Color bgColor;
    private Image image;

    public void setBackgroundColor(Color color){
        this.bgColor = color;
        this.repaint();
    }
    public void setImage(Image image){
        this.image = image;
        this.repaint();
    }
    public void setImage(File file){
        try {
            this.image = ImageIO.read(file);
            this.repaint();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void setImage(String resourcePath){
        try{
            InputStream res = this.getClass().getResourceAsStream(resourcePath);
            this.image = ImageIO.read(res);

            this.repaint();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = this.getWidth();
        int height = this.getHeight();


        if(bgColor != null ){
            g.setColor(bgColor);
            g.fillRect(0,0,width,height);
        }

        if(image != null) {
            //保持长宽比
            int imgW = image.getWidth(null);
            int imgH = image.getHeight(null);

            int fitW = width;
            int fitH = width * imgH / imgW;
            if (fitH > height) {
                fitH = height;
                fitW = height * imgW / imgH;
            }
            int fitX = (width - fitW) / 2;
            int fitY = (height - fitH) / 2;

            g.drawImage(image, fitX, fitY, fitW, fitH, null);
        }
    }
}
