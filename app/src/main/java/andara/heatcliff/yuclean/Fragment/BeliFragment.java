package andara.heatcliff.yuclean.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import andara.heatcliff.yuclean.Adapter.AdapterBeli;
import andara.heatcliff.yuclean.ItemObject.ItemObjectBeli;
import andara.heatcliff.yuclean.R;

/**
 * Created by irfan on 10/17/2016.
 */
public class BeliFragment extends Fragment {

    private RecyclerView lstBeli;
    private LinearLayoutManager layoutManager;
    public List<ItemObjectBeli> itemObjectBeliList;
    private AdapterBeli adapterBeli;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_beli, container, false);

        lstBeli = (RecyclerView) rootView.findViewById(R.id.lstBeli);
        layoutManager = new LinearLayoutManager(getContext());
        lstBeli.setLayoutManager(layoutManager);

        itemObjectBeliList = new ArrayList<>();
        itemObjectBeliList.add(new ItemObjectBeli("Payung","10000","www.google.com",R.drawable.icon_app));
        itemObjectBeliList.add(new ItemObjectBeli("Payung","10000","www.google.com",R.drawable.icon_app));
        itemObjectBeliList.add(new ItemObjectBeli("Payung","10000","www.google.com",R.drawable.icon_app));
        itemObjectBeliList.add(new ItemObjectBeli("Payung","10000","www.google.com",R.drawable.icon_app));
        itemObjectBeliList.add(new ItemObjectBeli("Payung","10000","www.google.com",R.drawable.icon_app));
        itemObjectBeliList.add(new ItemObjectBeli("Payung","10000","www.google.com",R.drawable.icon_app));
        itemObjectBeliList.add(new ItemObjectBeli("Payung","10000","www.google.com",R.drawable.icon_app));
        itemObjectBeliList.add(new ItemObjectBeli("Payung","10000","www.google.com",R.drawable.icon_app));
        itemObjectBeliList.add(new ItemObjectBeli("Payung","10000","www.google.com",R.drawable.icon_app));
        itemObjectBeliList.add(new ItemObjectBeli("Payung","10000","www.google.com",R.drawable.icon_app));
        itemObjectBeliList.add(new ItemObjectBeli("Payung","10000","www.google.com",R.drawable.icon_app));

        adapterBeli = new AdapterBeli(getContext(), itemObjectBeliList);
        lstBeli.setAdapter(adapterBeli);
        lstBeli.setHasFixedSize(true);
        setHasOptionsMenu(true);
        return rootView;
    }
}