package andara.heatcliff.yuclean.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import andara.heatcliff.yuclean.Adapter.AdapterHistory;
import andara.heatcliff.yuclean.ItemObject.ItemObjectHistoryOffline;
import andara.heatcliff.yuclean.R;

/**
 * Created by Heatcliff on 25/08/2016.
 */
public class HistoryFragment extends Fragment {

    public RecyclerView lstHistory;
    public LinearLayoutManager layoutManager;
    public List<ItemObjectHistoryOffline> itemObjectHistoryOfflineList;
    public AdapterHistory adapterHistory;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        lstHistory = (RecyclerView) rootView.findViewById(R.id.lstHistory);
        layoutManager = new LinearLayoutManager(getContext());
        lstHistory.setLayoutManager(layoutManager);

        itemObjectHistoryOfflineList = new ArrayList<>();
        itemObjectHistoryOfflineList.add(new ItemObjectHistoryOffline("Plastik", "1 Kg","08:00", "25/01/2016"));
        itemObjectHistoryOfflineList.add(new ItemObjectHistoryOffline("Kapas", "0,5 Kg","08:00", "25/01/2016"));
        itemObjectHistoryOfflineList.add(new ItemObjectHistoryOffline("Besi", "2 Kg","08:00", "25/01/2016"));
        itemObjectHistoryOfflineList.add(new ItemObjectHistoryOffline("Plastik", "1 Kg","08:00", "25/01/2016"));


        adapterHistory = new AdapterHistory(getContext(), itemObjectHistoryOfflineList);
        lstHistory.setAdapter(adapterHistory);
        lstHistory.setHasFixedSize(true);
        setHasOptionsMenu(true);
        return rootView;
    }
}
