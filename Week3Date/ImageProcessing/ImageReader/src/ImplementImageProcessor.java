import imagereader.IImageProcessor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

enum color{
    RED, GREEN, BLUE, GRAY
}

public class ImplementImageProcessor implements IImageProcessor
{
    public Image showChanelR(Image img){
        ColorFilter filter = new ColorFilter(color.RED);
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(), filter));
    }

    public Image showChanelG(Image img){
        ColorFilter filter = new ColorFilter(color.GREEN);
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(), filter));
    }

    public Image showChanelB(Image img){
        ColorFilter filter = new ColorFilter(color.BLUE);
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(), filter));
    }

    public Image showGray(Image img){
        ColorFilter filter = new ColorFilter(color.GRAY);
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(), filter));
    }

}
class ColorFilter extends RGBImageFilter
{
    private color type;

    public ColorFilter(color code){
        type = code;
    }

    public int filterRGB(int x, int y, int rgb){
        switch(type) {
            case RED:
                return (rgb & 0xffff0000);
            case GREEN:
                return (rgb & 0xff00ff00);
            case BLUE:
                return (rgb & 0xff0000ff);
            case GRAY:
                int gray = (int)( ((rgb & 0x00ff0000) >> 16)*0.299 + ((rgb & 0x0000ff00) >> 8 )*0.587 + ((rgb & 0x000000ff))*0.114 );  
                return (rgb & 0xff000000) + (gray << 16) + (gray << 8) + gray;  
            default:
                break; 
        }
        return 0;
    }
}

