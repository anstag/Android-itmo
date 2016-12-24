package ru.anstag.app.notes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class NoteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView systemRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_note, container, false);

        String[] noteNames = new String[3];
        noteNames[0] = "Первая";
        noteNames[1] = "Вторая";
        noteNames[2] = "Третья";

        String[] noteDescription = new String[3];
        noteDescription[0] = "noteDescription";
        noteDescription[1] = "noteDescription2";
        noteDescription[2] = "noteDescription3";

        CardViewAdapter adapter = new CardViewAdapter(noteDescription, noteNames);
        systemRecycler.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        systemRecycler.setLayoutManager(layoutManager);

        return systemRecycler;
    }

}
