package local.takamk2.selfmanager3.view.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.EditText
import android.widget.Toast
import local.takamk2.selfmanager3.R
import timber.log.Timber

class AddTodoDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity ?: throw IllegalStateException("activity is null")
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.dialog_add_todo, null, false)
        val builder = AlertDialog.Builder(activity!!)
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            Timber.d("DEBUG:: positiveButton clicked")
            val todo_edit = view.findViewById<EditText>(R.id.todo_edit)
            val todo = todo_edit.text.toString()
            if (todo.isEmpty()) {
                // FIXME: エラーがある場合はDialogを閉じない
            } else {
                // TODO: Roomで登録する
                Toast.makeText(activity, "「$todo」を登録します", Toast.LENGTH_SHORT).show()
            }
        })
        builder.setNegativeButton("Cancel", null)
        builder.setView(view)
        return builder.create()
    }
}