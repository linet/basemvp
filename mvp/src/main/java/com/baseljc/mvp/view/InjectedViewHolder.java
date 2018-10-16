package com.baseljc.mvp.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by linet on 2017/8/21.
 */

public class InjectedViewHolder extends RecyclerView.ViewHolder {

    public InjectedViewHolder(View itemView) {

        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
