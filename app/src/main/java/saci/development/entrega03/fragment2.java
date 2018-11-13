package saci.development.entrega03;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

public class fragment2 extends Fragment {

    private View rootView;

    ProgressBar pd;

    SampleResultReceiver resultReceiever;
    String urlQuadro = "https://images.metmuseum.org/CRDImages/ep/original/DT1502_cropped2.jpg";

    public fragment2() {
        // Required empty public constructor
    }

    Button botao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_fragment2, container, false);


        resultReceiever = new SampleResultReceiver(new Handler());
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        botao = (Button)getView().findViewById(R.id.downloadButton3);

        pd = (ProgressBar)getView().findViewById(R.id.downloadPD);

        botao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getContext(),
                    IntentServiceDownload.class);
                startIntent.putExtra("receiver", resultReceiever);
                startIntent.putExtra("url",urlQuadro);
                getContext().startService(startIntent);

                pd.setVisibility(View.VISIBLE);
                pd.setIndeterminate(true);
            }

        });

    }

    private class SampleResultReceiver extends ResultReceiver {

        public SampleResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            switch (resultCode) {
                case IntentServiceDownload.DOWNLOAD_ERROR:
                    Toast.makeText(getContext(), "error in download",
                            Toast.LENGTH_SHORT).show();
                    pd.setVisibility(View.INVISIBLE);
                    break;

                case IntentServiceDownload.DOWNLOAD_SUCCESS:
                    String filePath = resultData.getString("filePath");
                    Bitmap bmp = BitmapFactory.decodeFile(filePath);
                    if ( bmp != null) {
                        Toast.makeText(getContext(),
                                "image download via IntentService is done",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(),
                                "error in decoding downloaded file",
                                Toast.LENGTH_SHORT).show();
                    }
                    pd.setIndeterminate(false);
                    pd.setVisibility(View.INVISIBLE);

                    MediaScannerConnection.scanFile(getContext(), new String[] { filePath }, new String[] { "image/jpeg" }, null);

                    break;
            }
            super.onReceiveResult(resultCode, resultData);
        }

    }
}
