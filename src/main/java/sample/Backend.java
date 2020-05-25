package sample;

import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Backend {

    public static void ffmpegTest() throws Exception {

        //convertMovietoJPG("/home/anton/Downloads/tutorial.mp4", "/home/anton/Downloads/images", "jpg", 0);

        //otherCopyPastedThing();

        customizedCopyPastedThing();
    }


    public static void customizedCopyPastedThing() throws IOException, InterruptedException {

        //set temp inputs and outputs
        String input = "/home/anton/Downloads/ex.gif";
        String output = "/home/anton/Downloads/images";

        //start things
        InputStream inputStream = new FileInputStream(input);
        Java2DFrameConverter bimConverter = new Java2DFrameConverter();
        Java2DFrameConverter bimConverter2 = new Java2DFrameConverter();

        //initialize frame grabbers
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(inputStream);
        frameGrabber.start();
        Frame frame;
        Frame frame2;

        double frameRate = frameGrabber.getFrameRate();
        int nFrames = frameGrabber.getLengthInFrames();
        System.out.println("Video has " + nFrames + " frames and has frame rate of " + frameRate);

        //Set baseImage to first frame
        frameGrabber.setFrameNumber(0);
        frame = frameGrabber.grabKeyFrame();
        BufferedImage baseImage = bimConverter.convert(frame);

        int width = baseImage.getWidth();
        int height = baseImage.getHeight();

        //Loop through all frames

        coolio:
        for (int i = 1; i < nFrames; i++) {

            int changed = 0;
            int notChanged = 0;

            System.out.println("Starting frame: " + i);

            //Set currentImage to i:th image
            frameGrabber.setFrameNumber(i);

            System.out.println("should be: "+i+" is: "+ frameGrabber.getFrameNumber());


            frame2 = frameGrabber.grabKeyFrame();
            System.out.println("frame2thing: " + frame2);
            BufferedImage currentImage = bimConverter2.convert(frame2);


            for (int w = 0; w < width; w++) {

                for (int h = 0; h < height; h++) {

                    Color baseColor = new Color(baseImage.getRGB(w, h));
                    int baseBrightness = baseColor.getRed() + baseColor.getGreen() + baseColor.getBlue();

                    //System.out.println("current img!: " + currentImage);
                    if(currentImage == null){
                        System.out.println("NULL!!!!!, SKIPPING!!!!");
                        //continue coolio;
                    }
                    int currentValue = currentImage.getRGB(w, h);
                    Color currentColor = new Color(currentValue);
                    int currentBrightness = currentColor.getRed() + currentColor.getGreen() + currentColor.getBlue();

                    //System.out.println("base: " + baseBrightness + "current: " + currentBrightness);

                    if (currentBrightness < baseBrightness) {
                        baseImage.setRGB(w, h, currentValue);
                        //System.out.println("Changed things");
                        changed++;
                    } else {
                        //System.out.println("Didn't change things");
                        notChanged++;
                    }

                }
            }

            System.out.println("Done with frame: " + i);
            System.out.println("Changed: " + changed + "and didn't change: " + notChanged);

            float percent = 100 * (float)i / (float)nFrames;
            System.out.println(percent + "% finished\n");
        }

        //Save image
        String path = output + File.separator + "output" + ".png";
        ImageIO.write(baseImage, "png", new File(path));

        //Close frameGrabber because that's probably good
        frameGrabber.stop();
        frameGrabber.close();
    }
}
