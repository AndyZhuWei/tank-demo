package test;

import com.andy.ResourceMgr;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author HP
 * @Description TODO
 * @date 2020/9/7-9:40
 */
class ResourceMgrTest {


    @Test
    public void test01() throws IOException {
        BufferedImage read = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
        assertNotNull(read);
    }

}