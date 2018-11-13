package saci.development.entrega03;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Button;

import java.io.File;
import java.util.concurrent.Future;

public class ServiceDownloadQuadro extends Service {


    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String url = "https://images.metmuseum.org/CRDImages/ep/original/DT1502_cropped2.jpg";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("Oil on canvas");
        request.setTitle("Self-Portrait with a Straw Hat (obverse: The Potato Peeler)");
// Verificar se a versão do android suporta o download manager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "DT1502_cropped2.jpg");

// instacia o DM e enfileira o download
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
