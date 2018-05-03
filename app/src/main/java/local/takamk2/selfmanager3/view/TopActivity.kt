package local.takamk2.selfmanager3.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.util.SortedList
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_top.*
import local.takamk2.selfmanager3.R
import local.takamk2.selfmanager3.viewmodel.TopActivityVM
import timber.log.Timber

class TopActivity : AppCompatActivity() {

    private lateinit var vm: TopActivityVM

    private lateinit var titleListAdapter: TitleListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top)

        vm = ViewModelProviders.of(this).get(TopActivityVM::class.java)

        // RecyclerViewの初期化
        titleListAdapter = TitleListAdapter(this)
        title_list.adapter = titleListAdapter
        title_list.layoutManager = LinearLayoutManager(this)

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        divider.setDrawable(resources.getDrawable(R.drawable.divider, theme))
        title_list.addItemDecoration(divider)

        title_list.itemAnimator = DefaultItemAnimator()

        titleListAdapter.data.addAll(listOf(SampleData(1, "A"), SampleData(2, "B"),
                SampleData(3, "C"), SampleData(4, "D"), SampleData(5, "E")))
        titleListAdapter.notifyDataSetChanged()

        // Titleの追加
        RxView.clicks(add_button).subscribe {
            if (title_edit.text.toString().isEmpty()) {
                return@subscribe
            }
            val size = titleListAdapter.data.size()
            val id = size + 1
            titleListAdapter.data.add(SampleData(id, title_edit.text.toString()))
            title_edit.setText("")
        }

        // データのクリア
        RxView.clicks(clear_button).subscribe {
            titleListAdapter.data.clear()
        }
    }

    class TitleListAdapter(context: Context) : RecyclerView.Adapter<MyViewHolder>() {

        val data: SortedList<SampleData> = SortedList(SampleData::class.java, MySortedList(this))

        private val inflater = LayoutInflater.from(context)!!

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
            return MyViewHolder(inflater.inflate(R.layout.item_title_list, parent, false))
        }

        override fun getItemCount(): Int {
            return data.size()
        }

        override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
            holder ?: return
            Timber.d("onBindViewHolder: position=$position")
            holder.title_text.text = data[position].data
            val id = data[position].id
            RxView.clicks(holder.delete_button).subscribe {
                val d = getItemById(id)
                data.remove(d)
            }
        }

        private fun getItemById(id: Int): SampleData? {
            for (i in 0..data.size()) {
                val d = data[i]
                if (d.id == id) {
                    return d
                }
            }
            return null
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title_text = itemView.findViewById<TextView>(R.id.title_text)
        val delete_button = itemView.findViewById<ImageView>(R.id.delete_button)
    }

    private class MySortedList(private val adapter: TitleListAdapter) : SortedList.Callback<SampleData>() {

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            Timber.d("onMoved: fromPosition=$fromPosition, toPosition=$toPosition")
            adapter.notifyItemMoved(fromPosition, toPosition)
        }

        override fun onChanged(position: Int, count: Int) {
            Timber.d("onChanged: position=$position, count=$count")
            adapter.notifyItemRangeChanged(position, count)
        }

        override fun onInserted(position: Int, count: Int) {
            Timber.d("onInserted: position=$position, count=$count")
            adapter.notifyItemRangeInserted(position, count)
        }

        override fun onRemoved(position: Int, count: Int) {
            Timber.d("onRemoved: position=$position, count=$count")
            adapter.notifyItemRangeRemoved(position, count)
        }

        override fun compare(o1: SampleData?, o2: SampleData?): Int {
            Timber.d("compare: o1=$o1, o2=$o2")
            return o1!!.id - o2!!.id
        }

        override fun areContentsTheSame(oldItem: SampleData?, newItem: SampleData?): Boolean {
            Timber.d("areContentsTheSame: oldItem.id=${oldItem?.id}, newItem.id=${newItem?.id}")
            return oldItem?.id == newItem?.id && oldItem?.data == newItem?.data
        }

        override fun areItemsTheSame(item1: SampleData?, item2: SampleData?): Boolean {
            Timber.d("areItemsTheSame: item1=$item1, item2=$item2")
            return item1?.id == item2?.id
        }
    }

    class SampleData(val id: Int, var data: String)
}
