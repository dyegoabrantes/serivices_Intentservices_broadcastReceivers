package saci.development.entrega03;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class fragment4 extends Fragment implements View.OnClickListener {

    private View rootView;
    private Button start, stop;
    public fragment4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_fragment4, container, false);


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        start = (Button) getView().findViewById(R.id.playButton2);
        stop = (Button)getView().findViewById(R.id.stopButton2);


        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == start) {
            getContext().startService(new Intent(getContext(), TocaMusicaService.class));
        }else {
            getContext().stopService(new Intent(getContext(), TocaMusicaService.class));
        }
    }
}
