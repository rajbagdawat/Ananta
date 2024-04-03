//package com.shiva.ananta.models;
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import com.github.barteksc.pdfviewer.PDFView;
//import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
//import com.github.barteksc.pdfviewer.scroll.ScrollHandle;
//
//import org.xml.sax.helpers.DefaultHandler;
//
//public class CustomScrollHandle extends DefaultScrollHandle {
//
//    private Paint handlePaint;
//
//    public CustomScrollHandle(Context context) {
//        super(context);
//        init();
//    }
//
//    private void init() {
//        handlePaint = new Paint();
//        handlePaint.setColor(Color.RED); // Customize the handle color
//    }
//
//    @Override
//    public void setupLayout(PDFView pdfView) {
//        super.setupLayout(pdfView);
//        // Custom handle setup without page count
//    }
//
//    @Override
//    public void destroyLayout() {
//        // Destroy any resources here if necessary
//        super.destroyLayout();
//    }
//
//    @Override
//    public void setScroll(float position) {
//        // Handle the scroll position
//        super.setScroll(position);
//    }
//
//
//    @Override
//    public void setupLayout(PDFView pdfView, float position) {
//        // Handle the layout setup with position
//        super.setupLayout(pdfView, position);
//    }
//
//    @Override
//    public void draw(Canvas canvas, float offset) {
//        // Get the bounds of the PDFView
//        int viewWidth = getPdfView().getWidth();
//        int viewHeight = getPdfView().getHeight();
//
//        // Customize the handle position
//        float handleRadius = 20;
//        float handleCenterX = viewWidth - 50;
//        float handleCenterY = viewHeight / 2;
//
//        // Draw the handle
//        canvas.drawCircle(handleCenterX, handleCenterY, handleRadius, handlePaint);
//    }
//}
