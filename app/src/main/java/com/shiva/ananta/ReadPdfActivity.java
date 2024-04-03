package com.shiva.ananta;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

public class ReadPdfActivity extends AppCompatActivity {
//    String pdfviewUri = "https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/PDF%2Fbhagwat-geeta.pdf?alt=media&token=82964508-47de-4b7f-a64b-96f8d49b43ee&_gl=1*hc0slq*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5NzU1NjE1Ni4yNy4xLjE2OTc1NjEzOTcuNjAuMC4w";
//    String pdfviewUri = "https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/PDF%2FDIVALI.pdf?alt=media&token=d681da70-4b93-4488-8826-7f9f9b378a18&_gl=1*1jow9h7*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5NzYxMzU1OC4yOS4xLjE2OTc2MTM4MDEuNDEuMC4w";
//String pdfviewUri = "https://firebasestorage.googleapis.com/v0/b/ananta-f1ec0.appspot.com/o/PDF%2FMahabharata%20Volume%201.pdf?alt=media&token=ed2d3198-761e-4b3e-bd60-80628302e5a7&_gl=1*qu22ws*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5OTM0MjQxOC40NC4xLjE2OTkzNDQzNjguMi4wLjA.";

//    String pdfviewUri = "https://firebasestorage.googleapis.com/v0/b/vaibhavpawar-4b6c7.appspot.com/o/%E0%A4%B5%E0%A4%BE%E0%A4%B2%E0%A5%8D%E0%A4%AE%E0%A5%80%E0%A4%95%E0%A4%BF-%E0%A4%B0%E0%A4%BE%E0%A4%AE%E0%A4%BE%E0%A4%AF%E0%A4%A3-srimad-valmiki-ramayana.pdf?alt=media&token=c371c4c4-ed88-421b-b7d1-63583fc06797&_gl=1*16edfji*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5OTQyMzYzNi4zMC4xLjE2OTk0MjQxNDkuNjAuMC4w";
//    String pdfviewUri = "https://firebasestorage.googleapis.com/v0/b/vaibhavpawar-4b6c7.appspot.com/o/Mahabharata%20Volume%201.pdf?alt=media&token=b1371bf8-abbe-4e5e-917f-ffeaa4b12ffc&_gl=1*sfm98e*_ga*NzM5NTI3ODUuMTY3NTMxNDgwNA..*_ga_CW55HF8NVT*MTY5OTQyMzYzNi4zMC4xLjE2OTk0MjQ0MTQuOC4wLjA.";
    PDFView pdfview;
    TextView pageCountTextView,Sections,bookName;
    private EditText textView;
    private int pageNumber = 0;
   @SuppressLint("StaticFieldLeak")

    static int pagecountforreader =0;

    private static final int PICK_PDF_REQUEST = 1;
    ImageView textspeech,audiooff;
    private TextToSpeech textToSpeech;
    private Timer timer;
    private TimerTask timerTask;
    private final Handler handler = new Handler();
    View loadingbar;

    InputStream inputStream;
    public void extractTextFromOnlinePdf(String pdfUrl,int pagecountforreader) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                Log.d("PageCountForReader", "Page count for reader: " + pagecountforreader); // Log the value of pagecountforreader


                StringBuilder builder = new StringBuilder();
                PdfReader reader = null;
                try {
                    URL url = new URL(pdfUrl);
                    InputStream inputStream = url.openStream();
                    reader = new PdfReader(inputStream);

                    int numberOfPages = reader.getNumberOfPages();

                    int currentPage = pagecountforreader - 1;

                    if (currentPage >= 0 && currentPage < numberOfPages) {
                        String pageText = PdfTextExtractor.getTextFromPage(reader, currentPage + 1);
                        byte[] contentBytes = pageText.getBytes(StandardCharsets.UTF_16);
                        String content = new String(contentBytes, StandardCharsets.UTF_16);
                        builder.append(content);
                    }

                } catch (IOException e) {
                    Log.e("PDF_Text_Extractor", "IOException: " + e.getMessage());
                } finally {
                    if (reader != null) {
                        reader.close();
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
//                            builder.setLength(0);
                            Typeface hindiFont = Typeface.createFromAsset(getAssets(), "dev1.ttf");
                            textView.setTypeface(hindiFont);
                            textView.setText(builder.toString());
                            audiooff.setVisibility(View.GONE);
                            textspeech.setVisibility(View.VISIBLE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        }).start();
        loadingbar.setVisibility(View.GONE);
    }



