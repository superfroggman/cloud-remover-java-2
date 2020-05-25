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


    public static void customizedCopyPastedThing() throws IOException {

        //set temp inputs and outputs
        String input = "/home/anton/Downloads/mackan.mp4";
        String output = "/home/anton/Downloads/images";

        //start things
        InputStream inputStream = new FileInputStream(input);
        Java2DFrameConverter bimConverter = new Java2DFrameConverter();

        //initialize frame grabbers
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(inputStream);
        frameGrabber.start();
        Frame frame;

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
        for (int i = 1; i < nFrames - 1; i++) {

            System.out.println("Starting frame: " + i);

            //Set currentImage to i:th image
            frameGrabber.setFrameNumber(i);
            frame = frameGrabber.grabKeyFrame();
            BufferedImage currentImage = bimConverter.convert(frame);

            for (int w = 0; w < width; w++) {

                for (int h = 0; h < height; h++) {

                    Color baseColor = new Color(baseImage.getRGB(w, h));
                    int baseBrightness = baseColor.getRed() + baseColor.getGreen() + baseColor.getBlue();

                    int currentValue = currentImage.getRGB(w, h);
                    Color currentColor = new Color(currentValue);
                    int currentBrightness = currentColor.getRed() + currentColor.getGreen() + currentColor.getBlue();

                    if (currentBrightness > baseBrightness) {
                        baseImage.setRGB(w, h, currentValue);
                        //System.out.println("Changed things");
                    } else {
                        //System.out.println("Didn't change things");
                    }

                    //baseImage.setRGB(w,h,);
                    //System.out.println("Current image: " + currentImage.getRGB(w, h));
                }
            }

            System.out.println("Done with frame: " + i);
        }

        //Save image
        String path = output + File.separator + "output" + ".jpg";
        ImageIO.write(baseImage, "png", new File(path));

        //Close frameGrabber because that's probably good
        frameGrabber.stop();
        frameGrabber.close();
    }

    //ignore things below this

    //actually works!!!
    public static void otherCopyPastedThing() throws FrameGrabber.Exception, FileNotFoundException {
        InputStream inputStream = new FileInputStream("/home/anton/Downloads/tutorial.mp4");
        Java2DFrameConverter bimConverter = new Java2DFrameConverter();

        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(inputStream);

        String output = "/home/anton/Downloads/images";
        frameGrabber.start();
        Frame frame;
        double frameRate = frameGrabber.getFrameRate();
        int imgNum = 5;
        System.out.println("Video has " + frameGrabber.getLengthInFrames() + " frames and has frame rate of " + frameRate);
        try {
            frameGrabber.setFrameNumber(1000);
            frame = frameGrabber.grabKeyFrame();
            BufferedImage bi = bimConverter.convert(frame);
            String path = output + File.separator + imgNum + ".jpg";
            ImageIO.write(bi, "png", new File(path));
            frameGrabber.stop();
            frameGrabber.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //almost functioning copy pasted thing
    public static void convertMovietoJPG(String mp4Path, String imagePath, String imgType, int frameJump) throws Exception, IOException {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(mp4Path);
        frameGrabber.start();
        Frame frame;
        double frameRate = frameGrabber.getFrameRate();
        int imgNum = 0;
        System.out.println("Video has " + frameGrabber.getLengthInFrames() + " frames and has frame rate of " + frameRate);
        try {
            for (int ii = 1; ii <= frameGrabber.getLengthInFrames(); ii++) {
                imgNum++;
                frameGrabber.setFrameNumber(ii);
                frame = frameGrabber.grab();
                BufferedImage bi = converter.convert(frame);
                String path = imagePath + File.separator + imgNum + ".jpg";
                ImageIO.write(bi, imgType, new File(path));
                ii += frameJump;
            }
            frameGrabber.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
