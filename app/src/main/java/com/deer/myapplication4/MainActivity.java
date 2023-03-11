package com.deer.myapplication4;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.deer.utils.GlideEngine;
import com.deer.utils.MeSandboxFileEngine;
import com.luck.picture.lib.basic.PictureSelectionModel;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.config.SelectModeConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnQueryFilterListener;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;
import com.luck.picture.lib.style.PictureSelectorStyle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final static int ACTIVITY_RESULT = 1;
    private final static int CALLBACK_RESULT = 2;
    private final static int LAUNCHER_RESULT = 3;
    private int resultMode = LAUNCHER_RESULT;
    private PictureSelectorStyle selectorStyle;
    private ActivityResultLauncher<Intent> launcherResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launcherResult = createActivityResultLauncher();
        findViewById(R.id.tv_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PictureSelectionModel selectionModel = PictureSelector.create(MainActivity.this)
                        .openGallery(SelectMimeType.ofImage())
                        .setSelectorUIStyle(new PictureSelectorStyle())
                        .setImageEngine(GlideEngine.createGlideEngine())
//                        .setVideoPlayerEngine(videoPlayerEngine)
//                        .setCropEngine(getCropFileEngine())
//                        .setCompressEngine(getCompressFileEngine())
                        .setSandboxFileEngine(new MeSandboxFileEngine())
//                        .setCameraInterceptListener(getCustomCameraEvent())
//                        .setRecordAudioInterceptListener(new MeOnRecordAudioInterceptListener())
//                        .setSelectLimitTipsListener(new MeOnSelectLimitTipsListener())
//                        .setEditMediaInterceptListener(getCustomEditMediaEvent())
//                        .setPermissionDescriptionListener(getPermissionDescriptionListener())
//                        .setPreviewInterceptListener(getPreviewInterceptListener())
//                        .setPermissionDeniedListener(getPermissionDeniedListener())
//                        .setAddBitmapWatermarkListener(getAddBitmapWatermarkListener())
//                        .setVideoThumbnailListener(getVideoThumbnailEventListener())
//                        .isAutoVideoPlay(false)
//                        .isLoopAutoVideoPlay(false)
//                        .isPageSyncAlbumCount(true)
//                        .setCustomLoadingListener(getCustomLoadingListener())
                        .setQueryFilterListener(new OnQueryFilterListener() {
                            @Override
                            public boolean onFilter(LocalMedia media) {
                                return false;
                            }
                        })
                        //.setExtendLoaderEngine(getExtendLoaderEngine())
//                        .setInjectLayoutResourceListener(getInjectLayoutResource())
                        .setSelectionMode(SelectModeConfig.MULTIPLE)
//                        .setLanguage(language)
                        .setQuerySortOrder(MediaStore.MediaColumns.DATE_MODIFIED )
//                        .setOutputCameraDir(chooseMode == SelectMimeType.ofAudio()
//                                ? getSandboxAudioOutputPath() : getSandboxCameraOutputPath())
//                        .setOutputAudioDir(chooseMode == SelectMimeType.ofAudio()
//                                ? getSandboxAudioOutputPath() : getSandboxCameraOutputPath())
//                        .setQuerySandboxDir(chooseMode == SelectMimeType.ofAudio()
//                                ? getSandboxAudioOutputPath() : getSandboxCameraOutputPath())
//                        .isDisplayTimeAxis(cb_time_axis.isChecked())
                        .isOnlyObtainSandboxDir(false)
                        .isPageStrategy(false)
                        .isOriginalControl(false)
                        .isDisplayCamera(false)
                        .isOpenClickSound(false)
//                        .setSkipCropMimeType(getNotSupportCrop())
                        .isFastSlidingSelect(false)
                        //.setOutputCameraImageFileName("luck.jpeg")
                        //.setOutputCameraVideoFileName("luck.mp4")
                        .isWithSelectVideoImage(false)
                        .isPreviewFullScreenMode(false)
                        .isVideoPauseResumePlay(false)
                        .isPreviewZoomEffect(false)
                        .isPreviewImage(false)
                        .isPreviewVideo(false)
                        .isPreviewAudio(false)
                        //.setQueryOnlyMimeType(PictureMimeType.ofGIF())
                        .isMaxSelectEnabledMask(true)
                        .isDirectReturnSingle(true)
                        .setMaxSelectNum(9)
                        .setMaxVideoSelectNum(9)
//                        .setRecyclerAnimationMode(animationMode)
                        .isGif(false);
//                        .setSelectedData(mAdapter.getData());
                forSelectResult(selectionModel);
            }
        });
    }
    private void forSelectResult(PictureSelectionModel model) {
        switch (resultMode) {
            case ACTIVITY_RESULT:
                model.forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case CALLBACK_RESULT:
                model.forResult(new MeOnResultCallbackListener());
                break;
            default:
                model.forResult(launcherResult);
                break;
        }
    }
    private ActivityResultLauncher<Intent> createActivityResultLauncher() {
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        int resultCode = result.getResultCode();
                        if (resultCode == RESULT_OK) {
                            ArrayList<LocalMedia> selectList = PictureSelector.obtainSelectorList(result.getData());
//                            analyticalSelectResults(selectList);
                        } else if (resultCode == RESULT_CANCELED) {
//                            Log.i(TAG, "onActivityResult PictureSelector Cancel");
                        }
                    }
                });
    }
    /**
     * 选择结果
     */
    private class MeOnResultCallbackListener implements OnResultCallbackListener<LocalMedia> {
        @Override
        public void onResult(ArrayList<LocalMedia> result) {
//            analyticalSelectResults(result);
        }

        @Override
        public void onCancel() {
//            Log.i(TAG, "PictureSelector Cancel");
        }
    }
}