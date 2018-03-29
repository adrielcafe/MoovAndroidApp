package cafe.adriel.moov.view.activity;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import cafe.adriel.moov.R;
import cafe.adriel.moov.view.activity.resource.ElapsedTimeIdlingResource;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

	@Rule
	public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

	@Test
	public void mainActivityTest() {
		// Click on first movie
		IdlingResource mainIdlingResource = registerIdlingResource(1);
		onView(nthChildOf(withId(R.id.vMovies), 0))
				.perform(click());

		// Open MovieDetailActivity
		onView(withId(R.id.vMore))
				.perform(click());

		// Back to MainActivity
		pressBack();

		// Open SearchActivity
		onView(withId(R.id.action_search))
				.perform(click())
				.perform(click());

		// Search for a movie
		onView(withId(android.support.design.R.id.search_src_text))
				.perform(typeText("john wick"))
				.perform(pressImeActionButton());

		// Click on first movie
		IdlingResource searchIdlingResource = registerIdlingResource(3);
		onView(nthChildOf(withId(R.id.vMovies), 0))
				.perform(click());

		// Back to MainActivity
		pressBack();

		// Clean up
		Espresso.unregisterIdlingResources(mainIdlingResource);
		Espresso.unregisterIdlingResources(searchIdlingResource);
	}

	private IdlingResource registerIdlingResource(long seconds){
		IdlingResource idlingResource = new ElapsedTimeIdlingResource(TimeUnit.SECONDS.toMillis(seconds));
		Espresso.registerIdlingResources(idlingResource);
		return idlingResource;
	}

	private Matcher<View> nthChildOf(final Matcher<View> parentMatcher, final int childPosition) {
		return new TypeSafeMatcher<View>() {
			@Override
			public void describeTo(Description description) {
				description.appendText("with " + childPosition + " child view of type parentMatcher");
			}
			@Override
			public boolean matchesSafely(View view) {
				if (!(view.getParent() instanceof ViewGroup)) {
					return parentMatcher.matches(view.getParent());
				}
				ViewGroup group = (ViewGroup) view.getParent();
				return parentMatcher.matches(view.getParent()) && group.getChildAt(childPosition).equals(view);
			}
		};
	}

}
