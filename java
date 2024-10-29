// 01
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class RedSquareDetection {

    public static void main(String[] args) {
        // Load the OpenCV library.
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Read the image
        Mat image = Imgcodecs.imread("your_image.jpg");

        // Convert the image to grayscale
        Mat grayImage = new Mat();
        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);

        // Detect edges in the grayscale image
        Mat edges = new Mat();
        Imgproc.Canny(grayImage, edges, 100, 200);

        // Find contours in the edge-detected image
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Draw red squares around the detected contours
        for (MatOfPoint contour : contours) {
            Rect rect = Imgproc.boundingRect(contour);
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 2);
        }

        // Display the result
        HighGui.imshow("Red Squares", image);
        HighGui.waitKey(0);
        HighGui.destroyAllWindows();
    }
}
