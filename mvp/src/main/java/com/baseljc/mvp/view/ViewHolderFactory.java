package com.baseljc.mvp.view;

import android.view.View;

/**
 * Created by linet on 2017/8/21.
 */

public interface ViewHolderFactory <VIEW_HOLDER> {

    VIEW_HOLDER createViewHolder(View view);
}
