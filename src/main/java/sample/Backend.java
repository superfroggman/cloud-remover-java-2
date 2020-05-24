package sample;

import org.bytedeco.javacv.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Backend {

    public static void ffmpegTest() throws Exception {

        //convertMovietoJPG("/home/anton/Downloads/tutorial.mp4", "/home/anton/Downloads/images", "jpg", 0);

        otherCopyPastedThing();
    }


    public static void customizedCopyPastedThing(){

    }

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
