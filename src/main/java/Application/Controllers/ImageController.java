package Application.Controllers;

import Application.DataBase.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

@Controller
public class ImageController {

    @Autowired
    ImageRepository ir;

    @PostMapping("/upload")
    public @ResponseBody String handleImageUpload(@RequestParam("name") String name,
                                                  @RequestParam("file") MultipartFile file)
            throws IOException
    {
        byte[] img = image2ByteArray(file);
        ir.addImage(Arrays.toString(img).replace("[", "").replace("]", ""));
        int idx = 0;

        byte[] img2 = new byte[img.length];
        for (String imgByte : ir.getImage().split(", ")) {
            img2[idx++] = Byte.parseByte(imgByte);
        }
        byteArray2Image(img2);
        return "done";
    }

    private byte[] image2ByteArray(MultipartFile file) throws IOException {
        BufferedImage bImage = ImageIO.read(file.getInputStream());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos);
        return bos.toByteArray();
    }

    private void byteArray2Image(byte[] data) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        BufferedImage bImage = ImageIO.read(bis);
        ImageIO.write(bImage, "png", new File("output.png"));
    }

}