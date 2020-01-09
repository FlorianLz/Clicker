package com.example.florianlaignez.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class ApplicationFlorian extends AppCompatActivity implements TimerAction {

    private int cpt = 0;
    private int multiplicateur=1;
    private RefreshHandler handler;
    private int niveauActuel = 1;
    private int niveauMaxAuto;
    private boolean plusUtilisé = false;
    private boolean foisUtilisé = false;
    private boolean randomUtilisé = false;
    private boolean aleaUtilisé = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_florian);


        handler = new RefreshHandler(this);
    }

    public void onClickValider(View view) {
        cpt+= multiplicateur;
        update();
    }

    private void update() {
        int clics = cpt;
        int niveau =1;
        int niveauMax = 8;
        if(niveauActuel < niveauMax) {
            while (clics > niveau * 50) {
                clics -= 50 * niveau;
                ++niveau;
                niveauActuel = niveau;
                ((TextView) findViewById(R.id.textHaut)).setText("Étape "+niveauActuel);

            }
            ((TextView) findViewById(R.id.clickTotal)).setText("Total :"+cpt);
            int reste = (50 * niveau) - clics;
            ((TextView) findViewById(R.id.clickReste)).setText("Reste :" + reste);
            if(niveau == 2){
                ((ImageView) findViewById(R.id.ImageA)).setImageResource(R.mipmap.niveaub);
            }
            if(niveau == 3){
                ((ImageView) findViewById(R.id.ImageA)).setImageResource(R.mipmap.niveauc);
            }
            if(niveau == 4){
                ((ImageView) findViewById(R.id.ImageA)).setImageResource(R.mipmap.niveaud);
            }
            if(niveau == 5){
                ((ImageView) findViewById(R.id.ImageA)).setImageResource(R.mipmap.niveaue);
            }
            if(niveau == 6){
                ((ImageView) findViewById(R.id.ImageA)).setImageResource(R.mipmap.niveauf);
            }
            if(niveau == 7){
                ((ImageView) findViewById(R.id.ImageA)).setImageResource(R.mipmap.niveaug);
            }
            if(niveau == 8){
                ((ImageView) findViewById(R.id.ImageA)).setImageResource(R.mipmap.niveauh);
            }
            if (niveau >= 2 && plusUtilisé == false) {
                findViewById(R.id.imagePlus).setVisibility(View.VISIBLE);
            }
            if (niveau >= 5 && foisUtilisé == false) {
                findViewById(R.id.imageFois).setVisibility(View.VISIBLE);
            }
            if (niveau >= 7 && randomUtilisé == false) {
                findViewById(R.id.imageRandom).setVisibility(View.VISIBLE);
            }
            if (niveau >= 1 && aleaUtilisé == false) {
                findViewById(R.id.imageAuto).setVisibility(View.VISIBLE);
            }
        }else {
            ((TextView) findViewById(R.id.textHaut)).setText("Félicitations !!! Cuisson à point :)");
            findViewById(R.id.imagePlus).setVisibility(View.INVISIBLE);
            findViewById(R.id.imageFois).setVisibility(View.INVISIBLE);
            findViewById(R.id.imageRandom).setVisibility(View.INVISIBLE);
            findViewById(R.id.imageAuto).setVisibility(View.INVISIBLE);
        }
    }

    public void onClickReset(View view) {
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.rotation360);
        view.startAnimation(animation);
        cpt=0;
        multiplicateur=1;
        niveauActuel=0;
        update();
        findViewById(R.id.imagePlus).setVisibility(View.INVISIBLE);
        findViewById(R.id.imageFois).setVisibility(View.INVISIBLE);
        findViewById(R.id.imageRandom).setVisibility(View.INVISIBLE);
        findViewById(R.id.imageAuto).setVisibility(View.INVISIBLE);
        plusUtilisé = false;
        foisUtilisé = false;
        randomUtilisé = false;
        aleaUtilisé = false;
        ((ImageView) findViewById(R.id.ImageA)).setImageResource(R.mipmap.niveaua);
        ((TextView) findViewById(R.id.textHaut)).setText("Étape 1");
    }

    public void onClickPlus(View view) {
        plusUtilisé = true;
        cpt += 50;
        update();
        findViewById(R.id.imagePlus).setVisibility(View.INVISIBLE);
    }


    public void onClickMultiplie(View view) {
        foisUtilisé = true;
        if(niveauActuel>=5) {
            multiplicateur = 2;
            findViewById(R.id.imageFois).setVisibility(View.INVISIBLE);
        }
    }

    public void onClickAuto(View view) {
        aleaUtilisé = true;
        niveauMaxAuto = niveauActuel +1;
        updateTimer();
    }

    public void onClickRandom(View view) {
        randomUtilisé = true;
        Random r = new Random();
        int valeurMin = 100;
        int valeurMax = 350;
        int valeur = valeurMin + r.nextInt(valeurMax - valeurMin);
        cpt += valeur;
        update();
        findViewById(R.id.imageRandom).setVisibility(View.INVISIBLE);
    }

    @Override
    public void updateTimer() {
        ++cpt;
        update();
        if (niveauActuel<niveauMaxAuto) handler.scheduleRefresh(100);
        findViewById(R.id.imageAuto).setVisibility(View.INVISIBLE);
    }
}