package com.hoodlums.linkbucket.core.di

import android.content.Context
import com.hoodlums.linkbucket.data.local.SharedPreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalInjector {
    @Singleton
    @Provides
    fun provideSharedPrefs(@ApplicationContext context: Context) = SharedPreferenceManager(context)
}