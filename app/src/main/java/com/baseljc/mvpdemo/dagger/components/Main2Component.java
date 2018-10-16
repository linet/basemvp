package com.baseljc.mvpdemo.dagger.components;


import com.baseljc.mvp.dagger.components.ApplicationComponent;
import com.baseljc.mvp.dagger.components.BaseActivityComponent;
import com.baseljc.mvp.dagger.scopes.ActivityScope;
import com.baseljc.mvpdemo.MainActivity2;
import com.baseljc.mvpdemo.dagger.modules.MainModule2;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = MainModule2.class)
public interface Main2Component extends BaseActivityComponent<MainActivity2> {
}
