package devandroid.frederico.listavip.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import devandroid.frederico.listavip.R;
import devandroid.frederico.listavip.model.Pessoa;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder> {

    private List<Pessoa> pessoaList;

    public PessoaAdapter(List<Pessoa> pessoaList) {
        this.pessoaList = pessoaList;
    }

    public void updateData(List<Pessoa> newPessoaList) {
        pessoaList = newPessoaList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PessoaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pessoa, parent, false);
        return new PessoaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PessoaViewHolder holder, int position) {
        Pessoa pessoa = pessoaList.get(position);
        String nomeCompleto = pessoa.getPrimeiroNome() + " " + pessoa.getSobrenome();
        holder.textViewNomeCompleto.setText(nomeCompleto);
        holder.textViewTelefone.setText(pessoa.getTelefone());
        holder.textViewGenero.setText((pessoa.getGenero()));
        holder.textViewCpf.setText(pessoa.getCpf());

        

    }

    @Override
    public int getItemCount() {
        return pessoaList.size();
    }

    public static class PessoaViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNomeCompleto, textViewTelefone, textViewGenero, textViewSobrenome, textViewCpf;

        public PessoaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomeCompleto = itemView.findViewById(R.id.textViewNomeCompleto);
            textViewTelefone = itemView.findViewById(R.id.textViewTelefone);
            textViewGenero = itemView.findViewById(R.id.textViewGenero);
            textViewSobrenome = itemView.findViewById(R.id.textViewSobrenome);
            textViewCpf = itemView.findViewById(R.id.textViewCpf);
        }
    }


}
