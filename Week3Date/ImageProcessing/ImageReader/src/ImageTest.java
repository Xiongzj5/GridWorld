import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;
import java.io.FileInputStream;
import java.awt.image.BufferedImage;
import java.awt.Image;

import javax.imageio.ImageIO;

public class ImageTest
{
    @Test
    public void testRed() throws IOException
    {
          // 分别用于测试第一副图和第二副图
        ImplementImageIO image1 = new ImplementImageIO();
        ImplementImageIO image2 = new ImplementImageIO();

        // 读取到第一副图和第二副图
        Image goalImage1 = image1.myRead("/home/parallels/GridWorld/Week3Date/ImageProcessing/bmptest/1.bmp");
        Image goalImage2 = image2.myRead("/home/parallels/GridWorld/Week3Date/ImageProcessing/bmptest/2.bmp");

        //两个process分别对第一副图和第二副图进行处理
        ImplementImageProcessor processor1 = new ImplementImageProcessor();
        ImplementImageProcessor processor2 = new ImplementImageProcessor();

        //接下来我们通过这两个process对我们的图1和图2进行红色的提取
        Image redImage1 = processor1.showChanelR(goalImage1);
        Image redImage2 = processor2.showChanelR(goalImage2);

        //然后我们再把目标文件拿出来准备待会进行对比
        FileInputStream file1 = new FileInputStream("/home/parallels/GridWorld/Week3Date/ImageProcessing/bmptest/goal/1_red_goal.bmp");
        FileInputStream file2 = new FileInputStream("/home/parallels/GridWorld/Week3Date/ImageProcessing/bmptest/goal/2_red_goal.bmp");
        
        //生成的图片在内存里有一个图像缓冲区,便于我们对之操作
        BufferedImage testFile1 = ImageIO.read(file1);
        BufferedImage testFile2 = ImageIO.read(file2);
        //拿到图片的宽高
        int width1 = redImage1.getWidth(null);
        int width2 = redImage2.getWidth(null);
        int height1 = redImage1.getHeight(null);
        int height2 = redImage2.getHeight(null);
        BufferedImage myTestFile1 = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_BGR);
        BufferedImage myTestFile2 = new BufferedImage(width2, height2, BufferedImage.TYPE_INT_BGR);
        myTestFile1.getGraphics().drawImage(redImage1, 0, 0, width1, height1, null);
        myTestFile2.getGraphics().drawImage(redImage2, 0, 0, width2, height2, null);
        //对第一副图的检测
        for(int i=0;i<testFile1.getWidth(null);++i)
        {
            for(int j=0;j<testFile1.getHeight(null);++j)
            {
                assertEquals(testFile1.getRGB(i,j), myTestFile1.getRGB(i,j));
            }
        }
        assertEquals(redImage1.getWidth(null), testFile1.getWidth(null));
        assertEquals(redImage1.getHeight(null), testFile1.getHeight(null));
        //对第二副图进行检测
        for(int i=0;i<testFile2.getWidth(null);++i)
        {
            for(int j=0;j<testFile2.getHeight(null);++j)
            {
                assertEquals(testFile2.getRGB(i,j), myTestFile2.getRGB(i,j));
            }
        }
        assertEquals(redImage2.getWidth(null), testFile2.getWidth(null));
        assertEquals(redImage2.getHeight(null), testFile2.getHeight(null));
    }

    @Test
    public void testGreen() throws IOException
    {
        // 分别用于测试第一副图和第二副图
        ImplementImageIO image1 = new ImplementImageIO();
        ImplementImageIO image2 = new ImplementImageIO();

        // 读取到第一副图和第二副图
        Image goalImage1 = image1.myRead("/home/parallels/GridWorld/Week3Date/ImageProcessing/bmptest/1.bmp");
        Image goalImage2 = image2.myRead("/home/parallels/GridWorld/Week3Date/ImageProcessing/bmptest/2.bmp");

        //两个process分别对第一副图和第二副图进行处理
        ImplementImageProcessor processor1 = new ImplementImageProcessor();
        ImplementImageProcessor processor2 = new ImplementImageProcessor();

        //接下来我们通过这两个process对我们的图1和图2进行绿色的提取
        Image greenImage1 = processor1.showChanelG(goalImage1);
        Image greenImage2 = processor2.showChanelG(goalImage2);

        //然后我们再把目标文件拿出来准备待会进行对比
        FileInputStream file1 = new FileInputStream("/home/parallels/GridWorld/Week3Date/ImageProcessing/bmptest/goal/1_green_goal.bmp");
        FileInputStream file2 = new FileInputStream("/home/parallels/GridWorld/Week3Date/ImageProcessing/bmptest/goal/2_green_goal.bmp");
        
        //生成的图片在内存里有一个图像缓冲区,便于我们对之操作
        BufferedImage testFile1 = ImageIO.read(file1);
        BufferedImage testFile2 = ImageIO.read(file2);
        //拿到图片的宽高
        int width1 = greenImage1.getWidth(null);
        int width2 = greenImage2.getWidth(null);
        int height1 = greenImage1.getHeight(null);
        int height2 = greenImage2.getHeight(null);
        BufferedImage myTestFile1 = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_BGR);
        BufferedImage myTestFile2 = new BufferedImage(width2, height2, BufferedImage.TYPE_INT_BGR);
        myTestFile1.getGraphics().drawImage(greenImage1, 0, 0, width1, height1, null);
        myTestFile2.getGraphics().drawImage(greenImage2, 0, 0, width2, height2, null);
        //对第一副图的检测
        for(int i=0;i<testFile1.getWidth(null);++i)
        {
            for(int j=0;j<testFile1.getHeight(null);++j)
            {
                assertEquals(testFile1.getRGB(i,j), myTestFile1.getRGB(i,j));
            }
        }
        assertEquals(greenImage1.getWidth(null), testFile1.getWidth(null));
        assertEquals(greenImage1.getHeight(null), testFile1.getHeight(null));
        //对第二副图进行检测
        for(int i=0;i<testFile2.getWidth(null);++i)
        {
            for(int j=0;j<testFile2.getHeight(null);++j)
            {
                assertEquals(testFile2.getRGB(i,j), myTestFile2.getRGB(i,j));
            }
        }
        assertEquals(greenImage2.getWidth(null), testFile2.getWidth(null));
        assertEquals(greenImage2.getHeight(null), testFile2.getHeight(null));
    }
    @Test
    public void testBlue() throws IOException
    {
         // 分别用于测试第一副图和第二副图
        ImplementImageIO image1 = new ImplementImageIO();
        ImplementImageIO image2 = new ImplementImageIO();

        // 读取到第一副图和第二副图
        Image goalImage1 = image1.myRead("/home/parallels/GridWorld/Week3Date/ImageProcessing/bmptest/1.bmp");
        Image goalImage2 = image2.myRead("/home/parallels/GridWorld/Week3Date/ImageProcessing/bmptest/2.bmp");

        //两个process分别对第一副图和第二副图进行处理
        ImplementImageProcessor processor1 = new ImplementImageProcessor();
        ImplementImageProcessor processor2 = new ImplementImageProcessor();

        //接下来我们通过这两个process对我们的图1和图2进行蓝色的提取
        Image blueImage1 = processor1.showChanelB(goalImage1);
        Image blueImage2 = processor2.showChanelB(goalImage2);

        //然后我们再把目标文件拿出来准备待会进行对比
        FileInputStream file1 = new FileInputStream("/home/parallels/GridWorld/Week3Date/ImageProcessing/bmptest/goal/1_blue_goal.bmp");
        FileInputStream file2 = new FileInputStream("/home/parallels/GridWorld/Week3Date/ImageProcessing/bmptest/goal/2_blue_goal.bmp");
        
        //生成的图片在内存里有一个图像缓冲区,便于我们对之操作
        BufferedImage testFile1 = ImageIO.read(file1);
        BufferedImage testFile2 = ImageIO.read(file2);
        //拿到图片的宽高
        int width1 = blueImage1.getWidth(null);
        int width2 = blueImage2.getWidth(null);
        int height1 = blueImage1.getHeight(null);
        int height2 = blueImage2.getHeight(null);
        BufferedImage myTestFile1 = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_BGR);
        BufferedImage myTestFile2 = new BufferedImage(width2, height2, BufferedImage.TYPE_INT_BGR);
        myTestFile1.getGraphics().drawImage(blueImage1, 0, 0, width1, height1, null);
        myTestFile2.getGraphics().drawImage(blueImage2, 0, 0, width2, height2, null);
        //对第一副图的检测
        for(int i=0;i<testFile1.getWidth(null);++i)
        {
            for(int j=0;j<testFile1.getHeight(null);++j)
            {
                assertEquals(testFile1.getRGB(i,j), myTestFile1.getRGB(i,j));
            }
        }
        assertEquals(blueImage1.getWidth(null), testFile1.getWidth(null));
        assertEquals(blueImage1.getHeight(null), testFile1.getHeight(null));
        //对第二副图进行检测
        for(int i=0;i<testFile2.getWidth(null);++i)
        {
            for(int j=0;j<testFile2.getHeight(null);++j)
            {
                assertEquals(testFile2.getRGB(i,j), myTestFile2.getRGB(i,j));
            }
        }
        assertEquals(blueImage2.getWidth(null), testFile2.getWidth(null));
        assertEquals(blueImage2.getHeight(null), testFile2.getHeight(null));
    }
    @Test
    public void testGray() throws IOException
    {
         // 分别用于测试第一副图和第二副图
         ImplementImageIO image1 = new ImplementImageIO();
         ImplementImageIO image2 = new ImplementImageIO();
 
         // 读取到第一副图和第二副图
         Image goalImage1 = image1.myRead("/home/parallels/GridWorld/Week3Date/ImageProcessing/bmptest/1.bmp");
         Image goalImage2 = image2.myRead("/home/parallels/GridWorld/Week3Date/ImageProcessing/bmptest/2.bmp");
 
         //两个process分别对第一副图和第二副图进行处理
         ImplementImageProcessor processor1 = new ImplementImageProcessor();
         ImplementImageProcessor processor2 = new ImplementImageProcessor();
 
         //接下来我们通过这两个process对我们的图1和图2进行灰色的提取
         Image grayImage1 = processor1.showGray(goalImage1);
         Image grayImage2 = processor2.showGray(goalImage2);
 
         //然后我们再把目标文件拿出来准备待会进行对比
         FileInputStream file1 = new FileInputStream("/home/parallels/GridWorld/Week3Date/ImageProcessing/bmptest/goal/1_gray_goal.bmp");
         FileInputStream file2 = new FileInputStream("/home/parallels/GridWorld/Week3Date/ImageProcessing/bmptest/goal/2_gray_goal.bmp");
         
         //生成的图片在内存里有一个图像缓冲区,便于我们对之操作
         BufferedImage testFile1 = ImageIO.read(file1);
         BufferedImage testFile2 = ImageIO.read(file2);
         //拿到图片的宽高
         int width1 = grayImage1.getWidth(null);
         int width2 = grayImage2.getWidth(null);
         int height1 = grayImage1.getHeight(null);
         int height2 = grayImage2.getHeight(null);
         BufferedImage myTestFile1 = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_BGR);
         BufferedImage myTestFile2 = new BufferedImage(width2, height2, BufferedImage.TYPE_INT_BGR);
         myTestFile1.getGraphics().drawImage(grayImage1, 0, 0, width1, height1, null);
         myTestFile2.getGraphics().drawImage(grayImage2, 0, 0, width2, height2, null);
         //对第一副图的检测
         for(int i=0;i<testFile1.getWidth(null);++i)
         {
             for(int j=0;j<testFile1.getHeight(null);++j)
             {
                 assertEquals(testFile1.getRGB(i,j), myTestFile1.getRGB(i,j));
             }
         }
         assertEquals(grayImage1.getWidth(null), testFile1.getWidth(null));
         assertEquals(grayImage1.getHeight(null), testFile1.getHeight(null));
         //对第二副图进行检测
         for(int i=0;i<testFile2.getWidth(null);++i)
         {
             for(int j=0;j<testFile2.getHeight(null);++j)
             {
                 assertEquals(testFile2.getRGB(i,j), myTestFile2.getRGB(i,j));
             }
         }
         assertEquals(grayImage2.getWidth(null), testFile2.getWidth(null));
         assertEquals(grayImage2.getHeight(null), testFile2.getHeight(null));
    }
}