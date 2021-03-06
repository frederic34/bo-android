package org.blitzortung.android.app

import android.app.Dialog
import android.content.Context
import android.support.v7.widget.PopupMenu
import android.util.Log
import android.view.MenuItem
import android.view.View
import org.blitzortung.android.app.components.VersionComponent
import org.blitzortung.android.dialogs.AlertDialog
import org.blitzortung.android.dialogs.AlertDialogColorHandler
import org.blitzortung.android.dialogs.InfoDialog
import org.blitzortung.android.dialogs.LogDialog
import org.jetbrains.anko.startActivity

class MainPopupMenu(private val context: Context, anchor: View) : PopupMenu(context, anchor) {

    init {
        setOnMenuItemClickListener(ClickListener(context))
    }

    class ClickListener(private val context: Context) : OnMenuItemClickListener {
        override fun onMenuItemClick(item: MenuItem?): Boolean {
            val versionComponent = VersionComponent(context)
            if (item?.itemId == R.id.menu_preferences) {
                context.startActivity<Preferences>()
            } else {
                val dialog = when (item?.itemId) {
                    R.id.menu_info -> InfoDialog(context, versionComponent)

                    R.id.menu_alarms -> AlertDialog(context, AppService.instance, AlertDialogColorHandler(BOApplication.sharedPreferences))

                    R.id.menu_log -> LogDialog(context, versionComponent)

                    else -> null
                }

                if (dialog is Dialog) {
                    dialog.show()
                } else {
                    return false
                }
            }

            return true
        }
    }

    fun showPopupMenu() {
        inflate(R.menu.main_menu)
        show()
        Log.v(Main.LOG_TAG, "MainPopupMenu.showPopupMenu()")
    }
}
