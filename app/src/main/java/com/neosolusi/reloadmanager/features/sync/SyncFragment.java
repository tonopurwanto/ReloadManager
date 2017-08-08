package com.neosolusi.reloadmanager.features.sync;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class SyncFragment extends Fragment implements SyncContract.View
{
    public static final String TAG = "SyncFragment";

    @BindView(R.id.downloadStatus) TextView mDownloadStatus;
    @BindView(R.id.downloadProgress) ProgressBar mDownloadProgress;

    @Inject SyncContract.Presenter mPresenter;

    public SyncFragment()
    {
        // Required empty public constructor
    }

    public static SyncFragment getInstance()
    {
        return new SyncFragment();
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ((ReloadManager) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_sync, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override public void onResume()
    {
        super.onResume();

        mPresenter.setView(this);
    }

    @Override public void onPause()
    {
        mPresenter.unsetView();

        super.onPause();
    }

    @Override public void showMessage(@NonNull String message)
    {
        mDownloadStatus.setText(message);
    }

    @Override public void showSyncFailed(@NonNull String message)
    {
        new AlertDialog.Builder(getActivity())
                .setTitle("Download Failed")
                .setIcon(ActivityCompat.getDrawable(getActivity(), R.drawable.ic_error_outline_red_700_24dp))
                .setMessage(message)
                .show();
    }

    @Override public void showSyncFinish()
    {
        Timber.d("Synchronize success, opening Main Menu");

        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
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

    public void setPresenter(SyncContract.Presenter presenter)
    {
        this.mPresenter = presenter;
    }
}
