package pl.kornelius.saveit

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.single_exapnse_row.view.*

/**
 * Created by seeyIT.
 */
class ExpanseAdapter (private val myDataset: ArrayList<Expanse>) :
        RecyclerView.Adapter<ExpanseAdapter.ViewHolder>() {

    class ViewHolder(val expanse: LinearLayout) : RecyclerView.ViewHolder(expanse)


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ExpanseAdapter.ViewHolder {
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.single_exapnse_row, parent, false) as LinearLayout
        return ViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.expanse.expanse_thing.text = myDataset[position].thing
        holder.expanse.expanse_price.text = myDataset[position].price
        holder.expanse.expanse_date.text = myDataset[position].date.toDate() // TODO add extension method to format long to date
    }

    override fun getItemCount() = myDataset.size
}