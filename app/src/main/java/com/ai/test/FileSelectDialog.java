package com.ai.test;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.adl.base.AdlApp;
import com.adl.base.common.AdlExecutor;
import com.adl.base.file.AdlFileHelper;
import com.adl.ts.general.R;
import com.ai.connect.DialogBase;

import java.io.File;

/**
 * ProjectName: AdlMountGeneral
 * Author     : 南山
 * Date       : 2025/5/16
 * Describe   : 类描述
 */
public class FileSelectDialog extends DialogBase {

    private OnFileSelectedCallback mCallback;
    private FileAdapter mAdapter;
    private ListView listView;
    private File curFile;

    public FileSelectDialog(Context context) {
        super(context);

        setContentView(R.layout.dialog_class_filter_layout);
        autoWidth(AdlApp.dp2Px(400));
        setCanceledOnTouchOutside(false);
        setCancelable(false);

        View.OnClickListener cancelClick = v -> {
            dismiss();
        };
        findViewById(R.id.bnt_close).setOnClickListener(cancelClick);
        findViewById(R.id.bnt_cancel).setOnClickListener(cancelClick);

        findViewById(R.id.bnt_ok).setOnClickListener(v -> {
            if (mCallback != null) {
                mCallback.onSelected(curFile);
            }
            dismiss();
        });

        listView = findViewById(R.id.list_view);
        mAdapter = new FileAdapter(context, file -> {
            curFile = file;
        });
        listView.setAdapter(mAdapter);
    }

    public FileSelectDialog setCallback(OnFileSelectedCallback callback) {
        mCallback = callback;
        return this;
    }

    public void show(String rootName) {
        show();
        if (!TextUtils.isEmpty(rootName)) {
            AdlExecutor.postUIDelayed(() -> AdlFileHelper.scanFiles(rootName, list -> {
                mAdapter.loadData(list);
            }), 200);
        }
    }

    public interface OnFileSelectedCallback {
        void onSelected(File file);
    }
}
