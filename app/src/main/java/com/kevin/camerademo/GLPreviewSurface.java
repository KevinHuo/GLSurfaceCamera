package com.kevin.camerademo;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLSurfaceView;
import android.view.SurfaceHolder;

import java.io.IOException;

/**
 * Created by kevin on 2017/8/27.
 */

public class GLPreviewSurface extends GLSurfaceView {
    private Camera mCamera;

    public GLPreviewSurface(Context context, Camera camera) {
        super(context);
        mCamera = camera;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        super.surfaceCreated(holder);

        try {
            mCamera.setPreviewTexture(new SurfaceTexture(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        super.surfaceDestroyed(holder);
        mCamera.release();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        super.surfaceChanged(holder, format, w, h);
    }
}
