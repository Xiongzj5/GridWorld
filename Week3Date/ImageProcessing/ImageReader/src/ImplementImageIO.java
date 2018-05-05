import java.awt.Image;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO; 

import imagereader.IImageIO;

public class ImplementImageIO implements IImageIO
{
    private static final int HEAD_BYTE = 14;
    private static final int INFO_BYTE = 40;
    private static final int FOUR_BYTE_PER_INFO = 4;
    private static final int MULTI_COLOR = 24;
    private static final int GRAY = 8;
    private int countOfBit; // 图像位数

    public Image myRead(String str) throws IOException
    {
        FileInputStream file = new FileInputStream(str);
        byte bmpHead[] = new byte[HEAD_BYTE];  // bmp文件一开始的十四个字节，位图头，是相应的识别信息
        byte bmpINFO[] = new byte[INFO_BYTE];  // bmp文件从第十五个字节到第五十四的字节，记录了图像的详细信息，一共四十个字节
        int offset;// 偏移量
        int RGBdate[];
        Image image = null;
        byte RGBoriginal[]; // 来存储RGB的原值，一位为一个字节，三位存一个pixel的RGB
        int width, height, imagesize, emptyBytes, pixelsize; // 宽度， 高度， image的大小， 空字节， 像素大小
    
        try{
            file.read(bmpHead, 0, HEAD_BYTE); // 读文件流前十四个字节
            file.read(bmpINFO, 0, INFO_BYTE); // 读接下来的四十个字节

            offset = (((int) bmpHead[13] & 0xff) << 24
                    | ((int) bmpHead[12] & 0xff) << 16
                    | ((int) bmpHead[11] & 0xff) << 8
                    | ((int) bmpHead[10] & 0xff) );

            offset -= 54; // 计算了偏移量， 前十四位中最后四位是记录的位图数据位置的偏移

            width = (((int) bmpINFO[7] & 0xff) << 24
                   | ((int) bmpINFO[6] & 0xff) << 16
                   | ((int) bmpINFO[5] & 0xff) << 8
                   | ((int) bmpINFO[4] & 0xff) ); // 拿到宽度

            height = (((int) bmpINFO[11] & 0xff) << 24
                    | ((int) bmpINFO[10] & 0xff) << 16
                    | ((int) bmpINFO[9] & 0xff) << 8
                    | ((int) bmpINFO[8] & 0xff) ); // 拿到高度

            countOfBit = (((int) bmpINFO[15] & 0xff) << 8
                         | ((int) bmpINFO[14] & 0xff) ); //像素位数

            imagesize = (((int) bmpINFO[23] & 0xff) << 24
                        | ((int) bmpINFO[22] & 0xff) << 16
                        | ((int) bmpINFO[21] & 0xff) << 8
                        | ((int) bmpINFO[20] & 0xff) ); // 图片大小， 原始位图数据的大小

            if(countOfBit == MULTI_COLOR)
            {
                emptyBytes = (imagesize / height) - width * 3; // 空字节的个数
                if(emptyBytes == 4)
                {
                    emptyBytes = 0;
                }

                pixelsize = height * (width + emptyBytes) * 3; // pixel的大小
            
                // 为RGB数组申请空间并读取所有的RGB数据
                if(emptyBytes == 0) 
                {
                    RGBoriginal = new byte[imagesize];
                    file.read(RGBoriginal, 0, imagesize);
                }
                else
                {
                    RGBoriginal = new byte[pixelsize];
                    file.read(RGBoriginal, 0, pixelsize);
                }

                RGBdate = new int[height * width];
                int index = 2;
                for(int j=0;j<height;++j){
                    for(int i=0;i<width;++i){
                        // 像素是从下到上、从左到右保存的
                        RGBdate[width * (height - j - 1) + i] = ((255 & 0xff) << 24
                                                            | (RGBoriginal[index] & 0xff) << 16 
                                                            | (RGBoriginal[index-1] & 0xff) << 8
                                                            | (RGBoriginal[index-2] & 0xff) );
                        index += 3;
                    }
                    // 如果一个图像水平线的字节数不是4的倍数，这行就使用空字节补齐
                    index += emptyBytes;
                }
                //createImage创建一幅用于双缓冲，可以在屏幕外绘制的图像
                image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, RGBdate, 0, width));
                //MemoryImageSource类实现了ImageProducer接口,它为整型数组建立图象数据
                //把图像放在画布上
            }
            file.close();
            //关闭文件流
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return image;
    }

    public Image myWrite(Image image, String str) throws IOException
    {
        try{
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferimg;

            if(countOfBit == MULTI_COLOR)
            {
                bufferimg = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            }
            else
            {
                bufferimg = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
            }

            bufferimg.getGraphics().drawImage(image, 0, 0, null);

            // open the file
            File filex = new File(str + ".bmp");
            ImageIO.write(bufferimg, "bmp", filex);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return image;
    }
}