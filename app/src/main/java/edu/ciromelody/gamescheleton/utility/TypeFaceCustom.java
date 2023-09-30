package edu.ciromelody.gamescheleton.utility;

import android.content.Context;
import android.graphics.Typeface;

public class TypeFaceCustom {
    public static final Typeface VAGRoundedBlack(Context context)
    {
        Typeface typeface = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "fonts/vag-rounded-black.ttf");
        return typeface;
    }
}
