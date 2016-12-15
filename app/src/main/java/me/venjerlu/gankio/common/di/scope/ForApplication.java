package me.venjerlu.gankio.common.di.scope;

import java.lang.annotation.Retention;
import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Author/Date: venjerLu / 2016/12/15 16:53
 * Email:       alwjlola@gmail.com
 * Description:
 */
@Qualifier @Retention(RUNTIME) public @interface ForApplication {
}
