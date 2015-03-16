package com.rustam.openweather.ui.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.rustam.openweather.R;

public class ChangeCityDialog extends DialogFragment {

  public static final String TAG = "changeCityDialog";

  public interface ChangeCityDialogListener {
    public void onDialogPositiveClick(String query);
  }

  ChangeCityDialogListener mListener;

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    try {
      mListener = (ChangeCityDialogListener) activity;
    } catch (ClassCastException e) {
      throw new ClassCastException(
          activity.toString() + " must implement ChangeCityDialogListener");
    }
  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();

    View view = inflater.inflate(R.layout.change_dialog, null);
    final EditText editText = (EditText) view.findViewById(R.id.cityEditText);
    builder.setView(view).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int id) {
        if (mListener != null) {
          mListener.onDialogPositiveClick(editText.getText().toString());
        }
      }
    }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        ChangeCityDialog.this.getDialog().cancel();
      }
    });
    return builder.create();
  }
}
