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

public class SocialMediaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView mediaRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_social_media, container, false);

        String[] mediaNames = new String[Media.medias.length];
        for (int i = 0; i < mediaNames.length; i++){
            mediaNames[i] = Media.medias[i].getName();
        }

        int[] mediaImages = new int[Media.medias.length];
        for (int i = 0; i < mediaImages.length; i++){
            mediaImages[i] = Media.medias[i].getImageResourceId();
        }

        CardViewAdapter adapter = new CardViewAdapter(mediaNames, mediaImages);
        mediaRecycler.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mediaRecycler.setLayoutManager(layoutManager);

        return mediaRecycler;
    }

}
