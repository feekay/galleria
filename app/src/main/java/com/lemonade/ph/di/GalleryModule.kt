package com.lemonade.ph.di

import com.lemonade.ph.repository.DefaultGalleryRepository
import com.lemonade.ph.repository.GalleryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class GalleryModule {
    @Binds
    abstract fun bindGalleryRepo(impl: DefaultGalleryRepository): GalleryRepository

}