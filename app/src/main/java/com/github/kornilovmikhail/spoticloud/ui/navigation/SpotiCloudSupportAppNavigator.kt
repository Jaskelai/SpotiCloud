package com.github.kornilovmikhail.spoticloud.ui.navigation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Replace
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import java.util.*

class SpotiCloudSupportAppNavigator(
    private val activity: FragmentActivity?,
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) : Navigator {

    private var localStackCopy: LinkedList<String?>? = null

    constructor(activity: FragmentActivity, containerId: Int) : this(
        activity,
        activity.supportFragmentManager,
        containerId
    )

    override fun applyCommands(commands: Array<Command>) {
        fragmentManager.executePendingTransactions()

        copyStackToLocal()

        for (command in commands) {
            applyCommand(command)
        }
    }

    private fun copyStackToLocal() {
        localStackCopy = LinkedList()

        val stackSize = fragmentManager.backStackEntryCount
        for (i in 0 until stackSize) {
            localStackCopy?.add(fragmentManager.getBackStackEntryAt(i).name)
        }
    }

    private fun applyCommand(command: Command) {
        when (command) {
            is Forward -> activityForward(command)
            is Replace -> activityReplace(command)
            is BackTo -> backTo(command)
            is Back -> fragmentBack()
            is ReplaceForContainer -> replaceForContainer(command)
        }
    }

    private fun activityForward(command: Forward) {
        val screen = command.screen as SupportAppScreen
        val activityIntent = screen.getActivityIntent(activity)

        if (activityIntent != null) {
            val options = createStartActivityOptions(command, activityIntent)
            checkAndStartActivity(screen, activityIntent, options)
        } else {
            fragmentForward(command)
        }
    }

    private fun fragmentForward(command: Forward) {
        val screen = command.screen as SupportAppScreen
        val fragment = createFragment(screen)

        val fragmentTransaction = fragmentManager.beginTransaction()

        setupFragmentTransaction(
            command,
            fragmentManager.findFragmentById(containerId),
            fragment,
            fragmentTransaction
        )

        fragmentTransaction
            .replace(containerId, fragment!!)
            .addToBackStack(screen.screenKey)
            .commit()
        localStackCopy?.add(screen.screenKey)
    }

    private fun fragmentBack() {
        localStackCopy?.let {
            if (it.size > 0) {
                fragmentManager.popBackStack()
                it.removeLast()
            } else {
                activityBack()
            }
        }
    }

    private fun activityBack() {
        activity?.finish()
    }

    private fun activityReplace(command: Replace) {
        val screen = command.screen as SupportAppScreen
        val activityIntent = screen.getActivityIntent(activity)

        // Replace activity
        if (activityIntent != null) {
            val options = createStartActivityOptions(command, activityIntent)
            checkAndStartActivity(screen, activityIntent, options)
            activity?.finish()
        } else {
            fragmentReplace(command)
        }
    }

    private fun fragmentReplace(command: Replace) {
        val screen = command.screen as SupportAppScreen
        val fragment = createFragment(screen)

        if (localStackCopy?.size!! > 0) {
            fragmentManager.popBackStack()
            localStackCopy?.removeLast()

            val fragmentTransaction = fragmentManager.beginTransaction()

            setupFragmentTransaction(
                command,
                fragmentManager.findFragmentById(containerId),
                fragment,
                fragmentTransaction
            )

            fragmentTransaction
                .replace(containerId, fragment!!)
                .addToBackStack(screen.screenKey)
                .commit()
            localStackCopy?.add(screen.screenKey)

        } else {
            val fragmentTransaction = fragmentManager.beginTransaction()

            setupFragmentTransaction(
                command,
                fragmentManager.findFragmentById(containerId),
                fragment,
                fragmentTransaction
            )

            fragmentTransaction
                .replace(containerId, fragment!!)
                .commit()
        }
    }

    private fun backTo(command: BackTo) {
        if (command.screen == null) {
            backToRoot()
        } else {
            val key = command.screen.screenKey
            val index = localStackCopy?.indexOf(key)
            val size = localStackCopy?.size

            if (index != -1) {
                for (i in 1 until (size?.minus(index!!) ?: 0)) {
                    localStackCopy?.removeLast()
                }
                fragmentManager.popBackStack(key, 0)
            } else {
                backToUnexisting(command.screen as SupportAppScreen)
            }
        }
    }

    private fun backToRoot() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        localStackCopy?.clear()
    }

    private fun checkAndStartActivity(
        screen: SupportAppScreen,
        activityIntent: Intent,
        options: Bundle?
    ) {
        if (activity?.packageManager?.let { activityIntent.resolveActivity(it) } != null) {
            activity.startActivity(activityIntent, options)
        } else {
            unexistingActivity(screen, activityIntent)
        }
    }

    private fun unexistingActivity(screen: SupportAppScreen, activityIntent: Intent) {
        // Do nothing by default
    }

    private fun createFragment(screen: SupportAppScreen): Fragment? {
        val fragment = screen.fragment

        if (fragment == null) {
            errorWhileCreatingScreen(screen)
        }
        return fragment
    }

    private fun backToUnexisting(screen: SupportAppScreen) {
        backToRoot()
    }

    private fun errorWhileCreatingScreen(screen: SupportAppScreen) {
        throw RuntimeException("Can't create a screen: " + screen.screenKey)
    }

    private fun setupFragmentTransaction(
        command: Command,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        fragmentTransaction: FragmentTransaction
    ) {
    }

    private fun createStartActivityOptions(command: Command, activityIntent: Intent): Bundle? = null

    private fun replaceForContainer(command: ReplaceForContainer) {
        val screen = command.getScreen() as SupportAppScreen
        val fragment = createFragment(screen)

        if (localStackCopy?.size!! > 0) {
            fragmentManager.popBackStack()
            localStackCopy?.removeLast()

            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.setPrimaryNavigationFragment(fragment)

            setupFragmentTransaction(
                command,
                fragmentManager.findFragmentById(containerId),
                fragment,
                fragmentTransaction
            )

            fragmentTransaction
                .replace(containerId, fragment!!)
                .addToBackStack(screen.screenKey)
                .commit()
            localStackCopy?.add(screen.screenKey)

        } else {

            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.setPrimaryNavigationFragment(fragment)

            setupFragmentTransaction(
                command,
                fragmentManager.findFragmentById(containerId),
                fragment,
                fragmentTransaction
            )

            fragmentTransaction
                .replace(containerId, fragment!!)
                .commit()
        }
    }
}
