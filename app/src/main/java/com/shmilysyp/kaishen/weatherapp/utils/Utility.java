package com.shmilysyp.kaishen.weatherapp.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.shmilysyp.kaishen.weatherapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import static com.shmilysyp.kaishen.weatherapp.WeatherApp.getApp;

/**
 * Created by kaishen on 16/10/27.
 */

public class Utility {

    public final static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public final static SimpleDateFormat sFormat_0 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    public final static SimpleDateFormat sFormat_1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
    public final static SimpleDateFormat sFormat_2 = new SimpleDateFormat("yyyy-MM-dd");

    public static String timeToStr(long date) {
        return sFormat.format(new Date(date));
    }

    public static String timeToStr(Date date, SimpleDateFormat format) {
        return format.format(date);
    }

    public static int dip2px(float dipValue) {
        float reSize = getApp().getResources().getDisplayMetrics().density;
        return (int) ((dipValue * reSize) + 0.5);
    }

    /**
     * 获取某个dp的资源对应的pix值
     */
    public static int pxOfDpResId(int dpResId) {
        try {
            return getApp().getResources().getDimensionPixelSize(dpResId);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int px2dip(int pxValue) {
        float reSize = getApp().getResources().getDisplayMetrics().density;
        return (int) ((pxValue / reSize) + 0.5);
    }

    public static float sp2px(int spValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue,
                getApp().getResources().getDisplayMetrics());
    }

    /**
     * 判断SD卡是否可用
     */
    public static boolean isSdCardOK() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡跟路径。SD卡不可用时，返回null
     */
    public static String getSdCardRoot() {
        if (isSdCardOK()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }

        return null;
    }

    /**
     * 获取字符串中某个字符串出现的次数。
     */
    public static int countMatches(String res, String findString) {
        if (res == null) {
            return 0;
        }

        if (findString == null || findString.length() == 0) {
            throw new IllegalArgumentException("The param findString cannot be null or 0 length.");
        }

        return (res.length() - res.replace(findString, "").length()) / findString.length();
    }

    /**
     * 判断该文件是否是一个图片。
     */
    public static boolean isImage(String fileName) {
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png");
    }

    /**
     * 判断该文件是否是一个图片。
     */
    public static boolean isVideo(String fileName) {
        return fileName.endsWith(".mp4");
    }


    private static String mTTID;

//        /**
//         * 获取ttid版本信息。
//         */
//        public static String getTTID() {
//            if (TextUtils.isEmpty(mTTID)) {
//                try {
//                    PackageInfo pInfo = getApp()
//                            .getPackageManager()
//                            .getPackageInfo(getApp().getPackageName(), 0);
//                    Integer version = pInfo.versionCode;
//
//                    // 1-10001-bigtoy_android_wandoujia_1000909
//                    //格式：channelCode-channelType-appName_android_appName_channelName
//                    //新格式android_mhc_1050206
//                    mTTID = String.format("android_%s_%s", BentleyApp.getApp().getChannelName(), version);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            return mTTID;
//
//        }

    /**
     * 获取app_info文件。
     */
    public static Properties getAppInfo() {
        Context context = getApp();
        AssetManager assetManager = context.getAssets();
        Properties properties = new Properties();
        try {
            InputStream inputStream = assetManager.open("app_info");
            properties.load(inputStream);
        } catch (IOException e) {
        }
        return properties;
    }

    /**
     * 获取get version name。
     */
    public static String getVersionName() {
        try {
            PackageInfo pInfo = getApp()
                    .getPackageManager()
                    .getPackageInfo(getApp().getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    private static String VALIDATE_KEY = "henhuanghenbaoli";

    /**
     * 获取URL 请求加密串
     */
    public static Map getSalt() {
        String time = String.valueOf(new Date().getTime());
        String value = md5(time + VALIDATE_KEY);
        Map<String, String> map = new HashMap<>();
        map.put("time", time);
        map.put("salt", value);
        return map;
    }

    /**
     * 字符串 md5。
     */
    public static String md5(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            StringBuilder hex = new StringBuilder(bytes.length * 2);
            for (byte b : bytes) {
                int i = (b & 0xFF);
                if (i < 0x10) hex.append('0');
                hex.append(Integer.toHexString(i));
            }
            return hex.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 安装apk
     */
    public static void installApp(Context context, String filePath) {
        LogUtil.e("installApp filePath = " + filePath);
        if (TextUtils.isEmpty(filePath)) return;
        File file = new File(filePath);
        if (!file.exists()) return;
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        context.startActivity(intent);

    }

    public static PackageInfo getApkInfo(Context context, String filePath) {
        PackageManager pm = context.getPackageManager();
        return pm.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
    }

    public static boolean isEditTextEmpty(EditText editText) {
        if (editText == null || editText.getText() == null) return true;
        String content = editText.getText().toString();
        return TextUtils.isEmpty(content);
    }

    public static Bitmap getWholeListViewItemsToBitmap(ListView listview) {

        ListAdapter adapter = listview.getAdapter();
        int itemsCount = adapter.getCount();
        int allItemsHeight = 0;
        List<Bitmap> bps = new ArrayList<>();

        for (int i = 0; i < itemsCount; i++) {
            View childView = adapter.getView(i, null, listview);
            childView.measure(View.MeasureSpec.makeMeasureSpec(listview.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
            childView.setDrawingCacheEnabled(true);
            childView.buildDrawingCache();
            bps.add(childView.getDrawingCache());
            allItemsHeight += childView.getMeasuredHeight();
        }

        Bitmap bigBitmap = Bitmap.createBitmap(listview.getMeasuredWidth(), allItemsHeight, Bitmap.Config.ARGB_8888);
        Canvas bigCanvas = new Canvas(bigBitmap);

        Paint paint = new Paint();
        int iHeight = 0;

        for (int i = 0; i < bps.size(); i++) {
            Bitmap bmp = bps.get(i);
            bigCanvas.drawBitmap(bmp, 0, iHeight, paint);
            iHeight += bmp.getHeight();
            bmp.recycle();
        }
        return bigBitmap;
    }

    public static void saveBitmapToFile(Bitmap bitmap, String filePath) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void hideKeyboard(Context context, EditText editText) {
        if (editText != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }


//        public static void showKeyboard(Context context, EditText editText) {
//            editText.setSelection(editText.length());
//            editText.postDelayed(() -> {
//                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
//            }, 300);
//        }


    /**
     * 跳转拨号界面
     */
    public static void callDial(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

//        /**
//         * 计时一天
//         */
//        public static boolean isAfterDay(String startTime) {
//            if (startTime == null) return true;
//            int diffHour = DateUtil.getDiffHour(startTime, DateUtil.getCurrentTime());
//            if (diffHour < 0) diffHour = 0 - diffHour;
//            return diffHour >= 24;
//        }


    public static void showPhoneDpi(Context context) {
        float xdpi = context.getResources().getDisplayMetrics().xdpi;

        if (xdpi > 0 && xdpi < 120) {
            Log.i("dpi", "you phone is ldpi");
        } else if (xdpi > 120 && xdpi < 160) {
            Log.i("dpi", "you phone is mdpi");
        } else if (xdpi > 160 && xdpi < 240) {
            Log.i("dpi", "you phone is hdpi");
        } else if (xdpi > 240 && xdpi < 320) {
            Log.i("dpi", "you phone is xdpi");
        } else if (xdpi > 320 && xdpi < 480) {
            Log.i("dpi", "you phone is xhdpi");
        } else if (xdpi > 480 && xdpi < 640) {
            Log.i("dpi", "you phone is xxhdpi");
        }
    }

//        /**
//         * 为编辑框和删除按钮设置关联
//         */
//        public static void setDeleteBtn(Context context, EditText edit, ImageView btn) {
//            edit.addTextChangedListener(new TextWatcherAdapter() {
//                @Override
//                public void afterTextChanged(Editable s) {
//                    if (edit.hasFocus() && StringUtil.isNotEmpty(s.toString())) {
//                        btn.setVisibility(View.VISIBLE);
//                    } else {
//                        btn.setVisibility(View.INVISIBLE);
//                    }
//                }
//            });
//
//            edit.setOnFocusChangeListener((v1, hasFocus) -> {
//                if (hasFocus && !StringUtil.isEmpty(edit.getText().toString())) {
//                    btn.setVisibility(View.VISIBLE);
//                } else {
//                    btn.setVisibility(View.GONE);
//                }
//            });
//            btn.setOnClickListener(v -> {
//                edit.setText("");
//                edit.requestFocus();
//                showKeyboard(context, edit);
//            });
//        }

//        public static void requestKeyBoard(Context context, EditText edit) {
//            edit.requestFocus();
//            showKeyboard(context, edit);
//            edit.setSelection(edit.length());
//        }

    /**
     * 判断app是否在前台运行
     */
    public static boolean isAppOnForeground() {

        ActivityManager activityManager =
                (ActivityManager) getApp().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApp().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }

    /**
     * 正浮点型判断
     */
    public static boolean isPositiveDecimal(String str) {
        if (str == null || "".equals(str))
            return false;
        Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 浮点型判断
     */
    public static boolean isDecimal(String str) {
        if (str == null || "".equals(str))
            return false;
        Pattern pattern = Pattern.compile("-?[0-9]*(\\.?)[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 高精度减法计算
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        String md5 = bigInt.toString(16);
        //不满足32位补0
        if (md5.length() < 32) {
            for (int i = 0; i < 32 - md5.length(); i++) {
                md5 = "0" + md5;
            }
        }
        return md5;
    }

    /**
     * drawable转成bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 将bitmap保存到系统图库
     */
    @SuppressLint("SimpleDateFormat")
    public static void saveImageToGallery(Context context, Bitmap bmp) {

        File picDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!picDir.exists()) {
            if (picDir.mkdir()) {
                LogUtil.i("picDir创建成功");
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "IMAGE_" + timeStamp + ".jpg";
        // 首先保存图片
        File file = new File(picDir.getPath() + File.separator + fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        ContentValues values = new ContentValues();

        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.MediaColumns.DATA, file.getAbsolutePath());

        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
    }

    /**
     * 短信发送
     */
    public static void smsSingle(Context context, String content, String number) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("sms_body", content);
        Uri uri = Uri.parse("smsto:" + number);
        intent.setData(uri);
        context.startActivity(intent);
    }

    /**
     * 短信发送(群发)
     */
    public static void smsGroup(Context context, String content, List<String> numbers) {
        StringBuilder numbersStr = new StringBuilder();
        for (String number : numbers) {
            numbersStr.append(number + ";");
        }
        smsSingle(context, content, numbersStr.toString());
    }

    /**
     * 控制右侧的箭头显示隐藏
     */
    public static void setRightArrow(TextView tv, boolean visible) {
        if (visible) {
            tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_right_arrow, 0);
        } else {
            tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
    }


    /**
     * ScrollView嵌套listview 显示不全的问题,计算listview item的高度
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     * 动态设置窗口全屏
     *
     * @param activity
     * @param fullScreen
     */
    public static void setWindowFullScreen(Activity activity, boolean fullScreen) {
        if (activity == null || activity.getWindow() == null) {
            return;
        }
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        if (fullScreen) {
            params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            params.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
//            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
//            params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            params.flags |= WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN;
            params.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        activity.getWindow().setAttributes(params);


    }
}
