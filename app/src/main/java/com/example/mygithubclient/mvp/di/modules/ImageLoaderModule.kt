package com.example.mygithubclient.mvp.di.modules

import android.widget.ImageView
import com.example.mygithubclient.mvp.model.image.IImageLoader
import com.example.mygithubclient.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageLoaderModule {

    @Singleton
    @Provides
    fun imageLoad(): IImageLoader<ImageView> = GlideImageLoader()

}