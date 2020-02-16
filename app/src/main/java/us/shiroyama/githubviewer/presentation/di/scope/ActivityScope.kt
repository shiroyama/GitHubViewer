package us.shiroyama.githubviewer.presentation.di.scope

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Activity [Scope]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class ActivityScope