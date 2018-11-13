package saci.development.entrega03;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class fragment1 extends Fragment {

    private View rootView;

    public fragment1() {
        // Required empty public constructor
    }
    Button botao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_fragment1, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        botao = (Button)getView().findViewById(R.id.downloadButton);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentService= new Intent(getContext(), ServiceDownloadQuadro.class);
                getContext().startService(intentService);
                Toast.makeText(getActivity().getApplicationContext(), "Baixando Pintura",
                        Toast.LENGTH_LONG).show();
            }
        });


    }
}