      static String pdfviewUri;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_pdf);

        pdfview = findViewById(R.id.pdfView);
        pageCountTextView = findViewById(R.id.pageCountTextView);
        textView = findViewById(R.id.textview);
        Sections = findViewById(R.id.Sections);
        bookName = findViewById(R.id.bookName);

        Intent intent = getIntent();
        if (intent.hasExtra("bookKeyword")) {
            String data = intent.getStringExtra("bookKeyword");
            if (data != null) {
                timer = new Timer();
                timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();

                                database.getReference().child("Ananta").child("Librarybooks").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        if (snapshot != null) {

                                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                                String bookname = dataSnapshot.child("bookname").getValue(String.class);
                                                String bookurl = dataSnapshot.child("bookurl").getValue(String.class);
                                                String bookKeyword = dataSnapshot.child("bookkeyword").getValue(String.class);

                                                if (bookKeyword.equals(data)) {
                                                    pdfviewUri = bookurl;
                                                    bookName.setText(bookname);
                                                    Log.d("oninitt","pdfviewUri : " +pdfviewUri);
                                                }
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }
                        });
                    }
                };

                timer.schedule(timerTask, 1000);

            }

            bookName.setText(data);
        }
        Sections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadPdfActivity.this, MainActivity.class);
                intent.putExtra("check","true");
                startActivity(intent);
            }
        });

        loadingbar = findViewById(R.id.loadingbar);
        loadingbar.setVisibility(View.VISIBLE);
        Log.d("oninitt","pdfviewUri : " +pdfviewUri);

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (pdfviewUri!=null){
                            new RetrivePDFfromUrl().execute(pdfviewUri);
                        }else {
                            Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        };

        timer.schedule(timerTask, 2000);



        textspeech = findViewById(R.id.texttosppech);
        audiooff = findViewById(R.id.audiooff);

        textspeech.setVisibility(View.GONE);
        audiooff.setVisibility(View.VISIBLE);

        textToSpeech = new TextToSpeech(getBaseContext(), new TextToSpeech.OnInitListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    for (Voice voice : textToSpeech.getVoices()) {
                        Log.d("onInit_InstallerVoice :",voice.getName());
                    }
                    int result =  textToSpeech.setLanguage(Locale.forLanguageTag("hi"));
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.d("oninit","Language is not supported");
                        textToSpeech.setLanguage(Locale.forLanguageTag("en"));
                    }else {
                        textToSpeech.setLanguage(Locale.forLanguageTag("hi"));

                        Voice voice  = new Voice("hi-in-x-hie-network",new Locale("hi","IN"),
                                400,200,false,null);
                        textToSpeech.setVoice(voice);
                        textToSpeech.setSpeechRate(0.6f);
                    }
                }else {
                    Log.d("onInit","initilization Failed");
                }
            }
        });

        textspeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   String message;
                    message = textView.getText().toString().trim();
                    speakMessage(message);
            }

            private void speakMessage(String message) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    textToSpeech.speak(message,TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });
    }

    public void extractTextForCurrentPage() {
        textspeech.setVisibility(View.GONE);
        audiooff.setVisibility(View.VISIBLE);
        extractTextFromOnlinePdf(pdfviewUri, pagecountforreader);
    }
    @SuppressLint("StaticFieldLeak")
    private class RetrivePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            return inputStream;
        }

        @Override
        public void onPostExecute(InputStream inputStream) {
            pdfview.fromStream(inputStream)
                    .defaultPage(pageNumber)
                    .onPageChange(new OnPageChangeListener() {
                        @Override
                        public void onPageChanged(int page, int pageCount) {
                            pageNumber = page;
                            String pageText = (page + 1) + " of " + pageCount;
                            pagecountforreader = (page+1);
                            pageCountTextView.setText(pageText);
                            pageCountTextView.setVisibility(View.VISIBLE);
                            textView.setText(null);
                            extractTextForCurrentPage();
                            if (page == pageCount - 1) {
                            }
                        }
                    })
//                    .scrollHandle(new DefaultScrollHandle(getBaseContext()))
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .enableAnnotationRendering(false)
                    .password(null)
                    .enableAntialiasing(false)
                    .spacing(1)
                    .autoSpacing(true)
                    .pageFitPolicy(FitPolicy.WIDTH)
                    .fitEachPage(true)
                    .pageSnap(true)
                    .pageFling(true)
                    .nightMode(false)
                    .load();


        }

    }
    public void back(View view) {
        pdfviewUri = "";
        finish();
    }
}