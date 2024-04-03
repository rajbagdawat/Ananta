package com.shiva.ananta;

import android.annotation.SuppressLint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;
//import com.github.barteksc.pdfviewer.util.FitPolicy;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ReadPDFFragment extends Fragment {

//    String pdfviewUri = "https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/PDF%2Fbhagwat-geeta.pdf?alt=media&token=82964508-47de-4b7f-a64b-96f8d49b43ee&_gl=1*hc0slq*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5NzU1NjE1Ni4yNy4xLjE2OTc1NjEzOTcuNjAuMC4w";
//     String pdfviewUri = "https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/PDF%2FDIVALI.pdf?alt=media&token=d681da70-4b93-4488-8826-7f9f9b378a18&_gl=1*1jow9h7*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5NzYxMzU1OC4yOS4xLjE2OTc2MTM4MDEuNDEuMC4w";
//     String pdfviewUri = "https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/PDF%2F%E0%A4%B5%E0%A4%BE%E0%A4%B2%E0%A5%8D%E0%A4%AE%E0%A5%80%E0%A4%95%E0%A4%BF-%E0%A4%B0%E0%A4%BE%E0%A4%AE%E0%A4%BE%E0%A4%AF%E0%A4%A3-srimad-valmiki-ramayana.pdf?alt=media&token=c5e99c96-358c-4a47-b199-9ccec9886cee&_gl=1*4d7so6*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5OTM0MjQxOC40NC4xLjE2OTkzNDM3MjguNjAuMC4w";
     String pdfviewUri = "https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/PDF%2FMahabharata%20Volume%201.pdf?alt=media&token=ed2d3198-761e-4b3e-bd60-80628302e5a7&_gl=1*qu22ws*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5OTM0MjQxOC40NC4xLjE2OTkzNDQzNjguMi4wLjA.";
    PDFView pdfview;
    private int pageNumber = 0;
    private PdfDocument pdfDocument;

    public ReadPDFFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View view = inflater.inflate(R.layout.fragment_read_p_d_f, container, false);
//     pdfview = view.findViewById(R.id.pdfView);

//        new RetrivePDFfromUrl().execute(pdfviewUri);

        return view;
    }


//    @SuppressLint("StaticFieldLeak")
//    private class RetrivePDFfromUrl extends AsyncTask<String,Void, InputStream> {
//        @Override
//        protected InputStream doInBackground(String... strings) {
//            InputStream inputStream = null;
//            try{
//                URL url = new URL(strings[0]);
//                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
//                if(urlConnection.getResponseCode() == 200){
//                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
//                }
//
//            }catch (IOException e){
//                e.printStackTrace();
//                return null;
//            }
//
//            return inputStream;
//        }
//        @Override
//        public void onPostExecute(InputStream inputStream){
//            pdfview.fromStream(inputStream)
//                    .defaultPage(pageNumber)
//                    .onPageChange(new OnPageChangeListener() {
//                        @Override
//                        public void onPageChanged(int page, int pageCount) {
//                            pageNumber = page;
//                            // Update the page count text view
//                            String pageText = (page + 1) + " of " + pageCount;
//                            pageCountTextView.setText(pageText);
//                            if (page == pageCount - 1) {
//                                // Load next page or implement your logic here
//                            }
//                        }
//                    })
//                    .onPageChange(new OnPageChangeListener() {
//                        @Override
//                        public void onPageChanged(int page, int pageCount) {
//                            pageNumber = page;
//                            if (page == pageCount - 1) {
//                                // Load next page or implement your logic here
//                            }
//                        }
//                    })
//                    .enableSwipe(true)
//                    .swipeHorizontal(false)
//                    .enableDoubletap(true)
//                    .enableAnnotationRendering(false)
//                    .password(null)
//                    .scrollHandle(new DefaultScrollHandle(getContext()))
//                    .enableAntialiasing(false)
//                    .spacing(1)
//                    .autoSpacing(true)
//                    .pageFitPolicy(FitPolicy.WIDTH)
//                    .fitEachPage(true)
//                    .pageSnap(true)
//                    .pageFling(true)
//                    .nightMode(false)
//                    .load();

//                    .enableSwipe(true) // allows to block changing pages using swipe
//                    .swipeHorizontal(false)
//                    .enableDoubletap(true)
//                    .defaultPage(0)
//                    .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
//                    .password(null)
//                    .scrollHandle(null)
//                    .enableAntialiasing(false) // improve rendering a little bit on low-res screens
//                    .spacing(0)
//                    .autoSpacing(true) // add dynamic spacing to fit each page on its own on the screen
//                    .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
//                    .fitEachPage(true) // fit each page to the view, else smaller pages are scaled relative to largest page.
//                    .pageSnap(false) // snap pages to screen boundaries
//                    .pageFling(true) // make a fling change only a single page like ViewPager
//                    .nightMode(false) // toggle night mode
//                    .load();
//        }
//    }
}