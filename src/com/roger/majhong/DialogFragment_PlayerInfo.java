package com.roger.majhong;

import com.roger.majhong.DialogFragment_Add.AddInputListener;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DialogFragment_PlayerInfo extends DialogFragment {
	private TextView tvp1;
	private TextView tvp2;
	private TextView tvp3;
	private TextView tvp4;
	private TextView tvJDS;
	private EditText etP1;
	private EditText etP2;
	private EditText etP3;
	private EditText etP4;
	private EditText etJDS;
	
    public interface InfoInputListener  
    {  
        void onInfoInputComplete(String p1Name, String p2Name, String p3Name, String p4Name, String JDS);  
    }
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	//return super.onCreateDialog(savedInstanceState);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.dialogfragment_playerinfo, null);
		
		int textsize = Utility.GetTextSizeFactor(this.getActivity());
		//Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();
		
		tvp1 = (TextView)view.findViewById(R.id.info_tvDong);
		tvp1.setTextSize(textsize);
		
		tvp2 = (TextView)view.findViewById(R.id.info_tvNan);
		tvp2.setTextSize(textsize);
		
		tvp3 = (TextView)view.findViewById(R.id.info_tvXi);
		tvp3.setTextSize(textsize);
		
		tvp4 = (TextView)view.findViewById(R.id.info_tvBei);
		tvp4.setTextSize(textsize);
		
		tvJDS = (TextView)view.findViewById(R.id.info_tvJDValue);
		tvJDS.setTextSize(textsize);
		
		etP1 = (EditText)view.findViewById(R.id.info_editPlayerOne);
		etP1.setTextSize(textsize);

		etP2 = (EditText)view.findViewById(R.id.info_editPlayerTwo);
		etP2.setTextSize(textsize);
		
		etP3 = (EditText)view.findViewById(R.id.info_editPlayerThree);
		etP3.setTextSize(textsize);

		etP4 = (EditText)view.findViewById(R.id.info_editPlayerFour);
		etP4.setTextSize(textsize);
		
		etJDS = (EditText)view.findViewById(R.id.info_etJDValue);
		etJDS.setTextSize(textsize);
		
		builder.setView(view)
		.setPositiveButton("提交",
				new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface diglog, int id)
						// TODO Auto-generated method stub
						{
							String p1 =etP1.getText().toString().trim().isEmpty()?"东":etP1.getText().toString().trim();
							String p2 =etP2.getText().toString().trim().isEmpty()?"南":etP2.getText().toString().trim();
							String p3 =etP3.getText().toString().trim().isEmpty()?"西":etP3.getText().toString().trim();
							String p4 =etP4.getText().toString().trim().isEmpty()?"北":etP4.getText().toString().trim();
							String jds = etJDS.getText().toString().trim().isEmpty()?"50":etJDS.getText().toString().trim();
							
							InfoInputListener infoInputListener = (InfoInputListener)getActivity();
							infoInputListener.onInfoInputComplete(p1, p2, p3, p4, jds);
						}
				}).setNegativeButton("取消", null);
		return builder.create();		
    }
}
