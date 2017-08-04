package com.neosolusi.reloadmanager.features.sync;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.neosolusi.reloadmanager.R;
import com.neosolusi.reloadmanager.ReloadManager;
import com.neosolusi.reloadmanager.features.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class SyncActivity extends AppCompatActivity implements SyncContract.View
{
    @Inject SyncContract.Presenter mPresenter;

    @BindView(R.id.downloadStatus) TextView mDownloadStatus;
    @BindView(R.id.downloadProgress) ProgressBar mDownloadProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);

        ButterKnife.bind(this);
        ((ReloadManager) getApplication()).getComponent().inject(this);
    }

    @Override public void showMessage(String message)
    {
        mDownloadStatus.setText(message);
    }

    @Override public void showSyncFailed(String message)
    {
        new AlertDialog.Builder(this)
                .setTitle("Download Failed")
                .setIcon(ActivityCompat.getDrawable(this, R.drawable.ic_error_outline_red_700_24dp))
                .setMessage(message)
                .show();
    }

    @Override public void showSyncFinish()
    {
        Timber.d("Synchronize success, opening Main Menu");

        startActivity(new Intent(SyncActivity.this, MainActivity.class));
        finish();
    }

    @Override public void changeProgress(final int recordCount)
    {
        mDownloadProgress.setIndeterminate(false);
        mDownloadProgress.setMax(recordCount);
        mDownloadProgress.setProgress(0);
        mDownloadProgress.setInterpolator(new DecelerateInterpolator());
        mDownloadProgress.post(new Runnable()
        {
            @Override public void run()
            {
                for (int i = 0; i <= recordCount; i++) {
                    mDownloadProgress.setProgress(i);
                }
            }
        });
    }

    @Override public void resetProgress()
    {
        mDownloadProgress.setIndeterminate(true);
        mDownloadProgress.setMax(0);
        mDownloadProgress.setProgress(0);
    }

    @Override protected void onResume()
    {
        super.onResume();

        mPresenter.attachView(this);
        mPresenter.download();
    }

    @Override protected void onPause()
    {
        mPresenter.detachView();

        super.onPause();
    }
}
