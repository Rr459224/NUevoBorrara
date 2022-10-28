package com.example.listacompra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    //private final String[] mDataset;
    private List<String> mDataset;
    private Context context;


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<String> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);

        return new MyViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        // holder.mIdView.setText(mDataset[position]);
        holder.nombre.setText(mDataset.get(position));
        //String nombre=mDataset.get(position).getName();
        //System.out.println(nombre);

        holder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String verdura= (String) holder.nombre.getText();
                MainActivity.verLista.add((String) holder.nombre.getText());
               // MainActivity.verLista.add(holder.n)

               // System.out.println(MainActivity.verLista.size());
                ;


            }
        });


        // Log.i("onBindViewHolder()", holder.mItem.toString() + " : " + position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //public final View mView;

        TextView nombre;
        //public final TextView t;


        public MyViewHolder(View view) {
            super(view);

            nombre = view.findViewById(R.id.textViewNombre);

        }

        @Override
        public String toString() {
            return super.toString() + " '";
        }
    }
}