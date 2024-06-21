package com.example.todolistapp;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAddTask() {
        // Open the dialog
        onView(withId(R.id.add)).perform(ViewActions.click());

        // Enter a task name
        onView(withId(R.id.nameEdit)).perform(ViewActions.typeText("Test Task"), ViewActions.closeSoftKeyboard());

        // Save the task
        onView(withText("SAVE")).perform(ViewActions.click());

        // Check if the task is added to the list
        onView(withText("Test Task")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Delete the task
        onView(withId(R.id.delete)).perform(ViewActions.click());

        // Check if the task is removed from the list
        onView(withText("Test Task")).check(ViewAssertions.doesNotExist());
    }


    @Test
    public void testCancelAddTask() {
        // Open the dialog
        onView(withId(R.id.add)).perform(ViewActions.click());

        // Enter a task name
        onView(withId(R.id.nameEdit)).perform(ViewActions.typeText("Cancelled Task"), ViewActions.closeSoftKeyboard());

        // Cancel the task
        onView(withText("Cancel")).perform(ViewActions.click());

        // Check if the task is not added to the list
        onView(withText("Cancelled Task")).check(ViewAssertions.doesNotExist());
    }

    @Test
    public void testAddEmptyTask() {
        // Open the dialog
        onView(withId(R.id.add)).perform(ViewActions.click());

        // Save without entering a task name
        onView(withText("SAVE")).perform(ViewActions.click());

        // Check if an empty task is not added
        onView(withText("")).check(ViewAssertions.doesNotExist());
    }

    @Test
    public void testAddDuplicateTask() {
        String taskName = "Duplicate Task";

        // Add the task twice
        for (int i = 0; i < 2; i++) {
            onView(withId(R.id.add)).perform(ViewActions.click());
            onView(withId(R.id.nameEdit)).perform(ViewActions.typeText(taskName), ViewActions.closeSoftKeyboard());
            onView(withText("SAVE")).perform(ViewActions.click());
        }

        // Check if both tasks are displayed
        onView(allOf(withId(R.id.name), withText(taskName))).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }


    @Test
    public void testDialogAppears() {
        // Open the dialog
        onView(withId(R.id.add)).perform(ViewActions.click());

        // Check if the dialog appears with correct title
        onView(withText("Enter your Task")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testDialogInputField() {
        // Open the dialog
        onView(withId(R.id.add)).perform(ViewActions.click());

        // Check if the input field is displayed
        onView(withId(R.id.nameEdit)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testDialogSaveButton() {
        // Open the dialog
        onView(withId(R.id.add)).perform(ViewActions.click());

        // Check if the SAVE button is displayed
        onView(withText("SAVE")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testDialogCancelButton() {
        // Open the dialog
        onView(withId(R.id.add)).perform(ViewActions.click());

        // Check if the Cancel button is displayed
        onView(withText("Cancel")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testDeleteButtonExists() {
        // Add a task
        onView(withId(R.id.add)).perform(ViewActions.click());
        onView(withId(R.id.nameEdit)).perform(ViewActions.typeText("Delete Test Task"), ViewActions.closeSoftKeyboard());
        onView(withText("SAVE")).perform(ViewActions.click());

        // Check if the delete button exists for the task
        onView(withId(R.id.delete)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
