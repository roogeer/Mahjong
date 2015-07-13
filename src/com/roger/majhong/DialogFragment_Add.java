package com.roger.majhong;

//import android.R.string;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DialogFragment_Add extends DialogFragment {
	private TextView tvP1;
	private TextView tvP2;
	private TextView tvP3;
	private TextView tvP4;
	private EditText etP1;
	private EditText etP2;
	private EditText etP3;
	private EditText etP4;	
	
    public interface AddInputListener  
    {  
        void onAddInputComplete(String p1Vlaue, String p2Vlaue, String p3Vlaue, String p4Value);  
    }	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		//return super.onCreateDialog(savedInstanceState);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.dialogfragment_add, null);
		
		int textsize = Utility.GetTextSizeFactor(this.getActivity());
		Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();
		tvP1 = (TextView)view.findViewById(R.id.add_tvDong);
		tvP1.setTextSize(textsize);
		//tvP1.setText(activity_NewGame.playerOneName);
		tvP1.setText(activity_NewGame.PlayerInfoP1.Name);
		
		tvP2 = (TextView)view.findViewById(R.id.add_tvNan);
		tvP2.setTextSize(textsize);
		tvP2.setText(activity_NewGame.PlayerInfoP2.Name);
		
		tvP3 = (TextView)view.findViewById(R.id.add_tvXi);
		tvP3.setTextSize(textsize);
		tvP3.setText(activity_NewGame.PlayerInfoP3.Name);

		tvP4 = (TextView)view.findViewById(R.id.add_tvBei);
		tvP4.setTextSize(textsize);
		tvP4.setText(activity_NewGame.PlayerInfoP4.Name);
		
		etP1 = (EditText)view.findViewById(R.id.add_editPlayerOne);
		etP1.setTextSize(textsize);

		etP2 = (EditText)view.findViewById(R.id.add_editPlayerTwo);
		etP2.setTextSize(textsize);
		
		etP3 = (EditText)view.findViewById(R.id.add_editPlayerThree);
		etP3.setTextSize(textsize);

		etP4 = (EditText)view.findViewById(R.id.add_editPlayerFour);
		etP4.setTextSize(textsize);
		
		builder.setView(view)
		.setPositiveButton("提交",
				new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface diglog, int id)
						// TODO Auto-generated method stub
						{
							String p1 =etP1.getText().toString().trim().isEmpty()?"-":etP1.getText().toString().trim();
							String p2 =etP2.getText().toString().trim().isEmpty()?"-":etP2.getText().toString().trim();
							String p3 =etP3.getText().toString().trim().isEmpty()?"-":etP3.getText().toString().trim();
							String p4 =etP4.getText().toString().trim().isEmpty()?"-":etP4.getText().toString().trim();							
							
							AddInputListener addInputListener = (AddInputListener)getActivity();
							addInputListener.onAddInputComplete(p1, p2, p3, p4);
						}
				}).setNegativeButton("取消", null);
		return builder.create();
	}
}
