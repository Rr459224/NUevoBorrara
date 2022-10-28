package com.example.listacompra.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listacompra.MainActivity;
import com.example.listacompra.MyAdapter;
import com.example.listacompra.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CrearFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrearFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter mAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CrearFragment() {
        // Required empty public constructor
    }

    public static CrearFragment newInstance(String param1, String param2) {
        CrearFragment fragment = new CrearFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_crear, container, false);

        Context context = view.getContext();


        //adaptador=new ArrayAdapter<String>
        //        (getContext(), android.R.layout.activity_list_item,lista);
        // recyclerView.setAdapter(adaptador);


        recyclerView = (RecyclerView) view;

        //recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        //recyclerView.setLayoutManager(new LinearLayoutManager(context));

        recyclerView.setLayoutManager(layoutManager);

        MyAdapter mAdapter = new MyAdapter(MainActivity.lista);
        // specify an adapter (see also next example)
        // mAdapter = new MyAdapter(ProductContent.ITEMS);
        recyclerView.setAdapter(mAdapter);


        return view;
    }


}