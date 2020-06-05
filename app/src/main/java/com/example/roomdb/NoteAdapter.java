package com.example.roomdb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> notes = new ArrayList<>();
    Onitemclicklistner onitemclicklistner;

    public void setOnitemclicklistner(Onitemclicklistner onitemclicklistner) {
        this.onitemclicklistner = onitemclicklistner;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note note = notes.get(position);
        holder.tite.setText(note.getTitle());
        holder.dec.setText(note.getDescription());
        holder.cardView.setCardBackgroundColor(note.getPriority());
        holder.posi.setText(Integer.toString(note.getId()));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getnoteposition(int position) {
        return notes.get(position);
    }

    public class NoteHolder extends RecyclerView.ViewHolder {
        TextView tite, posi, dec;
        CardView cardView;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            tite = itemView.findViewById(R.id.title);
            posi = itemView.findViewById(R.id.pos);
            dec = itemView.findViewById(R.id.desc);
            cardView=itemView.findViewById(R.id.card);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (onitemclicklistner != null && position != RecyclerView.NO_POSITION) {
                        onitemclicklistner.onItemClick(notes.get(position));
                    }
                }
            });
        }
    }

    public interface Onitemclicklistner {
        void onItemClick(Note note);
    }
}
