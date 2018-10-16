//package com.linet.mvp.images;
//
///**
// * Created by linet on 2017/8/22.
// */
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.drawable.Drawable;
//import android.net.Uri;
//import android.support.annotation.DrawableRes;
//import android.support.annotation.Nullable;
//import android.widget.ImageView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.DataSource;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.load.engine.GlideException;
//import com.bumptech.glide.request.RequestListener;
//import com.bumptech.glide.request.RequestOptions;
//import com.bumptech.glide.request.target.Target;
//import com.resourcestorelib.ImageHelper;
//import com.soyute.mvp.R;
//
//import timber.log.Timber;
//
///**
// * Class works as our image loading library wrapper. Allows for easier testing and replacement of the image loading library.
// */
//public class ImageLoader {
//
//    private final Context context;
//
//    public ImageLoader(Context context) {
//
//        this.context = context;
//    }
//
//    public void loadImage(String imageUri, ImageView viewToLoadImageIn) {
//
//        Timber.d("Fetching image from uri: %s", imageUri);
//        Uri uri = Uri.parse(imageUri);
////        Glide.with(context)
////                .load(uri)
////                .fitCenter()
////                .into(viewToLoadImageIn);
//    }
//
//    /**
//     * 默认加载图片的方法
//     *
//     * @param context
//     * @param uri
//     * @param imageView
//     */
//    public static void displayImage(Context context, String uri, ImageView imageView) {
//        displayImage0(context, uri, imageView);
//    }
//
//    /**
//     * 加载头像的方法，有默认的头像
//     *
//     * @param context
//     * @param uri
//     * @param imageView
//     */
//    public static void displayHeadImage(Context context, String uri, ImageView imageView) {
//        displayImage(context, uri
//                , imageView
//                , R.drawable.icon_head_defualt
//                , R.drawable.icon_head_defualt
//                , R.drawable.icon_head_defualt
//        );
//    }
//
//    /**
//     * 加载本地图片的方法
//     *
//     * @param context
//     * @param uri
//     * @param imageView
//     */
//    public static void displayLocalImage(Context context, String uri, ImageView imageView) {
//        displayLocalImage0(context, uri, imageView);
//    }
//
//    /**
//     * 自定义loading图片的加载方法
//     *
//     * @param context
//     * @param uri
//     * @param imageView
//     * @param loadingDrawable
//     */
//    public static void displayImage(Context context, String uri, ImageView imageView
//            , @DrawableRes int loadingDrawable) {
//        displayImage0(context, uri, imageView
//                , getOption(loadingDrawable));
//    }
//
//    /**
//     * 自定义loading、error、null图片的加载方法
//     *
//     * @param context
//     * @param uri
//     * @param imageView
//     * @param loadingDrawable
//     */
//    public static void displayImage(Context context, String uri, ImageView imageView
//            , @DrawableRes int loadingDrawable
//            , @DrawableRes int errorDrawable
//            , @DrawableRes int nullDrawable) {
//        displayImage0(context, uri
//                , imageView, getOption(loadingDrawable, errorDrawable, nullDrawable));
//    }
//
//    /**
//     * 使用此方法请自定义一个接口，将数据返回
//     */
//    public static void displayImageWithLoadingListener(Context context, String uri
//            , ImageView imageView) {
//        displayImage0(context, uri, imageView
//                , new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e
//                            , Object model, Target<Drawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model
//                            , Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        return false;
//                    }
//                });
//    }
//
//    /**
//     * 使用此方法请自定义一个接口，将数据返回
//     */
//    public static void displayImageWithLoadingListener1(Context context, String uri
//            , ImageView imageView) {
//        displayImage0(context, ImageHelper.addImageDomain(uri)
//                , imageView, getDefualtOption(), new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model
//                            , Target<Drawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model
//                            , Target<Drawable> target, DataSource dataSource
//                            , boolean isFirstResource) {
//                        return false;
//                    }
//                });
//    }
//
//    public static void displayImageWithLoadBitmap(Context context, String uri, ImageView imageView, ImageLoaderListener listener) {
//        Timber.d("-------------->ImageHelper.addImageDomain(uri)=" + ImageHelper.addImageDomain(uri));
//        try {
//            Glide.with(context)
//                    .asBitmap()
//                    .listener(new RequestListener<Bitmap>() {
//                        @Override
//                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
//                            if (listener != null) {
//                                listener.onFail(e, uri, imageView);
//                            }
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
//                            if (listener != null) {
//                                listener.onComplete(resource, uri, imageView);
//                            }
//                            return false;
//                        }
//                    })
//                    .load(ImageHelper.addImageDomain(uri))
//                    .apply(getDefualtOption())
//                    .into(imageView);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void displayImage0(Context context, String uri, ImageView imageView) {
//        Timber.d("-------------->ImageHelper.addImageDomain(uri)=" + ImageHelper.addImageDomain(uri));
//        try {
//            Glide.with(context)
//                    .load(ImageHelper.addImageDomain(uri))
//                    .apply(getDefualtOption())
//                    .into(imageView);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private static void displayLocalImage0(Context context, String uri, ImageView imageView) {
//        try {
//            Glide.with(context)
//                    .load(ImageHelper.addLocalFileFlag(uri))
//                    .apply(getDefualtOption())
//                    .into(imageView);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void displayImage0(Context context, String uri, ImageView imageView
//            , RequestOptions options) {
//        try {
//            Glide.with(context)
//                    .load(ImageHelper.addImageDomain(uri))
//                    .apply(options)
//                    .into(imageView);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private static void displayImage0(Context context, String uri
//            , ImageView imageView, RequestListener<Drawable> loadingListener) {
//        try {
//            Glide.with(context)
//                    .load(ImageHelper.addImageDomain(uri))
//                    .listener(loadingListener)
//                    .into(imageView);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void displayImage0(Context context, String uri
//            , ImageView imageView, RequestOptions options
//            , RequestListener<Drawable> loadingListener) {
//        try {
//            Glide.with(context)
//                    .load(uri)
//                    .apply(options)
//                    .listener(loadingListener)
//                    .into(imageView);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 默认加载显示
//     * 此方法d等ui确定后还需要添加默认的loading、error、null图片
//     *
//     * @return
//     */
//    public static RequestOptions getDefualtOption() {
//        RequestOptions options = new RequestOptions()
////                .centerCrop()
//                .placeholder(R.drawable.icon_img_defualt)//加载中默认图片
//                .error(R.drawable.icon_img_defualt)//  加载错误默认图片
//                .fallback(R.drawable.icon_img_defualt)//请求url/model为空
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
////                .skipMemoryCache(true)//内存缓存
//                ;
//        return options;
//    }
//
//    /**
//     * 自定义默认的loading图片
//     * 此方法d等ui确定后还需要添加默认的error、null图片
//     *
//     * @param loadingDrawable
//     * @return
//     */
//    public static RequestOptions getOption(@DrawableRes int loadingDrawable) {
//        RequestOptions options = new RequestOptions()
//                .centerCrop()
//                .placeholder(loadingDrawable)//加载中默认图片
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .skipMemoryCache(true);//内存缓存
//        return options;
//    }
//
//    /**
//     * 自定义默认的loading、error、null图片
//     *
//     * @param loadingDrawable
//     * @return
//     */
//    private static RequestOptions getOption(
//            @DrawableRes int loadingDrawable
//            , @DrawableRes int errorDrawable
//            , @DrawableRes int nullDrawable) {
////        DiskCacheStrategy.ALL 使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE来缓存本地数据。
////        DiskCacheStrategy.NONE 不使用磁盘缓存
////        DiskCacheStrategy.DATA 在资源解码前就将原始数据写入磁盘缓存
////        DiskCacheStrategy.RESOURCE 在资源解码后将数据写入磁盘缓存，即经过缩放等转换后的图片资源。
////        DiskCacheStrategy.AUTOMATIC 根据原始图片数据和资源编码策略来自动选择磁盘缓存策略。
//        RequestOptions options = new RequestOptions()
//                .centerCrop()
//                .placeholder(loadingDrawable)//加载中默认图片
//                .error(errorDrawable)//  加载错误默认图片
//                .fallback(nullDrawable)//请求url/model为空
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
////                .skipMemoryCache(true)//内存缓存
//                ;
//        return options;
//    }
//
//    public interface ImageLoaderListener {
//
//        /**
//         * 完成加载图片
//         *
//         * @param resource     加载回来的bitmap
//         * @param srcUri       加载时的图片源地址
//         * @param srcImageView 加载时的图片控件
//         */
//        void onComplete(Bitmap resource, String srcUri, ImageView srcImageView);
//
//        /**
//         * 加载图片失败
//         *
//         * @param e
//         * @param srcUri       加载回来的bitmap
//         * @param srcImageView 加载时的图片源地址
//         */
//        void onFail(Exception e, String srcUri, ImageView srcImageView);
//
//    }
//
//
//}
