package com.ai.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.adl.base.common.AdlToast;
import com.adl.ts.general.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: AiPowerCoreSpecial
 * Author     : 南山
 * Date       : 2023/11/7
 * Describe   : 类描述
 */
public class FileAdapter extends BaseAdapter {

    private List<File> files = new ArrayList<>();
    private LayoutInflater inflate;
    private OnFileCallback callback;

    public FileAdapter(Context context, OnFileCallback callback) {
        this.inflate = LayoutInflater.from(context);
        this.callback = callback;
    }

    public void loadData(List<File> list) {
        if (list == null) return;
        files.clear();
        files.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return files.size();
    }

    @Override
    public File getItem(int position) {
        return files.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemHolder holder = null;
        if (convertView == null) {
            convertView = inflate.inflate(R.layout.item_file, null);
            holder = new ItemHolder();
            holder.tvName = convertView.findViewById(R.id.item_name);
            convertView.setTag(holder);
        } else {
            holder = (ItemHolder) convertView.getTag();
        }

        File file = getItem(position);
        holder.tvName.setText((position + 1) + ". " + file.getName());
        convertView.setOnClickListener(v -> {
            if (callback != null) {
                callback.onClickFile(file);
            }
            AdlToast.show("打开文件: " + file.getName());
        });

        return convertView;
    }

    private class ItemHolder {
        TextView tvName;
    }

    public interface OnFileCallback {

        void onClickFile(File file);
    }
}
