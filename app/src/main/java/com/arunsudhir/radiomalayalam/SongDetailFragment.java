package com.arunsudhir.radiomalayalam;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.arunsudhir.radiomalayalam.service.PlayerService;
import com.arunsudhir.radiomalayalam.song.SongItem;

/**
 * A fragment representing a single Song detail screen.
 * This fragment is either contained in a {@link SongListActivity}
 * in two-pane mode (on tablets) or a {@link SongDetailActivity}
 * on handsets.
 */
public class SongDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_SONG_NAME = "Poonkattinodum";

    /**
     * The dummy content this fragment is presenting.
     */
    private SongItem mItem;
    private String songname;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SongDetailFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras =getActivity().getIntent().getExtras();
        if(extras != null) {
            if (extras.containsKey(ARG_SONG_NAME)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                songname =extras.getString(ARG_SONG_NAME);
                //mItem = SongContent.ITEM_MAP.get(getArguments().getString(ARG_SONG_NAME));
            }

            if (extras.containsKey("songItem")) {
                mItem = extras.getParcelable("songItem");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_song_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.song_name)).setText(mItem.getSongName());
            ((TextView) rootView.findViewById(R.id.song_album)).setText(mItem.album);
        }
        final Activity thisActivity = getActivity();

        FloatingActionButton playButton = (FloatingActionButton) rootView.findViewById(R.id.play_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(thisActivity, PlayerService.class);
                try {
                    //LOG.info("Toggling play/pause");
                    serviceIntent.putExtra("serviceCommand", "toggle");
                    thisActivity.startService(serviceIntent);
                } catch (Exception e) {
                    //LOG.error(e, "Failed to toggle state");
                }
            }
        });

        FloatingActionButton backButton = (FloatingActionButton) rootView.findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(thisActivity, PlayerService.class);
                try {
                    //LOG.info("Toggling play/pause");
                    serviceIntent.putExtra("serviceCommand", "skipBack");
                    thisActivity.startService(serviceIntent);
                } catch (Exception e) {
                    //LOG.error(e, "Failed to toggle state");
                }
            }
        });

        FloatingActionButton fwdButton = (FloatingActionButton) rootView.findViewById(R.id.fwd_button);

        fwdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(thisActivity, PlayerService.class);
                try {
                    //LOG.info("Toggling play/pause");
                    serviceIntent.putExtra("serviceCommand", "skipFwd");
                    thisActivity.startService(serviceIntent);
                } catch (Exception e) {
                    //LOG.error(e, "Failed to toggle state");
                }
            }
        });
        return rootView;
    }
}
