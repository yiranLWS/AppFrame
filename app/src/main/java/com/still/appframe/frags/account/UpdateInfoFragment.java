package com.still.appframe.frags.account;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.still.appframe.R;
import com.still.appframe.frags.media.GalleryFragment;
import com.still.common.Contants;
import com.still.common.app.Application;
import com.still.common.app.Fragment;
import com.still.common.widget.PortraitView;
import com.still.factory.Factory;
import com.still.factory.net.UploadHelper;
import com.yalantis.ucrop.UCrop;


import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
* 跟新用户信息的界面
 */
public class UpdateInfoFragment extends Fragment {

    @BindView(R.id.im_portrait)
    PortraitView mPortrait;

    public UpdateInfoFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_update_info;
    }

    @OnClick(R.id.im_portrait)
    void onPortraitClick() {
        new GalleryFragment()
                .setListener(path -> {
                    UCrop.Options options = new UCrop.Options();
                    // 设置图片处理的格式JPEG
                    options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
                    // 设置压缩后的图片精度
                    options.setCompressionQuality(96);

                    // 得到头像的缓存地址
                    File dPath = Application.getPortraitTmpFile();

                    // 发起剪切
                    UCrop.of(Uri.fromFile(new File(path)), Uri.fromFile(dPath))
                            .withAspectRatio(1, 1) // 1比1比例
                            .withMaxResultSize(520, 520) // 返回最大的尺寸
                            .withOptions(options) // 相关参数
                            .start(getActivity());
                })
                // show 的时候建议使用getChildFragmentManager，
                // tag GalleryFragment class 名
                .show(getChildFragmentManager(), GalleryFragment.class.getName());
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 收到从Activity传递过来的回调，然后取出其中的值进行图片加载
        // 如果是我能够处理的类型
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            // 通过UCrop得到对应的Uri
            final Uri resultUri = UCrop.getOutput(data);
            if (resultUri != null) {
                loadPortrait(resultUri);
            }

        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        }
    }


    /**
     * 加载Uri到当前的头像中
     *
     * @param uri Uri
     */
    private void loadPortrait(Uri uri) {
        Glide.with(this)
                .asBitmap()
                .load(uri)
                .apply(Contants.Accountoptions)
                .into(mPortrait);


        // 拿到本地文件的地址
        final String localPath = uri.getPath();
        Log.e("TAG", "localPath:" + localPath);

        Factory.runOnAsync(new Runnable() {
            @Override
            public void run() {
                String url = UploadHelper.uploadPortrait(localPath);
                Log.e("TAG", "url:" + url);
            }
        });
    }
}
