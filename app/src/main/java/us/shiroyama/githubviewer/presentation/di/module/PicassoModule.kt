package us.shiroyama.githubviewer.presentation.di.module

import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

/**
 * A [Module] for [Picasso]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
@Module
class PicassoModule {
    @Provides
    fun providesPicasso(): Picasso {
        return Picasso.get()
    }
}