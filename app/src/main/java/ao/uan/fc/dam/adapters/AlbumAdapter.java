package ao.uan.fc.dam.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ao.uan.fc.dam.R;
import ao.uan.fc.dam.models.album;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MeuViewHolder> {

    Context ctx;
    List<album>lista;

    public class MeuViewHolder extends RecyclerView.ViewHolder{
        TextView txtNome;
        //TextView txtID;
        public MeuViewHolder(@NonNull View view) {

            super(view);
            txtNome = (TextView)view.findViewById(R.id.IteNome);
            //txtID = (TextView)view.findViewById(R.id.IteID);
        }
    }

    public AlbumAdapter(Context ctx1, List<album>lista1){
        this.ctx = ctx1;
        this.lista = lista1;
    }
    @NonNull
    @Override
    public MeuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_album, viewGroup,false);
        return new AlbumAdapter.MeuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHolder meuViewHolder, int i) {
        album a = lista.get(i);
        meuViewHolder.txtNome.setText(a.getTitulo());
        //meuViewHolder.txtID.setText(a.getId());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


}
