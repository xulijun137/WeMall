//package com.xu.wemall.controller.common;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.WriterException;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
//import io.swagger.annotations.Api;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Api(value = "二维码接口集合", tags = "二维码接口集合")
//@RestController
//@RequestMapping(value = "/qrcode")
//public class QrCodeController {
//
//    /**
//     * 生成二维码方法
//     *
//     * @param content 要生成的内容
//     * @param resp    response对象
//     * @throws Exception 抛出异常
//     */
//    @GetMapping(value = "/create")
//    public void getQrcode(String content, HttpServletResponse resp) throws Exception {
//        ServletOutputStream stream = null;
//        try {
//            stream = resp.getOutputStream();
//            Map<EncodeHintType, Object> hints = new HashMap<>();
//            //编码
//            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//            //边框距
//            hints.put(EncodeHintType.MARGIN, 0);
//            QRCodeWriter qrCodeWriter = new QRCodeWriter();
//            BitMatrix bm = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
//            MatrixToImageWriter.writeToStream(bm, "png", stream);
//        } catch (WriterException e) {
//            e.getStackTrace();
//
//        } finally {
//            if (stream != null) {
//                stream.flush();
//                stream.close();
//            }
//        }
//    }
//}
