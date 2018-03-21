package cn.org.xinke.util;

import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.util.StringUtils;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 条形码生成工具类
 *
 * @author cinco
 * @createDate 2018-03-21
 */
public class BarcodeUtil {

    /**
     * 生成文件
     *
     * @param msg
     * @param path
     * @return
     */
    public static File generateFile(String msg, String path) {
        File file = new File(path);

        try {
            FileOutputStream ous = new FileOutputStream(file);
            if (StringUtils.isEmpty(msg) || ous == null) {
                throw new Exception("条形码内容或文件保存路径无效");
            }

            // 采用Code39编码
            Code39Bean bean = new Code39Bean();

            // 精细度
            final int dpi = 150;
            // module宽度
            final double moduleWidth = UnitConv.in2mm(1.0f / dpi);

            // 配置对象
            bean.setModuleWidth(moduleWidth);
            bean.setWideFactor(3);
            bean.doQuietZone(false);

            String format = "image/png";
            // 输出到流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi,
                    BufferedImage.TYPE_BYTE_BINARY, false, 0);

            // 生成条形码
            bean.generateBarcode(canvas, msg);

            // 结束绘制
            canvas.finish();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    public static void main(String[] args) {
        String msg = "1420640581";
        String path = "images/barcode.png";
        generateFile(msg, path);
    }
}