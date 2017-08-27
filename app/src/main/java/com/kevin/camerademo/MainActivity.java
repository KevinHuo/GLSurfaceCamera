package com.kevin.camerademo;

import android.hardware.Camera;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private GLPreviewSurface mGLPreviewSurface;
    private PreviewRenderer mPreviewRenderer;
    private Camera mCamera;
    private PermissionChecker mPermissionChecker = new PermissionChecker(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean isPermissionOK = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || mPermissionChecker.checkPermission();
        if (!isPermissionOK) {
            return;
        }


        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.PreviewFrameLayout);

        mCamera = Utils.getCameraInstance();
        mGLPreviewSurface = new GLPreviewSurface(this,mCamera);
        mPreviewRenderer = new PreviewRenderer(mCamera,mGLPreviewSurface);
        mGLPreviewSurface.setEGLContextClientVersion(2);
        mGLPreviewSurface.setRenderer(mPreviewRenderer);
        mGLPreviewSurface.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        frameLayout.addView(mGLPreviewSurface);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLPreviewSurface.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLPreviewSurface.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mCamera.release();
        mCamera = null;
    }
}
