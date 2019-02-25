package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activeplayer=0;//0 means circle
    LinearLayout l1;
    TextView mess;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        l1 = (LinearLayout) findViewById(R.id.mylayout);
        mess= (TextView)findViewById(R.id.message);
        b1 = (Button)findViewById(R.id.playagain);
        super.onCreate(savedInstanceState);
        // System.out.println("Running Create");
        setContentView(R.layout.activity_main);
    }

    int [] gamestate={-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int [][]winningpositions ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int count=0,winner=0;
    Boolean isactive =true;

    public void droping(View view){

        ImageView im= (ImageView) view;

        if(im.getDrawable()==null && isactive) {
            im.setTranslationY(-1000f);
            int tappedpos = Integer.parseInt(view.getTag().toString());
            gamestate[tappedpos]=activeplayer;
            String Player = "Circle";
            if(activeplayer==1)
                 Player = "Cross";
            if(activeplayer==0) {
                im.setImageResource(R.drawable.circle);
                activeplayer=1;
            }
            else{
                im.setImageResource(R.drawable.cross);
                activeplayer=0;
            }
            im.animate().translationYBy(1000f).rotation(360f).setDuration(300);
            System.out.println("Running");
            for(int [] winnigpos : winningpositions)
            {
                if(gamestate[winnigpos[0]]==gamestate[winnigpos[1]] &&
                        gamestate[winnigpos[1]]==gamestate[winnigpos[2]] &&
                        gamestate[winnigpos[0]]!=-1)
                {

                    //TextView mess= (TextView)findViewById(R.id.message);
                     //Button b1 = (Button)findViewById(R.id.playagain);

                    l1 = (LinearLayout) findViewById(R.id.mylayout);
                    mess= (TextView)findViewById(R.id.message);
                    b1 = (Button)findViewById(R.id.playagain);
                    isactive = false;
                    Toast tost= Toast.makeText(getApplicationContext(),"message winner is "+Player,Toast.LENGTH_LONG);
                    tost.show();
                    mess.setText(Player +" has won the game");
                    l1.setVisibility(View.VISIBLE);
                    winner=1;
                }

            }
            count++;
            if(count==9 && winner!=1)
            {
                b1 = (Button)findViewById(R.id.playagain);
                l1 = (LinearLayout) findViewById(R.id.mylayout);
                mess= (TextView)findViewById(R.id.message);
                mess.setText(" No one Won");
                l1.setVisibility(View.VISIBLE);

            }
        }
    }
    public void playagain(View view){
        for(int i=0;i<9;i++)
        {
            gamestate[i]=-1;
            isactive=true;
            l1.setVisibility(View.INVISIBLE);
        }
        android.support.v7.widget.GridLayout g1 =(android.support.v7.widget.GridLayout)  findViewById(R.id.back);
        for(int i=0;i<g1.getChildCount();i++)
        {
            ((ImageView)g1.getChildAt(i)).setImageResource(0);
        }
        count=0;
        winner=0;
    }

}
