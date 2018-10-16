package com.baseljc.mvpdemo.dagger.components

import com.baseljc.mvp.dagger.components.ApplicationComponent
import com.baseljc.mvp.dagger.components.BaseActivityComponent
import com.baseljc.mvp.dagger.scopes.ActivityScope
import com.baseljc.mvpdemo.MainActivity
import com.baseljc.mvpdemo.dagger.modules.MainModule
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(MainModule::class))
interface MainComponent : BaseActivityComponent<MainActivity> {

}