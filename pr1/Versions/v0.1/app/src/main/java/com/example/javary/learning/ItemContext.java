package com.example.javary.learning;

import android.graphics.Bitmap;

public class ItemContext {
    public String title, description;
    public Bitmap bitmapIcon;
    public int maxProgress, currentProgress;

    public ItemContext(
            String title,
            String description,
            Bitmap bitmapIcon,
            int maxProgress,
            int currentProgress
    ) {
        this.title = title;
        this.description = description;
        this.bitmapIcon = bitmapIcon;
        this.maxProgress = maxProgress;
        this.currentProgress = currentProgress;
    }
}
