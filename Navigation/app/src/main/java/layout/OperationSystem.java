package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.anstag.app.navigation.CardViewAdapter;
import ru.anstag.app.navigation.Media;
import ru.anstag.app.navigation.R;
import ru.anstag.app.navigation.System;

public class OperationSystem extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView systemRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_operation_system, container, false);

        String[] systemNames = new String[System.systems.length];
        for (int i = 0; i < systemNames.length; i++){
            systemNames[i] = System.systems[i].getName();
        }

        int[] systemImages = new int[System.systems.length];
        for (int i = 0; i < systemImages.length; i++){
            systemImages[i] = System.systems[i].getImageResourceId();
        }

        CardViewAdapter adapter = new CardViewAdapter(systemNames, systemImages);
        systemRecycler.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        systemRecycler.setLayoutManager(layoutManager);

        return systemRecycler;
    }

}
