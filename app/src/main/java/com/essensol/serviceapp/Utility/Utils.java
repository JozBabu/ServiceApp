package com.essensol.serviceapp.Utility;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.essensol.serviceapp.R;

public class Utils {

    public static void ShowCustomToast(String message, Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View layout = inflater.inflate(R.layout.toast_layout,null);
        ViewGroup grp=(ViewGroup)layout.findViewById(R.id.toast_root);

        TextView text = (TextView) grp.findViewById(R.id.toast_txt);
        text.setText(message);
        Typeface custom_font2 = Typeface.createFromAsset(context.getAssets(),  "fonts/MontserratMedium.ttf");
        text.setTypeface(custom_font2);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM, 0, 90);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    //SnackBar
    public static void setSnackBar(View root, String snackTitle) {
        Snackbar snackbar = Snackbar.make(root, snackTitle, Snackbar.LENGTH_SHORT);
        snackbar.show();
        View view = snackbar.getView();
        TextView txtv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        txtv.setGravity(Gravity.CENTER_HORIZONTAL);

    }
}
