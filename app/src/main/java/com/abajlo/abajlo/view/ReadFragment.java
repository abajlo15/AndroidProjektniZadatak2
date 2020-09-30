package com.abajlo.abajlo.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.abajlo.abajlo.R;
import com.abajlo.abajlo.adapter.AutomobilAdapter;
import com.abajlo.abajlo.adapter.AutomobilClickListener;
import com.abajlo.abajlo.model.Automobil;
import com.abajlo.abajlo.viewModel.AutomobilViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReadFragment extends Fragment {

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.lista)
    ListView listView;

    private AutomobilViewModel model;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read,
                container, false);
        ButterKnife.bind(this,view);

        model = ((MainActivity)getActivity()).getModel();

        definirajListu();
        definirajSwipe();
        osvjeziPodatke();


        return view;
    }

    private void osvjeziPodatke(){
        model.dohvatiAutomobile().observe(this, new Observer<List<Automobil>>() {
            @Override
            public void onChanged(@Nullable List<Automobil> Automobili) {
                swipeRefreshLayout.setRefreshing(false);
                ((AutomobilAdapter)listView.getAdapter()).setListaAutomobila(Automobili);
                ((AutomobilAdapter) listView.getAdapter()).osvjeziPodatke();

            }
        });
    }

    private void definirajSwipe() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                osvjeziPodatke();
            }
        });

    }

    private void definirajListu() {

        listView.setAdapter( new AutomobilAdapter(getActivity(), R.layout.red_liste, new AutomobilClickListener() {
            @Override
            public void onItemClick(Automobil automobil) {
                model.setAutomobil(automobil);
                ((MainActivity)getActivity()).cud();
            }
        }));
    }

    @OnClick(R.id.fab)
    public void novoPlovilo(){
        model.setAutomobil(new Automobil());
        ((MainActivity)getActivity()).cud();
    }

}
