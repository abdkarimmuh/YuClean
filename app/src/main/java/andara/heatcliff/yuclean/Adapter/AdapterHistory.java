package andara.heatcliff.yuclean.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import andara.heatcliff.yuclean.Holder.HolderHargaSampah;
import andara.heatcliff.yuclean.Holder.HolderHistory;
import andara.heatcliff.yuclean.ItemObject.ItemObjectHargaSampahOffline;
import andara.heatcliff.yuclean.ItemObject.ItemObjectHistoryOffline;
import andara.heatcliff.yuclean.R;

/**
 * Created by Heatcliff on 25/08/2016.
 */
public class AdapterHistory extends RecyclerView.Adapter<HolderHistory> {

    List<ItemObjectHistoryOffline> itemObjectHistoryOfflineList;
    Context context;

    public AdapterHistory (Context context, List<ItemObjectHistoryOffline> itemObjectHistoryOfflineList) {
        this.context = context;
        this.itemObjectHistoryOfflineList = itemObjectHistoryOfflineList;
    }

    @Override
    public HolderHistory onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_history, null);
        return new HolderHistory(view);
    }

    @Override
    public void onBindViewHolder(HolderHistory holder, int position) {
        holder.txtNamaSampah.setText(itemObjectHistoryOfflineList.get(position).nama);
        holder.txtJumlahSampah.setText(itemObjectHistoryOfflineList.get(position).jumlah);
        holder.txtHour.setText(itemObjectHistoryOfflineList.get(position).jam);
        holder.txtDate.setText(itemObjectHistoryOfflineList.get(position).tanggal);

    }

    @Override
    public int getItemCount() {
        return itemObjectHistoryOfflineList.size();
    }
}
