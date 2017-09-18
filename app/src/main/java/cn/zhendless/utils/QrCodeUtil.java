package cn.zhendless.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.Random;

public class QrCodeUtil {

    public static final String TAG = QrCodeUtil.class.getSimpleName();

    /**
     * 生成二维码图片
     *
     * @param url      content that will be generated in QR code
     * @param qrWidth, qrHeight width and height for QR code image.
     * @return Bitmap for success, null for Exception.
     * @
     */
    public static Bitmap generateQRCode(String url, int qrWidth, int qrHeight) {

        try {
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.MARGIN, 0);
            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, qrWidth, qrHeight, hints);
            int[] pixels = new int[qrHeight * qrWidth];
            for (int i = 0; i < qrHeight; i++) {
                for (int j = 0; j < qrWidth; j++) {
                    if (bitMatrix.get(j, i)) {
                        pixels[i * qrWidth + j] = 0xff000000;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(qrWidth, qrHeight, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, qrWidth, 0, 0, qrWidth, qrHeight);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成纯色图片
     *
     * @param qrWidth,  width for QR code image.
     * @param qrHeight, height for QR code image.
     * @return Bitmap for success, null for Exception.
     * @
     */
    public static Bitmap generateOneColorPicture(int qrWidth, int qrHeight) {
        int[] pixels = new int[qrHeight * qrWidth];
        int rgbColor = Color.parseColor(randomRBG());
        Log.d(TAG, "rgbColor = " + rgbColor);
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = rgbColor;
        }
        Bitmap bitmap = Bitmap.createBitmap(qrWidth, qrHeight, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, qrWidth, 0, 0, qrWidth, qrHeight);
        return bitmap;
    }

    /**
     * 将Bitmap转换成Jpg
     *
     * @param targetDir, Directory of new jpg, will be created if it's not exist.
     * @param fileName,  name of new jpg
     * @param bitmap,    source which shall be generated new jpg.
     */
    public static boolean saveBitmapAsJpg(String targetDir, String fileName, Bitmap bitmap) {

        File dirFile = new File(targetDir);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(targetDir + fileName);

        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 随机生成6位16进制数，组成RGB颜色值
     */
    private static String randomRBG() {
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;

        String result = "#" + r + g + b;
        Log.d(TAG, "RGB random color = " + result);
        return result;
    }

    public static String getDefaultDir() {
        String dir = Environment.getExternalStorageDirectory() + "/WoAiChenDaBao/";
        Log.d(TAG, "getDefaultDir = " + dir);
        return dir;
    }
}
