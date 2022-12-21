package com.example.cointrack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cointrack.R
import com.example.cointrack.models.Currency


class CurrencyAdapter()
    : RecyclerView.Adapter< CurrencyAdapter.CurrencyHolder>() {

    private var currencyList:List<Currency> = ArrayList()


    override fun getItemCount(): Int {


        val size = if (currencyList==null) 0 else currencyList.size
        return  size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val itemView = LayoutInflater.
        from(parent.context).
        inflate(R.layout.row_item_currency, parent, false)
        return CurrencyHolder(itemView)
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {


            with(getItem(position)) {
            holder.tvCurrencyName.text = name
            holder.tvSymbol.text = symbol
            holder.tvPrice.text = "$ "+usdPrice.toString()
        }
    }

    fun getNoteAt(position: Int) = getItem(position)

            private fun getItem(position: Int):Currency{
                return currencyList.get(position)
            }

   fun  setCurrency(hello:List<Currency>){
        this.currencyList=hello
       notifyDataSetChanged()
    }
    inner class CurrencyHolder(iv: View) : RecyclerView.ViewHolder(iv) {

        val tvCurrencyName: TextView = itemView.findViewById(R.id.tv_name)
        val tvSymbol: TextView = itemView.findViewById(R.id.tv_symbole)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_price)

//        init {
//            itemView.setOnClickListener {
//                if(adapterPosition != NO_POSITION)
//                    onItemClickListener(getItem(adapterPosition))
//            }
//        }

    }
}

private val diffCallback = object : DiffUtil.ItemCallback<Currency>() {
    override fun areItemsTheSame(oldItem: Currency, newItem: Currency) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency) =
        oldItem.name == newItem.name
                && oldItem.symbol == newItem.symbol
                && oldItem.usdPrice == newItem.usdPrice
}
